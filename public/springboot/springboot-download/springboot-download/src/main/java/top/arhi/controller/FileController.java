package top.arhi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.arhi.entity.FileRecord;
import top.arhi.mapper.FileRecordMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

@Controller
@CrossOrigin
public class FileController {

    @Value("${upload.dir}")
    private String uploadDir;

    @Autowired
    private FileRecordMapper fileRecordMapper;

    @PostMapping("/upload")
    @ResponseBody
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        // 生成文件ID
        String fileId = generateFileId();

        // 将文件保存到上传目录
        String filePath = uploadDir + File.separator + fileId;


        file.transferTo(new File(filePath));

        // 保存文件记录到数据库
        FileRecord fileRecord = new FileRecord();
        String originalFilename = file.getOriginalFilename();
        System.out.println(originalFilename);

        fileRecord.setFileName(originalFilename);
        fileRecord.setFileId(fileId);
        fileRecordMapper.insertFileRecord(fileRecord);

        return fileId;
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable("fileId") String fileId) throws IOException {
        // 根据文件ID从数据库获取文件记录
        FileRecord fileRecord = fileRecordMapper.findByFileId(fileId);
        if (fileRecord == null) {
            return ResponseEntity.notFound().build();
        }

        String filePath = uploadDir + File.separator + fileId;
        File file = new File(filePath);

        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }

        // 创建文件输入流
        InputStream inputStream = new FileInputStream(file);

        // 设置响应头，告诉浏览器文件的类型和下载方式
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileRecord.getFileName());

        // 创建包含文件流的 ResponseEntity
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(new InputStreamResource(inputStream));
    }


    @GetMapping("/filedownload")
    public void downloadFile(@RequestParam("fileId") String fileId, HttpServletResponse response) throws IOException {
        // 根据文件ID从数据库获取文件记录
        FileRecord fileRecord = fileRecordMapper.findByFileId(fileId);
        if (fileRecord == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String filePath = uploadDir + File.separator + fileId;
        File file = new File(filePath);

        if (!file.exists()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 创建文件输入流
        InputStream inputStream = new FileInputStream(file);

        // 设置响应头，告诉浏览器文件的类型和下载方式
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileRecord.getFileName());

        // 获取输出流
        OutputStream outputStream = response.getOutputStream();

        // 将文件流写入输出流
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        // 关闭流
        inputStream.close();
        outputStream.close();
    }


    private String generateFileId() {
        return UUID.randomUUID().toString();
    }
}

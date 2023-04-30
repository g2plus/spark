package top.arhi.util;

import sun.misc.BASE64Encoder;
import top.arhi.enums.FileType;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;


public abstract class DownloadUtil{

    public static String getFileName(String agent, String filename) throws UnsupportedEncodingException {
        if (agent.contains("MSIE")) {
            // IE浏览器
            filename = URLEncoder.encode(filename, "utf-8");
            filename = filename.replace("+", " ");
        } else if (agent.contains("Firefox")) {
            // 火狐浏览器
            BASE64Encoder base64Encoder = new BASE64Encoder();
            filename = "=?utf-8?B?" + base64Encoder.encode(filename.getBytes("utf-8")) + "?=";
        } else {
            // 其它浏览器
            filename = URLEncoder.encode(filename, "utf-8");
        }
        return filename;
    }

    public static void download(HttpServletRequest req, HttpServletResponse resp,String fileName) throws IOException {
        FileType fileType = FileType.findByExtension(fileName.substring(fileName.lastIndexOf(".")+1));
        String realPath = req.getServletContext().getRealPath("/upload/" + fileType.getType() + "/" + fileName);
        BufferedInputStream is = new BufferedInputStream(new FileInputStream(realPath));
        ServletOutputStream sos=resp.getOutputStream();

        String agent=req.getHeader("user-agent");
        fileName = DownloadUtil.getFileName(agent, fileName);
        resp.setHeader("content-type","application/octet-stream");
        resp.setHeader("content-disposition","attachment;filename="+fileName);
        byte[] buffer=new byte[1024*1024*8];
        int len;
        while((len=is.read(buffer))!=-1){
            sos.write(buffer,0,len);
        }
        is.close();
    }
}
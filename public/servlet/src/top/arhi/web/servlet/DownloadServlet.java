package top.arhi.web.servlet;


import top.arhi.enums.FileType;
import top.arhi.util.DownloadUtil;
import top.arhi.util.StringUtils;

import javax.servlet.ServletException;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/downloadServlet")
public class DownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileName = req.getParameter("filename");
        if (StringUtils.isNotBlank(fileName)) {
            DownloadUtil.download(req, resp, fileName);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }


}

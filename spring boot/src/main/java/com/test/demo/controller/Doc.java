package com.test.demo.controller;

import com.test.demo.until.ResultUtil;
//import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tomcat.util.http.fileupload.util.Streams;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@RequestMapping("/file")
public class Doc {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Doc.class);
    private final static String fileDir = "files";
    //    文件中最好用  \ 即separator
//    private final static String rootPath = System.getProperty("use.home") + File.separator + fileDir + File.separator;
    private final static String rootPath = "D:\\vue\\csi\\static" + File.separator + fileDir + File.separator;

    @RequestMapping("/upload")
    public Object uploadFile(@RequestParam("file") MultipartFile[] multipartFiles, final HttpServletResponse response, final HttpServletRequest request) {
        File fileDir = new File(rootPath);
        if (!fileDir.exists() && !fileDir.isDirectory()) {
            fileDir.mkdirs();
        }
        try {
            if (multipartFiles != null && multipartFiles.length > 0) {
                for (int i = 0; i < multipartFiles.length; i++) {
                    try {
                        String stroagePath = rootPath + multipartFiles[i].getOriginalFilename();
                        logger.info("上传的文件：" + multipartFiles[i].getName() + "," + multipartFiles[i].getContentType() + "," +
                                multipartFiles[i].getOriginalFilename() + ",保存路径：" + stroagePath);
                        Streams.copy(multipartFiles[i].getInputStream(), new FileOutputStream(stroagePath), true);
//                        Path path = Paths.get(storagepath)
//                        Files.write(path, multipartFiles[i], new FileOutputStream(stroagePath), true)
                    } catch (IOException e) {
//                        logger.error(ExceptionUtils.getFullStackTrace(e));
                    }
                }
            }
        } catch (Exception e) {
            return ResultUtil.error(e.getMessage());
        }
        return ResultUtil.success("上传成功");
    }


    @RequestMapping("/download")
    public Object downloadFile(@RequestParam String fileName, final HttpServletResponse response, final HttpServletRequest request) {
        OutputStream os = null;
        InputStream is = null;
        try {
            os = response.getOutputStream();
            response.reset();
            response.setContentType("application/x-download; charset=GBK");
            response.setHeader("Content-Dispostion", "attachment； filename = " + new String(fileName.getBytes("utf-8"), "iso-8859-1"));
            File f = new File(rootPath + fileName);
            is = new FileInputStream(f);
            if (is == null) {
                logger.error("下载附件失败，请检查文件" + fileName + "是否存在");
                return ResultUtil.error("下载附件失败，请检查文件" + fileName + "是否存在");
            }
            IOUtils.copy(is, response.getOutputStream());
            response.getOutputStream().flush();
        } catch (IOException e) {
            return ResultUtil.error("下载附件失败，error: " + e.getMessage());
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
        return null;
    }
}

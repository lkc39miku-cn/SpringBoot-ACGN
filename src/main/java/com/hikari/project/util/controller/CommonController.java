package com.hikari.project.util.controller;

import com.hikari.framework.config.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * CommonController
 * @author lkc39miku_cn
 */
@Slf4j
@RestController
public class CommonController {

    @Value("${file.path.image}")
    private String path;

    @Resource
    private Server server;


    @GetMapping(value = "/download")
    public void download(String fileName, Boolean delete, HttpServletResponse response, HttpServletRequest request) {
        try {
//            if (!FileUtils.checkAllowDownload(fileName)) {
//                throw new Exception("文件名称(" + fileName + ")非法，不允许下载。");
//            }
//            String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
//            String filePath = path + fileName;
//
//            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
//            FileUtils.setAttachmentResponseHeader(response, realFileName);
//            FileUtils.writeBytes(filePath, response.getOutputStream());
//            if (delete) {
//                FileUtils.deleteFile(filePath);
//            }
        } catch (Exception e) {
            log.error("下载文件失败", e);
        }
    }
}

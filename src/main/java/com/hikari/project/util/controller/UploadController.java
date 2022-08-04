package com.hikari.project.util.controller;

import com.hikari.commons.key.SpecialKey;
import com.hikari.commons.result.Result;
import com.hikari.commons.util.IdUtils;
import com.hikari.framework.config.Server;
import com.hikari.framework.exception.service.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * UploadController
 *
 * @author lkc39miku_cn
 */
@Slf4j
@RestController
public class UploadController {

    @Value("${file.path.image}")
    private String filePath;

    @Resource
    private Server server;

    @PostMapping(value = "/upload")
    public Result<Map<String, Object>> uploadFile(MultipartFile file, HttpServletRequest request) throws NullPointerException {
        log.info("file: {}", file.getName());
        log.info("file: {}", file.getOriginalFilename());
        Map<String, Object> map = new HashMap<>(5);
        String directory = String.valueOf(System.currentTimeMillis());

        File dir = new File(filePath + directory);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String suffix = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf(SpecialKey.NOUHAD));
        String newFileName = IdUtils.uuid() + suffix;

        File newFile = new File(filePath + directory + '-' + newFileName);

        try {
            file.transferTo(newFile);

            map.put("url", server.getUrl());
            map.put("fileName", file.getName());
            map.put("originalFilename", file.getOriginalFilename());
            map.put("newFileName", newFileName);
            return Result.success(map);
        } catch (IOException e) {
            throw new ServiceException("文件上传", e.getMessage());
        }
    }

    @PostMapping(value = "/upload/batch")
    public Result<List<Map<String, Object>>> uploadFileBatch(List<MultipartFile> multipartFileList, HttpServletRequest request) {
        List<Map<String, Object>> list = new ArrayList<>();
        multipartFileList.forEach(v -> {
            list.add(uploadFile(v, request).getData());
        });
        return Result.success(list);
    }
}

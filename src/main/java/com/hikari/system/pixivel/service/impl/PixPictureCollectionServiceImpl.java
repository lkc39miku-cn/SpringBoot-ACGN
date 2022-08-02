package com.hikari.system.pixivel.service.impl;

import com.hikari.commons.key.SpecialKey;
import com.hikari.commons.key.StatusKey;
import com.hikari.commons.result.ServiceExecute;
import com.hikari.commons.util.IdUtils;
import com.hikari.framework.config.Server;
import com.hikari.framework.exception.service.ServiceException;
import com.hikari.system.pixivel.entity.PixPicture;
import com.hikari.system.pixivel.entity.PixPictureDetailed;
import com.hikari.system.pixivel.mapper.PixPictureDetailedMapper;
import com.hikari.system.pixivel.mapper.PixPictureMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.hikari.system.pixivel.entity.PixPictureCollection;
import com.hikari.system.pixivel.mapper.PixPictureCollectionMapper;
import com.hikari.system.pixivel.service.PixPictureCollectionService;
import org.springframework.web.multipart.MultipartFile;

/**
 * PixPictureCollectionServiceImpl
 * @author lkc39miku_cn
 */
@Slf4j
@Service
public class PixPictureCollectionServiceImpl implements PixPictureCollectionService{

    @Resource
    private PixPictureCollectionMapper pixPictureCollectionMapper;

    @Resource
    private PixPictureMapper pixPictureMapper;

    @Resource
    private PixPictureDetailedMapper pixPictureDetailedMapper;

    @Resource
    private Server server;

    @Value("${file.path.image}")
    private String path;

    @Override
    public int deleteByPrimaryKey(String id) {
        return pixPictureCollectionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(PixPictureCollection record) {
        record.setCreateTime(LocalDateTime.now());
        ServiceExecute.compare(pixPictureCollectionMapper.insert(record), ServiceExecute.ExecuteStatus.INSERT);
        record.getMultipartFileList().forEach(v -> {
            String ppd_id = uploadImage(v, record.getType(), record.getMappingId());
            int i = 0;
            // 添加图片
            PixPicture pixPicture = new PixPicture()
                    .setSort(++i)
                    .setPictureCollectionId(record.getId())
                    .setPictureDetailedId(ppd_id);

            ServiceExecute.compare(pixPictureMapper.insert(pixPicture), ServiceExecute.ExecuteStatus.INSERT);
        });
        return StatusKey.SUCCESS_INT;
    }

    /**
     * 上传图片
     * @param file 文件
     * @param type 类型
     * @param mappingId 关联id
     * @return 图片id
     */
    private String uploadImage(MultipartFile file, int type, String mappingId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        // 时间文件夹
        String directory = LocalDateTime.now(ZoneOffset.of("+8")).format(formatter);

        File dir = new File(path + directory);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // 图片后缀
        String suffix = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf(SpecialKey.NOUHAD));

        // 图片前缀
        String prefix = Objects.requireNonNull(file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf(SpecialKey.NOUHAD)));
        // 新文件名
        String newFileName = IdUtils.uuid() + prefix;

        File newFile = new File(path + directory + '/' + newFileName);

        try {
            file.transferTo(newFile);
            // 读取图片
            BufferedImage image = ImageIO.read(file.getInputStream());
            int height = image.getHeight();
            int width = image.getWidth();

            int graphics;
            if (width > height) {
                graphics = 0;
            } else if (width == height) {
                graphics = 2;
            } else {
                graphics = 1;
            }

            PixPictureDetailed pixPictureDetailed = new PixPictureDetailed()
                    .setName(file.getOriginalFilename())
                    .setPath("/image/" + directory + "/" + newFileName)
                    .setPrefix(newFileName)
                    .setSuffix(suffix)
                    .setHeight(height)
                    .setWidth(width)
                    .setType(type)
                    .setGraphics(graphics)
                    .setMappingId(mappingId);

            ServiceExecute.compare(pixPictureDetailedMapper.insert(pixPictureDetailed), ServiceExecute.ExecuteStatus.INSERT);

            return pixPictureDetailed.getId();
        } catch (IOException e) {
            throw new ServiceException("Service.A10002", e.getMessage());
        }
    }

    @Override
    public PixPictureCollection selectByPrimaryKey(String id) {
        return pixPictureCollectionMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(PixPictureCollection record) {
        return pixPictureCollectionMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateBatchSelective(List<PixPictureCollection> list) {
        return pixPictureCollectionMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<PixPictureCollection> list) {
        return pixPictureCollectionMapper.batchInsert(list);
    }

    @Override
    public List<PixPictureCollection> selectList(PixPictureCollection pixPictureCollection) {
        return pixPictureCollectionMapper.selectList(pixPictureCollection);
    }

    @Override
    public List<PixPictureCollection> selectByCollect() {
        return pixPictureCollectionMapper.selectByCollect();
    }

    @Override
    public List<PixPictureCollection> selectByPraise() {
        return pixPictureCollectionMapper.selectByPraise();
    }

    @Override
    public List<PixPictureCollection> selectByIdList(List<String> idList) {
        return pixPictureCollectionMapper.selectByIdList(idList);
    }


}

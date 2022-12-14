package com.hikari.project.pixivel.service.impl;

import com.hikari.commons.key.SpecialKey;
import com.hikari.commons.key.StatusKey;
import com.hikari.commons.result.ServiceExecute;
import com.hikari.commons.util.IdUtils;
import com.hikari.framework.config.Server;
import com.hikari.framework.exception.service.ServiceException;
import com.hikari.project.pixivel.entity.PixPicture;
import com.hikari.project.pixivel.entity.PixPictureDetailed;
import com.hikari.project.pixivel.mapper.PixPictureDetailedMapper;
import com.hikari.project.pixivel.mapper.PixPictureMapper;
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

import com.hikari.project.pixivel.entity.PixPictureCollection;
import com.hikari.project.pixivel.mapper.PixPictureCollectionMapper;
import com.hikari.project.pixivel.service.PixPictureCollectionService;
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
        ServiceExecute.compare(pixPictureCollectionMapper.deleteByPrimaryKey(id), ServiceExecute.ExecuteStatus.DELETE);

        List<String> list = pixPictureMapper.selectByCollectionId(id)
                .stream()
                .map(PixPicture::getPictureDetailedId)
                .toList();

        // ?????? ?????? ?????? ????????????
        ServiceExecute.compare(pixPictureDetailedMapper.deleteByIdList(list), list.size(), ServiceExecute.ExecuteStatus.DELETE);
        ServiceExecute.compare(pixPictureMapper.deleteByPictureCollectionId(id), list.size(), ServiceExecute.ExecuteStatus.DELETE);

        return StatusKey.SUCCESS_INT;
    }

    @Override
    public int insert(PixPictureCollection record) {
        record.setCreateTime(LocalDateTime.now());

        // ??????????????????
        ServiceExecute.compare(pixPictureCollectionMapper.insert(record), ServiceExecute.ExecuteStatus.INSERT);

        // ???????????? ?????? ????????????
        record.getMultipartFileList().forEach(v -> {
            String ppd_id = uploadImage(v, record.getType(), record.getMappingId());
            int i = 0;
            // ????????????
            PixPicture pixPicture = new PixPicture()
                    .setSort(++i)
                    .setPictureCollectionId(record.getId())
                    .setPictureDetailedId(ppd_id);

            ServiceExecute.compare(pixPictureMapper.insert(pixPicture), ServiceExecute.ExecuteStatus.INSERT);
        });
        return StatusKey.SUCCESS_INT;
    }

    /**
     * ????????????
     * @param file ??????
     * @param type ??????
     * @param mappingId ??????id
     * @return ??????id
     */
    private String uploadImage(MultipartFile file, int type, String mappingId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        // ???????????????
        String directory = LocalDateTime.now(ZoneOffset.of("+8")).format(formatter);

        File dir = new File(path + directory);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // ????????????
        String suffix = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf(SpecialKey.NOUHAD));

        // ????????????
        String prefix = Objects.requireNonNull(file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf(SpecialKey.NOUHAD)));
        // ????????????
        String newFileName = IdUtils.uuid() + prefix;

        File newFile = new File(path + directory + '/' + newFileName);

        try {
            file.transferTo(newFile);
            // ????????????
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
        // ??????????????????
        ServiceExecute.compare(pixPictureCollectionMapper.updateByPrimaryKeySelective(record), ServiceExecute.ExecuteStatus.UPDATE);

        List<String> list = pixPictureMapper.selectByCollectionId(record.getId())
                .stream()
                .map(PixPicture::getPictureDetailedId)
                .toList();

        // ????????????????????? ?????? ?????? ????????????
        ServiceExecute.compare(pixPictureDetailedMapper.deleteByIdList(list), list.size(), ServiceExecute.ExecuteStatus.DELETE);
        ServiceExecute.compare(pixPictureMapper.deleteByPictureCollectionId(record.getId()), list.size(), ServiceExecute.ExecuteStatus.DELETE);

        // ???????????? ?????? ????????????
        record.getMultipartFileList().forEach(v -> {
            String ppd_id = uploadImage(v, record.getType(), record.getMappingId());
            int i = 0;
            // ????????????
            PixPicture pixPicture = new PixPicture()
                    .setSort(++i)
                    .setPictureCollectionId(record.getId())
                    .setPictureDetailedId(ppd_id);

            ServiceExecute.compare(pixPictureMapper.insert(pixPicture), ServiceExecute.ExecuteStatus.INSERT);
        });

        return StatusKey.SUCCESS_INT;
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

    @Override
    public List<PixPictureCollection> selectByAuthor(String authorId) {
        return pixPictureCollectionMapper.selectByAuthor(authorId);
    }

    @Override
    public synchronized int refreshPraise(String id) {
        PixPictureCollection pixPictureCollection = pixPictureCollectionMapper.selectByPrimaryKey(id);
        return pixPictureCollectionMapper.updateByPrimaryKeySelective(new PixPictureCollection()
                .setId(id)
                .setPraise(pixPictureCollection.getPraise() + 1));
    }

    @Override
    public int refreshCollect(String pictureCollectionId) {
        PixPictureCollection pixPictureCollection = pixPictureCollectionMapper.selectByPrimaryKey(pictureCollectionId);
        return pixPictureCollectionMapper.updateByPrimaryKeySelective(new PixPictureCollection()
                .setId(pictureCollectionId)
                .setPraise(pixPictureCollection.getCollect() + 1));
    }

    @Override
    public int refreshDeletePraise(String id) {
        PixPictureCollection pixPictureCollection = pixPictureCollectionMapper.selectByPrimaryKey(id);
        return pixPictureCollectionMapper.updateByPrimaryKeySelective(new PixPictureCollection()
                .setId(id)
                .setPraise(pixPictureCollection.getPraise() - 1));
    }

    @Override
    public int refreshDeleteCollect(String id) {
        PixPictureCollection pixPictureCollection = pixPictureCollectionMapper.selectByPrimaryKey(id);
        return pixPictureCollectionMapper.updateByPrimaryKeySelective(new PixPictureCollection()
                .setId(id)
                .setPraise(pixPictureCollection.getCollect() - 1));
    }
}

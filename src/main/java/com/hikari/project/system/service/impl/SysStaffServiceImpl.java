package com.hikari.project.system.service.impl;

import com.hikari.commons.key.NumberKey;
import com.hikari.commons.key.StatusKey;
import com.hikari.commons.key.SysStaffKey;
import com.hikari.commons.result.ServiceExecute;
import com.hikari.commons.util.SecurityUtils;
import com.hikari.framework.exception.service.ServiceException;
import com.hikari.project.system.entity.vo.SysStaffVo;
import com.hikari.project.system.mapper.SysPostMapper;
import com.hikari.project.system.mapper.SysRoleMapper;
import com.hikari.project.system.mapper.SysStaffRoleMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.hikari.project.system.mapper.SysStaffMapper;

import java.time.LocalDateTime;
import java.util.List;
import com.hikari.project.system.entity.SysStaff;
import com.hikari.project.system.service.SysStaffService;

/**
 * SysStaffServiceImpl
 * @author lkc39miku_cn
 */  
@Service
public class SysStaffServiceImpl implements SysStaffService{

    @Resource
    private SysStaffMapper sysStaffMapper;

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Resource
    private SysPostMapper sysPostMapper;

    @Resource
    private SysStaffRoleMapper sysStaffRoleMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return sysStaffMapper.updateByPrimaryKeySelective(new SysStaff()
                .setId(id)
                .setDeleteStatus(SysStaffKey.DELETE_YES)
                .setUpdateStaffId(SecurityUtils.getStaffId()));
    }

    @Override
    public int insert(SysStaff record) {
        record.setCreateTime(LocalDateTime.now());
        return sysStaffMapper.insert(record);
    }

    @Override
    public SysStaffVo selectByPrimaryKey(String id) {
        SysStaff sysStaff = sysStaffMapper.selectByPrimaryKey(id);
        return new SysStaffVo()
                .setSysStaff(sysStaff)
            .setRoleList(sysRoleMapper.selectListByStaffId(id))
            .setPostList(sysPostMapper.selectListByStaffId(id));
    }

    @Override
    public int updateByPrimaryKeySelective(SysStaff record) {
        record.setUpdateTime(LocalDateTime.now());
        return sysStaffMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateBatchSelective(List<SysStaff> list) {
        return sysStaffMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<SysStaff> list) {
        return sysStaffMapper.batchInsert(list);
    }

    @Override
    public List<SysStaff> selectList(SysStaff sysStaff) {
        return sysStaffMapper.selectList(sysStaff);
    }

    @Override
    public boolean checkStaffUsername(String username) {
        return selectList(new SysStaff().setUsername(username)).size() > NumberKey.ZERO;
    }

    @Override
    public boolean checkStaffPhone(String phone) {
        return selectList(new SysStaff().setPhone(phone)).size() > NumberKey.ZERO;
    }

    @Override
    public boolean checkStaffEmail(String email) {
        return selectList(new SysStaff().setEmail(email)).size() > NumberKey.ZERO;
    }

    @Override
    public boolean checkStaffUsername(String username, String id) {
        return selectNotList(new SysStaff().setUsername(username).setId(id)).size() > NumberKey.ZERO;
    }

    @Override
    public boolean checkStaffPhone(String phone, String id) {
        return selectNotList(new SysStaff().setPhone(phone).setId(id)).size() > NumberKey.ZERO;
    }

    @Override
    public boolean checkStaffEmail(String email, String id) {
        return selectNotList(new SysStaff().setEmail(email).setId(id)).size() > NumberKey.ZERO;
    }

    @Override
    public List<SysStaff> selectNotList(SysStaff sysStaff) {
        return sysStaffMapper.selectNotList(sysStaff);
    }

    @Override
    public SysStaffVo getRole(String id) {
        SysStaff sysStaff = sysStaffMapper.selectByPrimaryKey(id);
        return new SysStaffVo()
                .setSysStaff(sysStaff)
                .setRoleList(sysRoleMapper.selectListByStaffId(id));
    }

    @Override
    public int updateRole(String id, List<String> roleIds) {
        // 删除原有角色
        sysStaffRoleMapper.deleteByStaffId(id);

        // 添加新角色
        ServiceExecute.compare(sysStaffRoleMapper.batchInsertByRoleIds(id, roleIds), roleIds.size(), ServiceExecute.ExecuteStatus.INSERT);
        return StatusKey.SUCCESS_INT;
    }

    @Override
    public boolean registerStaff(SysStaff staff) {
        return sysStaffMapper.insert(staff) > NumberKey.ZERO;
    }

    @Override
    public void checkStaffAllowed(SysStaff sysStaff) {
        if (StringUtils.isNotEmpty(sysStaff.getId()) && sysStaff.isAdmin()) {
            throw new ServiceException("", "不允许操作超级管理员用户");
        }
    }

    @Override
    public SysStaff selectStaffByUsername(String username) {
        return selectList(new SysStaff().setUsername(username)).get(NumberKey.ZERO);
    }
}

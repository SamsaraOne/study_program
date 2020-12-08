package com.lx.eduucenter.mapper;

import com.lx.eduucenter.entity.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2020-11-28
 */
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {

    Integer getRegisterNum(String day);
}

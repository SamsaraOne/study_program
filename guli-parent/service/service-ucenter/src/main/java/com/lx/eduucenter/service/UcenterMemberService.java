package com.lx.eduucenter.service;

import com.lx.eduucenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lx.eduucenter.entity.vo.LoginVo;
import com.lx.eduucenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-11-28
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(LoginVo loginVo);

    void register(RegisterVo registerVo);


    UcenterMember selectByOpenId(String open_id);
}

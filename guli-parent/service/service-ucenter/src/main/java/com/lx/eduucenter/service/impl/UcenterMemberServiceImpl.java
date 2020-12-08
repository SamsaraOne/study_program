package com.lx.eduucenter.service.impl;

import com.alibaba.nacos.common.util.Md5Utils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lx.commonutils.JwtUtils;
import com.lx.commonutils.MD5;
import com.lx.commonutils.R;
import com.lx.eduucenter.entity.UcenterMember;
import com.lx.eduucenter.entity.vo.LoginVo;
import com.lx.eduucenter.entity.vo.RegisterVo;
import com.lx.eduucenter.mapper.UcenterMemberMapper;
import com.lx.eduucenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lx.servicebase.exceptionhandler.TestException;
import com.mysql.cj.util.StringUtils;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-11-28
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Autowired
    private RedisTemplate<String, String> template;

    @Override
    public String login(LoginVo loginVo) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        if(StringUtils.isNullOrEmpty(mobile) ||StringUtils.isNullOrEmpty(password)){
            throw new TestException(20001,"登陆失败");
        }
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        UcenterMember member = baseMapper.selectOne(wrapper);
        if (member==null){
            throw new TestException(20001,"手机号不存在");
        }
        password = MD5.encrypt(password);



        if(MD5.encrypt(password).equals(member.getPassword())){
            throw new TestException(20001,"密码错误");
        }
        if(member.getIsDisabled()){
            throw new TestException(20001,"账号禁用");
        }
        String token = JwtUtils.getJwtToken(member.getId(), member.getNickname());
        return token;

    }

    @Override
    public void register(RegisterVo registerVo) {
        String code = registerVo.getCode();
        String mobile  = registerVo.getMobile();
        String nickname = registerVo.getNickname();
        String password = registerVo.getPassword();
        if(StringUtils.isNullOrEmpty(mobile) ||StringUtils.isNullOrEmpty(password)
        ||StringUtils.isNullOrEmpty(code)||StringUtils.isNullOrEmpty(nickname)){
            throw new TestException(20001,"注册失败");
        }
        if (!code.equals(template.opsForValue().get(mobile))){
            throw new TestException(20001,"注册码错误");
        }
        //手机号不能重复
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        Integer isHavePhone = baseMapper.selectCount(wrapper);
        if (isHavePhone>0){
            throw new TestException(20001,"手机号已经存在");
        }
        UcenterMember member = new UcenterMember();
        member.setMobile(mobile).setNickname(nickname).setPassword(MD5.encrypt(password)).setIsDisabled(false);
        baseMapper.insert(member);

    }

    @Override
    public UcenterMember selectByOpenId(String open_id) {
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("openid",open_id);
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public Integer getRegisterNum(String day) {


        Integer num = baseMapper.getRegisterNum(day);
        return num;
    }


}

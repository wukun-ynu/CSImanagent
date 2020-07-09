package com.test.demo.service.impl;

import com.test.demo.dao.UserInfDao;
import com.test.demo.entity.SysRole;
import com.test.demo.entity.UserInf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class CustomUserService implements UserDetailsService {
    @Autowired
    UserInfDao userInfDao;

    @Override
    public UserDetails loadUserByUsername(String userName) {
        UserInf user = userInfDao.findByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (SysRole role : user.getSysRole()) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            System.out.print(role.getRoleName());
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(),
                user.getPassword(), authorities);
    }
}

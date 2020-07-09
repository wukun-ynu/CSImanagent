package com.test.demo.service.impl;

import com.test.demo.dao.UserInfDao;
import com.test.demo.dto.UserInfExecution;
import com.test.demo.entity.UserInf;
import com.test.demo.enums.UserInfStateEnum;
import com.test.demo.service.UserInfService;
import com.test.demo.until.PageCalculator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserInfServiceImpl implements UserInfService {

    @Resource
    private UserInfDao userInfDao;

    @Override
    public UserInfExecution getUserListByPage(int pageIndex, int pageSize) {
        int rowIndex = PageCalculator.calculatePowIndex(pageIndex, pageSize);
        List<UserInf> userInfList = userInfDao.queryUserInfPage(rowIndex, pageSize);
        int count = userInfDao.queryUserInfCount();
        UserInfExecution ue = new UserInfExecution();
        if (userInfList != null) {
            ue.setUserInfList(userInfList);
            ue.setCount(count);
            ue.setState(UserInfStateEnum.SUCCESS.getState());
            ue.setStateInfo(UserInfStateEnum.SUCCESS.getStateInfo());
        } else {
            ue.setState(UserInfStateEnum.INNER_ERROR.getState());
        }
        return ue;
    }

    @Override
    public List<UserInf> getUserList() {
        return userInfDao.getUserList();
    }


    @Override
    public UserInfExecution getUserInfList(int pageIndex, int pageSize, UserInf userInfCondition) {
        List<UserInf> userInfList = null;
        int rowIndex = PageCalculator.calculatePowIndex(pageIndex, pageSize);
        userInfList = userInfDao.queryUserInfList(rowIndex, pageSize, userInfCondition);
        int count = userInfDao.queryUserInfConditionCount(userInfCondition);
        UserInfExecution ue = new UserInfExecution();
        if (userInfList != null) {
            ue.setUserInfList(userInfList);
            ue.setCount(count);
            ue.setState(UserInfStateEnum.SUCCESS.getState());
            ue.setStateInfo(UserInfStateEnum.SUCCESS.getStateInfo());
        } else {
            ue.setState(UserInfStateEnum.INNER_ERROR.getState());
        }
        return ue;
    }

    @Override
    @Transactional
    public int addUser(UserInf userInf) {
        int count = userInfDao.insertUserInf(userInf);
        return count;
    }

    @Override
    @Transactional
    public int updateUser(UserInf userInf) {
        int count = userInfDao.updateUserInf(userInf);
        return count;
    }

    @Override
    @Transactional
    public int deleteUsers(List<Long> ids) {
        int count = userInfDao.deleteUserInf(ids);
        return count;
    }

    @Override
    public UserInf findByUserName(String userName) {
        return userInfDao.findByUserName(userName);
    }
}

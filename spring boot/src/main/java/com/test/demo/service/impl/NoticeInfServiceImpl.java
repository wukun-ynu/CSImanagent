package com.test.demo.service.impl;

import com.test.demo.dao.NoticeInfDao;
import com.test.demo.dto.NoticeInfExecution;
import com.test.demo.entity.NoticeInf;
import com.test.demo.enums.NoticeInfStateEnum;
import com.test.demo.service.NoticeInfService;
import com.test.demo.until.PageCalculator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NoticeInfServiceImpl implements NoticeInfService {
    @Resource
    private NoticeInfDao noticeInfDao;

    @Override
    public List<NoticeInf> getNoticeInfList() {
        return noticeInfDao.queryNoticeInfList();
    }

    @Override
    public NoticeInfExecution queryNoticeInfPage(int pageIndex, int pageSize) {
        int rowIndex = PageCalculator.calculatePowIndex(pageIndex, pageSize);
        List<NoticeInf> noticeInfList = noticeInfDao.queryNoticeInfPage(rowIndex, pageSize);
        int count = noticeInfDao.queryNoticeInfPageCount();
        NoticeInfExecution ue = new NoticeInfExecution();
        if (noticeInfList != null) {
            ue.setNoticeInfList(noticeInfList);
            ue.setCount(count);
            ue.setState(NoticeInfStateEnum.SUCCESS.getState());
            ue.setStateInfo(NoticeInfStateEnum.SUCCESS.getStateInfo());
        } else {
            ue.setState(NoticeInfStateEnum.INNER_ERROR.getState());
        }
        return ue;
    }

    @Override
    public NoticeInfExecution queryNoticeInf(int pageIndex, int pageSize, NoticeInf noticeInfCondition) {
        int rowIndex = PageCalculator.calculatePowIndex(pageIndex, pageSize);
        List<NoticeInf> noticeInfList = noticeInfDao.queryNoticeInf(rowIndex, pageSize, noticeInfCondition);
        int count = noticeInfDao.queryNoticeInfConut(noticeInfCondition);
        NoticeInfExecution ue = new NoticeInfExecution();
        if (noticeInfList != null) {
            ue.setNoticeInfList(noticeInfList);
            ue.setCount(count);
            ue.setState(NoticeInfStateEnum.SUCCESS.getState());
            ue.setStateInfo(NoticeInfStateEnum.SUCCESS.getStateInfo());
        } else {
            ue.setState(NoticeInfStateEnum.INNER_ERROR.getState());
        }
        return ue;
    }

    @Override
    @Transactional
    public int insertNoticeInf(NoticeInf noticeInf) {
        return noticeInfDao.insertNoticeInf(noticeInf);
    }

    @Override
    @Transactional
    public int updateNoticeInf(NoticeInf noticeInf) {
        return noticeInfDao.updateNoticeInf(noticeInf);
    }

    @Override
    @Transactional
    public int deleteNoticeInf(List<Long> ids) {
        return noticeInfDao.deleteNoticeInf(ids);
    }
}

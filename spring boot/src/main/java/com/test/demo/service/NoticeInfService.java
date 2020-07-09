package com.test.demo.service;

import com.test.demo.dto.NoticeInfExecution;
import com.test.demo.entity.NoticeInf;

import java.util.List;

public interface NoticeInfService {

    /**
     * 获取所有通知信息
     *
     * @return
     */
    public List<NoticeInf> getNoticeInfList();

    /**
     * 所有信息分页
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public NoticeInfExecution queryNoticeInfPage(int pageIndex, int pageSize);

    /**
     * 查询信息分页
     *
     * @param pageIndex
     * @param pageSize
     * @param noticeInfCondition
     * @return
     */
    public NoticeInfExecution queryNoticeInf(int pageIndex, int pageSize, NoticeInf noticeInfCondition);

    /**
     * 有条件的查询
     * @return
     */
//    public List<NoticeInf> getNoticeInf(NoticeInf noticeInfCondition);

    /**
     * 插入一条记录
     *
     * @param noticeInf
     * @return
     */
    public int insertNoticeInf(NoticeInf noticeInf);

    /**
     * 更新信息
     *
     * @param noticeInf
     * @return
     */
    public int updateNoticeInf(NoticeInf noticeInf);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    public int deleteNoticeInf(List<Long> ids);
}

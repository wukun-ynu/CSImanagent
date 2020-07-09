package com.test.demo.dao;

import com.test.demo.entity.NoticeInf;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NoticeInfDao {

    /**
     * @return
     */
    List<NoticeInf> queryNoticeInfList();

    List<NoticeInf> queryNoticeInfPage(@Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

    int queryNoticeInfPageCount();

    /**
     * 根据输入条件进行查询
     *
     * @param noticeInfCondition
     * @return
     */
    List<NoticeInf> queryNoticeInf(@Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize, @Param("noticeInfCondition") NoticeInf noticeInfCondition);

    int queryNoticeInfConut(@Param("noticeInfCondition") NoticeInf noticeInfCondition);

    /**
     * 插入一条记录
     *
     * @param noticeInf
     * @return
     */
    int insertNoticeInf(NoticeInf noticeInf);

    /**
     * 更新公告信息
     *
     * @param noticeInf
     * @return
     */
    int updateNoticeInf(NoticeInf noticeInf);

    /**
     * 根据Id进行删除
     *
     * @param ids
     * @return
     */
    int deleteNoticeInf(List<Long> ids);
}

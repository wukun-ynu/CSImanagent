package com.test.demo.dao;

import com.test.demo.entity.DocumentInf;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DocumentInfDao {

    /**
     * 获取所有文档信息
     *
     * @return
     */
    List<DocumentInf> queryDocumentInfList();

    /**
     * 分页获取所有信息
     *
     * @param rowIndex
     * @param pageSize
     * @return
     */
    List<DocumentInf> queryDocumentInfPage(@Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

    /**
     * 所有信息总数
     *
     * @return
     */
    int queryDocumentInfPageCount();

    /**
     * 条件分页
     *
     * @param rowIndex
     * @param pageSize
     * @param documentInfCondition
     * @return
     */
    List<DocumentInf> queryDocumentInf(@Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize, @Param("documentInfCondition") DocumentInf documentInfCondition);

    /***
     * 条件查询总数
     * @param documentInfCondition
     * @return
     */
    int queryDocumentInfCount(@Param("documentInfCondition") DocumentInf documentInfCondition);

    /**
     * 插入一条数据
     *
     * @param documentInf
     * @return
     */
    int insertDocumentInf(DocumentInf documentInf);

    /**
     * 更新一条数据
     *
     * @param documentInf
     * @return
     */
    int updateDocumentInf(DocumentInf documentInf);

    /**
     * 删除多条数据
     *
     * @param ids
     * @return
     */
    int deleteDocumentInf(List<Long> ids);
}

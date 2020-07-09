package com.test.demo.service;

import com.test.demo.dto.DocumentInfExecution;
import com.test.demo.entity.DocumentInf;

import java.util.List;

public interface DocumentInfService {
    /**
     * 获取所有信息
     *
     * @return
     */
    List<DocumentInf> queryDocumentInfList();

    /**
     * 分页数据
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    DocumentInfExecution queryDocumentInfPage(int pageIndex, int pageSize);

    /**
     * 条件分页数据
     *
     * @param pageIndex
     * @param pageSize
     * @param documentInfCondition
     * @return
     */
    DocumentInfExecution queryDocumentInf(int pageIndex, int pageSize, DocumentInf documentInfCondition);


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

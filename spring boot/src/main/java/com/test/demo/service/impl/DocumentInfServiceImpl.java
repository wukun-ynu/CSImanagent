package com.test.demo.service.impl;

import com.test.demo.dao.DocumentInfDao;
import com.test.demo.dto.DocumentInfExecution;
import com.test.demo.entity.DocumentInf;
import com.test.demo.enums.DocumentInfStateEnum;
import com.test.demo.service.DocumentInfService;
import com.test.demo.until.PageCalculator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DocumentInfServiceImpl implements DocumentInfService {

    @Resource
    private DocumentInfDao documentInfDao;

    @Override
    public List<DocumentInf> queryDocumentInfList() {
        return documentInfDao.queryDocumentInfList();
    }

    @Override
    public DocumentInfExecution queryDocumentInfPage(int pageIndex, int pageSize) {
        int rowIndex = PageCalculator.calculatePowIndex(pageIndex, pageSize);
        List<DocumentInf> documentInfList = documentInfDao.queryDocumentInfPage(rowIndex, pageSize);
        int count = documentInfDao.queryDocumentInfPageCount();
        DocumentInfExecution ue = new DocumentInfExecution();
        if (documentInfList != null) {
            ue.setDocumentInfList(documentInfList);
            ue.setCount(count);
            ue.setState(DocumentInfStateEnum.SUCCESS.getState());
            ue.setStateInfo(DocumentInfStateEnum.SUCCESS.getStateInfo());
        } else {
            ue.setState(DocumentInfStateEnum.INNER_ERROR.getState());
        }
        return ue;
    }

    @Override
    public DocumentInfExecution queryDocumentInf(int pageIndex, int pageSize, DocumentInf deptInfCondition) {
        int rowIndex = PageCalculator.calculatePowIndex(pageIndex, pageSize);
        List<DocumentInf> documentInfList = documentInfDao.queryDocumentInf(rowIndex, pageSize, deptInfCondition);
        int count = documentInfDao.queryDocumentInfCount(deptInfCondition);
        DocumentInfExecution ue = new DocumentInfExecution();
        if (documentInfList != null) {
            ue.setDocumentInfList(documentInfList);
            ue.setCount(count);
            ue.setState(DocumentInfStateEnum.SUCCESS.getState());
            ue.setStateInfo(DocumentInfStateEnum.SUCCESS.getStateInfo());
        } else {
            ue.setState(DocumentInfStateEnum.INNER_ERROR.getState());
        }
        return ue;
    }

    @Override
    @Transactional
    public int insertDocumentInf(DocumentInf documentInf) {
        return documentInfDao.insertDocumentInf(documentInf);
    }

    @Override
    @Transactional
    public int updateDocumentInf(DocumentInf documentInf) {
        return documentInfDao.updateDocumentInf(documentInf);
    }

    @Override
    @Transactional
    public int deleteDocumentInf(List<Long> ids) {
        return documentInfDao.deleteDocumentInf(ids);
    }
}


package com.test.demo.controller;

import com.test.demo.dto.DocumentInfExecution;
import com.test.demo.entity.DocumentInf;
import com.test.demo.entity.GetList;
import com.test.demo.service.DocumentInfService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class DocumentInfControl {
    @Autowired
    DocumentInfService documentInfService;

    @RequestMapping(value = "/getDocumentInfList")
    public List<DocumentInf> getDocumentInfList() {
        return documentInfService.queryDocumentInfList();
    }

    @RequestMapping(value = "/getDocumentInfPage")
    public DocumentInfExecution getDocumentInfPage(@RequestParam(value = "pageIndex") int pageIndex, @RequestParam(value = "pageSize") int pageSize) {
        return documentInfService.queryDocumentInfPage(pageIndex, pageSize);
    }

    @RequestMapping(value = "/getDocumentInf", method = RequestMethod.POST)
    public DocumentInfExecution getDocumentInf(@RequestParam(value = "documentInfCondition") String documentInfCondition, @RequestParam(value = "pageIndex") int pageIndex, @RequestParam(value = "pageSize") int pageSize) {
        JSONObject jsonobject = JSONObject.fromObject(documentInfCondition);
        DocumentInf documentInfCondition1 = (DocumentInf) JSONObject.toBean(jsonobject, DocumentInf.class);
        return documentInfService.queryDocumentInf(pageIndex, pageSize, documentInfCondition1);
    }

    @RequestMapping(value = "/insertDocumentInf", method = RequestMethod.POST)
    public int insertDocumentInf(DocumentInf documentInf) {
        return documentInfService.insertDocumentInf(documentInf);
    }

    @RequestMapping(value = "/updateDocumentInf", method = RequestMethod.POST)
    public int updateDocumentInf(DocumentInf documentInf) {
        return documentInfService.updateDocumentInf(documentInf);
    }

    @RequestMapping(value = "/deleteDocumentInf", method = RequestMethod.POST)
    public int deleteNoticeInf(@RequestParam(value = "ids", required = false) String ids) {
        JSONObject jsonobject = JSONObject.fromObject(ids);
        GetList userInfCondition1 = (GetList) JSONObject.toBean(jsonobject, GetList.class);
//        List<Long> id1 = Arrays.asList(ids.split(",")).stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
        return documentInfService.deleteDocumentInf(userInfCondition1.getIds());
    }

}

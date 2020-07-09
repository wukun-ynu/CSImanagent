package com.test.demo.controller;

import com.test.demo.dto.NoticeInfExecution;
import com.test.demo.entity.GetList;
import com.test.demo.entity.NoticeInf;
import com.test.demo.service.NoticeInfService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class NoticeInfControl {
    @Autowired
    NoticeInfService noticeInfService;

    @RequestMapping(value = "/getNoticeInfList")
    public List<NoticeInf> getNoticeInfList() {
        return noticeInfService.getNoticeInfList();
    }

    @RequestMapping(value = "/getNoticeInfPage")
    public NoticeInfExecution getNoticeInfPage(@RequestParam(value = "pageIndex") int pageIndex, @RequestParam(value = "pageSize") int pageSize) {
        return noticeInfService.queryNoticeInfPage(pageIndex, pageSize);
    }

    @RequestMapping(value = "/getNoticeInf", method = RequestMethod.POST)
    public NoticeInfExecution getNoticeInf(@RequestParam(value = "noticeInfCondition") String noticeInfCondition, @RequestParam(value = "pageIndex") int pageIndex, @RequestParam(value = "pageSize") int pageSize) {
        JSONObject jsonobject = JSONObject.fromObject(noticeInfCondition);
        NoticeInf noticeInfCondition1 = (NoticeInf) JSONObject.toBean(jsonobject, NoticeInf.class);
        return noticeInfService.queryNoticeInf(pageIndex, pageSize, noticeInfCondition1);
    }

    @RequestMapping(value = "/insertNoticeInf", method = RequestMethod.POST)
    public int insertNoticeInf(NoticeInf noticeInf) {
        return noticeInfService.insertNoticeInf(noticeInf);
    }

    @RequestMapping(value = "/updateNoticeInf", method = RequestMethod.POST)
    public int updateNoticeInf(NoticeInf noticeInf) {
        return noticeInfService.updateNoticeInf(noticeInf);
    }

    @RequestMapping(value = "/deleteNoticeInf", method = RequestMethod.POST)
    public int deleteNoticeInf(@RequestParam(value = "ids", required = false) String ids) {
        JSONObject jsonobject = JSONObject.fromObject(ids);
        GetList userInfCondition1 = (GetList) JSONObject.toBean(jsonobject, GetList.class);
//        List<Long> id1 = Arrays.asList(ids.split(",")).stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
        return noticeInfService.deleteNoticeInf(userInfCondition1.getIds());
    }


//    @InitBinder
//    protected void initBinder(WebDataBinder binder) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
//    }
}

package com.test.demo.controller;

import com.test.demo.dto.DeptInfExecution;
import com.test.demo.entity.DeptInf;
import com.test.demo.entity.GetList;
import com.test.demo.service.DeptInfService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptInfControl {
    @Autowired
    DeptInfService deptInfService;

    @RequestMapping(value = "/DeptInfList", method = RequestMethod.GET)
    public List<DeptInf> DeptInfList() {
        return deptInfService.deptInfList();
    }

    @RequestMapping(value = "/getDeptInfList", method = RequestMethod.POST)
    public DeptInfExecution getDeptInfList(@RequestParam(value = "pageIndex") int pageIndex, @RequestParam(value = "pageSize") int pageSize) {
        return deptInfService.queryDeptInfList(pageIndex, pageSize);
    }

    @RequestMapping(value = "/getDeptInf", method = RequestMethod.POST)
    public DeptInfExecution getDeptInf(@RequestParam(value = "deptInfCondition") String deptInfCondition, @RequestParam(value = "pageIndex") int pageIndex, @RequestParam(value = "pageSize") int pageSize) {
        JSONObject jsonobject = JSONObject.fromObject(deptInfCondition);
        DeptInf deptInfCondition1 = (DeptInf) JSONObject.toBean(jsonobject, DeptInf.class);
        return deptInfService.queryDeptInf(pageIndex, pageSize, deptInfCondition1);
    }

    @RequestMapping(value = "/insertDeptInf", method = RequestMethod.POST)
    public int insertDeptInf(DeptInf deptInf) {
        return deptInfService.insertDeptInf(deptInf);
    }


    @RequestMapping(value = "/updateDeptInf", method = RequestMethod.POST)
    public int updateDeptInf(DeptInf deptInf) {
        return deptInfService.updateDeptInf(deptInf);
    }

    @RequestMapping(value = "/deleteDeptInf", method = RequestMethod.POST)
    public int deleteDeptInf(@RequestParam(value = "ids", required = false) String ids) {
        JSONObject jsonobject = JSONObject.fromObject(ids);
        GetList userInfCondition1 = (GetList) JSONObject.toBean(jsonobject, GetList.class);
//        List<Long> id1 = Arrays.asList(ids.split(",")).stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
        return deptInfService.deleteDeptInf(userInfCondition1.getIds());
    }
}

package com.test.demo.controller;

import com.test.demo.dto.EmployeeInfExecution;
import com.test.demo.entity.EmployeeInf;
import com.test.demo.entity.GetList;
import com.test.demo.service.EmployeeInfService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class EmployeeInfControl {
    @Autowired
    EmployeeInfService employeeInfService;

    @RequestMapping(value = "/getEmployeeInfList")
    public List<EmployeeInf> getEmployeeInfList() {
        return employeeInfService.getEmployeeInfList();
    }

    @RequestMapping(value = "/getEmployeeInfPage")
    public EmployeeInfExecution getEmployeeInfPage(@RequestParam(value = "pageIndex") int pageIndex, @RequestParam(value = "pageSize") int pageSize) {
        return employeeInfService.queryEmployeeInfPage(pageIndex, pageSize);
    }

    @RequestMapping(value = "/getEmployeeInf", method = RequestMethod.POST)
    public EmployeeInfExecution getEmployeeInf(@RequestParam(value = "employeeInfCondition") String employeeInfCondition, @RequestParam(value = "pageIndex") int pageIndex, @RequestParam(value = "pageSize") int pageSize) {
        JSONObject jsonobject = JSONObject.fromObject(employeeInfCondition);
        EmployeeInf employeeInfCondition1 = (EmployeeInf) JSONObject.toBean(jsonobject, EmployeeInf.class);
        return employeeInfService.getEmployeeInf(pageIndex, pageSize, employeeInfCondition1);
    }

    @RequestMapping(value = "/insertEmployeeInf", method = RequestMethod.POST)
    public int insertEmployeeInf(EmployeeInf employeeInf) {
        return employeeInfService.insertEmploeeInf(employeeInf);
    }

    @RequestMapping(value = "/updateEmployeeInf", method = RequestMethod.POST)
    public int updateEmployeeInf(EmployeeInf employeeInf) {
        return employeeInfService.updateEmployeeInf(employeeInf);
    }

    @RequestMapping(value = "/deleteEmployeeInf", method = RequestMethod.POST)
    public int deleteEmployeeInf(@RequestParam(value = "ids", required = false) String ids) {
        JSONObject jsonobject = JSONObject.fromObject(ids);
        GetList userInfCondition1 = (GetList) JSONObject.toBean(jsonobject, GetList.class);
//        List<Long> id1 = Arrays.asList(ids.split(",")).stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
        return employeeInfService.deleteEmployeeInf(userInfCondition1.getIds());
    }


    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}

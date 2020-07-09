package com.test.demo.controller;

import com.test.demo.dao.testRes;
import com.test.demo.dto.UserInfExecution;
import com.test.demo.entity.GetList;
import com.test.demo.entity.Page;
import com.test.demo.entity.UserInf;
import com.test.demo.service.JobInfService;
import com.test.demo.service.UserInfService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class UserInfController {

    @Autowired
    testRes testres;
    @Autowired
    JobInfService jobInfService;
    @Autowired
    UserInfService userInfService;

    @RequestMapping(value = "/getUserList", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public List<UserInf> getUsrList() {
        return userInfService.getUserList();

    }

    @RequestMapping(value = "/currentUser")
    public UserInf getCurrentUser(HttpServletRequest request) {
        UserInf user = (UserInf) request.getSession().getAttribute("user");
        return user;
    }

    @RequestMapping(value = "/getUserInfList", method = RequestMethod.POST)
    public UserInfExecution getUserInfList(@RequestParam(value = "userInfCondition") String userInfCondition, @RequestParam(value = "pageIndex") int pageIndex, @RequestParam(value = "pageSize") int pageSize) {
        JSONObject jsonobject = JSONObject.fromObject(userInfCondition);
        UserInf userInfCondition1 = (UserInf) JSONObject.toBean(jsonobject, UserInf.class);
        return userInfService.getUserInfList(pageIndex, pageSize, userInfCondition1);
    }

    @RequestMapping(value = "/addUserInf", method = RequestMethod.POST)
    public int addUser(UserInf userInfCondition) {

        return userInfService.addUser(userInfCondition);
    }

    @RequestMapping(value = "/updateUserInf", method = RequestMethod.POST)
    public int updateUser(UserInf userInfCondition) {

        return userInfService.updateUser(userInfCondition);
    }

    //    数据传递格式为ids: []
    @RequestMapping(value = "/deleteUserInf", method = RequestMethod.POST)
    public int deleteUsers(@RequestParam(value = "ids", required = false) String ids) {
        JSONObject jsonobject = JSONObject.fromObject(ids);
        GetList userInfCondition1 = (GetList) JSONObject.toBean(jsonobject, GetList.class);
//        List<Long> id1 = Arrays.asList(ids.split(",")).stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
        return userInfService.deleteUsers(userInfCondition1.getIds());
    }

    @RequestMapping("/test")
//    @CrossOrigin(origins = {"http://localhost:8080"})
    public String test(HttpServletResponse response) {

//        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
//        response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, PATCH, DELETE");
//        response.setHeader("Access.Control-Allow-Credentials", "true");
//        vue中文件的上传赋值
//        files.foreach(file => params.append('files', file))
        return "这是一个测试";
    }

    @RequestMapping(value = "/UserInfPage", method = RequestMethod.POST)
    public UserInfExecution UsersInfPage(@RequestParam(value = "pageIndex") int pageIndex, @RequestParam(value = "pageSize") int pageSize) {
        return userInfService.getUserListByPage(pageIndex, pageSize);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public UserInf user(@RequestParam("userName") String userName, @RequestParam("password") String password, HttpServletRequest request) {
        UserInf user = userInfService.findByUserName(userName);
        if (user.getPassword().equals(password)) {
            request.getSession().setAttribute("user", user);
            return user;
        } else {
            return user;
        }
    }
}

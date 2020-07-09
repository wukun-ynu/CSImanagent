package com.test.demo.controller;

import com.test.demo.dto.JobInfExecution;
import com.test.demo.entity.GetList;
import com.test.demo.entity.JobInf;
import com.test.demo.service.JobInfService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobInfControl {
    @Autowired
    JobInfService jobInfService;

    @RequestMapping(value = "/JobInfList")
    public List<JobInf> JobInfList() {
        return jobInfService.jobInfList();
    }

    @RequestMapping(value = "/getJobInfList")
    public JobInfExecution getJobInfList(@RequestParam(value = "pageIndex") int pageIndex, @RequestParam(value = "pageSize") int pageSize) {
        return jobInfService.getJobInfList(pageIndex, pageSize);
    }

    @RequestMapping(value = "/getJobInf", method = RequestMethod.POST)
    public JobInfExecution getDeptInf(@RequestParam(value = "jobInfCondition") String jobInfCondition, @RequestParam(value = "pageIndex") int pageIndex, @RequestParam(value = "pageSize") int pageSize) {
        JSONObject jsonobject = JSONObject.fromObject(jobInfCondition);
        JobInf jobInfCondition1 = (JobInf) JSONObject.toBean(jsonobject, JobInf.class);
        return jobInfService.getJobInf(pageIndex, pageSize, jobInfCondition1);
    }

    @RequestMapping(value = "/insertJobInf", method = RequestMethod.POST)
    public int insertDeptInf(JobInf jobInf) {
        return jobInfService.insertJobInf(jobInf);
    }


    @RequestMapping(value = "/updateJobInf", method = RequestMethod.POST)
    public int updateDeptInf(JobInf jobInf) {
        return jobInfService.updateJobInf(jobInf);
    }

    @RequestMapping(value = "/deleteJobInf", method = RequestMethod.POST)
    public int deleteDeptInf(@RequestParam(value = "ids", required = false) String ids) {
        JSONObject jsonobject = JSONObject.fromObject(ids);
        GetList userInfCondition1 = (GetList) JSONObject.toBean(jsonobject, GetList.class);
//        List<Long> id1 = Arrays.asList(ids.split(",")).stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
        return jobInfService.deleteJobInf(userInfCondition1.getIds());
    }

}

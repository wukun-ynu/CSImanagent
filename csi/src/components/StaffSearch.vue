<template>
  <div>
    <div>
      <el-form :inline="true" v-model="employeeInf">
        <el-form-item label="职位：">
            <el-select v-model="employeeInf.jobId">
              <el-option v-for="item in options" :key="item.jobId" :label="item.jobName" :value="item.jobId"></el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="姓名：">
          <el-input v-model="employeeInf.name"  placeholder="请输入姓名" clearable=""></el-input>
        </el-form-item>
        <el-form-item label="身份证号码：">
          <el-input v-model="employeeInf.cardId"  placeholder="请输入身份证号码" clearable=""></el-input>
        </el-form-item>
        <el-form-item label="性别：">
            <el-select v-model="employeeInf.sex">
              <el-option v-for="item in sexs" :key="item.value" :label="item.label" :value="item.value"></el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="手机：">
          <el-input v-model="employeeInf.phone"  placeholder="请输入手机号" clearable=""></el-input>
        </el-form-item>
        <el-form-item label="所属部门：">
            <el-select v-model="employeeInf.deptId">
              <el-option v-for="item in dept" :key="item.deptId" :label="item.deptName" :value="item.deptId"></el-option>
            </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" round="" @click.native.prevent="searchEmployee(employeeInf)">搜索</el-button>
          <el-button type="danger" round="" @click.native.prevent="show">删除</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div>
      <el-table ref="multipleTale" :data="tableData" tooltip-effect="dark" style="width: 100%" @selection-change="handleSelectionChange" show-overflow-tooltip="true">
        <el-table-column type="selection">
        </el-table-column>
        <el-table-column  label="姓名"   prop="name">
        </el-table-column>
        <el-table-column  label="性别"   prop="sex">
        </el-table-column>
        <el-table-column  label="邮箱"   prop="email">
        </el-table-column>
        <el-table-column  label="职位"   prop="jobInf.jobName">
        </el-table-column>
        <el-table-column  label="学历"   prop="education">
        </el-table-column>
        <el-table-column  label="身份证号码"   prop="cardId">
        </el-table-column>
        <el-table-column  label="部门"   prop="deptInf.deptName">
        </el-table-column>
        <el-table-column  label="联系地址"   prop="address">
        </el-table-column>
        <el-table-column  label="建档日期"   prop="birthday">
          <!--<template slot-scope="scope">{{scope.row.createDate | formatDate}}</template>-->
        </el-table-column>
        <el-table-column  label="操作"  >
          <template slot-scope="scope">
            <el-button @click.native.prevent="updateEmployee(scope.row)" type="primary" size="small" icon="el-icon-edit" circle=""></el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div>
      <el-dialog title="员工信息修改" :visible.sync="dialogFormVisible" :modal-append-to-body="false">
        <el-form label-width="auto" label-position="right" :model="updateEmployeeInf" :ref="updateEmployeeInf" :rules="rules">
          <el-row>
            <el-form-item label="姓名：" prop="name">
              <el-input  placeholder="请输入姓名" v-model=" updateEmployeeInf.name" clearable=""  autosize=""></el-input>
            </el-form-item>
            <el-form-item label="身份证号码：" prop="cardId">
              <el-input  placeholder="请输入" v-model=" updateEmployeeInf.cardId" clearable=""  autosize=""></el-input>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="性别：" prop="sex">
              <el-select v-model=" updateEmployeeInf.sex">
                <el-option v-for="item in sexs" :key="item.value" :label="item.label" :value="item.value"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="职位：" prop="jobId">
              <el-select v-model=" updateEmployeeInf.jobId">
                <el-option v-for="item in options" :key="item.jobId" :label="item.jobName" :value="item.jobId"></el-option>
              </el-select>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="学历：" prop="education">
              <el-input  placeholder="请输入学历" v-model=" updateEmployeeInf.education" clearable=""  autosize=""></el-input>
            </el-form-item>
            <el-form-item label="邮箱：" prop="email">
              <el-input  placeholder="请输入邮箱" v-model=" updateEmployeeInf.email" clearable=""  autosize=""></el-input>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="手机：" prop="phone">
              <el-input  placeholder="请输入手机号码" v-model=" updateEmployeeInf.phone" clearable=""  autosize=""></el-input>
            </el-form-item>
            <el-form-item label="电话：" prop="tel">
              <el-input  placeholder="请输入电话" v-model=" updateEmployeeInf.tel" clearable=""  autosize=""></el-input>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="政治面貌：" prop=".party">
              <el-input  placeholder="请输入政治面貌" v-model=" updateEmployeeInf.party" clearable=""  autosize=""></el-input>
            </el-form-item>
            <el-form-item label="QQ 号码：" prop="qqNum">
              <el-input  placeholder="请输入QQ 号码" v-model=" updateEmployeeInf.qqNum" clearable=""  autosize=""></el-input>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="联系地址：" prop="address">
              <el-input  placeholder="请输入联系地址" v-model=" updateEmployeeInf.address" clearable=""  autosize=""></el-input>
            </el-form-item>
            <el-form-item label="邮政编码：" prop="postCode">
              <el-input  placeholder="请输入邮政编码" v-model=" updateEmployeeInf.postCode" clearable=""  autosize=""></el-input>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="出生日期：">
              <el-date-picker v-model="updateEmployeeInf.birthday" type="date" placeholder="选择日期" format="yyyy 年 MM 月 dd 日"
                              value-format="yyyy-MM-dd"></el-date-picker>
              <!--<el-input  placeholder="请输入出生日期" v-model=" updateEmployeeInf.birthday" clearable=""  autosize="" format="yyyy 年 MM 月 dd 日"-->
                         <!--value-format="yyyy-MM-dd"></el-input>-->
            </el-form-item>
            <el-form-item label="民族：" prop="race">
              <el-input  placeholder="请输入民族" v-model=" updateEmployeeInf.race" clearable=""  autosize=""></el-input>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="所学专业：" prop="specility">
              <el-input  placeholder="请输入所学专业" v-model=" updateEmployeeInf.specility" clearable=""  autosize=""></el-input>
            </el-form-item>
            <el-form-item label="爱好：" prop="hobby">
              <el-input  placeholder="请输入爱好" v-model="updateEmployeeInf.hobby" clearable=""  autosize=""></el-input>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="备注：" prop="remark">
              <el-input  placeholder="请输入备注" v-model=" updateEmployeeInf.remark" clearable=""  autosize=""></el-input>
            </el-form-item>
            <el-form-item label="所属部门：" prop="deptId">
              <el-select v-model="updateEmployeeInf.deptId">
                <el-option v-for="item in dept" :key="item.deptId" :label="item.deptName" :value="item.deptId"></el-option>
              </el-select>
            </el-form-item>
          </el-row>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click.native.prevent="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click.native.prevent="subUpdateEmployee(updateEmployeeInf)">确 定</el-button>
        </div>
      </el-dialog>
    </div>
    <div>
      <!--<el-switch v-model="switchNum"></el-switch>-->
      <el-pagination :hide-on-single-page="switchNum" :total="count" layout="prev, pager, next, jumper, ->, total, slot" :page-size="2" :currentPage="currentPage" @current-change="handleCurrentChange"
      ></el-pagination>
    </div>
  </div>
</template>

<script>import {getRequest, postRequest, postRequest1} from '../untils/api'

export default {
  name: 'StaffSearch',
  data () {
    return {
      tableData: [],
      multipleSelection: [],
      switchNum: false,
      employeeInf: {
        'deptId': '',
        'jobId': '',
        'name': '',
        'cardId': '',
        'phone': '',
        'sex': ''
      },
      optionJob: {
        'jobId': '',
        'jobName': '',
        'jobRemark': ''
      },
      optionDept: {
        'deptId': '',
        'deptName': '',
        'deptRemark': ''
      },
      sexs: [{
        value: 1,
        label: '男'
      }, {
        value: 2,
        label: '女'
      }, {
        value: '',
        label: '全部'
      }],
      options: [],
      dept: [],
      dialogFormVisible: false,
      count: 0,
      currentPage: 1,
      pageSize: 2,
      searchPage: 1,
      updateEmployeeInf: {},
      deleteInfo: [],
      user: {},
      rules: {
        deptId: [
          { required: true, message: '请选择部门', trigger: 'change' }
        ],
        jobId: [
          { required: true, message: '请选择部门', trigger: 'change' }
        ],
        name: [
          { required: true, message: '请输入姓名', trigger: 'blur' },
          { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
        ],
        cardId: [
          { required: true, message: '请输入身份证号', trigger: 'blur' }
        ],
        address: [
          { required: true, message: '请输入地址', trigger: 'blur' }
        ],
        postCode: [
          { required: false, message: '请输入地址', trigger: 'blur' }
        ],
        tel: [
          { required: false, message: '请输入地址', trigger: 'blur' }
        ],
        phone: [
          { required: true, message: '请输入地址', trigger: 'blur' }
        ],
        qqNum: [
          { required: false, message: '请输入地址', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请输入地址', trigger: 'blur' }
        ],
        sex: [
          { required: true, message: '请选择性别', trigger: 'change' }
        ],
        party: [
          { required: false, message: '请输入地址', trigger: 'blur' }
        ],
        // birthday: [
        //   { type: 'date', required: false, message: '请输入地址', trigger: 'change' }
        // ],
        race: [
          { required: true, message: '请输入地址', trigger: 'blur' }
        ],
        education: [
          { required: false, message: '请输入地址', trigger: 'blur' }
        ],
        specility: [
          { required: false, message: '请输入地址', trigger: 'blur' }
        ],
        hobby: [
          { required: false, message: '请输入地址', trigger: 'blur' }
        ],
        remark: [
          { required: false, message: '请输入地址', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    deleteRow (index, rows) {
      rows.splice(index, 1)
    },
    show: function () {
      var _this = this
      _this.loading = true
      if (_this.user.status === 0) {
        _this.$message({
          message: '您没有权限',
          type: 'error'
        })
        return false
      }
      if (_this.multipleSelection.length === 0) {
        _this.$message({
          message: '请选择删除的用户',
          type: 'warning'
        })
        return false
      }
      _this.deleteInfo.splice(0, this.deleteInfo.length)
      _this.multipleSelection.forEach(row => {
        _this.deleteInfo.push(row.employeeId)
      })
      var deleteUserInfo = {
        ids: JSON.stringify({'ids': _this.deleteInfo})
      }
      postRequest1('http://localhost:8081/deleteEmployeeInf', deleteUserInfo).then(resp => {
        if (resp.status === 200) {
          _this.$message({
            message: '删除成功',
            type: 'success'
          })
        } else {
          _this.$alert('查询失败')
        }
      }, resp => {
        _this.loading = false
        _this.$alert('找不到服务器了', '失败')
      })
    },
    handleSelectionChange (val) {
      this.multipleSelection = val
    },
    handleCurrentChange (val) {
      var _this = this
      _this.currentPage = val
      if (_this.employeeInf != null) {
        var sub = {
          employeeInfCondition: JSON.stringify(_this.employeeInf),
          pageIndex: _this.currentPage,
          pageSize: _this.pageSize
        }
        postRequest1('http://localhost:8081/getEmployeeInf', sub).then(resp => {
          if (resp.status === 200) {
            _this.count = resp.data.count
            _this.tableData = resp.data.employeeInfList
          } else {
            _this.$alert('查询失败')
          }
        }, resp => {
          _this.loading = false
          _this.$alert('找不到服务器了', '失败')
        })
      } else {
        var sub1 = {
          'pageIndex': _this.currentPage,
          'pageSize': _this.pageSize
        }
        postRequest('http://localhost:8081/getEmployeeInfPage', sub1).then(resp => {
          if (resp.status === 200) {
            _this.count = resp.data.count
            _this.tableData = resp.data.employeeInfList
          } else {
            _this.$alert('查询失败')
          }
        }, resp => {
          _this.loading = false
          _this.$alert('找不到服务器了', '失败')
        })
      }
    },
    getJobInf: function () {
      var _this = this
      _this.loading = true
      getRequest('http://localhost:8081/JobInfList').then(resp => {
        if (resp.status === 200) {
          _this.options = resp.data
          _this.optionJob.jobId = ''
          _this.optionJob.jobName = '全部'
          _this.options.push(_this.optionJob)
        } else {
          _this.$alert('查询失败')
        }
      }, resp => {
        _this.loading = false
        _this.$alert('找不到服务器了', '失败')
      })
    },
    getDept: function () {
      var _this = this
      _this.loading = true
      getRequest('http://localhost:8081/DeptInfList').then(resp => {
        if (resp.status === 200) {
          _this.dept = resp.data
          _this.optionDept.deptId = ''
          _this.optionDept.deptName = '全部'
          _this.dept.push(_this.optionDept)
        } else {
          _this.$alert('查询失败')
        }
      }, resp => {
        _this.loading = false
        _this.$alert('找不到服务器了', '失败')
      })
    },
    searchEmployee: function (val) {
      var _this = this
      _this.loading = true
      var sub = {
        employeeInfCondition: JSON.stringify(val),
        pageIndex: _this.searchPage,
        pageSize: _this.pageSize
      }
      postRequest('http://localhost:8081/getEmployeeInf', sub).then(resp => {
        if (resp.status === 200) {
          _this.count = resp.data.count
          _this.tableData = resp.data.employeeInfList
        } else {
          _this.$alert('查询失败')
        }
      }, resp => {
        _this.loading = false
        _this.$alert('找不到服务器了', '失败')
      })
    },
    updateEmployee: function (val) {
      this.dialogFormVisible = true
      this.updateEmployeeInf = val
      delete this.updateEmployeeInf.deptInf
      delete this.updateEmployeeInf.jobInf
    },
    subUpdateEmployee: function (val) {
      this.$refs[val].validate((valid) => {
        if (valid) {
          var _this = this
          _this.loading = true
          postRequest1('http://localhost:8081/updateEmployeeInf', val).then(resp => {
            if (resp.status === 200) {
              _this.$alert('OK')
            } else {
              _this.$alert('查询失败')
            }
          }, resp => {
            _this.loading = false
            _this.$alert('找不到服务器了', '失败')
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    CurrentUser: function () {
      var _this = this
      _this.loading = true
      postRequest('http://localhost:8081/currentUser').then(resp => {
        if (resp.status === 200) {
          _this.user = resp.data
        } else {
          _this.$alert('查询失败')
        }
      }, resp => {
        _this.loading = false
        _this.$alert('找不到服务器', '失败')
      })
    }
  },
  mounted: function () {
    var _this = this
    _this.loading = true
    _this.getJobInf()
    _this.getDept()
    _this.CurrentUser()
    var sub = {
      'pageIndex': _this.currentPage,
      'pageSize': _this.pageSize
    }
    postRequest('http://localhost:8081/getEmployeeInfPage', sub).then(resp => {
      if (resp.status === 200) {
        _this.count = resp.data.count
        _this.tableData = resp.data.employeeInfList
      } else {
        _this.$alert('查询失败')
      }
    }, resp => {
      _this.loading = false
      _this.$alert('找不到服务器了', '失败')
    })
  }
}
</script>

<style scoped>

</style>

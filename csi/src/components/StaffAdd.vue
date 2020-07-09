<template>
  <div>
    <el-container>
      <el-main>
        <el-form label-width="auto" label-position="right" :model="employeeInf" :ref="employeeInf" :rules="rules">
          <el-row :gutter="100">
            <el-col :span="6">
            <el-form-item label="姓名：" prop="name">
                <el-input  placeholder="请输入姓名" v-model="employeeInf.name" clearable=""  autosize=""></el-input>
            </el-form-item>
            </el-col>
            <el-col :span="11">
            <el-form-item label="身份证号码：" prop="cardId">
              <el-input  placeholder="请输入" v-model="employeeInf.cardId" clearable=""  autosize=""></el-input>
            </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-form-item label="性别：" prop="sex">
              <el-select v-model="employeeInf.sex">
                <el-option v-for="item in sexs" :key="item.value" :label="item.label" :value="item.value"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="职位：" prop="jobId">
              <el-select v-model="employeeInf.jobId">
                <el-option v-for="item in options" :key="item.jobId" :label="item.jobName" :value="item.jobId"></el-option>
              </el-select>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="学历：" prop="education">
              <el-input  placeholder="请输入学历" v-model="employeeInf.education" clearable=""  autosize=""></el-input>
            </el-form-item>
            <el-form-item label="邮箱：" prop="email">
              <el-input  placeholder="请输入邮箱" v-model="employeeInf.email" clearable=""  autosize=""></el-input>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="手机：" prop="phone">
              <el-input  placeholder="请输入手机号码" v-model="employeeInf.phone" clearable=""  autosize=""></el-input>
            </el-form-item>
            <el-form-item label="电话：" prop="tel">
              <el-input  placeholder="请输入电话" v-model="employeeInf.tel" clearable=""  autosize=""></el-input>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="政治面貌：" prop="party">
              <el-input  placeholder="请输入政治面貌" v-model="employeeInf.party" clearable=""  autosize=""></el-input>
            </el-form-item>
            <el-form-item label="QQ 号码：" prop="qqNum">
              <el-input  placeholder="请输入QQ 号码" v-model="employeeInf.qqNum" clearable=""  autosize=""></el-input>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="联系地址：" prop="address">
              <el-input  placeholder="请输入联系地址" v-model="employeeInf.address" clearable=""  autosize=""></el-input>
            </el-form-item>
            <el-form-item label="邮政编码：" prop="postCode">
              <el-input  placeholder="请输入邮政编码" v-model="employeeInf.postCode" clearable=""  autosize=""></el-input>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="出生日期：">
              <el-date-picker v-model="employeeInf.birthday" type="date" placeholder="选择日期" format="yyyy 年 MM 月 dd 日"
                              value-format="yyyy-MM-dd"></el-date-picker>
              <!--<el-input  placeholder="请输入出生日期" v-model="employeeInf.birthday" clearable=""  autosize=""></el-input>-->
            </el-form-item>
            <el-form-item label="民族：" prop="race">
              <el-input  placeholder="请输入民族" v-model="employeeInf.race" clearable=""  autosize=""></el-input>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="所学专业：" prop="specility">
              <el-input  placeholder="请输入所学专业" v-model="employeeInf.specility" clearable=""  autosize=""></el-input>
            </el-form-item>
            <el-form-item label="爱好：" prop="hobby">
              <el-input  placeholder="请输入爱好" v-model="employeeInf.hobby" clearable=""  autosize=""></el-input>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="备注：" prop="remark">
              <el-input  placeholder="请输入备注" v-model="employeeInf.remark" clearable=""  autosize=""></el-input>
            </el-form-item>
            <el-form-item label="所属部门：" prop="deptId">
              <el-select v-model="employeeInf.deptId">
                <el-option v-for="item in dept" :key="item.deptId" :label="item.deptName" :value="item.deptId"></el-option>
              </el-select>
            </el-form-item>
          </el-row>
          <el-form-item>
            <el-button type="primary" round="" @click.native.prevent="submitEmployee(employeeInf)">添加</el-button>
            <el-button type="warning" round="" @click.native.prevent="resetForm(employeeInf)">取消</el-button>
          </el-form-item>
        </el-form>
      </el-main>
    </el-container>
  </div>
</template>

<script>import {postRequest, getRequest} from '../untils/api'
export default
{
  name: 'StaffAdd',
  data () {
    return {
      employeeInf: {
        'deptId': '',
        'jobId': '',
        'name': '',
        'cardId': '',
        'address': '',
        'postCode': '',
        'tel': '',
        'phone': '',
        'qqNum': '',
        'email': '',
        'sex': '',
        'party': '',
        'birthday': '',
        'race': '',
        'education': '',
        'specility': '',
        'hobby': '',
        'remark': ''
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
    submitEmployee: function (val) {
      this.$refs[val].validate((valid) => {
        if (valid) {
          var _this = this
          _this.loading = true
          postRequest('http://localhost:8081/insertEmployeeInf', val).then(resp => {
            if (resp.status === 200) {
              _this.$message({
                message: '添加员工成功',
                type: 'success'
              })
            } else {
              _this.$message({
                message: '添加员工失败',
                type: 'error'
              })
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
    resetForm (formName) {
      this.$refs[formName].resetFields()
    }
  },
  mounted: function () {
    var _this = this
    _this.loading = true
    _this.getJobInf()
    _this.getDept()
  }
}
</script>

<style scoped>

</style>

<template>
  <div>
    <el-container>
      <el-main>
        <el-form label-width="auto" label-position="right" :model="userInf" :ref="userInf" :rules="rules">
          <el-form-item label="姓名：" prop="userName">
            <el-col :span="4">
              <el-input  placeholder="请输入姓名" v-model="userInf.userName" clearable=""  autosize=""></el-input>
            </el-col>
          </el-form-item>
          <el-form-item label="状态：" prop="status">
            <el-col :span="4">
              <el-select v-model="userInf.status">
                <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"></el-option>
              </el-select>
            </el-col>
          </el-form-item>
          <el-form-item label="登录名:" prop="loginName">
            <el-col :span="4">
              <el-input placeholder="请输入登录名" v-model="userInf.loginName" clearable=""></el-input>
            </el-col>
          </el-form-item>
          <el-form-item label="密码：" v-if="userInf.status === 1" prop="password">
            <el-col :span="4">
              <el-input  type="password" placeholder="请输入密码" v-model="userInf.password" clearable="" show-password=""></el-input>
            </el-col>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" round="" @click.native.prevent="submitUser(userInf)">添加</el-button>
            <el-button type="warning" round=""  @click.native.prevent="resetForm(userInf)">取消</el-button>
          </el-form-item>
        </el-form>
      </el-main>
    </el-container>
  </div>
</template>

<script>import {getRequest, postRequest} from '../untils/api'
export default {
  name: 'UserAdd',
  data () {
    return {
      info: '',
      userInf: {
        'userName': '',
        'status': 1,
        'loginName': '',
        'password': '',
        'createDate': new Date()
      },
      options: [{
        value: 1,
        label: '管理员'
      }, {
        value: 0,
        label: '普通用户'
      }],
      rules: {
        userName: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 3, max: 9, message: '长度在 3 到 9 个字符', trigger: 'blur' }
        ],
        status: [
          { required: false, message: '请选择用户状态', trigger: 'change' }
        ],
        loginName: [
          { required: true, message: '请输入登录名', trigger: 'blur' }
        ],
        password: [
          { required: false, message: '请输入密码', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    deleteUser: function () {
      var _this = this
      getRequest('http://localhost:8081/test').then(resp => {
        _this.loading = false
        if (resp.status === 200) {
          _this.info = resp.data
          console.log(resp.data)
        } else {
          _this.$message({type: 'error', message: '数据加载失败！'})
        }
      }, resp => {
        _this.loading = false
        if (resp.status === 403) {
          var data = resp.response.data
          _this.$message({type: 'error', message: data})
        }
      })
    },
    submitUser: function (val) {
      this.$refs[val].validate((valid) => {
        if (valid) {
          var _this = this
          _this.loading = true
          postRequest('http://localhost:8081/addUserInf', val).then(resp => {
            if (resp.status === 200) {
              _this.$message({
                message: '添加用户成功',
                type: 'success'
              })
            } else {
              _this.$message({
                message: '添加用户失败',
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
  }
}
</script>

<style scoped>
.el-container{
  text-align: left;
}
  .el-input{
    display: inline-block!important;
  }
</style>

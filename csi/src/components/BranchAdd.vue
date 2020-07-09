<template>
  <div>
    <el-container>
      <el-main>
        <el-form label-width="auto" label-position="right" :model="deptInf" :ref="deptInf" :rules="rules">
          <el-form-item label="部门名称：" prop="deptName">
            <el-col :span="4">
              <el-input  placeholder="请输入部门名称" v-model="deptInf.deptName" clearable=""></el-input>
            </el-col>
          </el-form-item>
          <el-form-item label="详细信息:" prop="deptRemark">
            <el-col :span="4">
              <el-input placeholder="请输入详细信息" v-model="deptInf.deptRemark" clearable=""></el-input>
            </el-col>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" round="" @click="submitDept(deptInf)">添加</el-button>
            <el-button type="warning" round="" @click.native.prevent="resetForm(deptInf)">取消</el-button>
          </el-form-item>
        </el-form>
      </el-main>
    </el-container>
  </div>
</template>

<script>import {postRequest} from '../untils/api'

export default {
  name: 'BranchAdd',
  data () {
    return {
      deptInf: {
        'deptName': '',
        'deptRemark': ''
      },
      rules: {
        deptName: [
          { required: true, message: '请输入部门名称', trigger: 'blur' },
          { min: 3, max: 9, message: '长度在 3 到 9 个字符', trigger: 'blur' }
        ],
        deptRemark: [
          { required: true, message: '请输入部门描述', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    submitDept: function (val) {
      this.$refs[val].validate((valid) => {
        if (valid) {
          var _this = this
          _this.loading = true
          postRequest('http://localhost:8081/insertDeptInf', val).then(resp => {
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

</style>

<template>
  <div>
    <el-container>
      <el-main>
        <el-form label-width="auto" label-position="right" :model="jobInf" :ref="jobInf" :rules="rules">
          <el-form-item label="职位名称：" prop="jobName">
            <el-col :span="4">
              <el-input  placeholder="请输入职位名称" v-model="jobInf.jobName" clearable=""></el-input>
            </el-col>
          </el-form-item>
          <el-form-item label="详细信息:" prop="jobRemark">
            <el-col :span="4">
              <el-input placeholder="请输入详细信息" v-model="jobInf.jobRemark" clearable=""></el-input>
            </el-col>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" round="" @click="submitJob(jobInf)">添加</el-button>
            <el-button type="warning" round=""  @click.native.prevent="resetForm(jobInf)">取消</el-button>
          </el-form-item>
        </el-form>
      </el-main>
    </el-container>
  </div>
</template>

<script>import {postRequest} from '../untils/api'

export default {
  name: 'PostAdd',
  data () {
    return {
      jobInf: {
        'jobName': '',
        'jobRemark': ''
      },
      rules: {
        jobName: [
          { required: true, message: '请输入部门名称', trigger: 'blur' },
          { min: 3, max: 9, message: '长度在 3 到 9 个字符', trigger: 'blur' }
        ],
        jobRemark: [
          { required: true, message: '请输入部门描述', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    submitJob: function (val) {
      this.$refs[val].validate((valid) => {
        if (valid) {
          var _this = this
          _this.loading = true
          postRequest('http://localhost:8081/insertJobInf', val).then(resp => {
            if (resp.status === 200) {
              _this.$message({
                message: '添加职位成功',
                type: 'success'
              })
            } else {
              _this.$message({
                message: '添加职位失败',
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

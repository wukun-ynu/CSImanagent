<template>
  <div>
    <el-container>
      <el-main>
        <el-form label-width="auto" label-position="right" :model="noticeInf" :ref="noticeInf" :rules="rules">
          <el-form-item label="公告名称：" prop="title">
            <el-col :span="4">
              <el-input  placeholder="请输入公告名称" v-model="noticeInf.title" clearable=""></el-input>
            </el-col>
          </el-form-item>
          <el-form-item label="公告内容:" prop="content">
            <el-col :span="4">
              <el-input type="textarea" placeholder="请输入公告内容" v-model="noticeInf.content" clearable=""></el-input>
            </el-col>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" round="" @click.native.prevent="subNoticeInf(noticeInf)">添加</el-button>
            <el-button type="warning" round="" @click.native.prevent="resetForm(noticeInf)">取消</el-button>
          </el-form-item>
        </el-form>
      </el-main>
    </el-container>
  </div>
</template>

<script>import {postRequest} from '../untils/api'

export default {
  name: 'NoticeAdd',
  data () {
    return {
      noticeInf: {
        'userId': '',
        'title': '',
        'content': '',
        'createTime': new Date()
      },
      user: {},
      rules: {
        title: [
          { required: true, message: '请输入部门名称', trigger: 'blur' },
          { min: 1, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
        ],
        content: [
          { required: true, message: '请输入部门描述', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    subNoticeInf: function (val) {
      this.$refs[val].validate((valid) => {
        if (valid) {
          var _this = this
          _this.loading = true
          postRequest('http://localhost:8081/insertNoticeInf', val).then(resp => {
            if (resp.status === 200) {
              _this.$message({
                message: '添加公告成功',
                type: 'success'
              })
            } else {
              _this.$message({
                message: '添加公告失败',
                type: 'error'
              })
            }
          }, resp => {
            _this.loading = false
            _this.$alert('找不到服务器', '失败')
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
    postRequest('http://localhost:8081/currentUser').then(resp => {
      if (resp.status === 200) {
        _this.user = resp.data
        _this.noticeInf.userId = _this.user.userId
      } else {
        _this.$alert('查询失败')
      }
    }, resp => {
      _this.loading = false
      _this.$alert('找不到服务器', '失败')
    })
  }
}
</script>

<style scoped>

</style>

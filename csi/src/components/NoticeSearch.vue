<template>
  <div>
    <div>
      <el-form :inline="true" :model="notice">
        <el-form-item label="公告名称：">
          <el-input v-model="notice.title"  placeholder="请输入公告名称"></el-input>
        </el-form-item>
        <el-form-item label="公告内容：">
          <el-input v-model="notice.content"  placeholder="请输入内容"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" round="" @click.native.prevent="searchNotice(notice)">搜索</el-button>
          <el-button type="danger" round="" @click.native.prevent="show">删除</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div>
      <el-table ref="multipleTale" :data="tableData" tooltip-effect="dark" style="width: 100%" @selection-change="handleSelectionChange" show-overflow-tooltip="true">
        <el-table-column type="selection" >
        </el-table-column>
        <el-table-column  label="公告名称"  prop="title">
        </el-table-column>
        <el-table-column  label="公告内容" prop="content">
        </el-table-column>
        <el-table-column  label="创建时间" prop="createTime">
          <template slot-scope="scope">{{scope.row.createTime | formatDateTime}}</template>
        </el-table-column>
        <el-table-column  label="公告人"  prop="userInf.userName">
        </el-table-column>
        <el-table-column  label="操作" >
          <template slot-scope="scope">
            <el-button  @click="updateNotice(scope.row)" type="primary" size="small" icon="el-icon-edit" circle=""></el-button>
          </template>
        </el-table-column>
        <el-table-column  label="预览" >
          <template slot-scope="scope">
            <el-button  @click="preView(scope.row)"  type="info" size="small" icon="el-icon-view" circle=""></el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div>
      <el-dialog title="公告信息修改" :visible.sync="dialogFormVisible" :modal-append-to-body="false">
        <el-form :model="updateNoticeInf" label-width="auto" label-position="right" :rules="rules" :rel="updateNoticeInf">
          <el-form-item label="标题名称" prop="title">
            <el-input v-model="updateNoticeInf.title" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="公告内容" prop="content">
            <el-input v-model="updateNoticeInf.content" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="subUpdateNotice(updateNoticeInf)">确 定</el-button>
        </div>
      </el-dialog>
    </div>
    <div>
      <el-dialog title="预览公告" :visible.sync="dialogVisible" :data="oneNotice" :modal-append-to-body="false">
        <h2>{{oneNotice.title}}</h2>
        <p>{{oneNotice.content}}</p>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
        </span>
      </el-dialog>
    </div>
    <div>
      <!--<el-switch v-model="switchNum"></el-switch>-->
      <el-pagination :hide-on-single-page="switchNum" :total="count" layout="prev, pager, next, jumper, ->, total, slot" :page-size="2" :currentPage="currentPage" @current-change="handleCurrentChange"
      ></el-pagination>
    </div>
  </div>
</template>

<script>import {postRequest, postRequest1} from '../untils/api'

export default {
  name: 'NoticeSearch',
  data () {
    return {
      tableData: [],
      multipleSelection: [],
      switchNum: false,
      notice: {
        'title': '',
        'content': ''
      },
      loading: false,
      dialogFormVisible: false,
      dialogVisible: false,
      count: 0,
      currentPage: 1,
      pageSize: 2,
      searchPage: 1,
      updateNoticeInf: {},
      oneNotice: {},
      deleteInfo: [],
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
        _this.deleteInfo.push(row.noticeId)
      })
      var deleteUserInfo = {
        ids: JSON.stringify({'ids': _this.deleteInfo})
      }
      postRequest1('http://localhost:8081/deleteNoticeInf', deleteUserInfo).then(resp => {
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
      if (_this.notice != null) {
        var sub = {
          noticeInfCondition: JSON.stringify(_this.notice),
          pageIndex: _this.currentPage,
          pageSize: _this.pageSize
        }
        postRequest1('http://localhost:8081/getNoticeInf', sub).then(resp => {
          if (resp.status === 200) {
            _this.count = resp.data.count
            _this.tableData = resp.data.noticeInfList
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
        postRequest('http://localhost:8081/getNoticeInfPage', sub1).then(resp => {
          if (resp.status === 200) {
            _this.count = resp.data.count
            _this.tableData = resp.data.noticeInfList
          } else {
            _this.$alert('查询失败')
          }
        }, resp => {
          _this.loading = false
          _this.$alert('找不到服务器了', '失败')
        })
      }
    },
    searchNotice: function (val) {
      var _this = this
      _this.loading = true
      var sub = {
        'noticeInfCondition': JSON.stringify(val),
        'pageIndex': _this.searchPage,
        'pageSize': _this.pageSize
      }
      postRequest1('http://localhost:8081/getNoticeInf', sub).then(resp => {
        if (resp.status === 200) {
          _this.count = resp.data.count
          _this.tableData = resp.data.noticeInfList
        } else {
          _this.$alert('查询失败')
        }
      }, resp => {
        _this.loading = false
        _this.$alert('找不到服务器', '失败')
      })
    },
    updateNotice: function (val) {
      this.dialogFormVisible = true
      this.updateNoticeInf = val
    },
    preView: function (data) {
      this.dialogVisible = true
      this.oneNotice = data
    },
    subUpdateNotice: function (val) {
      this.$refs[val].validate((valid) => {
        if (valid) {
          var _this = this
          _this.loading = true
          delete val.userInf
          postRequest('http://localhost:8081/updateNoticeInf', val).then(resp => {
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
      this.dialogFormVisible = false
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
    _this.CurrentUser()
    var sub = {
      'pageIndex': _this.currentPage,
      'pageSize': _this.pageSize
    }
    postRequest('http://localhost:8081/getNoticeInfPage', sub).then(resp => {
      if (resp.status === 200) {
        _this.count = resp.data.count
        _this.tableData = resp.data.noticeInfList
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

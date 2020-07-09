<template>
  <div>
    <div>
    <el-form :inline="true" :model="userSearch">
      <el-form-item label="用户名：">
        <el-input v-model="userSearch.userName"  placeholder="请输入用户名"></el-input>
      </el-form-item>
      <el-form-item label="用户状态：">
        <el-select v-model="userSearch.status">
          <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" round="" @click.native.prevent="searchUser(userSearch)">搜索</el-button>
        <el-button type="danger" round="" @click.native.prevent="show">删除</el-button>
      </el-form-item>
    </el-form>
    </div>
    <div>
      <el-table ref="multipleTale" :data="tableData" tooltip-effect="dark" style="width: 100%" @selection-change="handleSelectionChange" show-overflow-tooltip="true">
        <el-table-column type="selection" >
        </el-table-column>
        <el-table-column  label="登录名"  prop="loginName">
        </el-table-column>
        <el-table-column  label="密码"  prop="password">
        </el-table-column>
        <el-table-column  label="用户名" prop="userName">
        </el-table-column>
        <el-table-column  label="状态"  prop="status">
        </el-table-column>
        <el-table-column  label="创建时间"  prop="createDate">
          <template slot-scope="scope">{{scope.row.createDate | formatDate}}</template>
        </el-table-column>
        <el-table-column  label="操作" >
          <template slot-scope="scope">
            <el-button @click.native.prevent="updateUser(scope.row)" type="primary" size="small" icon="el-icon-edit" circle=""></el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div>
      <el-dialog title="用户信息修改" :visible.sync="dialogFormVisible" :modal-append-to-body="false">
        <el-form :model="updateUserInf" label-width="auto" label-position="right" :ref="updateUserInf" :rules="rules">
          <el-form-item label="姓名" prop="userName">
            <el-input v-model="updateUserInf.userName" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="状态" prop="status">
            <el-select v-model="updateUserInf.status">
                <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="登录名" prop="loginName">
            <el-input v-model="updateUserInf.loginName" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="subUpdateUser(updateUserInf)">确 定</el-button>
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

<script>import dialogBar from './dialog.vue'
import {postRequest, postRequest1} from '../untils/api'
export default {
  components: {
    'dialog-bar': dialogBar
  },
  name: 'UserSearch',
  data () {
    return {
      loading: false,
      sendVal: false,
      content: [{
        NowTime: new Date()
      }],
      userSearch: {
        'userName': '',
        'status': ''
      },
      options: [{
        value: 1,
        label: '管理员'
      }, {
        value: 0,
        label: '普通用户'
      }, {
        value: '',
        label: '全部'
      }],
      tableData: [],
      multipleSelection: [],
      switchNum: true,
      count: 0,
      currentPage: 1,
      pageSize: 2,
      searchPage: 1,
      dialogFormVisible: false,
      updateUserInf: {},
      deleteInfo: [],
      user: {},
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
        _this.deleteInfo.push(row.userId)
      })
      var deleteUserInfo = {
        ids: JSON.stringify({'ids': _this.deleteInfo})
      }
      postRequest1('http://localhost:8081/deleteUserInf', deleteUserInfo).then(resp => {
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
    updateUser: function (val) {
      this.dialogFormVisible = true
      this.updateUserInf = val
    },
    handleSelectionChange (val) {
      this.multipleSelection = val
    },
    handleCurrentChange (val) {
      var _this = this
      _this.currentPage = val
      if (_this.userSearch != null) {
        var sub = {
          userInfCondition: JSON.stringify(_this.userSearch),
          pageIndex: _this.currentPage,
          pageSize: _this.pageSize
        }
        postRequest1('http://localhost:8081/getUserInfList', sub).then(resp => {
          if (resp.status === 200) {
            _this.count = resp.data.count
            _this.tableData = resp.data.userInfList
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
        postRequest('http://localhost:8081/UserInfPage', sub1).then(resp => {
          if (resp.status === 200) {
            _this.count = resp.data.count
            _this.tableData = resp.data.userInfList
          } else {
            _this.$alert('查询失败')
          }
        }, resp => {
          _this.loading = false
          _this.$alert('找不到服务器了', '失败')
        })
      }
    },
    searchUser: function (val) {
      var _this = this
      _this.loading = true
      var sub = {
        userInfCondition: JSON.stringify(val),
        pageIndex: _this.searchPage,
        pageSize: _this.pageSize
      }
      postRequest1('http://localhost:8081/getUserInfList', sub).then(resp => {
        if (resp.status === 200) {
          _this.count = resp.data.count
          _this.tableData = resp.data.userInfList
        } else {
          _this.$alert('查询失败')
        }
      }, resp => {
        _this.loading = false
        _this.$alert('找不到服务器了', '失败')
      })
    },
    subUpdateUser: function (val) {
      this.$refs[val].validate((valid) => {
        if (valid) {
          var _this = this
          _this.loading = true
          delete val.createDate
          delete val.sysRole
          postRequest1('http://localhost:8081/updateUserInf', val).then(resp => {
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
    deleteUser: function (deleteInfo) {
      var _this = this
      _this.loading = true
      var deleteUsrInfo = {
        'ids': deleteInfo
      }
      // getRequest('http://localhost:8081/getUserList').then(resp => {
      postRequest1('http://localhost:8081/deleteUserInf', deleteUsrInfo).then(resp => {
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
    // getRequest('http://localhost:8081/getUserList').then(resp => {
    postRequest('http://localhost:8081/UserInfPage', sub).then(resp => {
      if (resp.status === 200) {
        _this.count = resp.data.count
        _this.tableData = resp.data.userInfList
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

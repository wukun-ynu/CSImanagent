<template>
  <div>
    <div>
      <el-form :inline="true" :model="jobInf">
        <el-form-item label="职位名称：">
          <el-input v-model="jobInf.jobName"  placeholder="请输入职位名称"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" round="" @click="searchJob(jobInf)">搜索</el-button>
          <el-button type="danger" round="" @click.native.prevent="show">删除</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div>
      <el-table ref="multipleTale" :data="tableData" tooltip-effect="dark" style="width: 100%" @selection-change="handleSelectionChange" show-overflow-tooltip="true">
        <el-table-column type="selection" >
        </el-table-column>
        <el-table-column  label="职位名称"   prop="jobName">
        </el-table-column>
        <el-table-column  label="详细信息"   prop="jobRemark">
        </el-table-column>
        <el-table-column  label="操作"  >
          <template slot-scope="scope">
            <el-button @click.native.prevent="updateJob(scope.row)" type="primary" size="small" icon="el-icon-edit" circle=""></el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div>
      <el-dialog title="职位信息修改" :visible.sync="dialogFormVisible" :modal-append-to-body="false">
        <el-form :model="updateJobInf" label-width="auto" label-position="right" :rel="updateJobInf" :rules="rules">
          <el-form-item label="职位名称" prop="jobName">
            <el-input v-model="updateJobInf.jobName" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="详细描述" prop="jobRemark">
            <el-input v-model="updateJobInf.jobRemark" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="subUpdateJob(updateJobInf)">确 定</el-button>
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

<script>import {postRequest, postRequest1} from '../untils/api'

export default {
  name: 'PostSearch',
  data () {
    return {
      jobInf: {
        jobName: ''
      },
      tableData: [],
      multipleSelection: [],
      switchNum: false,
      dialogFormVisible: false,
      count: 0,
      currentPage: 1,
      pageSize: 2,
      searchPage: 1,
      updateJobInf: {},
      deleteInfo: [],
      user: {},
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
        _this.deleteInfo.push(row.jobId)
      })
      var deleteUserInfo = {
        ids: JSON.stringify({'ids': _this.deleteInfo})
      }
      postRequest1('http://localhost:8081/deleteJobInf', deleteUserInfo).then(resp => {
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
      if (_this.jobInf != null) {
        var sub = {
          jobInfCondition: JSON.stringify(_this.jobInf),
          pageIndex: _this.currentPage,
          pageSize: _this.pageSize
        }
        postRequest1('http://localhost:8081/getJobInf', sub).then(resp => {
          if (resp.status === 200) {
            _this.count = resp.data.count
            _this.tableData = resp.data.jobInfList
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
        postRequest('http://localhost:8081/getJobInfList', sub1).then(resp => {
          if (resp.status === 200) {
            _this.count = resp.data.count
            _this.tableData = resp.data.jobInfList
          } else {
            _this.$alert('查询失败')
          }
        }, resp => {
          _this.loading = false
          _this.$alert('找不到服务器了', '失败')
        })
      }
    },
    searchJob: function (val) {
      var _this = this
      _this.loading = true
      var sub = {
        jobInfCondition: JSON.stringify(val),
        pageIndex: _this.searchPage,
        pageSize: _this.pageSize
      }
      postRequest('http://localhost:8081/getJobInf', sub).then(resp => {
        if (resp.status === 200) {
          _this.count = resp.data.count
          _this.tableData = resp.data.jobInfList
        } else {
          _this.$alert('查询失败')
        }
      }, resp => {
        _this.loading = false
        _this.$alert('找不到服务器了', '失败')
      })
    },
    updateJob: function (val) {
      this.dialogFormVisible = true
      this.updateJobInf = val
    },
    subUpdateJob: function (val) {
      this.$refs[val].validate((valid) => {
        if (valid) {
          var _this = this
          _this.loading = true
          postRequest1('http://localhost:8081/updateJobInf', val).then(resp => {
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
    postRequest('http://localhost:8081/getJobInfList', sub).then(resp => {
      if (resp.status === 200) {
        _this.count = resp.data.count
        _this.tableData = resp.data.jobInfList
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

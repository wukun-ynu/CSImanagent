<template>
  <div>
    <div>
      <el-form :inline="true" :model="documentInf">
        <el-form-item label="标题：">
          <el-input v-model="documentInf.title"  placeholder="请输入标题"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" round="" @click="searchDoc(documentInf)">搜索</el-button>
          <el-button type="danger" round="" @click.native.prevent="show">删除</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div>
      <el-table ref="multipleTale" :data="tableData" tooltip-effect="dark" style="width: 100%" @selection-change="handleSelectionChange" show-overflow-tooltip="true">
        <el-table-column type="selection" >
        </el-table-column>
        <el-table-column  label="标题"  prop="title">
        </el-table-column>
        <el-table-column  label="创建时间"  prop="createTime">
          <template slot-scope="scope">{{scope.row.createTime | formatDate}}</template>
        </el-table-column>
        <el-table-column  label="创建人" prop="userInf.userName">
        </el-table-column>
        <el-table-column  label="描述"  prop="remark">
        </el-table-column>
        <el-table-column  label="操作" >
          <template slot-scope="scope">
            <el-button @click.native.prevent="updateDoc(scope.row)" type="primary" size="small" icon="el-icon-edit" circle=""></el-button>
          </template>
        </el-table-column>
        <el-table-column  label="下载" >
          <template slot-scope="scope">
            <a :href="fileAbouslte + scope.row.fileName" rel="external nofollow" download=""><el-button  type="info" size="small" icon=" " circle="" class="fa fa-download"></el-button></a>
            <!--<a :href="fileAbouslte + scope.row.fileName" download=""></a>-->
            <!--<el-button @click.native.prevent="download(scope.row)" type="info" size="small" icon=" " circle="" class="fa fa-download"></el-button>-->
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div>
      <el-dialog title="文档信息修改" :visible.sync="dialogFormVisible" :modal-append-to-body="false">
        <el-form :model="updateDocumentInf" label-width="auto" label-position="right" :ref="updateDocumentInf" :rules="rules">
          <el-form-item label="文档标题" prop="title">
            <el-input v-model="updateDocumentInf.title" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="详细描述" prop="remark">
            <el-input type="textarea" v-model="updateDocumentInf.remark" autocomplete="off"></el-input>
          </el-form-item>
          <el-upload class="upload-demo" drag="" action="" multiple=""
                     :limit="1"
                     :before-upload="beforeUpload">
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
            <div class="el-upload__tip" slot="tip">注意事项</div>
          </el-upload>
          <el-form-item>
            <el-button type="primary" @click="subUpdateDocument(updateDocumentInf)">确 定</el-button>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <!--<el-button type="primary" @click="subUpdateDocument(updateDocumentInf)">确 定</el-button>-->
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

<script>import {postRequest, postRequest1, uploadFileRequest} from '../untils/api'
export default {
  name: 'DocSearch',
  data () {
    return {
      documentInf: {
        'title': ''
      },
      fileAbouslte: '../../static/files/',
      tableData: [],
      multipleSelection: [],
      switchNum: false,
      dialogFormVisible: false,
      count: 0,
      currentPage: 1,
      pageSize: 2,
      searchPage: 1,
      updateDocumentInf: {},
      fileName: '',
      deleteInfo: [],
      user: {},
      rules: {
        title: [
          { required: true, message: '请输入部门名称', trigger: 'blur' },
          { min: 1, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
        ],
        remark: [
          { required: true, message: '请输入部门描述', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    deleteRow (index, rows) {
      rows.splice(index, 1)
    },
    download: function () {
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
        _this.deleteInfo.push(row.docId)
      })
      var deleteUserInfo = {
        ids: JSON.stringify({'ids': _this.deleteInfo})
      }
      postRequest1('http://localhost:8081/deleteDocumentInf', deleteUserInfo).then(resp => {
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
      if (_this.documentInf != null) {
        var sub = {
          documentInfCondition: JSON.stringify(_this.documentInf),
          pageIndex: _this.currentPage,
          pageSize: _this.pageSize
        }
        postRequest1('http://localhost:8081/getDocumentInf', sub).then(resp => {
          if (resp.status === 200) {
            _this.count = resp.data.count
            _this.tableData = resp.data.documentInfList
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
        postRequest('http://localhost:8081/getDocumentInfPage', sub1).then(resp => {
          if (resp.status === 200) {
            _this.count = resp.data.count
            _this.tableData = resp.data.documentInfList
          } else {
            _this.$alert('查询失败')
          }
        }, resp => {
          _this.loading = false
          _this.$alert('找不到服务器了', '失败')
        })
      }
    },
    searchDoc: function (val) {
      var _this = this
      _this.loading = true
      var sub = {
        documentInfCondition: JSON.stringify(val),
        pageIndex: _this.searchPage,
        pageSize: _this.pageSize
      }
      postRequest('http://localhost:8081/getDocumentInf', sub).then(resp => {
        if (resp.status === 200) {
          _this.count = resp.data.count
          _this.tableData = resp.data.documentInfList
        } else {
          _this.$alert('查询失败')
        }
      }, resp => {
        _this.loading = false
        _this.$alert('找不到服务器了', '失败')
      })
    },
    updateDoc: function (val) {
      this.dialogFormVisible = true
      this.updateDocumentInf = val
    },
    beforeUpload (file) {
      console.log(file, '文件')
      this.files = file
      const extension = file.name.split('.')[1] !== 'gif'
      const extension2 = file.name.split('.')[1] !== 'jpg'
      const isLt2M = file.size / 1024 / 1024 < 5
      if (!extension && !extension2) {
        this.$message.warning('上传模板可以能是pdf, ppt, doc, mp4, mp3格式!')
        return
      }
      if (!isLt2M) {
        this.$message.warning('上传模板大小不能超过 5MB!')
        return
      }
      this.fileName = file.name
      this.documentInf.fileName = file.name
      this.documentInf.filePath = 'D:\\vue\\csi\\static\\files\\' + this.documentInf.fileName
      return false // 返回false不会自动上传
    },
    subUpdateDocument (val) {
      this.$refs[val].validate((valid) => {
        if (valid) {
          var _this = this
          console.log('上传' + this.files.name)
          if (this.fileName === '') {
            this.$message.warning('请选择要上传的文件！')
            return false
          }
          let fileFormData = new FormData()
          // filename是键，file是值，就是要传的文件，test.zip是要传的文件名
          fileFormData.append('file', this.files, this.fileName)
          uploadFileRequest('http://localhost:8081/file/upload', fileFormData).then((res) => {
            if (res && res.status === 200) {
              delete val.userInf
              delete val.createTime
              postRequest('http://localhost:8081/updateDocumentInf', val).then(resp => {
                if (resp.status === 200) {
                  _this.$message({
                    message: '文件上传成功',
                    type: 'success'
                  })
                } else {
                  _this.$alert('更新失败')
                }
              }, resp => {
                _this.loading = false
                _this.$alert('找不到服务器了', '失败')
              })
              _this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1500,
                onClose: () => {
                  _this.visible = false
                  _this.$emit('refreshDataList')
                }
              })
            } else {
              _this.$message.error(res.msg)
            }
          }, resp => {
            _this.$alert('找不到服务器⊙﹏⊙∥!', '1失败!')
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
    _this.CurrentUser()
    var sub = {
      'pageIndex': _this.currentPage,
      'pageSize': _this.pageSize
    }
    postRequest('http://localhost:8081/getDocumentInfPage', sub).then(resp => {
      if (resp.status === 200) {
        _this.count = resp.data.count
        _this.tableData = resp.data.documentInfList
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

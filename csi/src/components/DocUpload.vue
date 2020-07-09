<template>
  <div>
    <el-container>
      <el-main>
        <el-form  label-position="left" label-width="auto" :model="documentInf" :ref="documentInf" :rules="rules">
          <el-form-item label="文档标题：" prop="title">
              <el-input  placeholder="请输入文档标题" v-model="documentInf.title" clearable=""></el-input>
          </el-form-item>
          <el-form-item label=" 文件描述:" prop="remark">
              <el-input type="textarea" placeholder="请输入文件描述" v-model="documentInf.remark" clearable="" autosize=""></el-input>
          </el-form-item>
          <el-upload class="upload-demo" drag="" action="" multiple=""
                     :limit="1"
                     :before-upload="beforeUpload">
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
            <div class="el-upload__tip" slot="tip">注意事项</div>
          </el-upload>
          <el-form-item>
            <el-button type="primary" round="" @click="submitUpload(documentInf)">添加</el-button>
            <el-button type="warning" round="" @click="visible = false" @click.native.prevent="resetForm(documentInf)">取消</el-button>
          </el-form-item>
        </el-form>
      </el-main>
    </el-container>
    <!--<div style="margin-top: 100px ">-->
      <!--<el-upload-->
        <!--class="upload-demo"-->
        <!--ref="upload"-->
        <!--action="doUpload"-->
        <!--:limit="1"-->
        <!--:file-list="fileList"-->
        <!--:before-upload="beforeUpload">-->
        <!--<el-button slot="trigger" size="small" type="primary">选取文件</el-button>-->
        <!--&lt;!&ndash; <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传到服务器</el-button> &ndash;&gt;-->
        <!--<div slot="tip" class="el-upload__tip">上传文件，且不超过5MB</div>-->
        <!--<div slot="tip" class="el-upload-list__item-name">{{fileName}}</div>-->
      <!--</el-upload>-->
      <!--<span slot="footer" class="dialog-footer">-->
 <!--<el-button @click="visible = false">取消</el-button>-->
 <!--<el-button type="primary" @click="submitUpload()">确定</el-button>-->
<!--</span>-->
    <!--</div>-->
  </div>
</template>

<script>import {uploadFileRequest, postRequest} from '../untils/api'
export default {
  name: 'DocUpload',
  data () {
    return {
      fileName: '',
      documentInf: {
        'userId': '',
        'title': '',
        'fileName': '',
        'remark': '',
        'createTime': new Date(),
        'filePath': ''
      },
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
    beforeUpload (file) {
      this.$alert('sure')
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
    submitUpload (val) {
      this.$refs[val].validate((valid) => {
        if (valid) {
          var _this = this
          this.$alert('upLoad')
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
              postRequest('http://localhost:8081/insertDocumentInf', val).then(resp => {
                if (resp.status === 200) {
                  _this.$alert('插入信息成功')
                } else {
                  _this.$alert('查询失败')
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
            _this.$alert(this.fileName)
            _this.$alert('找不到服务器⊙﹏⊙∥!', '1失败!')
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
        _this.documentInf.userId = _this.user.userId
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

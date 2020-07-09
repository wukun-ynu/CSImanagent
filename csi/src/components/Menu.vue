<template>
  <div>
    <div class="fix">
      <el-container>
        <el-header>
          <el-row :gutter="20">
            <el-col :span="6"><a href="#">
              <span style="font-size: 36px; color: #22dd80">CSI员工</span><span style="font-size: 36px; color: #f39f13">之家</span>
            </a></el-col>
            <el-col :span="4" :offset="4"><a href="#"><span>欢迎登录:</span><span>{{user.userName}}</span></a></el-col>
            <el-col :span="4"><a href="#"><i class="fa fa-clock-o"></i><span>{{NowTime|formatDateTime}}</span></a></el-col>
            <el-col :span="2"><a href="#" @click="updatePassword"><i  class="fa fa-cog"></i><span>修改密码</span></a></el-col>
            <el-col :span="2"><a href="#" @click="face"><i class="fa fa-cog"></i><span>人脸注册</span></a></el-col>
            <el-col :span="2"><a href="#" @click="logout"><i class="fa fa-sign-out"></i><span>退出登录</span></a></el-col>
          </el-row>
        </el-header>
        <el-container>
          <el-aside width="200px">
            <vue-scroll :ops="ops">
            <el-menu default-active="0"
            class="el-menu-vertical-demo"  background-color="#1e1c1c" text-color="#ececec"  router>
              <template v-for="(item,index) in this.$router.options.routes" v-if="!item.hidden">
                <el-submenu :index="index+ ''" v-if="item.children.length>1" :key="index">
                  <template slot="title">
                   <i :class="item.iconCls" aria-hidden="true"></i>
                    <span>{{item.name}}</span>
                  </template>
                  <el-menu-item v-for="child in item.children" v-if="!child.hidden" :index="child.path" :key="child.path">
                  {{child.name}}
                  </el-menu-item>
                </el-submenu>
                <template v-else>
                  <el-menu-item :key="item.children[0].path" :index="item.children[0].path">
                    <i :class="item.children[0].name"></i>
                    <span solt="title">{{item.children[0].name}}</span>
                  </el-menu-item>
                </template>
              </template>
            </el-menu>
            </vue-scroll>
          </el-aside>
          <el-main>
              <el-breadcrumb separator-class="el-icon-arrow-right">
                <el-breadcrumb-item :to="{ path: '/menu' }">首页</el-breadcrumb-item>
                <el-breadcrumb-item v-text="this.$router.currentRoute.name"></el-breadcrumb-item>
              </el-breadcrumb>
              <keep-alive>
                <router-view v-if="this.$route.meta.keepAlive"></router-view>
              </keep-alive>
              <router-view v-if="!this.$route.meta.keepAlive"></router-view>
          </el-main>
        </el-container>
      </el-container>
    </div>
    <el-dialog title="密码修改" :visible.sync="dialogFormVisible" :modal-append-to-body="false">
      <el-form  label-width="auto" label-position="right" :model="passwordForm" status-icon=""
      :rules="rules" ref="passwordForm">
        <el-form-item label="新的密码" prop="pass">
          <el-input v-model="passwordForm.pass" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="checkPass">
          <el-input v-model="passwordForm.checkPass" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="旧密码" prop="oldPass">
          <el-input v-model="passwordForm.oldPass" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button  @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitForm(passwordForm)">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>import {postRequest} from '../untils/api'

export default {
  data () {
    var checkOldPass = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('旧密码不能为空'))
      }
      setTimeout(() => {
        if (value !== this.user.password) {
          callback(new Error('输入密码错误'))
        } else {
          this.right = true
          callback()
        }
      }, 1000)
    }
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        if (this.passwordForm.checkPass !== '') {
          this.$refs.passwordForm.validateField('checkPass')
        }
        callback()
      }
    }
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.passwordForm.pass) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        this.checkPassWord = true
        callback()
      }
    }
    return {
      ops: {
      },
      right: false,
      checkPassWord: false,
      NowTime: new Date(),
      user: {},
      userInf: {
        'userId': '',
        'password': ''
      },
      loading: false,
      dialogFormVisible: false,
      passwordForm: {
        pass: '',
        checkPass: '',
        oldPass: ''
      },
      rules: {
        pass: [
          { validator: validatePass, trigger: 'blur' }
        ],
        checkPass: [
          { validator: validatePass2, trigger: 'blur' }
        ],
        oldPass: [
          { validator: checkOldPass, trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    updatePassword: function () {
      this.dialogFormVisible = true
    },
    submitForm: function (val) {
      var _this = this
      _this.loading = true
      if (_this.right === false && _this.checkPassWord === false) {
        return false
      } else {
        _this.userInf.password = val.pass
        _this.userInf.userId = _this.user.userId
        postRequest('http://localhost:8081/updateUserInf', _this.userInf).then(resp => {
          if (resp.status === 200) {
            _this.$message({
              message: '密码更新成功',
              type: 'success'
            })
            _this.right = false
            _this.checkPassWord = false
            _this.$router.replace({path: '/'})
          } else {
            _this.$alert('查询失败')
          }
        }, resp => {
          _this.loading = false
          _this.$alert('找不到服务器', '失败')
        })
      }
    },
    face: function () {
      this.$router.replace({path: '/face'})
    },
    logout: function () {
      this.$router.replace({path: '/'})
    }
  },
  mounted: function () {
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
}
</script>

<style scoped>
.fix{
  position: fixed;
  width: 100%;
  height: 100%;
  left: 0;
  top: 0;
}
.el-container{
  width: 100%;
  height: 100%;
  color: #ececec;
}
  .el-header{
    background: #1e1c1c;
    display: flex;
    align-items: center;
  }
  a{
    text-decoration: none;
    color: #ececec;
  }
.el-header .el-row{
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}
  .el-aside{
    background: #1e1c1c;
    overflow: auto;
  }
  .el-menu-item.is-active {
    background-color: #22dd80 !important;
  }
  .el-menu-item:hover{
    background-color: #22dd80 !important;
  }
</style>

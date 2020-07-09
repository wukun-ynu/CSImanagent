<template>
   <div class="bg_fix">
       <vue-particles
         color="#32C640"
         :linesWidth="2"
         :particlesNumber="100"
         class="lizi"
       >
       </vue-particles>
     <div class="login">
       <el-form :rules="rules" class="login-container" label-position="left" label-width="0px"  v-loading="loading">
         <span class="login_title">CSI员工</span>
         <span class="login_title_last">之家</span>
         <el-form-item prop="acccount">
           <el-input type="text" v-model="loginFrom.userName" auto-complete="off" placeholder="请输入用户名">
             <template slot="prepend"><i class="fa fa-user"></i></template>
           </el-input>
         </el-form-item>
         <el-form-item prop="checkPass">
           <el-input type="password" v-model="loginFrom.password" auto-complete="off" placeholder="请输入密码" >
             <template slot="prepend"><i class="fa fa-key"></i></template>
           </el-input>
         </el-form-item>
         <el-checkbox class="login_remember" v-model="checked" label-position="right">记住密码</el-checkbox>
         <el-form-item style="width: 100%">
           <!--@click.native.prevent阻止默认行为-->
           <!--<el-button type="primary" @click.native.prevent="sumbmitClick" style="width: 100%">登录</el-button>-->
           <el-row :gutter="20">
             <el-col :span="12"><div class="grid-content bg-purple">
               <el-button type="primary" @click.native.prevent="sumbmitClick" style="width: 100%">登录</el-button>
             </div></el-col>
             <el-col :span="12"><div class="grid-content bg-purple">
               <el-button type="primary" style="width: 100%"  @click.native.prevent="face">刷脸</el-button>
             </div></el-col>
           </el-row>
         </el-form-item>
       </el-form>
     </div>
   </div>
</template>

<script>import {postRequest} from '../untils/api'
export default {
  name: 'HelloWorld',
  mounted: function () {
  },
  data () {
    return {
      rules: {
        account: [{required: true, message: '请输入用户名', trigger: 'blur'}],
        checkPass: [{required: true, message: '请输入密码', trigger: 'blur'}]
      },
      checked: true,
      loginFrom: {
        userName: 'hugea',
        password: '123456'
      },
      loading: false
    }
  },
  methods: {
    sumbmitClick: function () {
      //   this.loading = true
      //   if (this.loginFrom.username === 'wukun' && this.loginFrom.password === '0305wukun') {
      //     this.$router.replace({path: '/menu'})
      //   } else {
      //     this.$router.replace({path: '/'})
      //   }
      // },
      // next () {
      //   if (this.active++ > 2) this.active = 0
      // }
      var _this = this
      this.loading = true
      postRequest('http://localhost:8081/user', {
        userName: this.loginFrom.userName,
        password: this.loginFrom.password
      }).then(resp => {
        if (resp.status === 200) {
          if (resp.data.password === this.loginFrom.password) {
            _this.$router.replace({path: 'menu'})
          } else {
            _this.$alert('用户名或密码错误')
            _this.loading = false
          }
        }
      }, resp => {
        _this.loading = false
        _this.$alert('找不到服务器⊙﹏⊙∥!', '失败!')
      })
    },
    next () {
      if (this.active++ > 2) this.active = 0
    },
    face: function () {
      this.$router.replace({path: 'face'})
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.bg_fix{
  position: fixed;
  width: 100%;
  height: 100%;
  left: 0;
  top: 0;
  z-index: -2;
}
  .lizi{
    position: fixed;
    width: 100%;
    height: 100%;
    left: 0;
    top: 0;
    background: url("../assets/login_bg.jpg") center center;
    -webkit-background-size: cover;
    background-size: cover;
    z-index: -1;
  }
  .login{
    margin-top: 200px;
  }
.login-container {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 0 auto;
  width: 350px;
  padding: 35px 35px 15px 35px;
  background: rgba(22, 22, 22, 0.8);
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}

.login_title {
  display: inline-block;
  font-size: 36px;
  margin: 0px auto 40px auto;
  text-align: center;
  color: #0cf8ff;
}
.login_title_last{
  display: inline-block;
  font-size: 36px;
  color: #d0c50d;
}

.login_remember {
  margin: 0px 0px 35px 0px;
  text-align: left;
}
.bottom_bg{
  position: fixed;
  bottom: 0;
  width: 100%;
  height: 300px;
  background: #30d3ff;
  z-index: -1;
  text-align: center;
}
.bottom_bg p{
  font-size: 36px;
}
.bottom_bg p:nth-child(1){
  margin-top: 100px;
}
</style>

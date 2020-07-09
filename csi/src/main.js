// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import VueParticles from 'vue-particles'
// 引用滚动条
import vuescroll from 'vuescroll/dist/vuescroll-native'
import 'vuescroll/dist/vuescroll.css'
import './untils/filter_time'
import axios from 'axios'
import 'font-awesome/css/font-awesome.min.css'

Vue.use(vuescroll)
Vue.use(VueParticles)
Vue.use(ElementUI)
Vue.config.productionTip = false

// Cookie丢失
axios.defaults.withCredentials = true

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})

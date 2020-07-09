import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import FaceLogin from '@/components/face_login'
import Menu from '@/components/Menu'
import UserSearch from '@/components/UserSearch'
import UserAdd from '@/components/UserAdd'
import BranchSearch from '@/components/BranchSearch'
import BranchAdd from '@/components/BranchAdd'
import PostSearch from '@/components/PostSearch'
import PostAdd from '@/components/PostAdd'
import StaffSearch from '@/components/StaffSearch'
import StaffAdd from '@/components/StaffAdd'
import NoticeSearch from '@/components/NoticeSearch'
import NoticeAdd from '@/components/NoticeAdd'
import DocSearch from '@/components/DocSearch'
import DocUpload from '@/components/DocUpload'
import Tab from '@/components/Tab'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      hidden: true,
      component: HelloWorld
    },
    {
      path: '/tab',
      name: 'tab',
      hidden: true,
      component: Tab
    },
    {
      path: '/face',
      name: 'face',
      hidden: true,
      component: FaceLogin
    },
    {
      path: '/menu',
      name: 'menu',
      hidden: true,
      component: Menu
    },
    {
      path: '/menu',
      name: '用户管理',
      iconCls: 'fa fa-user-o',
      component: Menu,
      children: [{
        path: '/UserSearch',
        name: '用户查询',
        component: UserSearch
      }, {
        path: '/UserAdd',
        name: '添加用户',
        component: UserAdd
      }]
    },
    {
      path: '/menu',
      name: '部门管理',
      iconCls: 'fa fa-modx',
      component: Menu,
      children: [{
        path: '/BranchSearch',
        name: '部门查询',
        component: BranchSearch
      }, {
        path: '/BranchAdd',
        name: '添加部门',
        component: BranchAdd
      }]
    },
    {
      path: '/menu',
      name: '职位管理',
      iconCls: 'fa fa-optin-monster',
      component: Menu,
      children: [{
        path: '/PostSearch',
        name: '职位查询',
        component: PostSearch
      }, {
        path: '/PostAdd',
        name: '添加职位',
        component: PostAdd
      }]
    },
    {
      path: '/menu',
      name: '员工管理',
      iconCls: 'fa fa-id-card-o',
      component: Menu,
      children: [{
        path: '/StaffSearch',
        name: '员工查询',
        component: StaffSearch
      }, {
        path: '/StaffAdd',
        name: '添加员工',
        component: StaffAdd
      }]
    },
    {
      path: '/menu',
      name: '公告管理',
      iconCls: 'fa fa-commenting-o',
      component: Menu,
      children: [{
        path: '/NoticeSearch',
        name: '公告查询',
        component: NoticeSearch
      }, {
        path: '/NoticeAdd',
        name: '添加公告',
        component: NoticeAdd
      }]
    },
    {
      path: '/menu',
      name: '下载中心',
      iconCls: 'fa fa-download',
      component: Menu,
      children: [{
        path: '/DocSearch',
        name: '文档查询',
        component: DocSearch
      }, {
        path: '/DocUpload',
        name: '上传文档',
        component: DocUpload
      }]
    }
  ]
})

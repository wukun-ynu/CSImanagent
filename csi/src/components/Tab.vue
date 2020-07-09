<template>
  <div class="fix">
    <el-container>
      <el-header>
        <el-row :gutter="20">
          <el-col :span="6"><a href="#">
            <span style="font-size: 36px; color: #22dd80">CSI员工</span><span style="font-size: 36px; color: #f39f13">之家</span>
          </a></el-col>
          <el-col :span="4" :offset="4"><a href="#"><span>欢迎登录:</span><span>{{CurrentName}}</span></a></el-col>
          <el-col :span="4"><a href="#"><i class="fa fa-clock-o"></i><span>{{NowTime|formatDateTime}}</span></a></el-col>
          <el-col :span="2"><a href="#"><i class="fa fa-cog"></i><span>修改密码</span></a></el-col>
          <el-col :span="2"><a href="#"><i class="fa fa-cog"></i><span>人脸注册</span></a></el-col>
          <el-col :span="2"><a href="#"><i class="fa fa-sign-out"></i><span>退出登录</span></a></el-col>
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
                  <el-menu-item v-for="child in item.children" v-if="!child.hidden" :index="child.path" :key="child.path" @click="addTab(editableTabsValue)">
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
          <div>
            <el-tabs v-model="activeIndex" type="border-card" closable=""  v-if="openTab.length" @tab-click="tabClick" @tab-remove="tabRemoe">
              <el-tab-pane
                v-for="item in  openTab"
                :key="item.name"
                :label="item.title"
                :name="item.route">
              </el-tab-pane>
            </el-tabs>
          </div>
         <div>
           <keep-alive>
             <router-view v-if="this.$route.meta.keepAlive"></router-view>
           </keep-alive>
           <router-view v-if="!this.$route.meta.keepAlive"></router-view>
         </div>
        </el-main>
      </el-container>
    </el-container>
  </div>
    <!--<div>-->
      <!--<div>-->
        <!--<el-button-->
          <!--size="small"-->
          <!--@click="addTab(editableTabsValue)">-->
          <!--add tab-->
        <!--</el-button>-->
      <!--</div>-->
      <!--<div>-->
        <!--<el-tabs v-model="editableTabsValue" type="card" closable="" @tab-remove="removeTab">-->
          <!--<el-tab-pane-->
            <!--v-for="item in editableTabs"-->
            <!--:key="item.name"-->
            <!--:label="item.title"-->
            <!--:name="item.name">-->
            <!--{{item.content}}-->
          <!--</el-tab-pane>-->
        <!--</el-tabs>-->
      <!--</div>-->
    <!--</div>-->
</template>

<script>export default {
  name: 'Tab',
  data () {
    return {
      ops: {
      },
      NowTime: new Date(),
      CurrentName: 'wukun'
    }
  },
  methods: {
    tabClick (tab) {
      console.log('tab', tab)
      console.log('active', this.activeIndex)
      this.$router.push({path: this.activeIndex})
    },
    tabRemove (targetName) {
      console.log('tabRemove', targetName)
    },
    addTab (targetName) {
      let newTabName = ++this.tabIndex + ''
      this.editableTabs.push({
        title: 'new tab',
        name: newTabName,
        content: 'new Tab content'
      })
      this.editableTabsValue = newTabName
    },
    removeTab (targetName) {
      let tabs = this.editableTabs
      let activeName = this.editableTabsValue
      if (activeName === targetName) {
        tabs.forEach((tab, index) => {
          if (tab.name === targetName) {
            let nextTab = tabs[index + 1] || tabs[index - 1]
            if (nextTab) {
              activeName = nextTab.name
            }
          }
        })
      }
      this.editableTabsValue = activeName
      this.editableTabs = tabs.filter(tab => tab.name !== targetName)
    }
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

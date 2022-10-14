import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.config.productionTip = false
Vue.use(ElementUI);
import {Message} from 'element-ui';

//插件
import {postKeyValueRequest} from "@/utils/api";
import {postRequest} from "@/utils/api";
import {getRequest} from "@/utils/api";
import {putRequest} from "@/utils/api";
import {deleteRequest} from "@/utils/api";
import {initMenu} from "@/utils/menus";
import message from "element-ui/packages/message";
import "font-awesome/css/font-awesome.min.css"
Vue.prototype.postRequest = postRequest;
Vue.prototype.postKeyValueRequest = postKeyValueRequest;
Vue.prototype.putRequest = putRequest;
Vue.prototype.deleteRequest = deleteRequest;
Vue.prototype.getRequest = getRequest;

//路由守卫
router.beforeEach((to,from,next)=>{
  if(to.path == '/'){
    next();
  }else {
    if(window.sessionStorage.getItem("user")){
      initMenu(router,store);
      next();
    }else {
      Message.error({message:"请先登录!"})
      next("/");
    }

  }
})
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')

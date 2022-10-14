import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    routers:[]
  },
  getters: {
  },
  mutations: {
    initRoutes(state,data){
      state.routers = data;
    }
  },
  actions: {
  },
  modules: {
  }
})

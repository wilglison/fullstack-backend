import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import router from './router'
import 'bootstrap/dist/css/bootstrap.css'

Vue.use(VueRouter)

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')

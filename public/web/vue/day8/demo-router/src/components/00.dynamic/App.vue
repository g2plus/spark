<template>
  <div>
    <h1>App 根组件</h1>
    <!--点击修改comName的值，决定挂载什么组件-->
    <button type="button" class="btn btn-primary" @click="comName = 'MyHome'">Home</button>
    <button type="button" class="btn btn-info ml-2" @click="comName = 'MyMovie'">Movie</button>
    <button type="button" class="btn btn-info ml-2" @click="comName = 'MyAbout'">About</button>
    <hr />
    <keep-alive>
      <component :is="comName"></component>
    </keep-alive>
  </div>

</template>

<script>
import MyHome from './MyHome.vue'
import MyMovie from './MyMovie.vue'
import MyAbout from './MyAbout.vue'

export default {
  name: 'MyApp',
  data() {
    return {
      //默认显示页面为MyHome组件内容
      comName: 'MyHome',
    }
  },
  created() {
    //debugger
  },
  components: {
    MyHome,
    MyMovie,
    MyAbout,
  },
}
</script>

<style></style>

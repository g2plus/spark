<template>
  <div>
    <h1>App 根组件</h1>
    <a href="#/home">Home</a>&nbsp;<a href="#/movie">Movie</a>&nbsp;<a href="#/about">About</a>&nbsp;
    <hr/>
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
    // 监听 hash 值变化的事件
    window.onhashchange = () => {
      // 通过 location.hash 获取到最新的hash值，并进行匹配
      switch (location.hash) {
        case '#/home':
          this.comName = 'MyHome'
          break
        case '#/movie':
          this.comName = 'MyMovie'
          break
        case '#/about':
          this.comName = 'MyAbout'
          break
      }
    }
  },
  components: {
    MyHome,
    MyMovie,
    MyAbout,
  },
}
</script>

<style></style>

<template>
  <div class="app-container">
    <!--header-->
    <my-header title="demo" color="white" bgcolor="#0078D7"></my-header>
    <!--数据主体-->
    <fruit-list></fruit-list>
  </div>
</template>

<script>
import MyHeader from './components/my-header/MyHeader.vue'
import FruitList from './components/fruit-list/FruitList.vue'

export default {
  name: 'MyApp',
  components: {
    MyHeader,
    FruitList,
  },
}
</script>

<style lang="less" scoped>
.app-container {
  padding-top: 45px;
}
</style>

<template>
  <div>
    <h1>app根组件</h1>
    <hr/>
    <!--调用自定义事件-->
    <my-counter @countAdd="getCount"></my-counter>
  </div>
</template>

<script>
import MyCounter from './Counter.vue'

export default {
  name: 'MyApp',
  methods: {
    getCount(val) {
      console.log('触发了 countChange 自定义事件', val)
    },
  },
  components: {
    MyCounter,
  },
}
</script>

<style></style>

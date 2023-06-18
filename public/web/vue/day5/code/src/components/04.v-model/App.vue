<template>
  <div>
    <h1>App 根组件  ---- {{count}}</h1>
    <button @click="count += 1">+1</button>
    <button @click="count -= 1">-1</button>
    <hr />
    <!--v-model数据通信-->
    <my-counter v-model:number="count"></my-counter>
  </div>
</template>

<script>
import MyCounter from './Counter.vue'

export default {
  name: 'MyApp',
  data() {
    return {
      count: 0,
    }
  },
  components: {
    MyCounter
  }
}
</script>

<style></style>

<template>
  <div>
    <h1 ref="myh1">App 根组件</h1>
    <hr />

    <button type="button" class="btn btn-primary" @click="getRefs">获取 $refs 引用</button>

    <my-counter ref="counterRef"></my-counter>
  </div>
</template>

<script>
// 导入组件
import MyCounter from './Counter.vue'

export default {
  name: 'MyApp',
  methods: {
    getRefs() {
      console.log(this.$refs)
      //修改引用dom的字体演示
      this.$refs.myh1.style.color = 'red'
      //修改背景颜色
      this.$refs.myh1.style.backgroundColor='blue'
      console.log(this.$refs.counterRef)
      //调用对象的reset方法
      this.$refs.counterRef.reset()
    },
  },
  // 注册组件
  components: {
    MyCounter,
  },
}
</script>

<style lang="less" scoped></style>

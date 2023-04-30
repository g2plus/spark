<template>
  <h3>Home 组件 --- {{ count }}</h3>
  <button type="button" class="btn btn-primary" @click="count += 1">+1</button>
</template>

<script>
export default {
  name: 'MyHome',
  data() {
    return {
      count: 0,
    }
  },
  //创建时候调用
  created() {
    console.log('created')
  },
  //取消挂载时候调用
  unmounted() {
    console.log('unmounted')
  }
}
</script>

<style lang="less" scoped></style>

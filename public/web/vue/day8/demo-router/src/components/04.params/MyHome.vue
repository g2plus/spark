<template>
  <div>
    <h3>MyHome 组件</h3>
    <!--通过按钮设置跳转到指定的路由 默认值为1-->
    <button type="button" class="btn btn-primary" @click="goToMovie(1)">导航到Movie页面</button>
  </div>
</template>

<script>
export default {
  name: 'MyHome',
  methods: {
    goToMovie(id) {
      this.$router.push('/movie/' + id)
    },
  },
}
</script>

<style lang="less" scoped></style>

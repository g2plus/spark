<template>
  <div>
    <h3>MyHome 组件</h3>
    <!--声明路由的使用-->
    <router-link :to="{ name: 'mov', params: { mid: 2 } }">go to movie - 声明导航</router-link>
    &nbsp;
    <button type="button" class="btn btn-primary" @click="goToMovie(1)">go to movie - 编程导航</button>
  </div>
</template>

<script>
export default {
  name: 'MyHome',
  methods: {
    goToMovie(id) {
      this.$router.push({
        name: 'mov',
        params: {
          mid: id,
        },
      })
    },
  },
}
</script>

<style lang="less" scoped></style>

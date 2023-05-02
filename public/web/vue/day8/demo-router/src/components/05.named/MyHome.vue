<template>
  <div>
    <h3>MyHome 组件</h3>
    <!--参数设置跳转 声明路由
    为 <router-link> 标签动态绑定 to 属性的值，并通过 name 属性指定要跳转到的路由规则。期间还可以用
params 属性指定跳转期间要携带的路由参数-->
    <router-link :to="{ name: 'mov', params: { mid: 3 } }">go to movie</router-link>
    &nbsp;
    <button type="button" class="btn btn-primary" @click="goToMovie(1)">go to movie</button>
  </div>
</template>

<script>
export default {
  name: 'MyHome',
  methods: {
    goToMovie(id) {
      //使用函数调用进行跳转
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

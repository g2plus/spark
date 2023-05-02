<template>
  <div>
    <!--接收路由参数$route.params.mid {{}} 插值表达式-->
    <h3>MyMovie 组件 --- {{ $route.params.mid }} --- {{ mid }}</h3>
    <button type="button" class="btn btn-danger" @click="goBack">后退</button>
  </div>
</template>

<script>
export default {
  name: 'MyMovie',
  props: ['mid'],
  methods: {
    goBack() {
      //后退1步
      this.$router.go(-1)
    },
  },
}
</script>

<style lang="less" scoped></style>

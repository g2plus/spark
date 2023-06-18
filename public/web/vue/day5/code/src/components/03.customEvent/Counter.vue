<template>
  <div>
    <p>count的值是:{{ count }}</p>
    <button @click="add">+1</button>
    <!--动态组件-->
    <span><button @click="minus">-1</button></span>
  </div>
</template>

<script>
export default {
  name: 'MyCounter',
  // 1. 声明自定义事件(暴露事件)
  emits: ['countAdd', 'countMinus'],
  data() {
    return {
      count: 0,
    }
  },
  methods: {
    add() {
      this.count++
      // 2. this.$emit() 触发自定义事件 传输结果
      this.$emit('countAdd', this.count)
    },
    minus() {
      this.count--;
      if (this.count < 0) {
        console.log("reset value to zero");
        this.count = 0;
        this.$emit("countMinus", this.count);
      }
      this.$emit("countMinus", this.count);
    }
  },
}
</script>

<style></style>

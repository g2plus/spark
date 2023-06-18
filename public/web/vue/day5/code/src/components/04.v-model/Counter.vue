<template>
  <div>
    <p>count值是：{{number}}</p>
    <button @click="add">+1</button>
    <button @click="minus">-1</button>
  </div>
</template>

<script>
export default {
  name: 'MyCounter',
  props: ['number'],
  //监听number的更新事件
  emits: ['update:number'],
  methods: {
    add() {
      this.$emit('update:number', this.number + 1)
    },
    minus(){
      this.$emit('update:number', this.number - 1)
    }
  }
}
</script>

<style></style>

<template>
  <div>
    <h3>count 的值为：{{ count }}</h3>
    <button type="button" class="btn btn-primary" @click="add">+1</button>
  </div>
</template>

<script>
import bus from './eventBus.js'

export default {
  name: 'MyLeft',
  data() {
    return {
      count: 0,
    }
  },

  created() {
    bus.on('countChange', num => {
      this.count = num
    })
  },
  methods: {
    add() {
      this.count++
      bus.emit('countChange', this.count)
    },
  },
}
</script>

<style lang="less" scoped></style>

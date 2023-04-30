<template>
  <div>
    <h3>count 的值为：{{ num }}</h3>
    <button type="button" class="btn btn-primary" @click="add">+1</button>
  </div>
</template>

<script>
import bus from './eventBus.js'

export default {
  name: 'MyRight',
  data() {
    return {
      num: 0,
    }
  },
  created() {
    bus.on('countChange', count => {
      this.num = count
    })
  },
  methods:{
    add() {
      this.num++
      bus.emit('countChange', this.num)
    },
  }
}
</script>

<style lang="less" scoped></style>

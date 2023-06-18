<template>
  <div>
    <h3>标题：{{title}}</h3>
    <h5>作者：{{author}}</h5>
    <h6>发布时间：{{ dateFormat(publishTime) }}</h6>
  </div>
</template>

<script>
import {computed} from 'vue'

export default {
  name: 'MyArticle',
  // 外界可以传递指定的数据，到当前的组件中s
  props: ['title', 'author', 'publishTime'],

  methods: {},

  setup() {
    debugger
    const dateFormat = computed(() => {
      return function (publishTime) {
        console.log(publishTime);
        const dt = new Date(publishTime)
        const y = dt.getFullYear()
        const m = padZeros(dt.getMonth() + 1)
        const d = padZeros(dt.getDate())
        const hh = padZeros(dt.getHours())
        const mm = padZeros(dt.getMinutes())
        const ss = padZeros(dt.getSeconds())
        return `${y}-${m}-${d} ${hh}:${mm}:${ss}`
      }
    })

    function padZeros(n) {
      return n > 9 ? n : '0' + n
    }

    return {dateFormat, padZeros}


  }


}
</script>

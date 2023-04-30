<template>
  <div>
    <h1>App 根组件 -- {{ color }}</h1>
    <button type="button" class="btn btn-primary"
            @click="toggleColor" :style="{'background-color':color}">Toggle button color</button>

    <hr />

    <level-two></level-two>
  </div>
</template>

<script>
import LevelTwo from './LevelTwo.vue'
import { computed } from 'vue'

export default {
  name: 'MyApp',
  data() {
    return {
      count:1,
      color: 'red',
    }
  },
  methods:{
    toggleColor(){
      this.count++;
      if(this.count%2==0){
        this.color="red";
      }else{
        this.color="blue";
      }

    }
  },
  provide() {
    // 返回要共享的数据对象
    return {
      color: computed(() => this.color),
      count: computed(()=>this.count)
    }
  },

  components: {
    LevelTwo,
  },
}
</script>

<style lang="less" scoped></style>

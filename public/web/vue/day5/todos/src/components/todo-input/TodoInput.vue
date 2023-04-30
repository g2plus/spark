<template>
  <form class="form-inline" @submit.prevent="onFormSubmit">
    <div class="input-group mb-2 mr-sm-2">
      <div class="input-group-prepend">
        <div class="input-group-text">任务</div>
      </div>
      <input type="text" class="form-control" placeholder="请输入任务信息" style="width: 356px" v-model.trim="taskName"/>
    </div>

    <button type="submit" class="btn btn-primary mb-2">添加新任务</button>
  </form>
</template>

<script>
export default {
  name: 'TodoInput',
  //自定义add事件
  emits: ['add'],
  data() {
    return {
      taskName: '',
    }
  },
  methods: {
    // 表单的提交事件处理函数
    onFormSubmit() {
      if (!Boolean(this.taskName)) {
        return alert("任务名称不能为空!")
      }
      //自定义事件与taskName关联
      this.$emit('add', this.taskName)
      //添加成功或将taskName设置为空
      this.taskName = ''
    },
  },
}
</script>

<style lang="less" scoped></style>

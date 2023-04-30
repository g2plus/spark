<template>
  <div>
    <h1>任务首页</h1>

    <hr />
    <!--调用自定义事件的暴露接口add-->

    <!--添加任务input框-->
    <todo-input @add="onAddNewTask"></todo-input>

    <!--任务列表-->
    <todo-list v-bind:list="taskList" class="mt-2"></todo-list>

    <!--切换tab-->
    <todo-button v-model:active="activeBtnIndex"></todo-button>


  </div>
</template>

<script>
// 导入 TodoList 组件
import TodoList from './components/todo-list/TodoList.vue'
// 导入 TodoInput 组件
import TodoInput from './components/todo-input/TodoInput.vue'
// 导入 TodoButton 组件
import TodoButton from './components/todo-button/TodoButton.vue'

export default {
  name: 'MyApp',
  data() {
    return {
      // 任务列表的数据
      todoList: [
        { id: 1, task: '周一早晨9点开会', done: false },
        { id: 2, task: '周一晚上8点聚餐', done: false },
        { id: 3, task: '准备周三上午的演讲稿', done: true },
      ],
      // 下一个可用的 Id
      nextId: 4,
      activeBtnIndex: 0,
    }
  },
  computed: {
    taskList() {
      switch(this.activeBtnIndex) {
        //根据用户选中的tab进行切换渲染页面数据
        case 0:
          return this.todoList
        case 1:
          return this.todoList.filter(x => x.done === true)
        case 2:
          return this.todoList.filter(x => x.done !== true)
      }
    }
  },
  methods: {
    onAddNewTask(taskName) {
        //添加到todolist中
        this.todoList.push({
          id: this.nextId,
          task: taskName,
          done: false,
        })
        this.nextId++
      }
  },
  components: {
    TodoList,
    TodoInput,
    TodoButton,
  },
}
</script>

<style lang="less" scoped></style>

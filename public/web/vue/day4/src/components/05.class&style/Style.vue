<template>
  <div>
    <!-- <h3 class="thin" :class="isItalic ? 'italic' : ''">MyStyle 组件</h3> -->
    <!-- <h3 class="thin" :class="[isItalic ? 'italic' : '', isDelete ? 'delete' : '']">MyStyle 组件</h3>
    <button @click="isItalic = !isItalic">Toggle Italic</button>
    <button @click="isDelete = !isDelete">Toggle Delete</button> -->

    <h3 class="thin" :class="classObj">MyStyle 组件</h3>
    <button @click="classObj.italic = !classObj.italic">Toggle Italic</button>
    <button @click="classObj.delete = !classObj.delete">Toggle Delete</button>

    <hr/>

    <div :style="{ color: active, fontSize: fntSize + 'px', 'background-color': backgroundColor}">啥也不是</div>
    <button @click="fntSize+=1">字号 +1</button>
    <button @click="fntSize-=1">字号 -1</button>
  </div>
</template>

<script>
export default {
  name: 'MyStyle',
  data() {
    return {
      // 字体是否倾斜
      isItalic: false,
      // 是否应用删除效果
      isDelete: false,
      classObj: {
        italic: true,
        delete: true,
      },
      // 高亮时的文本颜色
      active: 'red',
      // 文字的大小
      fntSize: 18,
      // 背景颜色
      backgroundColor: 'pink',
    }
  },
}
</script>

<style lang="less">
// 字体变细
.thin {
  font-weight: 200;
}

// 倾斜字体
.italic {
  font-style: italic;
}

.delete {
  text-decoration: line-through;
}
</style>

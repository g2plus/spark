<template>
  <div>
    <h1>这是 App.vue 根组件</h1>
    <hr />
    <my-article :title="info.title" :author="'post by ' + info.author" :publish-time="info.publishTime"></my-article>
  </div>
</template>

<script>
import MyArticle from './Article.vue'

export default {
  name: 'MyApp',
  data() {
    return {
      info: {
        title: '从你的全世界路过',
        author: '张嘉佳',
        publishTime:'2016'
      },
    }
  },
  components: {
    MyArticle,
  },
}
</script>

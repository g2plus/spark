<template>
  <div class="app-container">
    <es-header title="购物车案例"></es-header>
    <es-goods
      v-for="item in goodslist"
      :key="item.id"
      :id="item.id"
      :thumb="item.goods_img"
      :title="item.goods_name"
      :price="item.goods_price"
      :count="item.goods_count"
      :checked="item.goods_state"
      @stateChange="onGoodsStateChange"
      @countChange="onGoodsCountChange"
    ></es-goods>
    <es-footer :amount="amount" :total="total" @fullChange="onFullStateChange"></es-footer>
  </div>
</template>

<script>
import EsHeader from './components/es-header/EsHeader.vue'
import EsFooter from './components/es-footer/EsFooter.vue'
import EsGoods from './components/es-goods/EsGoods.vue'

export default {
  name: 'MyApp',
  data() {
    return {
      // 商品列表的数据
      goodslist: [],
    }
  },
  created() {
    this.getGoodsList()
  },
  methods: {
    // 获取商品列表数据的方法
    async getGoodsList() {
      const { data: res } = await this.$http.get('/api/cart')
      if (res.status !== 200) return alert('数据请求失败！')
      this.goodslist = res.list

    },
    // 监听选中状态变化的事件
    onFullStateChange(isFull) {
      // 调用子组件的onCheckBoxChange 然后重新渲染goodList数据 没有发起请求
      this.goodslist.forEach(x => (x.goods_state = isFull))
    },
    // 调用子组件的自定义事件stateChange事件，触发监听商品勾选状态变化的事件onGoodsStateChange
    onGoodsStateChange(e) {
      const findResult = this.goodslist.find(x => x.id === e.id)
      if (findResult) {
        findResult.goods_state = e.value
      }
    },
    // 监听商品数量变化的事件
    onGoodsCountChange(e) {
      const findResult = this.goodslist.find(x => x.id === e.id)
      if (findResult) {
        findResult.goods_count = e.value
      }
    },
  },
  computed: {
    // 已勾选商品的总价格
    amount() {
      let a = 0
      this.goodslist
        .filter(x => x.goods_state)
        .forEach(x => {
          a += x.goods_price * x.goods_count
        })

      return a
    },
    // 已勾选商品的总数量
    total() {
      let t = 0
      this.goodslist
        .filter(x => x.goods_state)
        .forEach(x => {
          t += x.goods_count
        })
      return t
    },
  },
  components: {
    EsHeader,
    EsFooter,
    EsGoods,
  },
}
//封装，暴露， 状态维护，computed,自定义事件
//TODO 修改程自己的后台接口数据
</script>

<style lang="less" scoped>
.app-container {
  padding-top: 45px;
  padding-bottom: 50px;
}
</style>

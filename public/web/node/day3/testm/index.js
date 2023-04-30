// 这是包的入口文件(多层级导入方式)
const index = require('./src/demo')
    // 向外暴露需要的成员
module.exports = {
    ...index
}
console.log('加载了 index.js')
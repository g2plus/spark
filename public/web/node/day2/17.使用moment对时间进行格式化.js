//注意：导入的名称，就是装包时候的名称
const moment = require('moment')
//convert curent time to formatted string
const dt = moment().format('YYYY-MM-DD HH:mm:ss')
console.log(dt)
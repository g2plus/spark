const tools1 = require("../time-tools/index");
const tools2 = require("../time-tools");
var dateStr1 = tools1.dateFormat(new Date());
var dateStr2 = tools2.dateFormat(new Date());
console.log(dateStr1)
console.log(dateStr2)
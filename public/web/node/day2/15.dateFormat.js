// 格式化时间函数
function dateFormat(dtStr) {
    const dt = new Date(dtStr)
    const year = dt.getFullYear()
    //外国人的月份从0开始
    const m = padZero(dt.getMonth() + 1)
    const d = padZero(dt.getDate())
    const hh = padZero(dt.getHours())
    const mm = padZero(dt.getMinutes())
    const ss = padZero(dt.getSeconds())
    //js语法级别支持解析
    return `${year}-${m}-${d} ${hh}:${mm}:${ss}`
}

//补零函数
function padZero(n) {
    return n > 9 ? n : '0' + n
}

//导出函数
module.exports = {
    dateFormat
}
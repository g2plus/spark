//导入时执行console.log("ok") 仅执行一次(模块优先从缓存中加载)
//内置的模块加载优先级最高
//自定义加载模块的方式 ./ ../ 没有标识符，当作内置或者第三方模块进行加载
// 在使用require导入自定义模块时,如果省略拓展名,node.js 按照如下顺序进行加载
// 1.按照确切的文件名加载
// 2.补全.js加载
// 3.补全.json加载
// 4.补全.node加载
// 5.加载失败

// 第三方模块的加载机制
// 尝试从node_modules文件夹中加载第三方模块,如果没有,则移动到再上一层父目录中,进行加载,知道系统的根目录
// 例如c:\test\project\test.js 文件调用require("cpc-util"),那么node按照以下顺序进行加载
// c:\test\project\node_modules\cpc-util 
// c:\test\node_modules\cpc-util
// c:\node_modules\cpc-util


//目录作为模块
// 再被加载的目录中查找package.json文件中寻找main属性,作为require()加载的入口
// 如果目录里没有package.json文件,或者main入口不存在,则node.js尝试加载目录下的index.js文件
// 如果两步失败,报告模块的缺失

require('./03.自定义模块')
require('./03.自定义模块')
require('./03.自定义模块')
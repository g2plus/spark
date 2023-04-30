JXL的基本使用

jxl的几个重要概念

工作簿 --- 使用静态方法createWorkBook()来进行创建

工作表 --- 使用工作簿对象来创建sheet，创建时，指定名称与索引

Label -- 数据存入到label对象中。三个参数：列，行，内容(列与行的索引都从0开始)

sheet.addCell()---参数label，将label添加到单元格当中

workbook.write() ---进行数据写入操作。

workbook.close() ---关闭数据流。



Apache poi的使用（今天6点开干）
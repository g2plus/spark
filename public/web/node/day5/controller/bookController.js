const server = require("../server/config")
const express = server.express;
const bookService = require("../service/bookService")
const book = express.Router();
book.get("/test0", bookService.test0)
book.get("/test1", bookService.test1)
book.get("/test2", bookService.test2)
book.get("/test5", bookService.test5)
book.get("/test6", bookService.test6)
book.get("/test7", bookService.test7)
book.get("/test8", bookService.test8)
book.get("/test9", bookService.test9);
module.exports = book;
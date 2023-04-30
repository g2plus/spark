import express from "express"
import bookService from "../service/bookService.js"
const bookController = new express.Router();
bookController.get("/test0", bookService.test0)
bookController.get("/test1", bookService.test1)
bookController.get("/test2", bookService.test2)
bookController.get("/test5", bookService.test5)
bookController.get("/test6", bookService.test6)
bookController.get("/test7", bookService.test7)
bookController.get("/test8", bookService.test8)
bookController.get("/test9", bookService.test9);
export default {
    bookController
}
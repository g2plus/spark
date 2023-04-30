package main

import "fmt"

// 这种因式分解关键字的写法一般用于声明全局变量
var x, y int
var (
	a0 int
	b0 bool
)

// iota https://blog.csdn.net/weixin_45514135/article/details/116048330
const (
	//i=1：左移 0 位，不变仍为 1。
	//j=3：左移 1 位，变为二进制 110，即 6。
	//k=3：左移 2 位，变为二进制 1100，即 12。
	//l=3：左移 3 位，变为二进制 11000，即 24。
	i1 = 1 << iota
	j1 = 3 << iota
	k1
	l1
)

const (
	a = iota //0
	b        //1
	c        //2
	d = "ha" //独立值，iota += 1
	e        //"ha"   iota += 1
	f = 100  //iota +=1
	g        //100  iota +=1
	h = iota //7,恢复计数
	i        //8
)

// 可以作为枚举
const (
	Unknown = 0
	Female  = 1
	Male    = 2
)

func main() {
	fmt.Println("Hello, World!")
	// %d 表示整型数字，%s 表示字符串
	//变量的声明 自动判断变量的数据类型
	var stockcode = 123
	var enddate = "2020-12-31"
	var url = "Code=%d&endDate=%s"
	var target_url = fmt.Sprintf(url, stockcode, enddate)
	fmt.Println(target_url)
	//https://www.runoob.com/go/go-data-types.html- 数据类型

	//变量声明并指定数据的类型
	var a string = "Runoob"
	fmt.Println(a)

	var b, c int = 1, 1
	//多个一起数据 1，2
	fmt.Println(b, c)
	fmt.Println(&b)
	fmt.Println(&c)

	//基本数据类型的标识
	var i int
	var f float64
	var bos bool
	var s string
	//https://blog.csdn.net/weixin_43874301/article/details/120948719 -go语言数据格式控制
	fmt.Printf("%v %v %v %q\n", i, f, bos, s)

	//指针
	var a1 *int
	//数组
	var a2 []int
	var a3 map[string]int
	//协程
	var a4 chan int
	var a5 func(string) int
	var a6 error // error 是接口
	fmt.Println(a1, a2, a3, a4, a5, a6)

	fmt.Println(x, y)
	fmt.Println(a0, b0)
	//var go1 int = 50
	go1 := 50
	var go2 int = 50
	fmt.Println(go1)
	fmt.Println(go2)

	_, numb, strs := numbers() //只获取函数返回值的后两个
	fmt.Println(numb, strs)

	PI, A, STR := consts()
	fmt.Println(PI, A, STR)

	fmt.Println(Unknown)

	fmt.Println("i=", i1)
	fmt.Println("j=", j1)
	fmt.Println("k=", k1)
	fmt.Println("l=", l1)

}

// go语言的函数格式
func numbers() (int, int, string) {
	a, b, c := 1, 2, "str"
	return a, b, c
}

func consts() (float64, int, string) {
	PI, A, STR := 3.14, 2, "str"
	return PI, A, STR
}

package main

import "fmt"

func main() {
	var a string = "Hello World!"
	fmt.Println(a)

	// var num1 int = 5
	// fmt.Println("Number 1:", num1)
	// var num2 int32 = 10
	// fmt.Println("Number 2:", num2)
	// fmt.Println("Sum num1 and num2:", num1+int(num2))
	// var sumNum1 int = num1 + int(num2)
	// fmt.Println("Sum stored in variable:", sumNum1)

	// haha := 5;

	var char1 byte
	char1 = 'A'
	fmt.Println("Character 1:", char1)
	var char2 rune = '世'
	fmt.Println("Character 2:", char2)

	var num3 int
	var num4 int
	var sum2 int = num3 + num4
	fmt.Println("Sum of uninitialized integers:", sum2)
	// var a,b,c int = 1,2,3
	// fmt.Println("Multiple variable declaration:", a,b,c)

	var b1 bool = true
	fmt.Println("Boolean 1:", b1)
	var b2 bool = false
	fmt.Println("Boolean 2:", b2)

	var f1 float32 = 5.67
	fmt.Println("Float 1:", f1)
	var f2 float64 = 9.87654321
	fmt.Println("Float 2:", f2)

	sum := add(5, 10)
	fmt.Println("Sum:", sum)
	midStr := middleString(a)
	fmt.Println("Middle string:", midStr)
}

func add(a int, b int) int {
	return a + b
}

func middleString(a string) string {
	n := len(a)
	mid := n / 2
	if n%2 == 0 {
		return a[mid-1 : mid+1]
	} else {
		return string(a[mid])
	}
}

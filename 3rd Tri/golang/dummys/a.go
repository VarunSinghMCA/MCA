package main

import "fmt"

func main() {
	// var for variable decalration
	var fruit1 string = "Apple" // var types: int, float32, bool, string
	var fruit2 = "Banana"
	fruit3 := "" // type is infered when by compiler when using := to assign variable

	fmt.Println(fruit1, "\n", fruit2, "\n", fruit3)

	var a, b, c int = 1, 3, 34
	fmt.Println(a, b, c)

	// var p,q = "Hello";
	// var r,s = "World!";

	// fmt.Println(p,q,r,s)
}

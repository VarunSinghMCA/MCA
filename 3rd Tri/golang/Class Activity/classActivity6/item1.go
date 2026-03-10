package main

import "fmt"

func main() {
	var num int
	fmt.Print("Enter a number: ")
	_, err := fmt.Scan(&num)
	if err != nil {
		fmt.Println("Error reading input")
		return
	}
	fmt.Println("You entered: ", num)
	//------------------------------------------
	// num2, ok := interface{}(num).(int)
	// if ok {
	// 	fmt.Println("The variable is of type int")
	// 	fmt.Println("The variable is:", num2)
	// } else {
	// 	fmt.Println("The variable is not of type int")
	// }
	//------------------------------------------
	fmt.Printf("The type of the variable is: %T\n", num)

	//------------------------------------------
	result, err := divide(10, 0)
	if err != nil {
		fmt.Println("Error:", err)
	} else {
		fmt.Println("Result of division: ", result)
	}
	//------------------------------------------

}

func divide(a int, b int) (int, error) {
	if b == 0 {
		return 0, fmt.Errorf("[Error] Division by zero is not allowed")
	}
	return a / b, nil
}

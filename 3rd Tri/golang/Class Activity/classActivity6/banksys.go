package main

import (
	"errors"
	"fmt"
)

func withdraw(balance float64, amount float64) (float64, error) {
	myError := errors.New("[Error] withdrawal amount must be greater than zero")
	if amount <= 0 {
		return balance, myError
	}
	if amount > balance {
		return balance, errors.New("[Error] insufficient funds")
	}
	return balance - amount, nil
}

func main() {
	balance := 1000.0
	balance2 := 500.0
	balance3 := 200.0

	newBalance, err := withdraw(balance, 1200)
	if err != nil {
		fmt.Println("Transaction failed:", err)
	} else {
		fmt.Println("New Balance:", newBalance)
	}

	newBalance, err = withdraw(balance2, -50)
	if err != nil {
		fmt.Println("Transaction failed:", err)
	} else {
		fmt.Println("New Balance:", newBalance)
	}

	newBalance, err = withdraw(balance3, 250)
	if err != nil {
		fmt.Println("Transaction failed:", err)
	} else {
		fmt.Println("New Balance:", newBalance)
	}

	newBalance, err = withdraw(balance3, 150)
	if err != nil {
		fmt.Println("Transaction failed:", err)
	} else {
		fmt.Println("New Balance:", newBalance)
	}
}

// Output:
// Transaction failed: [Error] insufficient funds
// Transaction failed: [Error] withdrawal amount must be greater than zero
// Transaction failed: [Error] insufficient funds
// New Balance: 50

package main

import (
	"errors"
	"fmt"
	"strings"
)

type User struct {
	Name     string
	Email    string
	Age      int
	Password string
}

func validateUser(user User) []error {
	var errs []error

	if user.Name == "" {
		errs = append(errs, errors.New("name cannot be empty"))
	} else if len(user.Name) < 3 {
		errs = append(errs, errors.New("name must be at least 3 characters"))
	}

	if user.Email == "" {
		errs = append(errs, errors.New("email cannot be empty"))
	} else if !strings.Contains(user.Email, "@") {
		errs = append(errs, errors.New("email must contain @ symbol"))
	}

	if user.Age < 18 {
		errs = append(errs, errors.New("age must be 18 or above"))
	} else if user.Age > 120 {
		errs = append(errs, errors.New("age must be less than 120"))
	}

	if user.Password == "" {
		errs = append(errs, errors.New("password cannot be empty"))
	} else if len(user.Password) < 6 {
		errs = append(errs, errors.New("password must be at least 6 characters"))
	}

	return errs
}

func printErrors(errs []error) {
	if len(errs) == 0 {
		fmt.Println("[OK] No errors found!")
		return
	}

	fmt.Printf("Found %d error(s):\n", len(errs))
	for i, err := range errs {
		fmt.Printf("  %d. %s\n", i+1, err.Error())
	}
}

func main() {
	fmt.Println("=== Test Case 1: Invalid User ===")
	user1 := User{
		Name:     "Jo",
		Email:    "invalid-email",
		Age:      15,
		Password: "123",
	}
	errs1 := validateUser(user1)
	printErrors(errs1)

	fmt.Println("\n=== Test Case 2: Valid User ===")
	user2 := User{
		Name:     "John Doe",
		Email:    "john@example.com",
		Age:      25,
		Password: "secure123",
	}
	errs2 := validateUser(user2)
	printErrors(errs2)

	fmt.Println("\n=== Test Case 3: Multiple Errors ===")
	user3 := User{
		Name:     "",
		Email:    "",
		Age:      150,
		Password: "",
	}
	errs3 := validateUser(user3)
	printErrors(errs3)
}

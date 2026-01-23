package main

import "fmt"

func main() {
	var x float64
	var v float64
	var a float64
	var m float64
	var n int
	fmt.Println("Enter initial position (x): ")
	fmt.Scan(&x)
	fmt.Println("Enter initial velocity (v): ")
	fmt.Scan(&v)
	fmt.Println("Enter acceleration (a): ")
	fmt.Scan(&a)
	fmt.Println("Enter mass (m): ")
	fmt.Scan(&m)
	fmt.Println("Enter number of time steps (n): ")
	fmt.Scan(&n)

	fmt.Println("1D Motion Simulator")

	for t := 1; t <= n; t++ {
		v = v + a
		x = x + v
		ke := 0.5 * m * v * v
		fmt.Printf("t=%d | x=%.4f | v=%.4f | KE=%.4f\n", t, x, v, ke)

		if v < 0 {
			fmt.Println("Object reversed direction")
		}
		if ke < 0.1 {
			fmt.Println("Motion nearly stopped")
		}
		if x > 20.0 {
			fmt.Println("Object left simulation boundary")
			break
		}
	}
}

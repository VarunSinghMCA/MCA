package main

import (
	"fmt"
)

func main() {
	years := []int{1900, 1947, 2000, 2012, 2023, 2024, 2100, 1985, 1975}
	items := []string{"naman", "12321", "sweets", "malayalam", "mela", "101", "isro"}

	// Task 1
	a_years := []int{}
	for i := 0; i < len(years); i++ {
		if years[i]%4 == 0 {
			if years[i]%100 == 0 {
				if years[i]%400 == 0 {
					a_years = append(a_years, years[i])
				} else {
					continue
				}
			}
			a_years = append(a_years, years[i])
		}
	}
	fmt.Println("Auspicious years are :", a_years)

	//Task 2
	map_values := make(map[string]int)
	for i := 0; i < len(items); i++ {
		for j := 0; j < len(items[i])/2; j++ {
			if items[i][j] != items[i][len(items[i])-1-j] {
				break
			}
			if j == (len(items[i])/2)-1 {
				map_values[items[i]] = len(items[i])
			}
		}
	}
	fmt.Println("Map values are :", map_values)

	//Task 3

	// gate_map := make(map[string]int)
	// for i := 0; i < len(a_years); i++ {
	// 	if i%3 == 0 {
	// 		gate_map["Gate 0"] = a_years[i]
	// 	} else if i%3 == 1 {
	// 		gate_map["Gate 1"] = a_years[i]
	// 	} else {
	// 		gate_map["Gate 2"] = a_years[i]
	// 	}
	// }
	// fmt.Print("Gate map is :", gate_map)

	gate_map := make(map[string][]int)
	for i := 0; i < len(a_years); i++ {
		if i%3 == 0 {
			gate_map["Gate 0"] = append(gate_map["Gate 0"], a_years[i])
		} else if i%3 == 1 {
			gate_map["Gate 1"] = append(gate_map["Gate 1"], a_years[i])
		} else {
			gate_map["Gate 2"] = append(gate_map["Gate 2"], a_years[i])
		}
	}
	fmt.Println("Gate map is :", gate_map)

	//Task 4
	unbalanced_years := []int{}
	for i := 0; i < len(years); i++ {
		if years[i]%4 != 0 {
			unbalanced_years = append(unbalanced_years, years[i])
		} else if years[i]%100 == 0 && years[i]%400 != 0 {
			unbalanced_years = append(unbalanced_years, years[i])
		}
	}
	fmt.Println("Unbalanced years are :", unbalanced_years)
	fmt.Println("First 2 unbalanced years are :", unbalanced_years[:2])

	//task 5
	fmt.Println("\n\n=======================================================")
	// fmt.Println("Auspicious years are :",a_years)
	fmt.Println("Anokhi Map :", map_values)
	fmt.Println("Gath Map :", gate_map)
	fmt.Println("Invitation Slice :", unbalanced_years[:2])
	fmt.Println("=======================================================")

}

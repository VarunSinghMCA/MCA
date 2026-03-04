package main

import (
	"fmt"
	"strings"
)

func main() {
	C_ids := []int{50, 42, 100, 12, 18, 55, 60, 24, 5}
	C_tags := []string{"level", "gamma", "area", "trust", "radar", "sense"}

	println("C_ids and C_tags:")
	for i, id := range C_ids {
		fmt.Printf("C_id: %d, C_tag: %s\n", id, C_tags[i%len(C_tags)])
	}

	// 1
	C_ids_e := []int{}
	C_tags_e := []string{}
	println("\n[C_ids] C_tags (are Even numbers but NOT divisible by 10):")
	for i := 0; i < len(C_ids); i++ {
		if C_ids[i]%2 == 0 && C_ids[i]%10 != 0 {
			fmt.Printf("[CID: %d] CTag: %s\n", C_ids[i], C_tags[i%len(C_tags)])
			// storing them in a slice
			C_ids_e = append(C_ids_e, C_ids[i])
			C_tags_e = append(C_tags_e, C_tags[i%len(C_tags)])
		}

	}

	// 2
	t_map := make(map[int]string)
	println("\nSecured Containers that start with same letter:")
	// storing them in a map - key -> tag, value -> total length of all C_ids
	for i := 0; i < len(C_tags); i++ {
		// total_length := 0
		if strings.ToUpper(C_tags[0]) == strings.ToUpper(C_tags[i]) {
			t_map[C_ids[i]] = C_tags[i]
		}
		fmt.Printf("\n[CID: %d] CTag: %s stored in map", C_ids[i], C_tags[i])
	}
	fmt.Printf("\n\nMap contents: %v\n", t_map)

	// 3
	// if CID>20 -> Bay A else Bay -> B
	bayA_cid := []int{}
	bayA_tags := []string{}
	bayB_cid := []int{}
	bayB_tags := []string{}
	println("\nExpress Cargo containers sorted into Bays:")
	for i := 0; i < len(C_ids); i++ {
		if C_ids[i] > 20 {
			bayA_cid = append(bayA_cid, C_ids[i])
			bayA_tags = append(bayA_tags, C_tags[i%len(C_tags)])
		} else {
			bayB_cid = append(bayB_cid, C_ids[i])
			bayB_tags = append(bayB_tags, C_tags[i%len(C_tags)])
		}
	}
	fmt.Println("\nBay A Containers:")
	for i := 0; i < len(bayA_cid); i++ {
		fmt.Printf("[CID: %d] CTag: %s\n", bayA_cid[i], bayA_tags[i])
	}
	fmt.Println("\nBay B Containers:")
	for i := 0; i < len(bayB_cid); i++ {
		fmt.Printf("[CID: %d] CTag: %s\n", bayB_cid[i], bayB_tags[i])
	}

	// 4
	// filter ids if cid>40 (heavy cargo) -> sort in asscending order -> retrive first two
	heavy_cargo_ids := []int{}
	heavy_cargo_tags := []string{}
	println("\nHeavy Cargo Containers (CID>40) in ascending order:")
	for i := 0; i < len(C_ids); i++ {
		if C_ids[i] > 40 {
			heavy_cargo_ids = append(heavy_cargo_ids, C_ids[i])
			heavy_cargo_tags = append(heavy_cargo_tags, C_tags[i%len(C_tags)])
		}
	}
	// sorting in ascending order
	for i := 0; i < len(heavy_cargo_ids)-1; i++ {
		for j := 0; j < len(heavy_cargo_ids)-i-1; j++ {
			if heavy_cargo_ids[j] > heavy_cargo_ids[j+1] {
				// swap ids
				heavy_cargo_ids[j], heavy_cargo_ids[j+1] = heavy_cargo_ids[j+1], heavy_cargo_ids[j]
				// swap corresponding tags
				heavy_cargo_tags[j], heavy_cargo_tags[j+1] = heavy_cargo_tags[j+1], heavy_cargo_tags[j]
			}
		}
	}

	for i := 0; i < len(heavy_cargo_ids) && i < 2; i++ {
		fmt.Printf("[CID: %d] CTag: %s\n", heavy_cargo_ids[i], heavy_cargo_tags[i])
	}

}

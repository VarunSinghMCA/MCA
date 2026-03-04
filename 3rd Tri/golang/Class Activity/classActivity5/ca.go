package main

import "fmt"

type Processor interface {
	Process(nums ...int) float64
}

type SumProcessor struct{}

func (s SumProcessor) Process(nums ...int) float64 {
	total := 0
	for _, num := range nums {
		total += num
	}
	return float64(total)
}

type AvgProcessor struct{}

func (a AvgProcessor) Process(nums ...int) float64 {
	total := 0
	for _, num := range nums {
		total += num
	}
	return float64(total) / float64(len(nums))
}

type MaxProcessor struct{}

func (m MaxProcessor) Process(nums ...int) float64 {
	max := nums[0]
	for _, num := range nums {
		if num > max {
			max = num
		}
	}
	return float64(max)
}

func GetProcessor(name string) Processor {
	processors := map[string]Processor{
		"sum": SumProcessor{},
		"avg": AvgProcessor{},
		"max": MaxProcessor{},
	}
	return processors[name]
}

func main() {
	p := GetProcessor("sum")
	fmt.Println(p.Process(1, 2, 3, 4))

	p = GetProcessor("avg")
	fmt.Println(p.Process(10, 20, 30))

	p = GetProcessor("max")
	fmt.Println(p.Process(5, 15, 10, 25, 3))

	// Pipeline of processors
	pipeline := []Processor{SumProcessor{}, AvgProcessor{}, MaxProcessor{}}
	for _, processor := range pipeline {
		fmt.Printf("Processing with %T: %.2f\n", processor, processor.Process(1, 2, 3, 4, 5))
	}
}

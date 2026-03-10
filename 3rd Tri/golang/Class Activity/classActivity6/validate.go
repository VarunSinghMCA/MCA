package main

import "fmt"

type ScoreError struct {
	Score int
}

func (e ScoreError) Error() string {
	return fmt.Sprintf("invalid score: %d", e.Score)
}

func main() {
	testScores := []int{85, -10, 105, 0, 100, 150, 50}

	for _, score := range testScores {
		err := validateScore(score)
		if err != nil {
			fmt.Println("Error:", err)
		} else {
			fmt.Printf("Valid score: %d\n", score)
		}
	}
}

func validateScore(score int) error {
	if score < 0 || score > 100 {
		return ScoreError{Score: score}
	}
	return nil
}

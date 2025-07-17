#include <stdio.h>
#include <stdlib.h>
#include <time.h>

// Global Variables
// Define the size of the board
#define SIZE 4

// Function to initialize the board with pairs of symbols
void initialize(char board[SIZE][SIZE], char symbols[]) {
    int index = 0;
    for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
            board[i][j] = symbols[index];
            index++;
            if (index == 8) index = 0; // 8 unique pairs (16 cards)
        }
    }
}


// Function to shuffle the board randomly
void shuffle(char board[SIZE][SIZE]) {
    // seeding for random number generation
    // to ensure different shuffles each time the game is played
    srand(time(NULL));
    for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
            int row = rand() % SIZE;
            int col = rand() % SIZE;
            // Swap the current card with a randomly chosen card
            // This creates a random shuffle of the board
            char temp = board[i][j];
            board[i][j] = board[row][col];
            board[row][col] = temp;
        }
    }
}


// Function to print the current state of the board
// Revealed cards are shown, unrevealed cards are shown as [$]
void printBoard(char board[SIZE][SIZE], int revealed[SIZE][SIZE]) {
    printf("\n   ");
    // Print column headers
    for (int i = 0; i < SIZE; i++) printf("%2d ", i + 1);
    printf("\n");
    for (int i = 0; i < SIZE; i++) {
        // Print row header
        printf("%2d ", i + 1);

        // Print the board row
        for (int j = 0; j < SIZE; j++) {
            if (revealed[i][j])
                printf(" %c ", board[i][j]);
            else
                printf("[$]");
        }

        printf("\n");
    }
}

// Main function to run the memory game
// Players will input the coordinates of the cards they want to reveal
int main() {
    // Initialize the game board and variables
    // Create a 4x4 board and an array to track revealed cards
    char board[SIZE][SIZE];
    int revealed[SIZE][SIZE] = {0};
    char symbols[8] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
    int pairsFound = 0;
    int x1, y1, x2, y2;

    initialize(board, symbols);
    shuffle(board);

    printf("Memory Game (Matching Pairs)\n");
    printf("Find all matching pairs of cards!\n");

    // problem --> Input Validation from user 

    while (pairsFound < SIZE*SIZE/2) {
        printBoard(board, revealed);

        // Input for the first card
        while (1) {
            printf("Enter first card row: ");
            if (scanf("%d", &x1) != 1) {
                printf("Invalid input! Please enter an integer.\n");
                while (getchar() != '\n'); 
                continue;
            }
            printf("Enter first card column: ");
            if (scanf("%d", &y1) != 1) {
                printf("Invalid input! Please enter an integer.\n");
                while (getchar() != '\n');
                continue;
            }
            // Adjust for 0-based indexing
            x1--; y1--;

            // Validate input for first card
            // Check if the input is within bounds and not already revealed
            if (x1 < 0 || x1 >= SIZE || y1 < 0 || y1 >= SIZE || revealed[x1][y1]) {
                printf("Invalid card! Try again.\n");
                continue;
            }
            break;
        }

        // Mark the first card as revealed then proceed to the second card
        revealed[x1][y1] = 1;
        printBoard(board, revealed);

        // Input for the second card
        while (1) {
            printf("Enter second card row: ");
            if (scanf("%d", &x2) != 1) {
                printf("Invalid input! Please enter an integer.\n");
                while (getchar() != '\n');
                continue;
            }
            printf("Enter second card column: ");
            if (scanf("%d", &y2) != 1) {
                printf("Invalid input! Please enter an integer.\n");
                while (getchar() != '\n');
                continue;
            }
            // Adjust for 0-based indexing
            x2--; y2--;

            // Validate input for second card
            // Check if the input is within bounds, not already revealed, and not the same as the first card
            if (x2 < 0 || x2 >= SIZE || y2 < 0 || y2 >= SIZE || revealed[x2][y2] || (x1 == x2 && y1 == y2)) {
                printf("Invalid card! Try again.\n");
                continue;
            }
            break;
        }

        // Mark the second card as revealed
        revealed[x2][y2] = 1;
        printBoard(board, revealed);

        // Check if the two revealed cards match
        // If they match, increment the pairs found counter
        if (board[x1][y1] == board[x2][y2]) {
            printf("Match found!\n");
            pairsFound++;
        } 
        // If they do not match, reset the revealed state of both cards
        else {
            printf("No match. Try again!\n");
            revealed[x1][y1] = 0;
            revealed[x2][y2] = 0;
        }
    }

    printf("Congratulations! You found all pairs!\n");
    return 0;
}
// Memory Game in C
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define SIZE 4

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

void shuffle(char board[SIZE][SIZE]) {
    srand(time(NULL));
    for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
            int row = rand() % SIZE;
            int col = rand() % SIZE;
            char temp = board[i][j];
            board[i][j] = board[row][col];
            board[row][col] = temp;
        }
    }
}

void printBoard(char board[SIZE][SIZE], int revealed[SIZE][SIZE]) {
    printf("\n   ");
    for (int i = 0; i < SIZE; i++) printf("%2d ", i + 1);
    printf("\n");
    for (int i = 0; i < SIZE; i++) {
        printf("%2d ", i + 1);
        for (int j = 0; j < SIZE; j++) {
            if (revealed[i][j])
                printf(" %c ", board[i][j]);
            else
                printf("[$]");
        }
        printf("\n");
    }
}

int main() {
    char board[SIZE][SIZE];
    int revealed[SIZE][SIZE] = {0};
    char symbols[8] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
    int pairsFound = 0;
    int x1, y1, x2, y2;

    initialize(board, symbols);
    shuffle(board);

    printf("Memory Game (Matching Pairs)\n");
    printf("Find all matching pairs of cards!\n");

    while (pairsFound < SIZE*SIZE/2) {
        printBoard(board, revealed);

        printf("Enter first card (row and column): ");
        scanf("%d %d", &x1, &y1);
        x1--; y1--;
        if (x1 < 0 || x1 >= SIZE || y1 < 0 || y1 >= SIZE || revealed[x1][y1]) {
            printf("Invalid card! Try again.\n");
            continue;
        }
        revealed[x1][y1] = 1;
        printBoard(board, revealed);

        printf("Enter second card (row and column): ");
        scanf("%d %d", &x2, &y2);
        x2--; y2--;
        if (x2 < 0 || x2 >= SIZE || y2 < 0 || y2 >= SIZE || revealed[x2][y2] || (x1 == x2 && y1 == y2)) {
            printf("Invalid card! Try again.\n");
            revealed[x1][y1] = 0;
            continue;
        }
        revealed[x2][y2] = 1;
        printBoard(board, revealed);

        if (board[x1][y1] == board[x2][y2]) {
            printf("Match found!\n");
            pairsFound++;
        } else {
            printf("No match. Try again!\n");
            revealed[x1][y1] = 0;
            revealed[x2][y2] = 0;
        }
    }

    printf("Congratulations! You found all pairs!\n");
    return 0;
}
// Memory Game in C
// This program implements a simple memory game where players match pairs of cards. 
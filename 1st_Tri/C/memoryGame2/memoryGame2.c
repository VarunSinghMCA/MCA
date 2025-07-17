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
            if (index == 8) index = 0; // 8 unique symbols for 16 cards
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

void showInstructions() {
    printf("\n--- Instructions ---\n");
    printf("1. This is a memory matching game.\n");
    printf("2. There are 16 cards arranged in a 4x4 grid.\n");
    printf("3. You will input two coordinates each turn to flip cards.\n");
    printf("4. If the cards match, they stay revealed.\n");
    printf("5. The goal is to find all 8 pairs with as few tries as possible.\n");
    printf("--------------------\n");
}

void playGame() {
    char board[SIZE][SIZE];
    int revealed[SIZE][SIZE] = {0};
    char symbols[8] = {'A','B','C','D','E','F','G','H'};
    int pairsFound = 0;
    int attempts = 0;

    int x1, y1, x2, y2;

    initialize(board, symbols);
    shuffle(board);

    while (pairsFound < (SIZE * SIZE) / 2) {
        printBoard(board, revealed);

        // First card
        while (1) {
            printf("Enter first card row and column (e.g. 1 2): ");
            if (scanf("%d %d", &x1, &y1) != 2 || x1 < 1 || x1 > SIZE || y1 < 1 || y1 > SIZE || revealed[x1-1][y1-1]) {
                printf("Invalid input. Try again.\n");
                while (getchar() != '\n');
                continue;
            }
            break;
        }
        x1--; y1--;
        revealed[x1][y1] = 1;
        printBoard(board, revealed);

        // Second card
        while (1) {
            printf("Enter second card row and column: ");
            if (scanf("%d %d", &x2, &y2) != 2 || x2 < 1 || x2 > SIZE || y2 < 1 || y2 > SIZE || revealed[x2-1][y2-1] || (x1 == x2 && y1 == y2)) {
                printf("Invalid input. Try again.\n");
                while (getchar() != '\n');
                continue;
            }
            break;
        }
        x2--; y2--;
        revealed[x2][y2] = 1;
        printBoard(board, revealed);

        attempts++;

        if (board[x1][y1] == board[x2][y2]) {
            printf("Match found!\n");
            pairsFound++;
        } else {
            printf("No match. Try again.\n");
            revealed[x1][y1] = 0;
            revealed[x2][y2] = 0;
        }
    }

    printf("Congratulations! You found all pairs!\n");
    printf("Total attempts: %d\n", attempts);
}

int main() {
    int choice;
    char playAgain;

    do {
        printf("\n--- Memory Game Menu ---\n");
        printf("1. Play Game\n");
        printf("2. Instructions\n");
        printf("3. Exit\n");
        printf("Choose an option: ");
        scanf("%d", &choice);
        while (getchar() != '\n'); // Clear input buffer

        switch (choice) {
            case 1:
                playGame();
                break;
            case 2:
                showInstructions();
                break;
            case 3:
                printf("Goodbye!\n");
                return 0;
            default:
                printf("Invalid choice. Try again.\n");
        }

        printf("\nWould you like to play again? (y/n): ");
        scanf(" %c", &playAgain);
        while (getchar() != '\n');

    } while (playAgain == 'y' || playAgain == 'Y');

    printf("Thanks for playing!\n");
    return 0;
}
// Memory Game in C Part 2
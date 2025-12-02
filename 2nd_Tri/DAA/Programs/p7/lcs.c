#include <stdio.h>
#include <string.h>
#include <ctype.h>

// Time Complexity: O(n * m)
// Space Complexity: O(n * m)

int main() {
    char X[100], Y[100];

    
    while (1) {
        printf("Enter first string X: ");
        fgets(X, sizeof(X), stdin);
        X[strcspn(X, "\n")] = 0;

        if (strlen(X) == 0) {
            printf("Error: String cannot be empty. Please re-enter.\n");
            continue;
        }

        break;
    }

    while (1) {
        printf("Enter second string Y: ");
        fgets(Y, sizeof(Y), stdin);
        Y[strcspn(Y, "\n")] = 0;

        if (strlen(Y) == 0) {
            printf("Error: String cannot be empty. Please re-enter.\n");
            continue;
        }

        break;
    }

    int m = strlen(X);
    int n = strlen(Y);

    int L[m+1][n+1];

    // ---------------- DP Table Initialization ----------------
    for (int i = 0; i <= m; i++) {
        for (int j = 0; j <= n; j++) {
            if (i == 0 || j == 0)
                L[i][j] = 0;
            else if (X[i-1] == Y[j-1])
                L[i][j] = L[i-1][j-1] + 1;
            else
                L[i][j] = (L[i-1][j] > L[i][j-1]) ? L[i-1][j] : L[i][j-1];
        }
    }

    printf("\nDP Length Table L[i][j]:\n");
    for (int i = 0; i <= m; i++) {
        for (int j = 0; j <= n; j++) {
            printf("%d ", L[i][j]);
        }
        printf("\n");
    }

    // ----------- LCS Length -----------
    printf("\nLCS Length = %d\n", L[m][n]);

    // ----------- Backtracking to get one LCS sequence -----------
    int index = L[m][n];
    char lcs[index + 1];
    lcs[index] = '\0';

    int i = m, j = n;
    while (i > 0 && j > 0) {
        if (X[i - 1] == Y[j - 1]) {
            lcs[index - 1] = X[i - 1];
            i--;
            j--;
            index--;
        }
        else if (L[i - 1][j] > L[i][j - 1])
            i--;
        else
            j--;    1
            .
    }

    printf("One LCS Sequence: %s\n", lcs);

    return 0;
}


#include <stdio.h>

#define N 6
#define INF 99999

// Genshin locations (vertices)
const char* names[N] = {
    "Mondstadt City",
    "Wolvendom",
    "Liyue Harbor",
    "Mt. Tianheng",
    "Inazuma City",
    "Narukami Island"
};

// Initial graph: direct travel costs between regions
int dist[N][N] = {
    {0,   5,   25,  28,  INF, INF},
    {5,   0,   22,  30,  INF, INF},
    {25,  22,  0,   6,   80,  INF},
    {28,  30,  6,   0,   75,  INF},
    {INF, INF, 80,  75,  0,   12},
    {INF, INF, INF, INF, 12,  0}
};

// Print distance matrix with region names
void printMatrix(int matrix[N][N]) {
    printf("\nShortest Distance Matrix:\n\n");

    // header row
    printf("%-18s", "");
    for(int j = 0; j < N; j++)
        printf("%-18s", names[j]);
    printf("\n");

    for (int i = 0; i < N; i++) {
        printf("%-18s", names[i]);
        for (int j = 0; j < N; j++) {
            if (matrix[i][j] == INF)
                printf("%-18s", "INF");
            else
                printf("%-18d", matrix[i][j]);
        }
        printf("\n");
    }
}

void floydWarshall() {
    int d[N][N];

    // Copy initial distances
    for (int i = 0; i < N; i++)
        for (int j = 0; j < N; j++)
            d[i][j] = dist[i][j];

    // Floyd–Warshall core
    for (int k = 0; k < N; k++) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                // Validation check before updating
                if (d[i][k] + d[k][j] < d[i][j])
                    d[i][j] = d[i][k] + d[k][j];
            }
        }
    }

    printMatrix(d);
}

int main() {
    printf("=== Floyd-Warshall (Genshin Impact Paths) ===\n");
    floydWarshall();
    return 0;
}
// gcc fwa.c -o fwa
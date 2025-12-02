#include <stdio.h>
#include <stdlib.h>
#include <limits.h>
#include <time.h>

// ---------- DIJKSTRA ----------
#define V 5

int minDistance(int dist[], int visited[]) {
    int min = INT_MAX, min_index = -1;
    for (int v = 0; v < V; v++)
        if (!visited[v] && dist[v] <= min) {
            min = dist[v];
            min_index = v;
        }
    return min_index;
}

void dijkstra(int graph[V][V], int src) {
    int dist[V], visited[V];
    for (int i = 0; i < V; i++) {
        dist[i] = INT_MAX;
        visited[i] = 0;
    }
    dist[src] = 0;

    for (int count = 0; count < V - 1; count++) {
        int u = minDistance(dist, visited);
        visited[u] = 1;

        for (int v = 0; v < V; v++)
            if (!visited[v] && graph[u][v] && dist[u] != INT_MAX &&
                dist[u] + graph[u][v] < dist[v])
                dist[v] = dist[u] + graph[u][v];
    }

    char *places[V] = {"Mondstadt", "Liyue", "Inazuma", "Sumeru", "Fontaine"};
    printf("\nShortest distances from %s:\n", places[src]);
    for (int i = 0; i < V; i++)
        printf("%s -> %s = %d\n", places[src], places[i], dist[i]);
}

// ---------- STRASSEN ----------
void add(int n, int A[n][n], int B[n][n], int C[n][n]) {
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            C[i][j] = A[i][j] + B[i][j];
}

void sub(int n, int A[n][n], int B[n][n], int C[n][n]) {
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            C[i][j] = A[i][j] - B[i][j];
}

void classicalMultiply(int n, int A[n][n], int B[n][n], int C[n][n]) {
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++) {
            C[i][j] = 0;
            for (int k = 0; k < n; k++)
                C[i][j] += A[i][k] * B[k][j];
        }
}

void strassen2x2(int A[2][2], int B[2][2], int C[2][2]) {
    int M1 = (A[0][0] + A[1][1]) * (B[0][0] + B[1][1]);
    int M2 = (A[1][0] + A[1][1]) * B[0][0];
    int M3 = A[0][0] * (B[0][1] - B[1][1]);
    int M4 = A[1][1] * (B[1][0] - B[0][0]);
    int M5 = (A[0][0] + A[0][1]) * B[1][1];
    int M6 = (A[1][0] - A[0][0]) * (B[0][0] + B[0][1]);
    int M7 = (A[0][1] - A[1][1]) * (B[1][0] + B[1][1]);

    C[0][0] = M1 + M4 - M5 + M7;
    C[0][1] = M3 + M5;
    C[1][0] = M2 + M4;
    C[1][1] = M1 - M2 + M3 + M6;
}

void runStrassen() {
    int A[2][2] = {{1, 2}, {3, 4}};
    int B[2][2] = {{5, 6}, {7, 8}};
    int C[2][2];
    clock_t start = clock();
    strassen2x2(A, B, C);
    clock_t end = clock();

    printf("\nMatrix Multiplication using Strassen (2x2):\n");
    for (int i = 0; i < 2; i++) {
        for (int j = 0; j < 2; j++)
            printf("%d ", C[i][j]);
        printf("\n");
    }
    printf("Time taken: %.6f sec\n", (double)(end - start) / CLOCKS_PER_SEC);
}

// ---------- MAIN MENU ----------
int main() {
    int graph[V][V] = {
        {0, 3, 0, 7, 0},
        {3, 0, 2, 0, 0},
        {0, 2, 0, 1, 3},
        {7, 0, 1, 0, 2},
        {0, 0, 3, 2, 0}
    };

    int choice;
    while (1) {
        printf("\n==== Genshin Impact Algorithm Menu ====\n");
        printf("1. Run Dijkstra's SSSP\n");
        printf("2. Run Strassen's Multiplication\n");
        printf("3. Show Domain Note\n");
        printf("4. Exit\n");
        printf("Enter choice: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1: dijkstra(graph, 0); break; // from Mondstadt
            case 2: runStrassen(); break;
            case 3:
                printf("\nDomain Note:\n");
                printf("- Graph nodes = Genshin locations (travel cost edges)\n");
                printf("- Matrices = Artifact synergy relations\n");
                printf("- Demonstrates Dijkstra (O(V^2)) and Strassen (O(n^2.81))\n");
                break;
            case 4: exit(0);
            default: printf("Invalid choice!\n");
        }
    }
    return 0;
}

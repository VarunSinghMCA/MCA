#include <stdio.h>
#include <limits.h>
#include <string.h>

// Traveling Salesman Problem (brute-force backtracking)
// 6 Genshin locations

#define N 6

const char* names[N] = {
    "Mondstadt City",
    "Wolvendom",
    "Liyue Harbor",
    "Mt. Tianheng",
    "Inazuma City",
    "Narukami Island"
};

// Example symmetric distance matrix. Replace these values with your own.
int dist[N][N] = {
    {0, 10, 15, 20, 10, 25},
    {10, 0, 35, 25, 17, 28},
    {15, 35, 0, 30, 20, 22},
    {20, 25, 30, 0, 28, 24},
    {10, 17, 20, 28, 0, 18},
    {25, 28, 22, 24, 18, 0}
};

int best_cost = INT_MAX;
int best_path[N+1];

void print_path(int path[]) {
    for (int i = 0; i < N; ++i) {
        printf("%s -> ", names[path[i]]);
    }
    printf("%s", names[path[0]]); // return to start
}

void dfs(int depth, int path[], int visited[], int current_cost) {
    if (depth == N) {
        int total_cost = current_cost + dist[path[depth-1]][path[0]];

        if (total_cost < best_cost) {
            best_cost = total_cost;
            memcpy(best_path, path, sizeof(int)*N);
            best_path[N] = path[0];
        }

        // BORDER + PRINT
        printf("\n==============================\n");
        print_path(path);
        printf("  | cost = %d\n", total_cost);
        printf("==============================\n");

        return;
    }

    for (int city = 1; city < N; ++city) {
        if (!visited[city]) {
            visited[city] = 1;
            path[depth] = city;
            int added = dist[path[depth-1]][city];

            if (current_cost + added < best_cost) {
                dfs(depth + 1, path, visited, current_cost + added);
            }
            visited[city] = 0;
        }
    }
}

int main() {
    int visited[N];
    int path[N];

    // initialize
    for (int i = 0; i < N; ++i) visited[i] = 0;

    path[0] = 0; // start at Mondstadt City
    visited[0] = 1;

    printf("All possible TSP routes starting at Mondstadt City:\n\n");

    dfs(1, path, visited, 0);

    if (best_cost == INT_MAX) {
        printf("No tour found.\n");
    } else {
        printf("\n\n===== BEST ROUTE FOUND =====\n");
        for (int i = 0; i < N; ++i) {
            printf("%s -> ", names[best_path[i]]);
        }
        printf("%s  | min cost = %d\n", names[best_path[0]], best_cost);
        printf("===========================\n");
    }

    
    return 0;
}

//gcc placesTravel.c -o placesTravel
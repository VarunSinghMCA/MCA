#include <stdio.h>

#define MAX 20

// Predefined graph (adjacency matrix)
int graph[MAX][MAX] = {0};
int visited[MAX] = {0};

int n = 5;   // number of vertices
int edges = 6;

void addEdge(int u, int v) {
    graph[u][v] = 1;
    graph[v][u] = 1;
}

void buildGraph() {
    // Predefined edges
    addEdge(0, 1);
    addEdge(0, 2);
    addEdge(1, 3);
    addEdge(2, 3);
    addEdge(3, 4);
    addEdge(1, 4);
}

void minimumVertexCover() {
    int cover[MAX];
    int count = 0;

    for (int u = 0; u < n; u++) {
        for (int v = u + 1; v < n; v++) {

            if (graph[u][v] && !visited[u] && !visited[v]) {
                
                visited[u] = 1;
                visited[v] = 1;

                cover[count++] = u;
                cover[count++] = v;
            }
        }
    }

    printf("\nVertex Cover (approx): ");
    for (int i = 0; i < count; i++) {
        printf("%d ", cover[i]);
    }
    printf("\n");
}

int main() {

    buildGraph();

    printf("Predefined graph with %d vertices and %d edges\n", n, edges);
    printf("Edges:\n");
    printf("(0,1)\n(0,2)\n(1,3)\n(2,3)\n(3,4)\n(1,4)\n");

    minimumVertexCover();

    return 0;
}

// # SNART CITY Road Network Analysis

#include <stdio.h>
#include <stdlib.h>

#define NODES 5 // H,P,F,D,G

// nodese
char nodes[NODES] = {'H', 'P', 'F', 'D', 'G'};

// adjacency matrix - weight
int graph[NODES][NODES];

// Simple adjacency (0/1)
int adj[NODES][NODES];

void createGraph() {
// Initialize matrices
int i,j;
for (i = 0; i < NODES; i++) {
    for (j = 0; j < NODES; j++) {
        graph[i][j] = 0;
        adj[i][j] = 0;
    }
}

// weights
graph[0][1] = 4; // H -> P
graph[0][2] = 1; // H -> F
graph[1][3] = 2; // P -> D
graph[3][1] = 2; // D -> P
graph[2][4] = 8; // F -> G
graph[3][4] = 1; // D -> G
graph[3][0] = 5; // D -> H

// adjacency matrix
for (i = 0; i < NODES; i++) {
    for (j = 0; j < NODES; j++) {
        adj[i][j] = (graph[i][j] != 0) ? 1 : 0;
    }
}

// 1. print adjacency matrix
printf(" H P F D G\n");
for (i = 0; i < NODES; i++) {
    printf("%c | ", nodes[i]);
    for (j = 0; j < NODES; j++) {
        printf("%d ", adj[i][j]);
    }
    printf("\n");
}
}

void DisplayGraph() {
    printf("----------------------------\n");
    printf("Format: Node -> Connected Node -> Weight\n\n");
    int i,j;
    for (i = 0; i < NODES; i++) {
        for (j = 0; j < NODES; j++) {
            if (graph[i][j] != 0) {
                printf("%c -> %c = %d\n", nodes[i], nodes[j], graph[i][j]);
            }
        }
    }

    printf("----------------------------\n");
}

// 2. Dijkstra from H
void DijkstraAlgo(int start) {
    int dist[NODES], visited[NODES];
    int i,c;
    for (i = 0; i < NODES; i++) {
        dist[i] = 9999;
        visited[i] = 0;
    }

    dist[start] = 0;

    for (c = 0; c < NODES - 1; c++) {

        int min = 9999, u = -1;
                                                                                                                                                                                                                                                                                                                                                
        // smallest unvisited node
        for (i = 0; i < NODES; i++) {
            if (!visited[i] && dist[i] < min) {
                min = dist[i];
                u = i;
            }
        }
        visited[u] = 1;

        // Relax
        int v;
        for (v = 0; v < NODES; v++) {
            if (graph[u][v] && !visited[v] && dist[u] + graph[u][v] < dist[v]) {
                dist[v] = dist[u] + graph[u][v];
            }
        }
    }

    printf("\nDijkstra Shortest Paths from H:\n");
    for (i = 0; i < NODES; i++) {
        printf("H -> %c = %d\n", nodes[i], dist[i]);
    }
}

int main() {

    printf("\nSmart City Planning Department\n");
    printf("Adjacency Matrix:\n");
    printf("----------------------------------\n");
    createGraph();
    printf("----------------------------------\n\n");

    printf("Graph Connections\n");
    DisplayGraph();

    printf("Running Dijkstra...\n");
    DijkstraAlgo(0);

    return 0;
}
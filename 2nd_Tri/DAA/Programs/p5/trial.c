#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX 50

/* ===================== DOMAIN: Genshin Impact ============================
 * KNAPSACK PROBLEM:
 * - Items represent weapon/artifact upgrades
 * - Value = ATK/DMG boost gained
 * - Weight = Mora cost required
 * - Capacity = Available Mora budget
 * 
 * MST PROBLEM:
 * - Nodes represent Genshin regions/domains
 * - Edges represent travel paths between regions
 * - Weights = Travel cost in Mora
 * - Goal: Find minimum cost to connect all regions
 ======================================================================= */

/* =========================== PART A: KNAPSACK =========================== */

// Structure to represent upgrade items (weapons/artifacts)
typedef struct {
    char name[30];      // Item name (e.g., "Skyward_Blade", "Gladiator_Artifact")
    float value;        // ATK/DMG boost value
    float weight;       // Mora cost required
} Item;

// Predefined Genshin Impact upgrade items
Item predefinedItems[] = {
    {"Skyward_Blade", 85.0, 45.0},         // High value sword
    {"Gladiator_Artifact", 60.0, 30.0},    // Balanced artifact
    {"Wanderer_Troupe", 70.0, 35.0},       // Good artifact set
    {"Bloodstained_Set", 55.0, 25.0},      // Budget option
    {"Noblesse_Oblige", 80.0, 40.0},       // Premium artifact
    {"Prototype_Rancour", 40.0, 20.0},     // F2P weapon
    {"Wolf_Gravestone", 95.0, 50.0}        // 5-star weapon
};
int numPredefinedItems = 7;

/* Comparator for fractional knapsack (descending value/weight) */
int cmp(const void *a, const void *b){
    Item *x = (Item*)a;
    Item *y = (Item*)b;
    float r1 = x->value / x->weight;
    float r2 = y->value / y->weight;
    return (r1 < r2) ? 1 : -1;
}

/* Fractional Knapsack - Greedy Approach
 * Time Complexity: O(n log n) for sorting + O(n) for selection = O(n log n)
 * Space Complexity: O(1)
 * Use Case: When items can be partially used (material splitting/farming)
 */
void fractionalKnapsack(){
    int n = numPredefinedItems;
    float W = 120.0;  // Slightly higher budget for fractional
    
    printf("\n=== FRACTIONAL KNAPSACK: Material Optimization ===\n");
    printf("Available Resource Budget: %.1f\n", W);
    
    // Create copy of items to avoid modifying original
    Item items[n];
    for(int i = 0; i < n; i++) {
        items[i] = predefinedItems[i];
    }
    
    // Sort items by value/weight ratio in descending order (Greedy choice)
    qsort(items, n, sizeof(Item), cmp);

    float totalValue = 0, remainingCapacity = W;

    printf("\nItems sorted by value/weight ratio:\n");
    for(int i = 0; i < n; i++) {
        printf("%d. %s - Ratio: %.2f\n", i+1, items[i].name, items[i].value/items[i].weight);
    }

    printf("\nGreedy Selection Process:\n");
    for(int i = 0; i < n; i++){
        if(items[i].weight <= remainingCapacity){
            // Take full item
            totalValue += items[i].value;
            remainingCapacity -= items[i].weight;
            printf("%s (100%%) - Value: %.1f, Cost: %.1f\n", 
                   items[i].name, items[i].value, items[i].weight);
        } else if(remainingCapacity > 0) {
            // Take fractional part
            float fraction = remainingCapacity / items[i].weight;
            totalValue += items[i].value * fraction;
            printf("%s (%.1f%%) - Value: %.1f, Cost: %.1f\n", 
                   items[i].name, fraction * 100, items[i].value * fraction, remainingCapacity);
            remainingCapacity = 0;
            break;
        }
    }

    printf("\nMaximum Benefit (Fractional): %.2f\n", totalValue);
    printf("Remaining Budget: %.2f\n", remainingCapacity);
}

/* ========================= PART B: MST (PRIM + KRUSKAL) ========================= */

// Edge structure for graph representation
typedef struct {
    int src, dest, wt;  // source, destination, weight (travel cost)
} Edge;

// Predefined Genshin Impact regions graph
// Nodes: 0=Mondstadt, 1=Liyue, 2=Inazuma, 3=Sumeru, 4=Fontaine
char* regionNames[] = {"Mondstadt", "Liyue", "Inazuma", "Sumeru", "Fontaine"};
int numRegions = 5;

// Predefined edges with travel costs
Edge predefinedEdges[] = {
    {0, 1, 10},  // Mondstadt -> Liyue (10 Mora)
    {0, 2, 25},  // Mondstadt -> Inazuma (25 Mora) 
    {0, 3, 30},  // Mondstadt -> Sumeru (30 Mora)
    {1, 2, 15},  // Liyue -> Inazuma (15 Mora)
    {1, 3, 20},  // Liyue -> Sumeru (20 Mora)
    {1, 4, 35},  // Liyue -> Fontaine (35 Mora)
    {2, 3, 18},  // Inazuma -> Sumeru (18 Mora)
    {2, 4, 22},  // Inazuma -> Fontaine (22 Mora)
    {3, 4, 12}   // Sumeru -> Fontaine (12 Mora)
};
int numPredefinedEdges = 9;

/* Prim's Algorithm - Minimum Spanning Tree
 * Time Complexity: O(V²) for adjacency matrix representation
 * Space Complexity: O(V²) for adjacency matrix
 * Approach: Grow MST one vertex at a time, always adding minimum weight edge
 */
void prim(){
    int V = numRegions;
    
    printf("\n=== PRIM'S MST: Genshin Impact Travel Network ===\n");
    printf("Regions: ");
    for(int i = 0; i < V; i++) {
        printf("%s(%d) ", regionNames[i], i);
    }
    printf("\n");
    
    // Initialize adjacency matrix with infinity (999999 = no direct path)
    int adj[V][V];
    for(int i = 0; i < V; i++)
        for(int j = 0; j < V; j++)
            adj[i][j] = (i == j) ? 0 : 999999;

    // Populate adjacency matrix with predefined edges
    printf("\nAvailable Travel Routes:\n");
    for(int i = 0; i < numPredefinedEdges; i++){
        int u = predefinedEdges[i].src;
        int v = predefinedEdges[i].dest;
        int w = predefinedEdges[i].wt;
        adj[u][v] = adj[v][u] = w;  // Undirected graph
        printf("%s ↔ %s (Cost: %d Mora)\n", regionNames[u], regionNames[v], w);
    }

    // Track selected vertices in MST
    int selected[V];
    for(int i = 0; i < V; i++) selected[i] = 0;
    selected[0] = 1;  // Start with Mondstadt (vertex 0)

    int edgesCount = 0, totalCost = 0;
    printf("\nPrim's Algorithm Execution:\n");
    printf("Starting from: %s\n", regionNames[0]);

    // Build MST by adding V-1 edges
    while(edgesCount < V-1){
        int minCost = 999999, fromVertex = -1, toVertex = -1;
        
        // Find minimum weight edge connecting selected to unselected vertex
        for(int i = 0; i < V; i++){
            if(selected[i]){  // For each selected vertex
                for(int j = 0; j < V; j++){
                    if(!selected[j] && adj[i][j] < 999999 && adj[i][j] < minCost){
                        minCost = adj[i][j];
                        fromVertex = i;
                        toVertex = j;
                    }
                }
            }
        }
        
        if(fromVertex == -1 || toVertex == -1) {
            printf("Error: Graph disconnected!\n");
            break;
        }
        
        // Add edge to MST
        selected[toVertex] = 1;
        totalCost += minCost;
        printf("Step %d: %s → %s (Cost: %d Mora)\n", 
               edgesCount + 1, regionNames[fromVertex], regionNames[toVertex], minCost);
        edgesCount++;
    }

    printf("\nPrim's MST Complete!\n");
    printf("Total Minimum Travel Cost: %d Mora\n", totalCost);
    printf("Edges in MST: %d\n", edgesCount);
}

/* FIND with path compression */
int findp(int parent[], int x){
    return (parent[x] == x) ? x : (parent[x] = findp(parent, parent[x]));
}

/* UNION by rank */
void unionp(int parent[], int rank[], int a, int b){
    a = findp(parent, a);
    b = findp(parent, b);
    if(a != b){
        if(rank[a] < rank[b]) parent[a] = b;
        else if(rank[b] < rank[a]) parent[b] = a;
        else { parent[b] = a; rank[a]++; }
    }
}

/* Kruskal's Algorithm - Minimum Spanning Tree
 * Time Complexity: O(E log E) for sorting edges + O(E α(V)) for union-find
 * Space Complexity: O(V) for union-find structure
 * Approach: Sort all edges, add minimum weight edges that don't create cycles
 */
void kruskal(){
    int V = numRegions;
    int E = numPredefinedEdges;
    
    printf("\n=== KRUSKAL'S MST: Optimized Transport Network ===\n");
    printf("Regions: ");
    for(int i = 0; i < V; i++) {
        printf("%s(%d) ", regionNames[i], i);
    }
    printf("\n");
    
    // Copy predefined edges for sorting
    Edge edges[E];
    for(int i = 0; i < E; i++) {
        edges[i] = predefinedEdges[i];
    }
    
    printf("\nAll Available Routes (before sorting):\n");
    for(int i = 0; i < E; i++) {
        printf("%s → %s (Cost: %d)\n", 
               regionNames[edges[i].src], regionNames[edges[i].dest], edges[i].wt);
    }

    // Sort edges by weight using bubble sort (for simplicity)
    printf("\nSorting edges by cost (ascending)...\n");
    for(int i = 0; i < E-1; i++)
        for(int j = i+1; j < E; j++)
            if(edges[i].wt > edges[j].wt){
                Edge temp = edges[i]; 
                edges[i] = edges[j]; 
                edges[j] = temp;
            }

    printf("Sorted edges:\n");
    for(int i = 0; i < E; i++) {
        printf("%d. %s → %s (Cost: %d)\n", i+1,
               regionNames[edges[i].src], regionNames[edges[i].dest], edges[i].wt);
    }

    // Initialize Union-Find data structure
    int parent[V], rank[V], totalCost = 0;
    for(int i = 0; i < V; i++){ 
        parent[i] = i;    // Each node is its own parent initially
        rank[i] = 0;      // Rank for union by rank optimization
    }

    printf("\nKruskal's Algorithm Execution:\n");
    int mstEdges = 0, edgeIndex = 0;
    
    // Process edges in sorted order
    while(mstEdges < V-1 && edgeIndex < E){
        int u = edges[edgeIndex].src;
        int v = edges[edgeIndex].dest;
        int cost = edges[edgeIndex].wt;
        
        // Check if adding this edge creates a cycle
        if(findp(parent, u) != findp(parent, v)){
            // No cycle - add edge to MST
            unionp(parent, rank, u, v);
            printf("++ Added: %s → %s (Cost: %d) [No cycle]\n", 
                   regionNames[u], regionNames[v], cost);
            totalCost += cost;
            mstEdges++;
        } else {
            printf("-- Skipped: %s → %s (Cost: %d) [Would create cycle]\n", 
                   regionNames[u], regionNames[v], cost);
        }
        edgeIndex++;
    }
    
    if(mstEdges < V-1) {
        printf("Error: Graph is disconnected!\n");
    } else {
        printf("\n✓ Kruskal's MST Complete!\n");
        printf("Total Minimum Cost: %d Mora\n", totalCost);
        printf("Edges in MST: %d\n", mstEdges);
    }
}

/* =============================== MAIN FUNCTION =============================== */

int main(){
    printf("===============================================\n");
    printf("    GENSHIN IMPACT: ALGORITHM DEMONSTRATION    \n");
    printf("===============================================\n");
    printf("This program demonstrates key algorithms using\n");
    printf("Genshin Impact game mechanics as examples.\n");
    printf("===============================================\n");
    
    // Run all algorithms with predefined data
    printf("\nKNAPSACK PROBLEMS:\n");
    fractionalKnapsack();   // Greedy approach for divisible items
    
    printf("\n\nMINIMUM SPANNING TREE PROBLEMS:\n");
    prim();                 // Vertex-based MST algorithm
    kruskal();              // Edge-based MST algorithm
    
    printf("\n===============================================\n");
    printf("           ALGORITHM COMPARISON SUMMARY        \n");
    printf("===============================================\n");
    printf("KNAPSACK ALGORITHMS:\n");
    printf("--Fractional (Greedy): O(n log n) - Optimal for divisible items\n\n");
    printf("MST ALGORITHMS:\n");
    printf("--Prim's: O(V²) - Good for dense graphs\n");
    printf("--Kruskal's: O(E log E) - Good for sparse graphs\n");
    printf("===============================================\n");
    
    return 0;
}
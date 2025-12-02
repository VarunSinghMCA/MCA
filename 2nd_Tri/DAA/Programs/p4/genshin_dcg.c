#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

#define MAXV 50
#define LABEL_LEN 50

// ===================== GRAPH STRUCTURES =====================
typedef struct AdjNode {
    int v;
    struct AdjNode *next;
} AdjNode;

typedef struct {
    int V;
    char labels[MAXV][LABEL_LEN];
    AdjNode *adj[MAXV];
} Graph;

// ===================== FUNCTION DECLARATIONS =====================
void buildResetGraphMenu(Graph *g);
void loadSampleDataset(Graph *g);
void displayGraph(Graph *g);
void topologicalSort(Graph *g);

// ===================== UTILITY FUNCTIONS =====================
int findVertex(Graph *g, char *label) {
    for (int i = 0; i < g->V; i++)
        if (strcmp(g->labels[i], label) == 0)
            return i;
    return -1;
}

bool addVertex(Graph *g, char *label) {
    if (findVertex(g, label) != -1) {
        printf("Vertex '%s' already exists!\n", label);
        return false;
    }
    if (g->V >= MAXV) {
        printf("Graph full!\n");
        return false;
    }
    strcpy(g->labels[g->V], label);
    g->adj[g->V] = NULL;
    g->V++;
    return true;
}

bool addEdge(Graph *g, char *from, char *to) {
    int u = findVertex(g, from), v = findVertex(g, to);
    if (u == -1 || v == -1) {
        printf("Invalid vertices!\n");
        return false;
    }
    if (u == v) {
        printf("Self-loop not allowed!\n");
        return false;
    }
    AdjNode *p = g->adj[u];
    while (p) {
        if (p->v == v) {
            printf("Parallel edge not allowed!\n");
            return false;
        }
        p = p->next;
    }
    AdjNode *node = malloc(sizeof(AdjNode));
    node->v = v;
    node->next = g->adj[u];
    g->adj[u] = node;
    return true;
}

void removeEdge(Graph *g, char *from, char *to) {
    int u = findVertex(g, from), v = findVertex(g, to);
    if (u == -1 || v == -1) {
        printf("Invalid vertices!\n");
        return;
    }
    AdjNode *curr = g->adj[u], *prev = NULL;
    while (curr) {
        if (curr->v == v) {
            if (prev)
                prev->next = curr->next;
            else
                g->adj[u] = curr->next;
            free(curr);
            printf("Edge %s -> %s removed.\n", from, to);
            return;
        }
        prev = curr;
        curr = curr->next;
    }
    printf("Edge not found!\n");
}

void removeVertex(Graph *g, char *label) {
    int idx = findVertex(g, label);
    if (idx == -1) {
        printf("Vertex not found!\n");
        return;
    }

    // Remove all outgoing edges
    AdjNode *p = g->adj[idx];
    while (p) {
        AdjNode *temp = p;
        p = p->next;
        free(temp);
    }
    g->adj[idx] = NULL;

    // Remove all incoming edges
    for (int i = 0; i < g->V; i++) {
        if (i == idx) continue;
        AdjNode *curr = g->adj[i], *prev = NULL;
        while (curr) {
            if (curr->v == idx) {
                if (prev)
                    prev->next = curr->next;
                else
                    g->adj[i] = curr->next;
                free(curr);
                break;
            }
            prev = curr;
            curr = curr->next;
        }
    }

    // Shift vertices
    for (int i = idx; i < g->V - 1; i++) {
        strcpy(g->labels[i], g->labels[i + 1]);
        g->adj[i] = g->adj[i + 1];
    }
    g->V--;

    // Update edges
    for (int i = 0; i < g->V; i++) {
        AdjNode *node = g->adj[i];
        while (node) {
            if (node->v > idx)
                node->v--;
            node = node->next;
        }
    }
    printf("Vertex '%s' removed successfully.\n", label);
}

void clearGraph(Graph *g) {
    for (int i = 0; i < g->V; i++) {
        AdjNode *p = g->adj[i];
        while (p) {
            AdjNode *temp = p;
            p = p->next;
            free(temp);
        }
        g->adj[i] = NULL;
    }
    g->V = 0;
    printf("Graph cleared.\n");
}

void displayGraph(Graph *g) {
    printf("\n--- Current DAG (Adjacency List) ---\n");
    for (int i = 0; i < g->V; i++) {
        printf("%s -> ", g->labels[i]);
        AdjNode *p = g->adj[i];
        while (p) {
            printf("%s ", g->labels[p->v]);
            p = p->next;
        }
        printf("\n");
    }
}

// ===================== TOPOLOGICAL SORT =====================
void topologicalSort(Graph *g) {
    int indeg[MAXV] = {0};
    for (int i = 0; i < g->V; i++) {
        AdjNode *p = g->adj[i];
        while (p) {
            indeg[p->v]++;
            p = p->next;
        }
    }

    int queue[MAXV], front = 0, rear = 0;
    for (int i = 0; i < g->V; i++)
        if (indeg[i] == 0)
            queue[rear++] = i;

    int count = 0;
    printf("\nTopological Order: ");
    while (front < rear) {
        int u = queue[front++];
        printf("%s ", g->labels[u]);
        count++;
        AdjNode *p = g->adj[u];
        while (p) {
            indeg[p->v]--;
            if (indeg[p->v] == 0)
                queue[rear++] = p->v;
            p = p->next;
        }
    }

    if (count != g->V)
        printf("\nCycle detected! Topological sort not possible.\n");
    else
        printf("\nTopological sorting complete.\n");
    printf("Complexity: O(V + E)\n");
}



// ===================== SAMPLE DATA =====================
void loadSampleDataset(Graph *g) {
    clearGraph(g);
    printf("\nLoaded sample dataset: Genshin Impact Quest Dependencies\n");
    addVertex(g, "Archon_Quest");
    addVertex(g, "Mondstadt_Story");
    addVertex(g, "Liyue_Story");
    addVertex(g, "Inazuma_Story");
    addVertex(g, "Sumeru_Story");

    addEdge(g, "Archon_Quest", "Mondstadt_Story");
    addEdge(g, "Mondstadt_Story", "Liyue_Story");
    addEdge(g, "Liyue_Story", "Inazuma_Story");
    addEdge(g, "Inazuma_Story", "Sumeru_Story");
    printf("Dataset loaded.\n");
}

// ===================== MAIN MENU =====================
void buildResetGraphMenu(Graph *g) {
    int ch;
    char a[LABEL_LEN], b[LABEL_LEN];
    do {
        printf("\n--- Build/Reset Graph Menu ---\n");
        printf("1. Add Vertex\n2. Add Directed Edge\n3. Remove Directed Edge\n4. Remove Vertex\n5. Clear Graph\n6. Back\nChoice: ");
        scanf("%d", &ch);
        switch (ch) {
            case 1: printf("Enter vertex label: "); scanf("%s", a); addVertex(g, a); break;
            case 2: printf("Enter edge (from to): "); scanf("%s %s", a, b); addEdge(g, a, b); break;
            case 3: printf("Enter edge (from to): "); scanf("%s %s", a, b); removeEdge(g, a, b); break;
            case 4: printf("Enter vertex label: "); scanf("%s", a); removeVertex(g, a); break;
            case 5: clearGraph(g); break;
        }
    } while (ch != 6);
}

int main() {
    Graph g = {0};
    int choice;
    do {
        printf("\n==== Genshin Impact Quest DAG ====\n");
        printf("1. Build/Reset Graph\n");
        printf("2. Load Sample Dataset\n");
        printf("3. Display Graph\n");
        printf("4. Topological Sort\n");
        printf("5. Exit\n");
        printf("Choice: ");
        scanf("%d", &choice);
        switch (choice) {
            case 1: buildResetGraphMenu(&g); break;
            case 2: loadSampleDataset(&g); break;
            case 3: displayGraph(&g); break;
            case 4: topologicalSort(&g); break;
            case 5: printf("Exiting program.\n"); break;
            default: printf("Invalid choice!\n");
        }
    } while (choice != 5);
    return 0;
}
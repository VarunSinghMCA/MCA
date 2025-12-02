/*
Genshin Dijkstra & Strassen App (C version)
Author: ChatGPT

Domain Note (4-6 lines):
Locations in Genshin (e.g., Mondstadt, Liyue Harbor, Dragonspine) are mapped to graph nodes.
Edge weights represent traversal cost (stamina/time/teleport cost) and are non-negative for Dijkstra.
For Strassen, matrices represent artifact stat matrices or team synergy matrices (rows = characters,
cols = artifact stats). Matrices use dummy integer data for demonstration.

Features:
- Console menu-driven interface (options 1-6)
- Static dummy datasets (small & medium) and ability for user input
- Dijkstra's algorithm with detailed intermediate traces, distance table, and finalized nodes
- Strassen's matrix multiplication with threshold k to switch to classical algorithm
- Classical multiplication for correctness checks and runtime comparisons
- Input validation and clear messages
- Simple log capture (kept in memory) shown via menu option 4

Build: gcc -O2 -std=c11 -o genshin_app Genshin_Dijkstra_Strassen_App.c
Run: ./genshin_app
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>
#include <time.h>
#include <math.h>

#define MAX_NODES 50
#define MAX_LOGS 1000
#define LOG_MSG_LEN 256

/* -------------------- Logging -------------------- */
static char *logs[MAX_LOGS];
static int log_count = 0;

void add_log(const char *fmt, ...) {
    if (log_count >= MAX_LOGS) return;
    char buf[LOG_MSG_LEN];
    va_list ap;
    va_start(ap, fmt);
    vsnprintf(buf, sizeof(buf), fmt, ap);
    va_end(ap);
    logs[log_count] = strdup(buf);
    log_count++;
}

void show_logs() {
    if (log_count == 0) {
        printf("\nNo logs yet. Run an algorithm first.\n");
        return;
    }
    printf("\n--- Intermediate Traces / Logs ---\n");
    for (int i = 0; i < log_count; ++i) {
        printf("%s\n", logs[i]);
    }
    printf("--- End of Logs ---\n");
}

void clear_logs() {
    for (int i = 0; i < log_count; ++i) free(logs[i]);
    log_count = 0;
}

/* -------------------- Graph representation for Dijkstra -------------------- */
typedef struct {
    char name[64];
    int id;
} Node;

typedef struct {
    int n; // number of nodes
    int adj[MAX_NODES][MAX_NODES]; // adjacency matrix with weights; INT_MAX means no edge
    Node nodes[MAX_NODES];
} Graph;

void init_graph(Graph *g) {
    g->n = 0;
    for (int i = 0; i < MAX_NODES; ++i)
        for (int j = 0; j < MAX_NODES; ++j)
            g->adj[i][j] = INT_MAX;
}

int add_node(Graph *g, const char *name) {
    if (g->n >= MAX_NODES) return -1;
    strncpy(g->nodes[g->n].name, name, sizeof(g->nodes[0].name)-1);
    g->nodes[g->n].name[sizeof(g->nodes[0].name)-1] = '\0';
    g->nodes[g->n].id = g->n;
    return g->n++;
}

int find_node_by_name(Graph *g, const char *name) {
    for (int i = 0; i < g->n; ++i) {
        if (strcmp(g->nodes[i].name, name) == 0) return i;
    }
    return -1;
}

void add_edge(Graph *g, int u, int v, int w) {
    if (u < 0 || v < 0 || u >= g->n || v >= g->n) return;
    g->adj[u][v] = w;
}

void print_graph(Graph *g) {
    printf("Graph nodes (%d):\n", g->n);
    for (int i = 0; i < g->n; ++i) printf("%d: %s\n", i, g->nodes[i].name);
    printf("Adjacency (weights, INF means no edge):\n");
    printf("\t");
    for (int i = 0; i < g->n; ++i) printf("%d\t", i);
    printf("\n");
    for (int i = 0; i < g->n; ++i) {
        printf("%d\t", i);
        for (int j = 0; j < g->n; ++j) {
            if (g->adj[i][j] == INT_MAX) printf("INF\t");
            else printf("%d\t", g->adj[i][j]);
        }
        printf("\n");
    }
}

/* -------------------- Dijkstra (O(V^2) style for trace clarity) -------------------- */

void dijkstra(Graph *g, int src, int *dist_out, int *prev_out, int *finalized_out, int *finalized_count) {
    int n = g->n;
    int *dist = (int*)malloc(sizeof(int)*n);
    int *prev = (int*)malloc(sizeof(int)*n);
    int *finalized = (int*)calloc(n, sizeof(int));

    for (int i = 0; i < n; ++i) { dist[i] = INT_MAX; prev[i] = -1; }
    dist[src] = 0;
    add_log("Initialized distances with source=%s (id=%d)", g->nodes[src].name, src);

    int count = 0;
    while (count < n) {
        int u = -1, best = INT_MAX;
        for (int i = 0; i < n; ++i) {
            if (!finalized[i] && dist[i] < best) { best = dist[i]; u = i; }
        }
        if (u == -1) break; // remaining unreachable
        add_log("Selected node '%s' (id=%d) with tentative dist=%s", g->nodes[u].name, u, (dist[u]==INT_MAX?"INF":""));
        if (dist[u] != INT_MAX) add_log("  numeric dist=%d", dist[u]);
        finalized[u] = 1;
        for (int v = 0; v < n; ++v) {
            if (finalized[v]) continue;
            if (g->adj[u][v] == INT_MAX) continue;
            int w = g->adj[u][v];
            if (w < 0) {
                add_log("Error: Negative edge weight detected (%d -> %d = %d). Aborting.", u, v, w);
                // pass through and abort gracefully
                free(dist); free(prev); free(finalized);
                return;
            }
            long alt_l = (dist[u]==INT_MAX ? LONG_MAX : (long)dist[u] + w);
            add_log("Considering edge %s -> %s (w=%d). alt=%s vs dist[%s]=%s",
                    g->nodes[u].name, g->nodes[v].name, w,
                    (alt_l==LONG_MAX?"INF":""), g->nodes[v].name, (dist[v]==INT_MAX?"INF":""));
            if (alt_l != LONG_MAX && alt_l < dist[v]) {
                dist[v] = (int)alt_l;
                prev[v] = u;
                add_log("Updated dist[%s] = %d; prev[%s] = %s", g->nodes[v].name, dist[v], g->nodes[v].name, g->nodes[u].name);
            }
        }
        count++;
    }

    // copy out
    for (int i = 0; i < n; ++i) {
        dist_out[i] = dist[i];
        prev_out[i] = prev[i];
        finalized_out[i] = finalized[i];
        if (finalized[i]) (*finalized_count)++;
    }
    add_log("Dijkstra finished");
    free(dist); free(prev); free(finalized);
}

void print_distance_table(Graph *g, int *dist, int *prev) {
    printf("\nDistance Table:\n");
    printf("Node\tDistance\tPredecessor\n");
    for (int i = 0; i < g->n; ++i) {
        printf("%s\t", g->nodes[i].name);
        if (dist[i] == INT_MAX) printf("INF\t\t"); else printf("%d\t\t", dist[i]);
        if (prev[i] == -1) printf("-\n"); else printf("%s\n", g->nodes[prev[i]].name);
    }
}

void reconstruct_path(Graph *g, int *prev, int target) {
    if (target < 0 || target >= g->n) { printf("Invalid target\n"); return; }
    int *stack = (int*)malloc(sizeof(int)*g->n);
    int top = 0;
    int cur = target;
    while (cur != -1) {
        stack[top++] = cur;
        cur = prev[cur];
    }
    printf("Path: ");
    for (int i = top-1; i >= 0; --i) {
        printf("%s", g->nodes[stack[i]].name);
        if (i) printf(" -> ");
    }
    printf("\n");
    free(stack);
}

/* -------------------- Strassen's Matrix Multiplication -------------------- */

int is_power_of_two(int n) { return (n & (n-1))==0; }
int next_pow2(int n) { int p = 1; while (p < n) p <<= 1; return p; }

int **alloc_matrix(int n) {
    int **m = (int**)malloc(sizeof(int*)*n);
    for (int i = 0; i < n; ++i) m[i] = (int*)calloc(n, sizeof(int));
    return m;
}

void free_matrix(int **m, int n) { for (int i = 0; i < n; ++i) free(m[i]); free(m); }

int **pad_matrix(int **A, int n, int p) {
    int **B = alloc_matrix(p);
    for (int i = 0; i < n; ++i)
        for (int j = 0; j < n; ++j) B[i][j] = A[i][j];
    return B;
}

int **unpad_matrix(int **A, int p, int n) {
    int **B = alloc_matrix(n);
    for (int i = 0; i < n; ++i)
        for (int j = 0; j < n; ++j) B[i][j] = A[i][j];
    return B;
}

int **add_mat(int **A, int **B, int n) {
    int **C = alloc_matrix(n);
    for (int i = 0; i < n; ++i)
        for (int j = 0; j < n; ++j) C[i][j] = A[i][j] + B[i][j];
    return C;
}

int **sub_mat(int **A, int **B, int n) {
    int **C = alloc_matrix(n);
    for (int i = 0; i < n; ++i)
        for (int j = 0; j < n; ++j) C[i][j] = A[i][j] - B[i][j];
    return C;
}

int **classical_mult(int **A, int **B, int n) {
    int **C = alloc_matrix(n);
    for (int i = 0; i < n; ++i) {
        for (int k = 0; k < n; ++k) {
            int aik = A[i][k];
            for (int j = 0; j < n; ++j) {
                C[i][j] += aik * B[k][j];
            }
        }
    }
    return C;
}

int **strassen_rec(int **A, int **B, int n, int k) {
    add_log("Strassen called with n=%d, k=%d", n, k);
    if (n <= k) {
        add_log("n <= k (%d <= %d), using classical multiplication", n, k);
        return classical_mult(A, B, n);
    }
    if (n == 1) {
        int **C = alloc_matrix(1);
        C[0][0] = A[0][0] * B[0][0];
        return C;
    }
    int mid = n/2;
    // allocate submatrices
    int **A11 = alloc_matrix(mid), **A12 = alloc_matrix(mid), **A21 = alloc_matrix(mid), **A22 = alloc_matrix(mid);
    int **B11 = alloc_matrix(mid), **B12 = alloc_matrix(mid), **B21 = alloc_matrix(mid), **B22 = alloc_matrix(mid);
    for (int i = 0; i < mid; ++i) {
        for (int j = 0; j < mid; ++j) {
            A11[i][j] = A[i][j]; A12[i][j] = A[i][j+mid];
            A21[i][j] = A[i+mid][j]; A22[i][j] = A[i+mid][j+mid];
            B11[i][j] = B[i][j]; B12[i][j] = B[i][j+mid];
            B21[i][j] = B[i+mid][j]; B22[i][j] = B[i+mid][j+mid];
        }
    }
    add_log("Divided matrices into quadrants (mid=%d)", mid);
    int **M1 = strassen_rec(add_mat(A11, A22, mid), add_mat(B11, B22, mid), mid, k);
    int **M2 = strassen_rec(add_mat(A21, A22, mid), B11, mid, k);
    int **M3 = strassen_rec(A11, sub_mat(B12, B22, mid), mid, k);
    int **M4 = strassen_rec(A22, sub_mat(B21, B11, mid), mid, k);
    int **M5 = strassen_rec(add_mat(A11, A12, mid), B22, mid, k);
    int **M6 = strassen_rec(sub_mat(A21, A11, mid), add_mat(B11, B12, mid), mid, k);
    int **M7 = strassen_rec(sub_mat(A12, A22, mid), add_mat(B21, B22, mid), mid, k);

    int **C11 = add_mat(sub_mat(add_mat(M1, M4, mid), M5, mid), M7, mid);
    int **C12 = add_mat(M3, M5, mid);
    int **C21 = add_mat(M2, M4, mid);
    int **C22 = add_mat(sub_mat(add_mat(M1, M3, mid), M2, mid), M6, mid);

    int **C = alloc_matrix(n);
    for (int i = 0; i < mid; ++i) {
        for (int j = 0; j < mid; ++j) {
            C[i][j] = C11[i][j];
            C[i][j+mid] = C12[i][j];
            C[i+mid][j] = C21[i][j];
            C[i+mid][j+mid] = C22[i][j];
        }
    }

    // free allocated subblocks and temporaries
    free_matrix(A11, mid); free_matrix(A12, mid); free_matrix(A21, mid); free_matrix(A22, mid);
    free_matrix(B11, mid); free_matrix(B12, mid); free_matrix(B21, mid); free_matrix(B22, mid);
    free_matrix(M1, mid); free_matrix(M2, mid); free_matrix(M3, mid); free_matrix(M4, mid);
    free_matrix(M5, mid); free_matrix(M6, mid); free_matrix(M7, mid);
    free_matrix(C11, mid); free_matrix(C12, mid); free_matrix(C21, mid); free_matrix(C22, mid);

    add_log("Combined quadrants into result of size %d", n);
    return C;
}

int **strassen(int **A, int n, int k) {
    int p = next_pow2(n);
    int **Ap = (p==n)? A : pad_matrix(A, n, p);
    int **Bp = (p==n)? NULL : pad_matrix(A, n, p); // Note: B passed in caller; we'll avoid reuse here
    // we changed signature: we will accept already-padded matrices in caller for simplicity
    return NULL; // placeholder: we use strassen_rec with already-padded matrices in the menu flow
}

/* -------------------- Helpers for matrices and tests -------------------- */

int **create_random_matrix(int n) {
    int **M = alloc_matrix(n);
    for (int i = 0; i < n; ++i)
        for (int j = 0; j < n; ++j)
            M[i][j] = rand() % 6; // small integers 0..5
    return M;
}

int matrices_equal(int **A, int **B, int n) {
    for (int i = 0; i < n; ++i)
        for (int j = 0; j < n; ++j)
            if (A[i][j] != B[i][j]) return 0;
    return 1;
}

void print_matrix(int **M, int n) {
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) printf("%d ", M[i][j]);
        printf("\n");
    }
}

/* -------------------- Menu-driven interaction -------------------- */

void load_dummy_graphs(Graph *small, Graph *medium) {
    init_graph(small);
    add_node(small, "Statue");
    add_node(small, "Windrise");
    add_node(small, "Mondstadt Gate");
    add_node(small, "Wolfhook");
    add_node(small, "Springvale");
    add_edge(small, 0,1,5); add_edge(small,1,0,5);
    add_edge(small,0,2,2); add_edge(small,2,0,2);
    add_edge(small,1,3,4); add_edge(small,2,1,1);
    add_edge(small,2,4,7); add_edge(small,3,1,4);
    add_edge(small,4,2,7);

    init_graph(medium);
    add_node(medium, "Liyue Harbor");
    add_node(medium, "Dawn Winery");
    add_node(medium, "Jueyun Karst");
    add_node(medium, "Qingce Village");
    add_node(medium, "Wolvendom");
    add_node(medium, "Dragonspine");
    add_edge(medium,0,1,6); add_edge(medium,1,0,6);
    add_edge(medium,0,2,10); add_edge(medium,2,0,10);
    add_edge(medium,1,4,8); add_edge(medium,1,5,20);
    add_edge(medium,2,3,4); add_edge(medium,3,2,4);
    add_edge(medium,3,4,3); add_edge(medium,4,1,8);
    add_edge(medium,4,5,12); add_edge(medium,5,1,20);
}

void run_dijkstra_menu(Graph *current_graph) {
    printf("\n-- Run Dijkstra's SSSP --\n");
    print_graph(current_graph);
    char srcname[64];
    printf("Enter source node name: ");
    getchar(); // consume newline
    if (!fgets(srcname, sizeof(srcname), stdin)) return;
    srcname[strcspn(srcname, "\n")] = '\0';
    int src = find_node_by_name(current_graph, srcname);
    if (src == -1) { printf("Invalid source. Aborting Dijkstra run.\n"); return; }
    int n = current_graph->n;
    int *dist = (int*)malloc(sizeof(int)*n);
    int *prev = (int*)malloc(sizeof(int)*n);
    int *finalized = (int*)malloc(sizeof(int)*n);
    for (int i = 0; i < n; ++i) finalized[i] = 0;
    int finalized_count = 0;
    dijkstra(current_graph, src, dist, prev, finalized, &finalized_count);
    print_distance_table(current_graph, dist, prev);
    printf("\nFinalized nodes: ");
    for (int i = 0; i < n; ++i) if (finalized[i]) printf("%s, ", current_graph->nodes[i].name);
    printf("\n");
    char tgtname[64];
    printf("Enter target node to reconstruct path (or press Enter to skip): ");
    if (!fgets(tgtname, sizeof(tgtname), stdin)) { free(dist); free(prev); free(finalized); return; }
    tgtname[strcspn(tgtname, "\n")] = '\0';
    if (strlen(tgtname) > 0) {
        int tgt = find_node_by_name(current_graph, tgtname);
        if (tgt == -1) printf("Invalid target\n");
        else reconstruct_path(current_graph, prev, tgt);
    }
    free(dist); free(prev); free(finalized);
}

void run_strassen_menu() {
    printf("\n-- Run Strassen's Matrix Multiplication --\n");
    int n; printf("Enter matrix size n (square matrices): ");
    if (scanf("%d", &n) != 1) { printf("Invalid size\n"); return; }
    if (n <= 0) { printf("Size must be positive\n"); return; }
    int k; printf("Enter threshold k to switch to classical (e.g., 1,16,32). Default 32: ");
    if (scanf("%d", &k) != 1) { k = 32; }
    if (k <= 0) k = 32;

    // create random matrices
    int **A = create_random_matrix(n);
    int **B = create_random_matrix(n);

    int p = next_pow2(n);
    int **Ap = (p==n)? A : pad_matrix(A, n, p);
    int **Bp = (p==n)? B : pad_matrix(B, n, p);

    clock_t t0 = clock();
    int **C_strassen_p = strassen_rec(Ap, Bp, p, k);
    clock_t t1 = clock();
    double t_strassen = (double)(t1 - t0) / CLOCKS_PER_SEC;

    int **C_strassen = (p==n)? C_strassen_p : unpad_matrix(C_strassen_p, p, n);

    t0 = clock();
    int **C_classical = classical_mult(A, B, n);
    t1 = clock();
    double t_classical = (double)(t1 - t0) / CLOCKS_PER_SEC;

    int same = matrices_equal(C_classical, C_strassen, n);
    add_log("Strassen vs Classical correctness: %s", same?"PASS":"FAIL");

    printf("Strassen time: %.6f s; Classical time: %.6f s\n", t_strassen, t_classical);
    printf("Correctness check: %s\n", same?"PASS":"FAIL");
    if (n <= 8) {
        printf("\nMatrix A:\n"); print_matrix(A, n);
        printf("\nMatrix B:\n"); print_matrix(B, n);
        printf("\nClassical Product:\n"); print_matrix(C_classical, n);
    }

    // free
    if (p != n) free_matrix(Ap, p); if (p != n) free_matrix(Bp, p); if (p != n) free_matrix(C_strassen_p, p);
    free_matrix(A, n); free_matrix(B, n); free_matrix(C_classical, n);
    if (p==n) free_matrix(C_strassen, n); else free_matrix(C_strassen, n);
}

void compare_runtimes_and_discuss() {
    printf("\n-- Compare Runtimes & Discuss --\n");
    int sizes[3] = {8,16,32};
    for (int idx = 0; idx < 3; ++idx) {
        int n = sizes[idx];
        int **A = create_random_matrix(n);
        int **B = create_random_matrix(n);
        int p = next_pow2(n);
        int **Ap = (p==n)? A : pad_matrix(A, n, p);
        int **Bp = (p==n)? B : pad_matrix(B, n, p);
        clock_t t0 = clock();
        int **C_classical = classical_mult(A, B, n);
        clock_t t1 = clock(); double t_classical = (double)(t1 - t0) / CLOCKS_PER_SEC;
        t0 = clock();
        int **C_strassen_p = strassen_rec(Ap, Bp, p, 32);
        t1 = clock(); double t_strassen = (double)(t1 - t0) / CLOCKS_PER_SEC;
        add_log("Runtime n=%d: classical=%.6fs strassen=%.6fs", n, t_classical, t_strassen);
        printf("n=%d: classical=%.6fs | strassen=%.6fs\n", n, t_classical, t_strassen);
        free_matrix(A, n); free_matrix(B, n); free_matrix(C_classical, n); free_matrix(C_strassen_p, p);
    }
    printf("\nDiscussion:\n");
    printf("- Strassen reduces multiplications per divide step (8 -> 7), giving O(n^{log2 7}) ~ O(n^{2.81}) vs O(n^3) classical.\n");
    printf("- In practice, recursion and copying overhead in languages like C/Python may make Strassen slower for small n; a threshold k is used.\n");
    printf("- Padding to the next power-of-two increases intermediate sizes; for large inputs, optimized libraries (BLAS) or blocked algorithms are preferable.\n");
}

int main() {
    srand((unsigned)time(NULL));
    Graph small, medium;
    load_dummy_graphs(&small, &medium);
    Graph *current = &small;
    int choice;
    printf("Welcome to Genshin Algorithms Demo (C)\n");
    while (1) {
        printf("\n=== Menu ===\n");
        printf("1. Load/Change Domain Dataset (current: %s)\n", (current==&small)?"small":"medium");
        printf("2. Run Dijkstra's SSSP\n");
        printf("3. Run Strassen (with option to set k)\n");
        printf("4. Show Intermediate Traces / Logs\n");
        printf("5. Compare Runtimes & Discuss\n");
        printf("6. Exit\n");
        printf("Choose an option [1-6]: ");
        if (scanf("%d", &choice) != 1) { printf("Invalid input. Exiting.\n"); break; }
        if (choice == 1) {
            printf("Datasets: (a) small, (b) medium\nChoose dataset: ");
            char c; scanf(" %c", &c);
            if (c == 'a' || c == 'A') { current = &small; add_log("Dataset changed to small graph"); printf("Loaded small Genshin graph.\n"); }
            else if (c == 'b' || c == 'B') { current = &medium; add_log("Dataset changed to medium graph"); printf("Loaded medium Genshin graph.\n"); }
            else printf("Invalid choice\n");
        } else if (choice == 2) {
            run_dijkstra_menu(current);
        } else if (choice == 3) {
            run_strassen_menu();
        } else if (choice == 4) {
            show_logs();
        } else if (choice == 5) {
            compare_runtimes_and_discuss();
        } else if (choice == 6) {
            printf("Goodbye!\n");
            break;
        } else {
            printf("Invalid option.\n");
        }
    }
    clear_logs();
    return 0;
}

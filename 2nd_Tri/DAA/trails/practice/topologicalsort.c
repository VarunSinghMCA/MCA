#include <stdio.h>
#include <stdlib.h>

#define MAX 100

int u,v,n,e;
int adj[MAX][MAX];
int indegree[MAX];

void takeInput(){
    printf("Enter Number of vertices/Nodes: ");
    scanf("%d",&n);
    printf("Enter Number of edges: ");
    scanf("%d",&e);
    
    for (int i=0;i<MAX;i++){
        indegree[i]=0;
        for(int j=0;j<n;j++){
            adj[i][j]=0;
        }
    }

    printf("Enter edges (u v) format where u -> v:\n");
    for (int i=0; i<e; i++){
        printf("Edge %d: ", i+1);
        scanf("%d %d", &u, &v);
        u --;
        v --;
        adj[u][v] = 1;
        indegree[v]++;
    }
}

// Using BFS for Topological Sort
void TopologicaSort(){
    int queue[MAX], front=0, rear=0, count=0;
    // input nodes with indegree = 0
    for (int i=0; i<n; i++){
        if (indegree[i]==0){
            queue[rear] = i;
            rear++;
        }
    }

    while(front<rear){
        int u = queue[front++];
        printf("%d", u + 1);
        count++;

        for(int j=0;j<n;j++){
            if (adj[u][j]==1){
                indegree[j]--;
                if (indegree[j]==0){
                    queue[rear]=j;
                    rear++;
                }
            }
        }
        printf("\n");
    }

    if (count != n) {
        printf("There exists a cycle in the graph\n");
    } else {
        printf("\nTopological Sort completed successfully.\n");
    }
}

int main(){
    takeInput();
    printf("Topological Sort Order:\n");
    TopologicaSort();
    return 0;
}
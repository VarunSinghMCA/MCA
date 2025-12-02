/* queue_app.c
   Menu-driven application implementing:
   1) Normal (Linear) Queue
   2) Circular Queue
   3) Priority Queue (MAX-priority)
   4) Double Ended Queue (Deque)
   Implementation: arrays (user-specified capacity up to MAX_CAPACITY)
   Author: ChatGPT (for user)
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_CAPACITY 100

/* Utility: flush stdin on invalid input */
void flush_stdin() {
    int c;
    while ((c = getchar()) != '\n' && c != EOF) {}
}

/* ---------- Normal (Linear) Queue using array ---------- */
typedef struct {
    int *arr;
    int capacity;
    int front; // index of front element
    int rear;  // index of last element
    int size;
} LinearQueue;

void initLinear(LinearQueue *q, int cap) {
    q->arr = (int*)malloc(sizeof(int)*cap);
    q->capacity = cap;
    q->front = 0;
    q->rear = -1;
    q->size = 0;
}

int isEmptyLinear(LinearQueue *q) { return q->size == 0; }
int isFullLinear(LinearQueue *q) { return q->size == q->capacity; }

void enqueueLinear(LinearQueue *q, int item) {
    if (isFullLinear(q)) {
        printf("Error: Linear Queue Overflow (capacity=%d)\n", q->capacity);
        return;
    }
    q->rear++;
    q->arr[q->rear] = item;
    q->size++;
    printf("Enqueued %d into Linear Queue.\n", item);
}

int dequeueLinear(LinearQueue *q, int *out) {
    if (isEmptyLinear(q)) {
        printf("Error: Linear Queue Underflow\n");
        return 0;
    }
    *out = q->arr[q->front];
    q->front++;
    q->size--;
    printf("Dequeued %d from Linear Queue.\n", *out);
    return 1;
}

void displayLinear(LinearQueue *q) {
    if (isEmptyLinear(q)) {
        printf("[Linear Queue empty]\n");
        return;
    }
    printf("Linear Queue elements (front->rear): ");
    for (int i = q->front; i <= q->rear; ++i) {
        printf("%d ", q->arr[i]);
    }
    printf("\n");
}

void statusLinear(LinearQueue *q) {
    printf("Status: size=%d, front_index=%d, rear_index=%d\n", q->size, q->front, q->rear);
    displayLinear(q);
}

/* ---------- Circular Queue using array (wrap-around) ---------- */
typedef struct {
    int *arr;
    int capacity;
    int front; // index of front element (valid if size>0)
    int rear;  // index of last element (valid if size>0)
    int size;
} CircularQueue;

void initCircular(CircularQueue *q, int cap) {
    q->arr = (int*)malloc(sizeof(int)*cap);
    q->capacity = cap;
    q->front = 0;
    q->rear = -1;
    q->size = 0;
}

int isEmptyCircular(CircularQueue *q) { return q->size == 0; }
int isFullCircular(CircularQueue *q) { return q->size == q->capacity; }

void enqueueCircular(CircularQueue *q, int item) {
    if (isFullCircular(q)) {
        printf("Error: Circular Queue Overflow (capacity=%d)\n", q->capacity);
        return;
    }
    q->rear = (q->rear + 1) % q->capacity;
    q->arr[q->rear] = item;
    q->size++;
    printf("Enqueued %d into Circular Queue.\n", item);
}

int dequeueCircular(CircularQueue *q, int *out) {
    if (isEmptyCircular(q)) {
        printf("Error: Circular Queue Underflow\n");
        return 0;
    }
    *out = q->arr[q->front];
    q->front = (q->front + 1) % q->capacity;
    q->size--;
    printf("Dequeued %d from Circular Queue.\n", *out);
    return 1;
}

void displayCircular(CircularQueue *q) {
    if (isEmptyCircular(q)) {
        printf("[Circular Queue empty]\n");
        return;
    }
    printf("Circular Queue elements (front->rear): ");
    int idx = q->front;
    for (int i = 0; i < q->size; ++i) {
        printf("%d ", q->arr[idx]);
        idx = (idx + 1) % q->capacity;
    }
    printf("\n");
}

void statusCircular(CircularQueue *q) {
    printf("Status: size=%d, front_index=%d, rear_index=%d\n", q->size, q->front, q->rear);
    displayCircular(q);
}

/* ---------- Priority Queue (max-priority) using ordered array of struct ---------- */
typedef struct {
    int value;
    int priority;
} PriorityItem;

typedef struct {
    PriorityItem *arr;
    int capacity;
    int size;
} PriorityQueue;

void initPriority(PriorityQueue *pq, int cap) {
    pq->arr = (PriorityItem*)malloc(sizeof(PriorityItem)*cap);
    pq->capacity = cap;
    pq->size = 0;
}

int isEmptyPriority(PriorityQueue *pq) { return pq->size == 0; }
int isFullPriority(PriorityQueue *pq) { return pq->size == pq->capacity; }

/* Enqueue: insert in descending order of priority (so front is highest priority at index 0) */
void enqueuePriority(PriorityQueue *pq, int value, int priority) {
    if (isFullPriority(pq)) {
        printf("Error: Priority Queue Overflow (capacity=%d)\n", pq->capacity);
        return;
    }
    int i = pq->size - 1;
    // Shift elements with lower priority to the right
    while (i >= 0 && pq->arr[i].priority < priority) {
        pq->arr[i+1] = pq->arr[i];
        i--;
    }
    pq->arr[i+1].value = value;
    pq->arr[i+1].priority = priority;
    pq->size++;
    printf("Enqueued value=%d with priority=%d into Priority Queue.\n", value, priority);
}

/* Dequeue: remove the element with highest priority (index 0) */
int dequeuePriority(PriorityQueue *pq, PriorityItem *out) {
    if (isEmptyPriority(pq)) {
        printf("Error: Priority Queue Underflow\n");
        return 0;
    }
    *out = pq->arr[0];
    // shift left
    for (int i = 1; i < pq->size; ++i)
        pq->arr[i-1] = pq->arr[i];
    pq->size--;
    printf("Dequeued value=%d with priority=%d from Priority Queue.\n", out->value, out->priority);
    return 1;
}

void displayPriority(PriorityQueue *pq) {
    if (isEmptyPriority(pq)) {
        printf("[Priority Queue empty]\n");
        return;
    }
    printf("Priority Queue elements (value:priority) highest->lowest: ");
    for (int i = 0; i < pq->size; ++i) {
        printf("%d:%d ", pq->arr[i].value, pq->arr[i].priority);
    }
    printf("\n");
}

void statusPriority(PriorityQueue *pq) {
    printf("Status: size=%d, capacity=%d\n", pq->size, pq->capacity);
    displayPriority(pq);
}

/* ---------- Deque (Double Ended Queue) using circular array ---------- */
typedef struct {
    int *arr;
    int capacity;
    int front; // index of front element
    int rear;  // index of rear element
    int size;
} Deque;

void initDeque(Deque *d, int cap) {
    d->arr = (int*)malloc(sizeof(int)*cap);
    d->capacity = cap;
    d->front = -1;
    d->rear = 0;
    d->size = 0;
}

int isEmptyDeque(Deque *d) { return d->size == 0; }
int isFullDeque(Deque *d) { return d->size == d->capacity; }

/* Insert at front */
void insertFront(Deque *d, int item) {
    if (isFullDeque(d)) {
        printf("Error: Deque Overflow\n");
        return;
    }
    if (isEmptyDeque(d)) {
        d->front = 0;
        d->rear = 0;
    } else {
        d->front = (d->front - 1 + d->capacity) % d->capacity;
    }
    d->arr[d->front] = item;
    d->size++;
    printf("Inserted %d at front of Deque.\n", item);
}

/* Insert at rear */
void insertRear(Deque *d, int item) {
    if (isFullDeque(d)) {
        printf("Error: Deque Overflow\n");
        return;
    }
    if (isEmptyDeque(d)) {
        d->front = 0;
        d->rear = 0;
    } else {
        d->rear = (d->rear + 1) % d->capacity;
    }
    d->arr[d->rear] = item;
    d->size++;
    printf("Inserted %d at rear of Deque.\n", item);
}

/* Delete front */
int deleteFront(Deque *d, int *out) {
    if (isEmptyDeque(d)) {
        printf("Error: Deque Underflow\n");
        return 0;
    }
    *out = d->arr[d->front];
    if (d->size == 1) {
        d->front = -1;
        d->rear = 0;
    } else {
        d->front = (d->front + 1) % d->capacity;
    }
    d->size--;
    printf("Deleted %d from front of Deque.\n", *out);
    return 1;
}

/* Delete rear */
int deleteRear(Deque *d, int *out) {
    if (isEmptyDeque(d)) {
        printf("Error: Deque Underflow\n");
        return 0;
    }
    *out = d->arr[d->rear];
    if (d->size == 1) {
        d->front = -1;
        d->rear = 0;
    } else {
        d->rear = (d->rear - 1 + d->capacity) % d->capacity;
    }
    d->size--;
    printf("Deleted %d from rear of Deque.\n", *out);
    return 1;
}

/* getFront, getRear */
int getFront(Deque *d, int *out) {
    if (isEmptyDeque(d)) {
        printf("Deque is empty\n");
        return 0;
    }
    *out = d->arr[d->front];
    return 1;
}
int getRear(Deque *d, int *out) {
    if (isEmptyDeque(d)) {
        printf("Deque is empty\n");
        return 0;
    }
    *out = d->arr[d->rear];
    return 1;
}

void displayDeque(Deque *d) {
    if (isEmptyDeque(d)) {
        printf("[Deque empty]\n");
        return;
    }
    printf("Deque elements (front->rear): ");
    int idx = d->front;
    for (int i = 0; i < d->size; ++i) {
        printf("%d ", d->arr[idx]);
        idx = (idx + 1) % d->capacity;
    }
    printf("\n");
}

void statusDeque(Deque *d) {
    printf("Status: size=%d, front_index=%d, rear_index=%d, capacity=%d\n", d->size, d->front, d->rear, d->capacity);
    displayDeque(d);
}

/* ---------- Helper input safe-read ---------- */
int read_int_with_prompt(const char *prompt) {
    int x;
    while (1) {
        printf("%s", prompt);
        if (scanf("%d", &x) != 1) {
            printf("Invalid input; please enter an integer.\n");
            flush_stdin();
            continue;
        }
        flush_stdin();
        return x;
    }
}

/* ---------- Domain & Scenarios (printed from program for reference) ---------- */
void print_domain_examples() {
    printf("\nDomain Integration Examples (for testing):\n");
    printf("1) Normal Queue (Ticketing counter): customers arrive -> enqueue, served -> dequeue.\n");
    printf("   Scenarios:\n");
    printf("   a) Morning ticket counter: enqueue 3 customers, dequeue 2 (service), check status.\n");
    printf("   b) Single-file checkout: enqueue until full -> overflow message.\n\n");

    printf("2) Circular Queue (CPU Round-Robin job scheduling): jobs placed into circular buffer.\n");
    printf("   Scenarios:\n");
    printf("   a) Insert jobs, rotate through them with dequeue/enqueue to simulate time-slice.\n");
    printf("   b) Buffer wrap-around test: fill, dequeue some, then enqueue more to force wrap.\n\n");

    printf("3) Priority Queue (Emergency Room triage) [Max-priority]: higher numeric priority treated earlier.\n");
    printf("   Scenarios:\n");
    printf("   a) Enqueue patients with priorities 2,5,3 -> dequeue should return priority 5 first.\n");
    printf("   b) Insert with same priorities and test FIFO among same priority (this implementation keeps insertion order among equal priorities).\n\n");

    printf("4) Deque (Playlist / History). Double-ended operations:\n");
    printf("   Scenarios:\n");
    printf("   a) Insert songs to rear, insert urgent song to front, delete rear (skip), getFront/getRear.\n");
    printf("   b) Browser-like history: push new pages at rear, user navigates back (deleteRear) or open new tab (insertFront).\n\n");
}

/* ---------- Main UI Loop ---------- */
int main() {
    printf("Queue Application — Menu-driven (C, array-based implementations)\n");
    print_domain_examples();

    int mainChoice;
    while (1) {
        printf("\nMain Menu — Choose Queue Type:\n");
        printf("1: Normal Queue (Linear)\n");
        printf("2: Circular Queue\n");
        printf("3: Priority Queue (Max-priority) / Double Ended Queue (Deque)\n");
        printf("4: Exit\n");
        mainChoice = read_int_with_prompt("Enter option (1-4): ");

        if (mainChoice == 4) {
            printf("Exiting application. Goodbye!\n");
            break;
        }

        if (mainChoice == 1) {
            int cap = read_int_with_prompt("Enter capacity for Linear Queue (1-%d): ", MAX_CAPACITY);
            if (cap < 1 || cap > MAX_CAPACITY) {
                printf("Invalid capacity. Using default 10.\n");
                cap = 10;
            }
            LinearQueue q;
            initLinear(&q, cap);
            int opt;
            while (1) {
                printf("\nLinear Queue Menu: 1=enq 2=deq 3=isEmpty 4=isFull 5=display 6=status 7=back\n");
                opt = read_int_with_prompt("Choose: ");
                if (opt == 1) {
                    int v = read_int_with_prompt("Enter integer to enqueue: ");
                    enqueueLinear(&q, v);
                    statusLinear(&q);
                } else if (opt == 2) {
                    int out;
                    if (dequeueLinear(&q, &out)) statusLinear(&q);
                } else if (opt == 3) {
                    printf(isEmptyLinear(&q) ? "Linear Queue is Empty\n" : "Linear Queue is NOT empty\n");
                } else if (opt == 4) {
                    printf(isFullLinear(&q) ? "Linear Queue is Full\n" : "Linear Queue is NOT full\n");
                } else if (opt == 5) {
                    displayLinear(&q);
                } else if (opt == 6) {
                    statusLinear(&q);
                } else if (opt == 7) {
                    free(q.arr);
                    break;
                } else {
                    printf("Invalid selection.\n");
                }
            }
        } else if (mainChoice == 2) {
            int cap = read_int_with_prompt("Enter capacity for Circular Queue (1-%d): ", MAX_CAPACITY);
            if (cap < 1 || cap > MAX_CAPACITY) {
                printf("Invalid capacity. Using default 10.\n");
                cap = 10;
            }
            CircularQueue cq;
            initCircular(&cq, cap);
            int opt;
            while (1) {
                printf("\nCircular Queue Menu: 1=enq 2=deq 3=isEmpty 4=isFull 5=display 6=status 7=back\n");
                opt = read_int_with_prompt("Choose: ");
                if (opt == 1) {
                    int v = read_int_with_prompt("Enter integer to enqueue: ");
                    enqueueCircular(&cq, v);
                    statusCircular(&cq);
                } else if (opt == 2) {
                    int out;
                    if (dequeueCircular(&cq, &out)) statusCircular(&cq);
                } else if (opt == 3) {
                    printf(isEmptyCircular(&cq) ? "Circular Queue is Empty\n" : "Circular Queue is NOT empty\n");
                } else if (opt == 4) {
                    printf(isFullCircular(&cq) ? "Circular Queue is Full\n" : "Circular Queue is NOT full\n");
                } else if (opt == 5) {
                    displayCircular(&cq);
                } else if (opt == 6) {
                    statusCircular(&cq);
                } else if (opt == 7) {
                    free(cq.arr);
                    break;
                } else {
                    printf("Invalid selection.\n");
                }
            }
        } else if (mainChoice == 3) {
            printf("\nOption 3: Choose sub-type:\n1 = Priority Queue (Max-priority)\n2 = Deque (Double-Ended Queue)\n3 = Back\n");
            int sub = read_int_with_prompt("Choose: ");
            if (sub == 1) {
                int cap = read_int_with_prompt("Enter capacity for Priority Queue (1-%d): ", MAX_CAPACITY);
                if (cap < 1 || cap > MAX_CAPACITY) {
                    printf("Invalid capacity. Using default 10.\n");
                    cap = 10;
                }
                PriorityQueue pq;
                initPriority(&pq, cap);
                int opt;
                while (1) {
                    printf("\nPriority Queue Menu: 1=enq 2=deq 3=isEmpty 4=display 5=status 6=back\n");
                    opt = read_int_with_prompt("Choose: ");
                    if (opt == 1) {
                        int val = read_int_with_prompt("Enter value (int): ");
                        int pr = read_int_with_prompt("Enter priority (int, higher => served first): ");
                        enqueuePriority(&pq, val, pr);
                        statusPriority(&pq);
                    } else if (opt == 2) {
                        PriorityItem out;
                        if (dequeuePriority(&pq, &out)) statusPriority(&pq);
                    } else if (opt == 3) {
                        printf(isEmptyPriority(&pq) ? "Priority Queue is Empty\n" : "Priority Queue is NOT empty\n");
                    } else if (opt == 4) {
                        displayPriority(&pq);
                    } else if (opt == 5) {
                        statusPriority(&pq);
                    } else if (opt == 6) {
                        free(pq.arr);
                        break;
                    } else {
                        printf("Invalid selection.\n");
                    }
                }
            } else if (sub == 2) {
                int cap = read_int_with_prompt("Enter capacity for Deque (1-%d): ", MAX_CAPACITY);
                if (cap < 1 || cap > MAX_CAPACITY) {
                    printf("Invalid capacity. Using default 10.\n");
                    cap = 10;
                }
                Deque d;
                initDeque(&d, cap);
                int opt;
                while (1) {
                    printf("\nDeque Menu: 1=insertFront 2=insertRear 3=deleteFront 4=deleteRear 5=getFront 6=getRear 7=isEmpty 8=isFull 9=display 10=status 11=back\n");
                    opt = read_int_with_prompt("Choose: ");
                    if (opt == 1) {
                        int v = read_int_with_prompt("Enter value to insert at front: ");
                        insertFront(&d, v);
                        statusDeque(&d);
                    } else if (opt == 2) {
                        int v = read_int_with_prompt("Enter value to insert at rear: ");
                        insertRear(&d, v);
                        statusDeque(&d);
                    } else if (opt == 3) {
                        int out;
                        if (deleteFront(&d, &out)) statusDeque(&d);
                    } else if (opt == 4) {
                        int out;
                        if (deleteRear(&d, &out)) statusDeque(&d);
                    } else if (opt == 5) {
                        int out;
                        if (getFront(&d, &out)) printf("Front element = %d\n", out);
                    } else if (opt == 6) {
                        int out;
                        if (getRear(&d, &out)) printf("Rear element = %d\n", out);
                    } else if (opt == 7) {
                        printf(isEmptyDeque(&d) ? "Deque is Empty\n" : "Deque is NOT empty\n");
                    } else if (opt == 8) {
                        printf(isFullDeque(&d) ? "Deque is Full\n" : "Deque is NOT full\n");
                    } else if (opt == 9) {
                        displayDeque(&d);
                    } else if (opt == 10) {
                        statusDeque(&d);
                    } else if (opt == 11) {
                        free(d.arr);
                        break;
                    } else {
                        printf("Invalid selection.\n");
                    }
                }
            } else if (sub == 3) {
                continue;
            } else {
                printf("Invalid sub-option.\n");
            }
        } else {
            printf("Invalid main option. Please enter 1-4.\n");
        }
    } // main loop

    return 0;
}

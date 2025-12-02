#include <stdio.h>
#include <stdlib.h>

#define SIZE 5   // Fixed size of all queues

// ---------- NORMAL QUEUE ----------
int normalQueue[SIZE];
int frontN = -1, rearN = -1;

void enqueueNormal(int val) {
    if (rearN == SIZE - 1) {
        printf("Normal Queue Overflow!\n");
        return;
    }
    if (frontN == -1) frontN = 0;
    normalQueue[++rearN] = val;
    printf("Enqueued %d into Normal Queue\n", val);
}

void dequeueNormal() {
    if (frontN == -1 || frontN > rearN) {
        printf("Normal Queue Underflow!\n");
        return;
    }
    printf("Dequeued %d from Normal Queue\n", normalQueue[frontN++]);
}

void displayNormal() {
    if (frontN == -1 || frontN > rearN) {
        printf("Normal Queue Empty!\n");
        return;
    }
    printf("Normal Queue: ");
    for (int i = frontN; i <= rearN; i++) {
        printf("%d ", normalQueue[i]);
    }
    printf("\n");
}

// ---------- CIRCULAR QUEUE ----------
int circularQueue[SIZE];
int frontC = -1, rearC = -1;

void enqueueCircular(int val) {
    if ((frontC == 0 && rearC == SIZE - 1) || (rearC + 1) % SIZE == frontC) {
        printf("Circular Queue Overflow!\n");
        return;
    }
    if (frontC == -1) frontC = 0;
    rearC = (rearC + 1) % SIZE;
    circularQueue[rearC] = val;
    printf("Enqueued %d into Circular Queue\n", val);
}

void dequeueCircular() {
    if (frontC == -1) {
        printf("Circular Queue Underflow!\n");
        return;
    }
    printf("Dequeued %d from Circular Queue\n", circularQueue[frontC]);
    if (frontC == rearC) frontC = rearC = -1;
    else frontC = (frontC + 1) % SIZE;
}

void displayCircular() {
    if (frontC == -1) {
        printf("Circular Queue Empty!\n");
        return;
    }
    printf("Circular Queue: ");
    int i = frontC;
    while (1) {
        printf("%d ", circularQueue[i]);
        if (i == rearC) break;
        i = (i + 1) % SIZE;
    }
    printf("\n");
}

// ---------- PRIORITY QUEUE (MIN PRIORITY) ----------
int priorityQueue[SIZE], pqSize = 0;

void enqueuePriority(int val) {
    if (pqSize == SIZE) {
        printf("Priority Queue Overflow!\n");
        return;
    }
    priorityQueue[pqSize++] = val;
    printf("Enqueued %d into Priority Queue\n", val);
}

void dequeuePriority() {
    if (pqSize == 0) {
        printf("Priority Queue Underflow!\n");
        return;
    }
    int minIndex = 0;
    for (int i = 1; i < pqSize; i++) {
        if (priorityQueue[i] < priorityQueue[minIndex])
            minIndex = i;
    }
    printf("Dequeued %d from Priority Queue\n", priorityQueue[minIndex]);
    for (int i = minIndex; i < pqSize - 1; i++) {
        priorityQueue[i] = priorityQueue[i + 1];
    }
    pqSize--;
}

void displayPriority() {
    if (pqSize == 0) {
        printf("Priority Queue Empty!\n");
        return;
    }
    printf("Priority Queue: ");
    for (int i = 0; i < pqSize; i++) {
        printf("%d ", priorityQueue[i]);
    }
    printf("\n");
}

// ---------- DEQUE ----------
int deque[SIZE];
int frontD = -1, rearD = -1;

void insertFront(int val) {
    if ((frontD == 0 && rearD == SIZE - 1) || (frontD == rearD + 1)) {
        printf("Deque Overflow!\n");
        return;
    }
    if (frontD == -1) frontD = rearD = 0;
    else if (frontD == 0) frontD = SIZE - 1;
    else frontD--;
    deque[frontD] = val;
    printf("Inserted %d at front of Deque\n", val);
}

void insertRear(int val) {
    if ((frontD == 0 && rearD == SIZE - 1) || (frontD == rearD + 1)) {
        printf("Deque Overflow!\n");
        return;
    }
    if (frontD == -1) frontD = rearD = 0;
    else if (rearD == SIZE - 1) rearD = 0;
    else rearD++;
    deque[rearD] = val;
    printf("Inserted %d at rear of Deque\n", val);
}

void deleteFront() {
    if (frontD == -1) {
        printf("Deque Underflow!\n");
        return;
    }
    printf("Deleted %d from front of Deque\n", deque[frontD]);
    if (frontD == rearD) frontD = rearD = -1;
    else if (frontD == SIZE - 1) frontD = 0;
    else frontD++;
}

void deleteRear() {
    if (rearD == -1) {
        printf("Deque Underflow!\n");
        return;
    }
    printf("Deleted %d from rear of Deque\n", deque[rearD]);
    if (frontD == rearD) frontD = rearD = -1;
    else if (rearD == 0) rearD = SIZE - 1;
    else rearD--;
}

void displayDeque() {
    if (frontD == -1) {
        printf("Deque Empty!\n");
        return;
    }
    printf("Deque: ");
    int i = frontD;
    while (1) {
        printf("%d ", deque[i]);
        if (i == rearD) break;
        i = (i + 1) % SIZE;
    }
    printf("\n");
}

// ---------- MAIN MENU ----------
int main() {
    int choice, type, val;
    while (1) {
        printf("\n--- Queue Menu ---\n");
        printf("1. Normal Queue\n2. Circular Queue\n3. Priority Queue\n4. Deque\n5. Exit\n");
        printf("Enter choice: ");
        scanf("%d", &type);
        if (type == 5) break;

        switch (type) {
        case 1: // Normal Queue
            printf("1.Enqueue 2.Dequeue 3.Display 4.Back\n");
            scanf("%d", &choice);
            if (choice == 1) { scanf("%d", &val); enqueueNormal(val); }
            else if (choice == 2) dequeueNormal();
            else if (choice == 3) displayNormal();
            break;

        case 2: // Circular Queue
            printf("1.Enqueue 2.Dequeue 3.Display 4.Back\n");
            scanf("%d", &choice);
            if (choice == 1) { scanf("%d", &val); enqueueCircular(val); }
            else if (choice == 2) dequeueCircular();
            else if (choice == 3) displayCircular();
            break;

        case 3: // Priority Queue
            printf("1.Enqueue 2.Dequeue 3.Display 4.Back\n");
            scanf("%d", &choice);
            if (choice == 1) { scanf("%d", &val); enqueuePriority(val); }
            else if (choice == 2) dequeuePriority();
            else if (choice == 3) displayPriority();
            break;

        case 4: // Deque
            printf("1.InsertFront 2.InsertRear 3.DeleteFront 4.DeleteRear 5.Display 6.Back\n");
            scanf("%d", &choice);
            if (choice == 1) { scanf("%d", &val); insertFront(val); }
            else if (choice == 2) { scanf("%d", &val); insertRear(val); }
            else if (choice == 3) deleteFront();
            else if (choice == 4) deleteRear();
            else if (choice == 5) displayDeque();
            break;

        default:
            printf("Invalid choice!\n");
        }
    }
    return 0;
}

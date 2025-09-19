#include <stdio.h>
#include <stdlib.h>

#define MAX 5

// ---------- Stack Structure ----------
typedef struct {
    int mainStack[MAX];
    int minStack[MAX];
    int maxStack[MAX];
    int top;
} GameStack;

// ---------- Initialize Stack ----------
void initStack(GameStack *s) {
    s->top = -1;
}

// ---------- Check if Stack is Empty ----------
int isEmpty(GameStack *s) {
    return s->top == -1;
}

// ---------- Check if Stack is Full ----------
int isFull(GameStack *s) {
    return s->top == MAX - 1;
}

// ---------- Push ----------
void push(GameStack *s, int value) {
    if (isFull(s)) {
        printf("[ERROR] Stack Overflow! Cannot push %d\n", value);
        return;
    }
    s->top++;
    s->mainStack[s->top] = value;

    // handle minStack
    if (s->top == 0)
        s->minStack[s->top] = value;
    else
        s->minStack[s->top] = (value < s->minStack[s->top - 1])
                              ? value
                              : s->minStack[s->top - 1];

    // handle maxStack
    if (s->top == 0)
        s->maxStack[s->top] = value;
    else
        s->maxStack[s->top] = (value > s->maxStack[s->top - 1])
                              ? value
                              : s->maxStack[s->top - 1];

    printf("[PUSH] Added score: %d\n", value);
    printf("Current Top: %d | Current Min: %d | Current Max: %d\n",
           s->mainStack[s->top], getMin(s), getMax(s));
}

// ---------- Pop ----------
int pop(GameStack *s) {
    if (isEmpty(s)) {
        printf("[ERROR] Stack Underflow! Nothing to pop.\n");
        return -1;
    }
    int poppedValue = s->mainStack[s->top];
    s->top--;
    printf("[POP] Removed score: %d\n", poppedValue);
    if (!isEmpty(s))
        printf("Current Top: %d | Current Min: %d | Current Max: %d\n",
               s->mainStack[s->top], getMin(s), getMax(s));
    else
        printf("Stack is now empty.\n");
    return poppedValue;
}

// ---------- Peek/Top ----------
int peek(GameStack *s) {
    if (isEmpty(s)) {
        printf("[ERROR] Stack is empty. No top element.\n");
        return -1;
    }
    return s->mainStack[s->top];
}

// ---------- Get Min ----------
int getMin(GameStack *s) {
    if (isEmpty(s)) {
        return -1; 
    }
    return s->minStack[s->top];
}

// ---------- Get Max ----------
int getMax(GameStack *s) {
    if (isEmpty(s)) {
        return -1;
    }
    return s->maxStack[s->top];
}

// ---------- Display Stack ----------
void display(GameStack *s) {
    if (isEmpty(s)) {
        printf("[INFO] Stack is empty.\n");
        return;
    }
    printf("\nCurrent Scores (bottom → top): ");
    for (int i = 0; i <= s->top; i++)
        printf("%d ", s->mainStack[i]);
    printf("\n");
}

// ---------- Main Function ----------
int main() {
    GameStack s;
    initStack(&s);

    // pushing player scores
    push(&s, 50);
    push(&s, 30);
    push(&s, 70);
    push(&s, 10);
    push(&s, 90);

    display(&s);
    printf("Peek (Top Score): %d\n", peek(&s));

    pop(&s);
    pop(&s);

    display(&s);
    printf("Peek (Top Score): %d\n", peek(&s));

    return 0;
}
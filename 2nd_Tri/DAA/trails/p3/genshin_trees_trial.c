/* genshin_trees.c
   Menu-driven BST and AVL implementation with Genshin Impact domain payload.
   Supports insert/delete/search/display for both BST and AVL.
   Author: ChatGPT (example)
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/* ---------- Domain payload ---------- */
typedef struct {
    char name[50];
    char element[20];
    char weapon[30];
    int rarity; /* 4 or 5 */
} Character;

/* Utility to create a Character */
Character make_character(const char* name, const char* element, const char* weapon, int rarity) {
    Character c;
    strncpy(c.name, name, sizeof(c.name)-1); c.name[sizeof(c.name)-1]=0;
    strncpy(c.element, element, sizeof(c.element)-1); c.element[sizeof(c.element)-1]=0;
    strncpy(c.weapon, weapon, sizeof(c.weapon)-1); c.weapon[sizeof(c.weapon)-1]=0;
    c.rarity = rarity;
    return c;
}

/* Print character payload */
void print_character(Character *c) {
    printf("ID Name: %s | Element: %s | Weapon: %s | Rarity: %d★\n", c->name, c->element, c->weapon, c->rarity);
}

/* ---------- BST Implementation ---------- */
typedef struct BSTNode {
    int key;
    Character payload;
    struct BSTNode *left, *right;
} BSTNode;

BSTNode* bst_create_node(int key, Character payload) {
    BSTNode* n = (BSTNode*)malloc(sizeof(BSTNode));
    n->key = key;
    n->payload = payload;
    n->left = n->right = NULL;
    return n;
}

/* BST insert (recursive) */
BSTNode* bst_insert(BSTNode* root, int key, Character payload) {
    if (root == NULL) return bst_create_node(key, payload);
    if (key < root->key)
        root->left = bst_insert(root->left, key, payload);
    else if (key > root->key)
        root->right = bst_insert(root->right, key, payload);
    else {
        /* duplicate key -> update payload */
        root->payload = payload;
    }
    return root;
}

/* Find minimum node in BST (used in delete) */
BSTNode* bst_min_value_node(BSTNode* node) {
    BSTNode* curr = node;
    while (curr && curr->left != NULL)
        curr = curr->left;
    return curr;
}

/* BST delete */
BSTNode* bst_delete(BSTNode* root, int key) {
    if (root == NULL) return root;
    if (key < root->key)
        root->left = bst_delete(root->left, key);
    else if (key > root->key)
        root->right = bst_delete(root->right, key);
    else {
        /* found node */
        if (root->left == NULL) {
            BSTNode* temp = root->right;
            free(root);
            return temp;
        } else if (root->right == NULL) {
            BSTNode* temp = root->left;
            free(root);
            return temp;
        } else {
            BSTNode* temp = bst_min_value_node(root->right);
            root->key = temp->key;
            root->payload = temp->payload;
            root->right = bst_delete(root->right, temp->key);
        }
    }
    return root;
}

/* BST search */
BSTNode* bst_search(BSTNode* root, int key) {
    if (root == NULL || root->key == key) return root;
    if (key < root->key) return bst_search(root->left, key);
    return bst_search(root->right, key);
}

/* Traversals */
void bst_inorder(BSTNode* root) {
    if (!root) return;
    bst_inorder(root->left);
    printf("Key: %d | ", root->key);
    print_character(&root->payload);
    bst_inorder(root->right);
}
void bst_preorder(BSTNode* root) {
    if (!root) return;
    printf("Key: %d | ", root->key);
    print_character(&root->payload);
    bst_preorder(root->left);
    bst_preorder(root->right);
}
void bst_postorder(BSTNode* root) {
    if (!root) return;
    bst_postorder(root->left);
    bst_postorder(root->right);
    printf("Key: %d | ", root->key);
    print_character(&root->payload);
}

/* Free BST */
void bst_free(BSTNode* root) {
    if (!root) return;
    bst_free(root->left);
    bst_free(root->right);
    free(root);
}

/* ---------- AVL Implementation ---------- */
typedef struct AVLNode {
    int key;
    Character payload;
    struct AVLNode *left, *right;
    int height;
} AVLNode;

/* Utility for height */
int avl_height(AVLNode* n) {
    return n ? n->height : 0;
}
int max_int(int a, int b) { return (a > b) ? a : b; }

AVLNode* avl_create_node(int key, Character payload) {
    AVLNode* n = (AVLNode*)malloc(sizeof(AVLNode));
    n->key = key;
    n->payload = payload;
    n->left = n->right = NULL;
    n->height = 1;
    return n;
}

/* Right rotate */
AVLNode* avl_right_rotate(AVLNode* y) {
    AVLNode* x = y->left;
    AVLNode* T2 = x->right;
    x->right = y;
    y->left = T2;
    y->height = max_int(avl_height(y->left), avl_height(y->right)) + 1;
    x->height = max_int(avl_height(x->left), avl_height(x->right)) + 1;
    return x;
}

/* Left rotate */
AVLNode* avl_left_rotate(AVLNode* x) {
    AVLNode* y = x->right;
    AVLNode* T2 = y->left;
    y->left = x;
    x->right = T2;
    x->height = max_int(avl_height(x->left), avl_height(x->right)) + 1;
    y->height = max_int(avl_height(y->left), avl_height(y->right)) + 1;
    return y;
}

/* Get balance factor */
int avl_get_balance(AVLNode* n) {
    if (!n) return 0;
    return avl_height(n->left) - avl_height(n->right);
}

/* AVL insert */
AVLNode* avl_insert(AVLNode* node, int key, Character payload) {
    if (node == NULL)
        return avl_create_node(key, payload);
    if (key < node->key)
        node->left = avl_insert(node->left, key, payload);
    else if (key > node->key)
        node->right = avl_insert(node->right, key, payload);
    else {
        node->payload = payload; /* update payload on duplicate */
        return node;
    }

    node->height = 1 + max_int(avl_height(node->left), avl_height(node->right));
    int balance = avl_get_balance(node);

    /* LL */
    if (balance > 1 && key < node->left->key)
        return avl_right_rotate(node);
    /* RR */
    if (balance < -1 && key > node->right->key)
        return avl_left_rotate(node);
    /* LR */
    if (balance > 1 && key > node->left->key) {
        node->left = avl_left_rotate(node->left);
        return avl_right_rotate(node);
    }
    /* RL */
    if (balance < -1 && key < node->right->key) {
        node->right = avl_right_rotate(node->right);
        return avl_left_rotate(node);
    }
    return node;
}

/* AVL min value node */
AVLNode* avl_min_value_node(AVLNode* node) {
    AVLNode* current = node;
    while (current->left != NULL) current = current->left;
    return current;
}

/* AVL delete */
AVLNode* avl_delete(AVLNode* root, int key) {
    if (root == NULL) return root;

    if (key < root->key)
        root->left = avl_delete(root->left, key);
    else if (key > root->key)
        root->right = avl_delete(root->right, key);
    else {
        if ((root->left == NULL) || (root->right == NULL)) {
            AVLNode* temp = root->left ? root->left : root->right;
            if (temp == NULL) {
                temp = root;
                root = NULL;
            } else {
                *root = *temp; /* copy contents */
            }
            free(temp);
        } else {
            AVLNode* temp = avl_min_value_node(root->right);
            root->key = temp->key;
            root->payload = temp->payload;
            root->right = avl_delete(root->right, temp->key);
        }
    }

    if (root == NULL) return root;

    root->height = 1 + max_int(avl_height(root->left), avl_height(root->right));
    int balance = avl_get_balance(root);

    /* Balancing cases */
    if (balance > 1 && avl_get_balance(root->left) >= 0)
        return avl_right_rotate(root);
    if (balance > 1 && avl_get_balance(root->left) < 0) {
        root->left = avl_left_rotate(root->left);
        return avl_right_rotate(root);
    }
    if (balance < -1 && avl_get_balance(root->right) <= 0)
        return avl_left_rotate(root);
    if (balance < -1 && avl_get_balance(root->right) > 0) {
        root->right = avl_right_rotate(root->right);
        return avl_left_rotate(root);
    }

    return root;
}

/* AVL search */
AVLNode* avl_search(AVLNode* root, int key) {
    if (root == NULL || root->key == key) return root;
    if (key < root->key) return avl_search(root->left, key);
    return avl_search(root->right, key);
}

/* AVL traversals */
void avl_inorder(AVLNode* root) {
    if (!root) return;
    avl_inorder(root->left);
    printf("Key: %d | ", root->key);
    print_character(&root->payload);
    avl_inorder(root->right);
}
void avl_preorder(AVLNode* root) {
    if (!root) return;
    printf("Key: %d | ", root->key);
    print_character(&root->payload);
    avl_preorder(root->left);
    avl_preorder(root->right);
}
void avl_postorder(AVLNode* root) {
    if (!root) return;
    avl_postorder(root->left);
    avl_postorder(root->right);
    printf("Key: %d | ", root->key);
    print_character(&root->payload);
}

/* Free AVL */
void avl_free(AVLNode* root) {
    if (!root) return;
    avl_free(root->left);
    avl_free(root->right);
    free(root);
}

/* ---------- Helper: load sample Genshin data ---------- */
void load_sample_bst(BSTNode** bst_root) {
    /* Sample IDs are arbitrary unique integers */
    *bst_root = bst_insert(*bst_root, 101, make_character("Diluc", "Pyro", "Claymore", 5));
    *bst_root = bst_insert(*bst_root, 102, make_character("Fischl", "Electro", "Bow", 4));
    *bst_root = bst_insert(*bst_root, 103, make_character("Keqing", "Electro", "Sword", 5));
    *bst_root = bst_insert(*bst_root, 104, make_character("Barbara", "Hydro", "Catalyst", 4));
    *bst_root = bst_insert(*bst_root, 105, make_character("Xiangling", "Pyro", "Polearm", 4));
}
void load_sample_avl(AVLNode** avl_root) {
    *avl_root = avl_insert(*avl_root, 201, make_character("Kazuha", "Anemo", "Sword", 5));
    *avl_root = avl_insert(*avl_root, 202, make_character("Bennett", "Pyro", "Sword", 4));
    *avl_root = avl_insert(*avl_root, 203, make_character("Mona", "Hydro", "Catalyst", 5));
    *avl_root = avl_insert(*avl_root, 204, make_character("Ningguang", "Geo", "Catalyst", 4));
    *avl_root = avl_insert(*avl_root, 205, make_character("Xingqiu", "Hydro", "Sword", 4));
}

/* ---------- Menus & I/O ---------- */

void display_traversal_choices() {
    printf("\nDisplay Traversals:\n");
    printf("1. Inorder\n2. Preorder\n3. Postorder\n");
}

void print_complexity_comparison() {
    printf("\n--- Time Complexity Comparison: BST vs AVL ---\n");
    printf("Operation    | BST (average) | BST (worst)   | AVL (guaranteed)\n");
    printf("---------------------------------------------------------------\n");
    printf("Search       | O(log n)      | O(n)          | O(log n)\n");
    printf("Insert       | O(log n)      | O(n)          | O(log n)\n");
    printf("Delete       | O(log n)      | O(n)          | O(log n)\n\n");
    printf("Notes:\n");
    printf("- A plain (unbalanced) BST can degrade to a linked list for certain insertion orders (e.g. sorted input), making worst-case operations O(n).\n");
    printf("- AVL tree maintains balance via rotations, guaranteeing O(log n) for search/insert/delete at the cost of extra rotations and slightly higher constant factors.\n");
    printf("- Use AVL when you need predictable (balanced) performance; BST can be slightly faster on average if input is random and rotations are expensive.\n");
    printf("- For memory overhead: AVL nodes store a height (int) and may perform more rotations; BST nodes are lighter.\n");
}

/* Read string safely */
void read_line(char *buf, int size) {
    if (!fgets(buf, size, stdin)) { buf[0] = '\0'; return; }
    size_t ln = strlen(buf);
    if (ln && buf[ln-1] == '\n') buf[ln-1] = '\0';
}

/* Operation menus for BST & AVL */
void bst_operations(BSTNode** bst_root) {
    while (1) {
        printf("\n-- BST Operations --\n");
        printf("1. Insert <key + payload>\n2. Delete <key>\n3. Search <key>\n4. Display (inorder/preorder/postorder)\n5. Load sample Genshin characters\n6. Back to Main Menu\n");
        printf("Choose (1-6): ");
        int ch; if (scanf("%d", &ch)!=1) { while (getchar()!= '\n'); continue; }
        while (getchar() != '\n'); /* flush newline */
        if (ch == 1) {
            int key; char name[50], element[20], weapon[30]; int rarity;
            printf("Enter integer Key (ID): "); scanf("%d", &key); while (getchar()!='\n');
            printf("Enter name: "); read_line(name, sizeof(name));
            printf("Enter element: "); read_line(element, sizeof(element));
            printf("Enter weapon: "); read_line(weapon, sizeof(weapon));
            printf("Enter rarity (4 or 5): "); scanf("%d", &rarity); while (getchar()!='\n');
            Character c = make_character(name, element, weapon, rarity);
            *bst_root = bst_insert(*bst_root, key, c);
            printf("Inserted/Updated node with Key %d into BST.\n", key);
        } else if (ch == 2) {
            int key; printf("Enter key to delete: "); scanf("%d", &key); while (getchar()!='\n');
            *bst_root = bst_delete(*bst_root, key);
            printf("If present, deleted key %d from BST.\n", key);
        } else if (ch == 3) {
            int key; printf("Enter key to search: "); scanf("%d", &key); while (getchar()!='\n');
            BSTNode* res = bst_search(*bst_root, key);
            if (res) { printf("Found node: Key %d | ", res->key); print_character(&res->payload); }
            else printf("Key %d not found in BST.\n", key);
        } else if (ch == 4) {
            display_traversal_choices();
            int t; printf("Choose traversal: "); scanf("%d", &t); while (getchar()!='\n');
            if (t == 1) { printf("\nBST Inorder:\n"); bst_inorder(*bst_root); }
            else if (t == 2) { printf("\nBST Preorder:\n"); bst_preorder(*bst_root); }
            else if (t == 3) { printf("\nBST Postorder:\n"); bst_postorder(*bst_root); }
            else printf("Invalid choice.\n");
            printf("\n");
        } else if (ch == 5) {
            load_sample_bst(bst_root);
            printf("Loaded sample Genshin characters into BST.\n");
        } else if (ch == 6) {
            break;
        } else {
            printf("Invalid option.\n");
        }
    }
}

void avl_operations(AVLNode** avl_root) {
    while (1) {
        printf("\n-- AVL Operations --\n");
        printf("1. Insert <key + payload>\n2. Delete <key>\n3. Search <key>\n4. Display (inorder/preorder/postorder)\n5. Load sample Genshin characters\n6. Back to Main Menu\n");
        printf("Choose (1-6): ");
        int ch; if (scanf("%d", &ch)!=1) { while (getchar()!= '\n'); continue; }
        while (getchar() != '\n');
        if (ch == 1) {
            int key; char name[50], element[20], weapon[30]; int rarity;
            printf("Enter integer Key (ID): "); scanf("%d", &key); while (getchar()!='\n');
            printf("Enter name: "); read_line(name, sizeof(name));
            printf("Enter element: "); read_line(element, sizeof(element));
            printf("Enter weapon: "); read_line(weapon, sizeof(weapon));
            printf("Enter rarity (4 or 5): "); scanf("%d", &rarity); while (getchar()!='\n');
            Character c = make_character(name, element, weapon, rarity);
            *avl_root = avl_insert(*avl_root, key, c);
            printf("Inserted/Updated node with Key %d into AVL.\n", key);
        } else if (ch == 2) {
            int key; printf("Enter key to delete: "); scanf("%d", &key); while (getchar()!='\n');
            *avl_root = avl_delete(*avl_root, key);
            printf("If present, deleted key %d from AVL.\n", key);
        } else if (ch == 3) {
            int key; printf("Enter key to search: "); scanf("%d", &key); while (getchar()!='\n');
            AVLNode* res = avl_search(*avl_root, key);
            if (res) { printf("Found node: Key %d | ", res->key); print_character(&res->payload); }
            else printf("Key %d not found in AVL.\n", key);
        } else if (ch == 4) {
            display_traversal_choices();
            int t; printf("Choose traversal: "); scanf("%d", &t); while (getchar()!='\n');
            if (t == 1) { printf("\nAVL Inorder:\n"); avl_inorder(*avl_root); }
            else if (t == 2) { printf("\nAVL Preorder:\n"); avl_preorder(*avl_root); }
            else if (t == 3) { printf("\nAVL Postorder:\n"); avl_postorder(*avl_root); }
            else printf("Invalid choice.\n");
            printf("\n");
        } else if (ch == 5) {
            load_sample_avl(avl_root);
            printf("Loaded sample Genshin characters into AVL.\n");
        } else if (ch == 6) {
            break;
        } else {
            printf("Invalid option.\n");
        }
    }
}

int main() {
    BSTNode* bst_root = NULL;
    AVLNode* avl_root = NULL;
    int choice;

    printf("=== Genshin-Trees: BST & AVL (Domain: Genshin Impact) ===\n");
    while (1) {
        printf("\nMain Menu:\n");
        printf("1. Work with Binary Search Tree (BST)\n");
        printf("2. Work with AVL Tree\n");
        printf("3. Compare Time Complexities (BST vs AVL)\n");
        printf("4. Exit\n");
        printf("Choose (1-4): ");
        if (scanf("%d", &choice) != 1) {
            while (getchar() != '\n');
            continue;
        }
        while (getchar() != '\n'); /* flush newline */

        if (choice == 1) {
            bst_operations(&bst_root);
        } else if (choice == 2) {
            avl_operations(&avl_root);
        } else if (choice == 3) {
            print_complexity_comparison();
            printf("\n(You can also try loading sample data in each tree and measure operations yourself.)\n");
        } else if (choice == 4) {
            printf("Exiting. Freeing memory...\n");
            bst_free(bst_root);
            avl_free(avl_root);
            break;
        } else {
            printf("Invalid selection — choose 1..4.\n");
        }
    }
    return 0;
}


// Notes & explanation
// Key: integer ID (unique). I used integer keys for simplicity and to keep BST/AVL comparisons clear. You can easily switch to string keys (names) but then comparisons use strcmp and additional care.
// Payload: Character struct — includes name, element, weapon, rarity. When inserting a duplicate key the payload is updated.
// BST delete: standard algorithm using inorder successor.
// AVL: classic height-based balancing with LL/LR/RR/RL rotations; delete rebalances similarly.
// Traversals: inorder / preorder / postorder implemented for both trees.
// Sample data: two small loaders load_sample_bst and load_sample_avl insert some sample Genshin characters and IDs so you can see tree shapes and try operations quickly.
// Correctness & demonstration ideas (domain-relevant scenarios)
// Insert characters with sorted IDs into BST: you'll see BST degrade (becomes a skewed tree). Example: insert IDs 1,2,3,4,5—BST height becomes n, making search O(n).
// Insert same sorted IDs to AVL: AVL rotations keep the tree balanced; height stays O(log n) and search remains O(log n).
// Use sample loaders and then try deletes and searches (e.g., delete a 5★ character and verify subsequent traversals and balances).

// Correctness & demonstration ideas (domain-relevant scenarios)
// Insert characters with sorted IDs into BST: you'll see BST degrade (becomes a skewed tree). Example: insert IDs 1,2,3,4,5—BST height becomes n, making search O(n).
// Insert same sorted IDs to AVL: AVL rotations keep the tree balanced; height stays O(log n) and search remains O(log n).
// Use sample loaders and then try deletes and searches (e.g., delete a 5★ character and verify subsequent traversals and balances).
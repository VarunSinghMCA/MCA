import java.util.Scanner;

/*
 GamingQueues.java
 Domain: Gaming Platform
 Implements:
  1) Normal (Linear) Queue using arrays
  2) Circular Queue using arrays
  3) Priority Queue (Min-priority) using singly linked list
  4) Deque (Double Ended Queue) using arrays

 Status line printed after each operation.
 Robust input validation included.
*/

public class GamingQueues {
    // Scanner object for user input throughout the program
    static Scanner sc = new Scanner(System.in);

    // ---------------------------
    // NORMAL (Linear) Queue - Array-based
    // ---------------------------
    static class NormalQueue {
        // Array to store queue elements
        private String[] arr;
        // front: index of first element, rear: index of last element, capacity: max size
        private int front, rear, capacity;

        // Constructor to initialize the normal queue
        public NormalQueue(int capacity) {
            this.capacity = capacity;
            arr = new String[capacity];
            front = 0;
            rear = -1; // rear points to last occupied index, -1 means empty
        }

        // Check if queue is empty (rear is behind front)
        public boolean isEmpty() { return rear < front; }
        
        // Check if queue is full (rear has reached maximum capacity)
        public boolean isFull() { return rear == capacity - 1; }

        // Add an element to the rear of the queue
        public void enqueue(String item) {
            // Check for overflow condition
            if (isFull()) {
                System.out.println("ERROR: Normal Queue Overflow (cannot enqueue).");
                return;
            }
            // Increment rear and add item
            arr[++rear] = item;
            // Display current status
            status();
        }

        // Remove and return element from the front of the queue
        public String dequeue() {
            // Check for underflow condition
            if (isEmpty()) {
                System.out.println("ERROR: Normal Queue Underflow (cannot dequeue).");
                return null;
            }
            // Get item from front and increment front pointer
            String item = arr[front++];
            // Display current status after removal
            status();
            return item;
        }

        // Display all elements in the queue from front to rear
        public void display() {
            System.out.print("NormalQueue contents (front->rear): ");
            if (isEmpty()) {
                System.out.println("[empty]");
                return;
            }
            // Print all elements from front to rear with separator
            for (int i = front; i <= rear; i++) {
                System.out.print(arr[i]);
                if (i < rear) System.out.print(" | ");
            }
            System.out.println();
        }

        // Display queue status including size and indices
        public void status() {
            System.out.println("STATUS: NormalQueue size=" + Math.max(0, rear - front + 1)
                    + ", frontIndex=" + front + ", rearIndex=" + rear);
            display();
        }
    }

    // ---------------------------
    // CIRCULAR QUEUE - Array-based
    // Uses modulo arithmetic to wrap around array indices
    // ---------------------------
    static class CircularQueue {
        // Array to store queue elements
        private String[] arr;
        // front: first element index, rear: last element index, size: current elements, capacity: max size
        private int front, rear, size, capacity;

        // Constructor to initialize circular queue
        public CircularQueue(int capacity) {
            this.capacity = capacity;
            arr = new String[capacity];
            front = 0;
            rear = -1;
            size = 0; // Track actual number of elements for easy isEmpty/isFull checks
        }

        // Check if queue is empty using size counter
        public boolean isEmpty() { return size == 0; }
        
        // Check if queue is full using size counter
        public boolean isFull() { return size == capacity; }

        // Add element to rear of circular queue
        public void enqueue(String item) {
            // Check for overflow
            if (isFull()) {
                System.out.println("ERROR: Circular Queue Overflow (cannot enqueue).");
                return;
            }
            // Move rear in circular manner using modulo
            rear = (rear + 1) % capacity;
            arr[rear] = item;
            size++; // Increment element count
            status();
        }

        // Remove and return element from front of circular queue
        public String dequeue() {
            // Check for underflow
            if (isEmpty()) {
                System.out.println("ERROR: Circular Queue Underflow (cannot dequeue).");
                return null;
            }
            String item = arr[front];
            // Move front in circular manner using modulo
            front = (front + 1) % capacity;
            size--; // Decrement element count
            status();
            return item;
        }

        // Display elements in circular queue from front to rear
        public void display() {
            System.out.print("CircularQueue contents (front->rear): ");
            if (isEmpty()) {
                System.out.println("[empty]");
                return;
            }
            int idx = front;
            // Traverse from front using circular indexing
            for (int i = 0; i < size; i++) {
                System.out.print(arr[idx]);
                if (i < size - 1) System.out.print(" | ");
                idx = (idx + 1) % capacity; // Circular increment
            }
            System.out.println();
        }

        // Display circular queue status with indices and size
        public void status() {
            System.out.println("STATUS: CircularQueue size=" + size + ", frontIndex=" + front + ", rearIndex=" + rear);
            display();
        }
    }

    // ---------------------------
    // PRIORITY QUEUE (MIN-PRIORITY) - Linked list implementation
    // Lower numeric priority => higher service priority (1 is highest)
    // Each node contains: (item, priority)
    // Enqueue inserts at appropriate position (O(n)), Dequeue removes head (O(1))
    // ---------------------------
    static class PriorityQueue {
        // Inner class to represent a node in the priority queue
        private static class Node {
            String item;        // Data stored in the node
            int priority;       // Priority value (lower number = higher priority)
            Node next;          // Reference to next node
            
            // Node constructor
            Node(String itm, int p) { item = itm; priority = p; next = null; }
        }

        private Node head;      // Points to highest priority node (smallest priority value)
        private int size;       // Number of elements in the queue

        // Constructor to initialize empty priority queue
        public PriorityQueue() { head = null; size = 0; }

        // Check if priority queue is empty
        public boolean isEmpty() { return head == null; }
        
        // Add element with priority to the queue (maintains sorted order)
        public void enqueue(String itm, int pr) {
            // Validate priority range
            if (pr < 1 || pr > 100) {
                System.out.println("ERROR: Priority out of allowed range (1-100).");
                return;
            }
            
            Node node = new Node(itm, pr);
            
            // Case 1: Empty list or new node has highest priority (smallest value)
            if (head == null || pr < head.priority) {
                node.next = head;
                head = node;
            } else {
                // Case 2: Find correct position to maintain priority order
                Node cur = head;
                // Traverse until we find position where new node should be inserted
                while (cur.next != null && cur.next.priority <= pr) {
                    cur = cur.next;
                }
                // Insert new node at correct position
                node.next = cur.next;
                cur.next = node;
            }
            size++;
            status();
        }

        // Remove and return highest priority element (from head)
        public String dequeue() {
            // Check for underflow
            if (isEmpty()) {
                System.out.println("ERROR: Priority Queue Underflow (cannot dequeue).");
                return null;
            }
            
            Node removed = head;        // Store reference to node being removed
            head = head.next;           // Move head to next node
            size--;                     // Decrement size
            
            // Return item with priority information
            String out = removed.item + " (priority=" + removed.priority + ")";
            status();
            return out;
        }

        // Display all elements in priority order (highest to lowest priority)
        public void display() {
            System.out.print("PriorityQueue contents (highest->lowest priority): ");
            if (isEmpty()) { System.out.println("[empty]"); return; }
            
            Node cur = head;
            boolean first = true;
            // Traverse linked list from head to tail
            while (cur != null) {
                if (!first) System.out.print(" | ");
                System.out.print(cur.item + "(" + cur.priority + ")");
                first = false;
                cur = cur.next;
            }
            System.out.println();
        }

        // Display priority queue status with size information
        public void status() {
            System.out.println("STATUS: PriorityQueue size=" + size);
            display();
        }
    }

    // ---------------------------
    // DEQUE (Double Ended Queue) - Array-based implementation
    // Supports insertion and deletion at both ends
    // Operations: insertFront, insertRear, deleteFront, deleteRear, getFront, getRear, isEmpty, isFull, display
    // ---------------------------
    static class Deque {
        private String[] arr;               // Array to store deque elements
        private int front, rear, size, capacity;   // Indices and counters

        // Constructor to initialize deque
        public Deque(int cap) {
            capacity = cap;
            arr = new String[capacity];
            front = 0;                      // Initialize front to 0
            rear = capacity - 1;           // Initialize rear to last index (circular)
            size = 0;                      // No elements initially
        }

        // Check if deque is empty
        public boolean isEmpty() { return size == 0; }
        
        // Check if deque is full
        public boolean isFull() { return size == capacity; }

        // Insert element at the front of deque
        public void insertFront(String item) {
            // Check for overflow
            if (isFull()) {
                System.out.println("ERROR: Deque Overflow (cannot insertFront).");
                return;
            }
            // Move front backwards in circular manner
            front = (front - 1 + capacity) % capacity;
            arr[front] = item;
            size++;
            status();
        }

        // Insert element at the rear of deque
        public void insertRear(String item) {
            // Check for overflow
            if (isFull()) {
                System.out.println("ERROR: Deque Overflow (cannot insertRear).");
                return;
            }
            // Move rear forward in circular manner
            rear = (rear + 1) % capacity;
            arr[rear] = item;
            size++;
            status();
        }

        // Delete and return element from front of deque
        public String deleteFront() {
            // Check for underflow
            if (isEmpty()) {
                System.out.println("ERROR: Deque Underflow (cannot deleteFront).");
                return null;
            }
            String item = arr[front];
            // Move front forward in circular manner
            front = (front + 1) % capacity;
            size--;
            status();
            return item;
        }

        // Delete and return element from rear of deque
        public String deleteRear() {
            // Check for underflow
            if (isEmpty()) {
                System.out.println("ERROR: Deque Underflow (cannot deleteRear).");
                return null;
            }
            String item = arr[rear];
            // Move rear backwards in circular manner
            rear = (rear - 1 + capacity) % capacity;
            size--;
            status();
            return item;
        }

        // Get front element without removing it (peek operation)
        public String getFront() {
            if (isEmpty()) { 
                System.out.println("Deque empty: getFront -> null"); 
                return null; 
            }
            System.out.println("Front element: " + arr[front]);
            return arr[front];
        }

        // Get rear element without removing it (peek operation)
        public String getRear() {
            if (isEmpty()) { 
                System.out.println("Deque empty: getRear -> null"); 
                return null; 
            }
            System.out.println("Rear element: " + arr[rear]);
            return arr[rear];
        }

        // Display all elements in deque from front to rear
        public void display() {
            System.out.print("Deque contents (front->rear): ");
            if (isEmpty()) { 
                System.out.println("[empty]"); 
                return; 
            }
            
            int idx = front;
            // Traverse from front to rear using circular indexing
            for (int i = 0; i < size; i++) {
                System.out.print(arr[idx]);
                if (i < size - 1) System.out.print(" | ");
                idx = (idx + 1) % capacity;     // Circular increment
            }
            System.out.println();
        }

        // Display deque status with size and index information
        public void status() {
            System.out.println("STATUS: Deque size=" + size + ", frontIndex=" + front + ", rearIndex=" + rear);
            display();
        }
    }

    // ---------------------------
    // Main method - Entry point of the program
    // Creates instances of all queue types and provides menu interface
    // ---------------------------
    public static void main(String[] args) {
        System.out.println("=== Gaming Platform: Multi-Queue Application ===");
        System.out.println("Context: Manage players, match lobbies, support tickets and matchmaking priority.");
        
        // Initialize all queue types with default capacities
        NormalQueue normalQ = new NormalQueue(10);      // Linear queue for player login
        CircularQueue circularQ = new CircularQueue(6); // Circular queue for match lobbies
        PriorityQueue pQueue = new PriorityQueue();      // Priority queue for support tickets
        Deque deque = new Deque(6);                      // Double-ended queue for matchmaking

        // Main program loop - display menu and handle user choices
        while (true) {
            System.out.println("\nChoose Queue Type:");
            System.out.println("1) Normal Queue (Player Login Queue)");
            System.out.println("2) Circular Queue (Match Lobby Round-Robin)");
            System.out.println("3) Priority Queue (Support Tickets - Min Priority) / Deque options");
            System.out.println("4) Exit");
            System.out.print("Enter choice (1-4): ");
            
            // Get user input with validation
            int choice = safeIntInput(1, 4);
            if (choice == 4) { 
                System.out.println("Exiting. Goodbye!"); 
                break; 
            }

            // Route to appropriate queue menu based on user choice
            switch (choice) {
                case 1:
                    normalMenu(normalQ);        // Handle normal queue operations
                    break;
                case 2:
                    circularMenu(circularQ);    // Handle circular queue operations
                    break;
                case 3:
                    priorityDequeMenu(pQueue, deque);  // Handle priority queue and deque operations
                    break;
            }
        }
    }

    // ---------------------------
    // Menu functions for each queue type
    // Each menu provides operations specific to that queue type
    // ---------------------------
    
    // Menu for Normal (Linear) Queue operations
    static void normalMenu(NormalQueue q) {
        // Normal queue menu loop
        while (true) {
            System.out.println("\n-- Normal Queue Menu (Player Login Queue) --");
            System.out.println("1) Enqueue (player joins login queue)");
            System.out.println("2) Dequeue (player served)");
            System.out.println("3) isEmpty");
            System.out.println("4) isFull");
            System.out.println("5) Display");
            System.out.println("6) Back to main menu");
            System.out.print("Choose: ");
            
            int c = safeIntInput(1, 6);
            if (c == 6) break;  // Return to main menu
            // Handle user choice for normal queue operations
            switch (c) {
                case 1:  // Enqueue operation
                    System.out.print("Enter playerID to enqueue (e.g. Player:Varun): ");
                    String itm = sc.nextLine().trim();
                    if (itm.isEmpty()) { 
                        System.out.println("Invalid input."); 
                        break; 
                    }
                    q.enqueue(itm);
                    break;
                case 2:  // Dequeue operation
                    String out = q.dequeue();
                    if (out != null) System.out.println("Dequeued: " + out);
                    break;
                case 3:  // Check if empty
                    System.out.println("isEmpty: " + q.isEmpty());
                    break;
                case 4:  // Check if full
                    System.out.println("isFull: " + q.isFull());
                    break;
                case 5:  // Display queue contents
                    q.display();
                    break;
            }
        }
    }

    // Menu for Circular Queue operations
    static void circularMenu(CircularQueue q) {
        while (true) {
            System.out.println("\n-- Circular Queue Menu (Match Lobby Round-Robin) --");
            System.out.println("1) Enqueue (player to lobby)");
            System.out.println("2) Dequeue (player leaves lobby)");
            System.out.println("3) isEmpty");
            System.out.println("4) isFull");
            System.out.println("5) Display");
            System.out.println("6) Back to main menu");
            System.out.print("Choose: ");
            int c = safeIntInput(1, 6);
            if (c == 6) break;
            switch (c) {
                case 1:
                    System.out.print("Enter playerName to enqueue (e.g. Player:Varun): ");
                    String itm = sc.nextLine().trim();
                    if (itm.isEmpty()) { System.out.println("Invalid input."); break; }
                    q.enqueue(itm);
                    break;
                case 2:
                    String out = q.dequeue();
                    if (out != null) System.out.println("Dequeued: " + out);
                    break;
                case 3:
                    System.out.println("isEmpty: " + q.isEmpty());
                    break;
                case 4:
                    System.out.println("isFull: " + q.isFull());
                    break;
                case 5:
                    q.display();
                    break;
            }
        }
    }

    // Menu for Priority Queue and Deque operations
    static void priorityDequeMenu(PriorityQueue pq, Deque dq) {
        // Priority queue and deque submenu loop
        while (true) {
            System.out.println("\n-- Priority Queue (Support Tickets) and Deque (Matchmaking) --");
            System.out.println("Choose:");
            System.out.println("1) Priority Queue (Support Tickets) menu");
            System.out.println("2) Deque (Matchmaking double-ended) menu");
            System.out.println("3) Back to main menu");
            
            int choice = safeIntInput(1, 3);
            if (choice == 3) break;  // Return to main menu
            // Handle priority queue or deque selection
            if (choice == 1) {
                // Priority Queue submenu
                while (true) {
                    System.out.println("\n--- Priority Queue Menu (Support Tickets) ---");
                    System.out.println("1) Enqueue ticket (item, priority)");
                    System.out.println("2) Dequeue ticket (serve highest priority)");
                    System.out.println("3) isEmpty");
                    System.out.println("4) Display");
                    System.out.println("5) Back");
                    
                    int c = safeIntInput(1, 5);
                    if (c == 5) break;  // Return to previous menu
                    // Handle priority queue operations
                    switch (c) {
                        case 1:  // Enqueue with priority
                            System.out.print("Enter ticket description (e.g. Ticket:Bug123): ");
                            String itm = sc.nextLine().trim();
                            System.out.print("Enter priority (1-100 where 1=highest): ");
                            int pr = safeIntInput(1, 100);
                            pq.enqueue(itm, pr);
                            break;
                        case 2:  // Dequeue highest priority
                            String served = pq.dequeue();
                            if (served != null) System.out.println("Served: " + served);
                            break;
                        case 3:  // Check if empty
                            System.out.println("isEmpty: " + pq.isEmpty());
                            break;
                        case 4:  // Display all tickets in priority order
                            pq.display();
                            break;
                    }
                }
            } else {
                // Deque submenu
                while (true) {
                    System.out.println("\n--- Deque Menu (Matchmaking special insertions) ---");
                    System.out.println("1) insertFront (VIP player to front)");
                    System.out.println("2) insertRear (normal player to rear)");
                    System.out.println("3) deleteFront (remove front)");
                    System.out.println("4) deleteRear (remove rear)");
                    System.out.println("5) getFront/getRear");
                    System.out.println("6) isEmpty/isFull");
                    System.out.println("7) Display");
                    System.out.println("8) Back");
                    
                    int c = safeIntInput(1, 8);
                    if (c == 8) break;  // Return to previous menu
                    // Handle deque operations
                    switch (c) {
                        case 1:  // Insert at front (VIP access)
                            System.out.print("Enter VIP player to insertFront: ");
                            String v = sc.nextLine().trim();
                            dq.insertFront(v);
                            break;
                        case 2:  // Insert at rear (normal access)
                            System.out.print("Enter player to insertRear: ");
                            String r = sc.nextLine().trim();
                            dq.insertRear(r);
                            break;
                        case 3:  // Delete from front
                            String df = dq.deleteFront();
                            if (df != null) System.out.println("Deleted front: " + df);
                            break;
                        case 4:  // Delete from rear
                            String dr = dq.deleteRear();
                            if (dr != null) System.out.println("Deleted rear: " + dr);
                            break;
                        case 5:  // Peek at both ends
                            dq.getFront();
                            dq.getRear();
                            break;
                        case 6:  // Check status
                            System.out.println("isEmpty: " + dq.isEmpty() + ", isFull: " + dq.isFull());
                            break;
                        case 7:  // Display all elements
                            dq.display();
                            break;
                    }
                }
            }
        }
    }

    // ---------------------------
    // Utility function for safe integer input with validation
    // Ensures user input is within specified range and handles invalid input gracefully
    // ---------------------------
    static int safeIntInput(int min, int max) {
        while (true) {
            String line = sc.nextLine().trim();
            try {
                // Parse the input string to integer
                int v = Integer.parseInt(line);
                
                // Check if input is within valid range
                if (v < min || v > max) {
                    System.out.print("Invalid choice. Enter a number between " + min + " and " + max + ": ");
                    continue;
                }
                return v;  // Return valid input
                
            } catch (NumberFormatException e) {
                // Handle non-numeric input
                System.out.print("Malformed input. Enter a number between " + min + " and " + max + ": ");
            }
        }
    }
}

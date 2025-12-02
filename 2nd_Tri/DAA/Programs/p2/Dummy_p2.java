import java.util.Scanner;

public class Dummy_p2 {
    static Scanner sc = new Scanner(System.in);

    static class NormalQueue {
        private String[] arr;
        private int front, rear, capacity;

        public NormalQueue(int capacity) {
            this.capacity = capacity;
            arr = new String[capacity];
            front = 0;
            rear = -1;
        }

        public boolean isEmpty() { return rear < front; }
        public boolean isFull() { return rear == capacity - 1; }

        public void enqueue(String item) {
            if (isFull()) {
                System.out.println("ERROR: Normal Queue Overflow (cannot enqueue).");
                return;
            }
            arr[++rear] = item;
            status();
        }

        public String dequeue() {
            if (isEmpty()) {
                System.out.println("ERROR: Normal Queue Underflow (cannot dequeue).");
                return null;
            }
            String item = arr[front++];
            status();
            return item;
        }

        public void display() {
            System.out.print("NormalQueue contents (front->rear): ");
            if (isEmpty()) {
                System.out.println("[empty]");
                return;
            }
            for (int i = front; i <= rear; i++) {
                System.out.print(arr[i]);
                if (i < rear) System.out.print(" | ");
            }
            System.out.println();
        }

        public void status() {
            System.out.println("STATUS: NormalQueue size=" + Math.max(0, rear - front + 1)
                    + ", frontIndex=" + front + ", rearIndex=" + rear);
            display();
        }
    }

    static class CircularQueue {
        private String[] arr;
        private int front, rear, size, capacity;

        public CircularQueue(int capacity) {
            this.capacity = capacity;
            arr = new String[capacity];
            front = 0;
            rear = -1;
            size = 0;
        }

        public boolean isEmpty() { return size == 0; }
        public boolean isFull() { return size == capacity; }

        public void enqueue(String item) {
            if (isFull()) {
                System.out.println("ERROR: Circular Queue Overflow (cannot enqueue).");
                return;
            }
            rear = (rear + 1) % capacity;
            arr[rear] = item;
            size++;
            status();
        }

        public String dequeue() {
            if (isEmpty()) {
                System.out.println("ERROR: Circular Queue Underflow (cannot dequeue).");
                return null;
            }
            String item = arr[front];
            front = (front + 1) % capacity;
            size--;
            status();
            return item;
        }

        public void display() {
            System.out.print("CircularQueue contents (front->rear): ");
            if (isEmpty()) {
                System.out.println("[empty]");
                return;
            }
            int idx = front;
            for (int i = 0; i < size; i++) {
                System.out.print(arr[idx]);
                if (i < size - 1) System.out.print(" | ");
                idx = (idx + 1) % capacity;
            }
            System.out.println();
        }

        public void status() {
            System.out.println("STATUS: CircularQueue size=" + size + ", frontIndex=" + front + ", rearIndex=" + rear);
            display();
        }
    }

    static class PriorityQueue {
        private static class Node {
            String item;
            int priority;
            Node next;
            Node(String itm, int p) { item = itm; priority = p; next = null; }
        }

        private Node head;
        private int size;

        public PriorityQueue() { head = null; size = 0; }

        public boolean isEmpty() { return head == null; }
        public void enqueue(String itm, int pr) {
            if (pr < 1 || pr > 100) {
                System.out.println("ERROR: Priority out of allowed range (1-100).");
                return;
            }
            Node node = new Node(itm, pr);
            if (head == null || pr < head.priority) {
                node.next = head;
                head = node;
            } else {
                Node cur = head;
                while (cur.next != null && cur.next.priority <= pr) {
                    cur = cur.next;
                }
                node.next = cur.next;
                cur.next = node;
            }
            size++;
            status();
        }

        public String dequeue() {
            if (isEmpty()) {
                System.out.println("ERROR: Priority Queue Underflow (cannot dequeue).");
                return null;
            }
            Node removed = head;
            head = head.next;
            size--;
            String out = removed.item + " (priority=" + removed.priority + ")";
            status();
            return out;
        }

        public void display() {
            System.out.print("PriorityQueue contents (highest->lowest priority): ");
            if (isEmpty()) { System.out.println("[empty]"); return; }
            Node cur = head;
            boolean first = true;
            while (cur != null) {
                if (!first) System.out.print(" | ");
                System.out.print(cur.item + "(" + cur.priority + ")");
                first = false;
                cur = cur.next;
            }
            System.out.println();
        }

        public void status() {
            System.out.println("STATUS: PriorityQueue size=" + size);
            display();
        }
    }

    static class Deque {
        private String[] arr;
        private int front, rear, size, capacity;

        public Deque(int cap) {
            capacity = cap;
            arr = new String[capacity];
            front = 0; rear = capacity - 1;
            size = 0;
        }

        public boolean isEmpty() { return size == 0; }
        public boolean isFull() { return size == capacity; }

        public void insertFront(String item) {
            if (isFull()) {
                System.out.println("ERROR: Deque Overflow (cannot insertFront).");
                return;
            }
            front = (front - 1 + capacity) % capacity;
            arr[front] = item;
            size++;
            status();
        }

        public void insertRear(String item) {
            if (isFull()) {
                System.out.println("ERROR: Deque Overflow (cannot insertRear).");
                return;
            }
            rear = (rear + 1) % capacity;
            arr[rear] = item;
            size++;
            status();
        }

        public String deleteFront() {
            if (isEmpty()) {
                System.out.println("ERROR: Deque Underflow (cannot deleteFront).");
                return null;
            }
            String item = arr[front];
            front = (front + 1) % capacity;
            size--;
            status();
            return item;
        }

        public String deleteRear() {
            if (isEmpty()) {
                System.out.println("ERROR: Deque Underflow (cannot deleteRear).");
                return null;
            }
            String item = arr[rear];
            rear = (rear - 1 + capacity) % capacity;
            size--;
            status();
            return item;
        }

        public String getFront() {
            if (isEmpty()) { System.out.println("Deque empty: getFront -> null"); return null; }
            System.out.println("Front element: " + arr[front]);
            return arr[front];
        }

        public String getRear() {
            if (isEmpty()) { System.out.println("Deque empty: getRear -> null"); return null; }
            System.out.println("Rear element: " + arr[rear]);
            return arr[rear];
        }

        public void display() {
            System.out.print("Deque contents (front->rear): ");
            if (isEmpty()) { System.out.println("[empty]"); return; }
            int idx = front;
            for (int i = 0; i < size; i++) {
                System.out.print(arr[idx]);
                if (i < size - 1) System.out.print(" | ");
                idx = (idx + 1) % capacity;
            }
            System.out.println();
        }

        public void status() {
            System.out.println("STATUS: Deque size=" + size + ", frontIndex=" + front + ", rearIndex=" + rear);
            display();
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Gaming Platform: Multi-Queue Application ===");
        System.out.println("Context: Manage players, match lobbies, support tickets and matchmaking priority.");
        NormalQueue normalQ = new NormalQueue(10);
        CircularQueue circularQ = new CircularQueue(6);
        PriorityQueue pQueue = new PriorityQueue();
        Deque deque = new Deque(6);

        while (true) {
            System.out.println("\nChoose Queue Type:");
            System.out.println("1) Normal Queue (Player Login Queue)");
            System.out.println("2) Circular Queue (Match Lobby Round-Robin)");
            System.out.println("3) Priority Queue (Support Tickets - Min Priority) / Deque options");
            System.out.println("4) Exit");
            System.out.print("Enter choice (1-4): ");
            int choice = safeIntInput(1, 4);
            if (choice == 4) { System.out.println("Exiting. Goodbye!"); break; }

            switch (choice) {
                case 1:
                    normalMenu(normalQ);
                    break;
                case 2:
                    circularMenu(circularQ);
                    break;
                case 3:
                    priorityDequeMenu(pQueue, deque);
                    break;
            }
        }
    }

    static void normalMenu(NormalQueue q) {
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
            if (c == 6) break;
            switch (c) {
                case 1:
                    System.out.print("Enter playerID to enqueue (e.g. Player:Varun): ");
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

    static void priorityDequeMenu(PriorityQueue pq, Deque dq) {
        while (true) {
            System.out.println("\n-- Priority Queue (Support Tickets) and Deque (Matchmaking) --");
            System.out.println("Choose:");
            System.out.println("1) Priority Queue (Support Tickets) menu");
            System.out.println("2) Deque (Matchmaking double-ended) menu");
            System.out.println("3) Back to main menu");
            int choice = safeIntInput(1, 3);
            if (choice == 3) break;
            if (choice == 1) {
                while (true) {
                    System.out.println("\n--- Priority Queue Menu (Support Tickets) ---");
                    System.out.println("1) Enqueue ticket (item, priority)");
                    System.out.println("2) Dequeue ticket (serve highest priority)");
                    System.out.println("3) isEmpty");
                    System.out.println("4) Display");
                    System.out.println("5) Back");
                    int c = safeIntInput(1, 5);
                    if (c == 5) break;
                    switch (c) {
                        case 1:
                            System.out.print("Enter ticket description (e.g. Ticket:Bug123): ");
                            String itm = sc.nextLine().trim();
                            System.out.print("Enter priority (1-100 where 1=highest): ");
                            int pr = safeIntInput(1, 100);
                            pq.enqueue(itm, pr);
                            break;
                        case 2:
                            String served = pq.dequeue();
                            if (served != null) System.out.println("Served: " + served);
                            break;
                        case 3:
                            System.out.println("isEmpty: " + pq.isEmpty());
                            break;
                        case 4:
                            pq.display();
                            break;
                    }
                }
            } else {
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
                    if (c == 8) break;
                    switch (c) {
                        case 1:
                            System.out.print("Enter VIP player to insertFront: ");
                            String v = sc.nextLine().trim();
                            dq.insertFront(v);
                            break;
                        case 2:
                            System.out.print("Enter player to insertRear: ");
                            String r = sc.nextLine().trim();
                            dq.insertRear(r);
                            break;
                        case 3:
                            String df = dq.deleteFront();
                            if (df != null) System.out.println("Deleted front: " + df);
                            break;
                        case 4:
                            String dr = dq.deleteRear();
                            if (dr != null) System.out.println("Deleted rear: " + dr);
                            break;
                        case 5:
                            dq.getFront();
                            dq.getRear();
                            break;
                        case 6:
                            System.out.println("isEmpty: " + dq.isEmpty() + ", isFull: " + dq.isFull());
                            break;
                        case 7:
                            dq.display();
                            break;
                    }
                }
            }
        }
    }

    static int safeIntInput(int min, int max) {
        while (true) {
            String line = sc.nextLine().trim();
            try {
                int v = Integer.parseInt(line);
                if (v < min || v > max) {
                    System.out.print("Invalid choice. Enter a number between " + min + " and " + max + ": ");
                    continue;
                }
                return v;
            } catch (NumberFormatException e) {
                System.out.print("Malformed input. Enter a number between " + min + " and " + max + ": ");
            }
        }
    }
}

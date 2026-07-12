// ============================================================
// CIRCULAR DOUBLY LINKED LIST - Session 14
// Nama  : Faiz Azhar
// Tugas : Latihan Circular Doubly Linked List pada Java
// ============================================================

public class CircularDoublyLinkedList {

    // Setiap node memiliki dua pointer: prev dan next
    // Node terakhir -> node pertama (next)
    // Node pertama -> node terakhir (prev)
    static class Node {
        int data;
        Node prev;
        Node next;

        Node(int data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

    Node head;

    // Helper: tampilkan list (forward)
    void display() {
        if (head == null) {
            System.out.println("List: (kosong)");
            return;
        }
        Node curr = head;
        System.out.print("List: ");
        do {
            System.out.print(curr.data);
            if (curr.next != head) System.out.print(" <-> ");
            curr = curr.next;
        } while (curr != head);
        System.out.println(" (circular)");
    }

    // Helper: tampilkan list (backward)
    void displayReverse() {
        if (head == null) {
            System.out.println("List reverse: (kosong)");
            return;
        }
        Node tail = head.prev;
        Node curr = tail;
        System.out.print("List reverse: ");
        do {
            System.out.print(curr.data);
            if (curr.prev != tail) System.out.print(" <-> ");
            curr = curr.prev;
        } while (curr != tail);
        System.out.println(" (circular)");
    }

    // =======================================================
    // INSERTION
    // =======================================================

    // 1. Insert at Beginning
    void insertAtBeginning(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            newNode.next = newNode;
            newNode.prev = newNode;
            head = newNode;
        } else {
            Node tail = head.prev;
            newNode.next = head;
            newNode.prev = tail;
            tail.next = newNode;
            head.prev = newNode;
            head = newNode;
        }
        System.out.println("Inserted at beginning: " + data);
    }

    // 2. Insert after a given node
    void insertAfterNode(int targetData, int data) {
        if (head == null) { System.out.println("List kosong."); return; }
        Node curr = head;
        do {
            if (curr.data == targetData) {
                Node newNode = new Node(data);
                newNode.next = curr.next;
                newNode.prev = curr;
                curr.next.prev = newNode;
                curr.next = newNode;
                System.out.println("Inserted " + data + " after node " + targetData);
                return;
            }
            curr = curr.next;
        } while (curr != head);
        System.out.println("Node " + targetData + " tidak ditemukan.");
    }

    // 3. Insert before a given node
    void insertBeforeNode(int targetData, int data) {
        if (head == null) { System.out.println("List kosong."); return; }
        Node curr = head;
        do {
            if (curr.data == targetData) {
                Node newNode = new Node(data);
                newNode.next = curr;
                newNode.prev = curr.prev;
                curr.prev.next = newNode;
                curr.prev = newNode;
                if (curr == head) head = newNode;
                System.out.println("Inserted " + data + " before node " + targetData);
                return;
            }
            curr = curr.next;
        } while (curr != head);
        System.out.println("Node " + targetData + " tidak ditemukan.");
    }

    // 4. Insert at a specific position (1-based)
    void insertAtPosition(int position, int data) {
        if (position < 1) { System.out.println("Posisi tidak valid."); return; }
        if (position == 1) { insertAtBeginning(data); return; }
        Node curr = head;
        for (int i = 1; i < position - 1; i++) {
            curr = curr.next;
            if (curr == head) { System.out.println("Posisi " + position + " melebihi panjang list."); return; }
        }
        Node newNode = new Node(data);
        newNode.next = curr.next;
        newNode.prev = curr;
        curr.next.prev = newNode;
        curr.next = newNode;
        System.out.println("Inserted " + data + " at position " + position);
    }

    // 5. Insert at End
    void insertAtEnd(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            newNode.next = newNode;
            newNode.prev = newNode;
            head = newNode;
        } else {
            Node tail = head.prev;
            tail.next = newNode;
            newNode.prev = tail;
            newNode.next = head;
            head.prev = newNode;
        }
        System.out.println("Inserted at end: " + data);
    }

    // =======================================================
    // DELETION
    // =======================================================

    // 1. Delete at Beginning
    void deleteAtBeginning() {
        if (head == null) { System.out.println("List kosong."); return; }
        System.out.println("Deleted at beginning: " + head.data);
        if (head.next == head) {
            head = null;
        } else {
            Node tail = head.prev;
            head = head.next;
            head.prev = tail;
            tail.next = head;
        }
    }

    // 2. Delete at End
    void deleteAtEnd() {
        if (head == null) { System.out.println("List kosong."); return; }
        Node tail = head.prev;
        System.out.println("Deleted at end: " + tail.data);
        if (tail == head) {
            head = null;
        } else {
            tail.prev.next = head;
            head.prev = tail.prev;
        }
    }

    // 3. Delete a specific node (by value)
    void deleteNode(int targetData) {
        if (head == null) { System.out.println("List kosong."); return; }
        Node curr = head;
        do {
            if (curr.data == targetData) {
                if (curr.next == curr) {
                    head = null;
                } else {
                    curr.prev.next = curr.next;
                    curr.next.prev = curr.prev;
                    if (curr == head) head = curr.next;
                }
                System.out.println("Deleted node: " + targetData);
                return;
            }
            curr = curr.next;
        } while (curr != head);
        System.out.println("Node " + targetData + " tidak ditemukan.");
    }

    // -------------------------------------------------------
    // MAIN - Demo semua operasi Circular Doubly Linked List
    // -------------------------------------------------------
    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println("   CIRCULAR DOUBLY LINKED LIST");
        System.out.println("============================================================");

        // --- INSERTION ---
        System.out.println("\n--- INSERTION ---");

        System.out.println("\n1. Insert at Beginning");
        CircularDoublyLinkedList cdll = new CircularDoublyLinkedList();
        for (int v : new int[]{10, 20, 30, 40}) cdll.insertAtEnd(v);
        cdll.display();
        cdll.insertAtBeginning(5);
        cdll.display();

        System.out.println("\n2. Insert after a given node (after 20)");
        cdll = new CircularDoublyLinkedList();
        for (int v : new int[]{10, 20, 30, 40}) cdll.insertAtEnd(v);
        cdll.display();
        cdll.insertAfterNode(20, 25);
        cdll.display();

        System.out.println("\n3. Insert before a given node (before 30)");
        cdll = new CircularDoublyLinkedList();
        for (int v : new int[]{10, 20, 30, 40}) cdll.insertAtEnd(v);
        cdll.display();
        cdll.insertBeforeNode(30, 25);
        cdll.display();

        System.out.println("\n4. Insert at a specific position (position 3)");
        cdll = new CircularDoublyLinkedList();
        for (int v : new int[]{10, 20, 30, 40}) cdll.insertAtEnd(v);
        cdll.display();
        cdll.insertAtPosition(3, 25);
        cdll.display();

        System.out.println("\n5. Insert at End");
        cdll = new CircularDoublyLinkedList();
        for (int v : new int[]{10, 20, 30, 40}) cdll.insertAtEnd(v);
        cdll.display();
        cdll.insertAtEnd(50);
        cdll.display();

        // --- DELETION ---
        System.out.println("\n--- DELETION ---");

        System.out.println("\n1. Delete at Beginning");
        cdll = new CircularDoublyLinkedList();
        for (int v : new int[]{10, 20, 30, 40}) cdll.insertAtEnd(v);
        cdll.display();
        cdll.deleteAtBeginning();
        cdll.display();

        System.out.println("\n2. Delete at End");
        cdll = new CircularDoublyLinkedList();
        for (int v : new int[]{10, 20, 30, 40}) cdll.insertAtEnd(v);
        cdll.display();
        cdll.deleteAtEnd();
        cdll.display();

        System.out.println("\n3. Delete a specific node (node 30)");
        cdll = new CircularDoublyLinkedList();
        for (int v : new int[]{10, 20, 30, 40}) cdll.insertAtEnd(v);
        cdll.display();
        cdll.deleteNode(30);
        cdll.display();

        // --- Circular check: reverse traversal ---
        System.out.println("\n--- CIRCULAR CHECK (reverse traversal) ---");
        cdll = new CircularDoublyLinkedList();
        for (int v : new int[]{10, 20, 30, 40}) cdll.insertAtEnd(v);
        cdll.display();
        cdll.displayReverse();

        System.out.println("\n============================================================");
    }
}

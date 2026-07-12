// ============================================================
// INSERTION DOUBLY LINKED LIST - Session 14
// Nama  : Faiz Azhar
// Tugas : Latihan Insertion pada Doubly Linked List
// ============================================================

public class InsertionDoublyLinkedList {

    // Setiap node memiliki dua pointer: prev dan next
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

    // Helper: tampilkan isi list
    void display() {
        if (head == null) {
            System.out.println("List: (kosong)");
            return;
        }
        Node curr = head;
        System.out.print("List: ");
        while (curr != null) {
            System.out.print(curr.data);
            if (curr.next != null) System.out.print(" <-> ");
            curr = curr.next;
        }
        System.out.println();
    }

    // -------------------------------------------------------
    // 1. Insert at Front/Beginning
    // -------------------------------------------------------
    void insertAtBeginning(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        System.out.println("Inserted at beginning: " + data);
    }

    // -------------------------------------------------------
    // 2. Insertion after a given node
    // -------------------------------------------------------
    void insertAfterNode(int targetData, int data) {
        Node curr = head;
        while (curr != null && curr.data != targetData) curr = curr.next;
        if (curr == null) {
            System.out.println("Node " + targetData + " tidak ditemukan.");
            return;
        }
        Node newNode = new Node(data);
        newNode.next = curr.next;
        newNode.prev = curr;
        if (curr.next != null) curr.next.prev = newNode;
        curr.next = newNode;
        System.out.println("Inserted " + data + " after node " + targetData);
    }

    // -------------------------------------------------------
    // 3. Insertion before a given node
    // -------------------------------------------------------
    void insertBeforeNode(int targetData, int data) {
        Node curr = head;
        while (curr != null && curr.data != targetData) curr = curr.next;
        if (curr == null) {
            System.out.println("Node " + targetData + " tidak ditemukan.");
            return;
        }
        Node newNode = new Node(data);
        newNode.next = curr;
        newNode.prev = curr.prev;
        if (curr.prev != null) curr.prev.next = newNode;
        else head = newNode;
        curr.prev = newNode;
        System.out.println("Inserted " + data + " before node " + targetData);
    }

    // -------------------------------------------------------
    // 4. Insertion at a specific position (1-based)
    // -------------------------------------------------------
    void insertAtPosition(int position, int data) {
        if (position < 1) {
            System.out.println("Posisi tidak valid.");
            return;
        }
        if (position == 1) {
            insertAtBeginning(data);
            return;
        }
        Node curr = head;
        for (int i = 1; i < position - 1; i++) {
            if (curr == null) {
                System.out.println("Posisi " + position + " melebihi panjang list.");
                return;
            }
            curr = curr.next;
        }
        if (curr == null) {
            System.out.println("Posisi " + position + " melebihi panjang list.");
            return;
        }
        Node newNode = new Node(data);
        newNode.next = curr.next;
        newNode.prev = curr;
        if (curr.next != null) curr.next.prev = newNode;
        curr.next = newNode;
        System.out.println("Inserted " + data + " at position " + position);
    }

    // -------------------------------------------------------
    // 5. Insertion at the End
    // -------------------------------------------------------
    void insertAtEnd(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            System.out.println("Inserted at end: " + data);
            return;
        }
        Node curr = head;
        while (curr.next != null) curr = curr.next;
        curr.next = newNode;
        newNode.prev = curr;
        System.out.println("Inserted at end: " + data);
    }

    // -------------------------------------------------------
    // MAIN - Demo semua operasi insertion
    // -------------------------------------------------------
    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println("   INSERTION IN DOUBLY LINKED LIST");
        System.out.println("============================================================");

        // 1. Insert at Beginning
        System.out.println("\n1. Insert a Node at Front/Beginning");
        InsertionDoublyLinkedList dll = new InsertionDoublyLinkedList();
        for (int v : new int[]{10, 20, 30, 40}) dll.insertAtEnd(v);
        dll.display();
        dll.insertAtBeginning(5);
        dll.display();

        // 2. Insertion after a given node
        System.out.println("\n2. Insertion after a given node (after 20)");
        dll = new InsertionDoublyLinkedList();
        for (int v : new int[]{10, 20, 30, 40}) dll.insertAtEnd(v);
        dll.display();
        dll.insertAfterNode(20, 25);
        dll.display();

        // 3. Insertion before a given node
        System.out.println("\n3. Insertion before a given node (before 30)");
        dll = new InsertionDoublyLinkedList();
        for (int v : new int[]{10, 20, 30, 40}) dll.insertAtEnd(v);
        dll.display();
        dll.insertBeforeNode(30, 25);
        dll.display();

        // 4. Insertion at a specific position
        System.out.println("\n4. Insertion at a specific position (position 3)");
        dll = new InsertionDoublyLinkedList();
        for (int v : new int[]{10, 20, 30, 40}) dll.insertAtEnd(v);
        dll.display();
        dll.insertAtPosition(3, 25);
        dll.display();

        // 5. Insertion at the End
        System.out.println("\n5. Insertion at the End");
        dll = new InsertionDoublyLinkedList();
        for (int v : new int[]{10, 20, 30, 40}) dll.insertAtEnd(v);
        dll.display();
        dll.insertAtEnd(50);
        dll.display();

        System.out.println("\n============================================================");
    }
}

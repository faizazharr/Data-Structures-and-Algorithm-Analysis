// ============================================================
// DELETION DOUBLY LINKED LIST - Session 14
// Nama  : Faiz Azhar
// Tugas : Latihan Deletion pada Doubly Linked List
// ============================================================

public class DeletionDoublyLinkedList {

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

    // Helper: tambah node di akhir (untuk setup awal)
    void append(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        Node curr = head;
        while (curr.next != null) curr = curr.next;
        curr.next = newNode;
        newNode.prev = curr;
    }

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
    // 1. Deletion at the Beginning
    // -------------------------------------------------------
    void deleteAtBeginning() {
        if (head == null) {
            System.out.println("List kosong.");
            return;
        }
        System.out.println("Deleted at beginning: " + head.data);
        if (head.next == null) {
            head = null;
        } else {
            head = head.next;
            head.prev = null;
        }
    }

    // -------------------------------------------------------
    // 2. Deletion after a given node
    // -------------------------------------------------------
    void deleteAfterNode(int targetData) {
        Node curr = head;
        while (curr != null && curr.data != targetData) curr = curr.next;
        if (curr == null) {
            System.out.println("Node " + targetData + " tidak ditemukan.");
            return;
        }
        if (curr.next == null) {
            System.out.println("Tidak ada node setelah " + targetData + ".");
            return;
        }
        Node toDelete = curr.next;
        System.out.println("Deleted after node " + targetData + ": " + toDelete.data);
        curr.next = toDelete.next;
        if (toDelete.next != null) toDelete.next.prev = curr;
    }

    // -------------------------------------------------------
    // 3. Deletion before a given node
    // -------------------------------------------------------
    void deleteBeforeNode(int targetData) {
        Node curr = head;
        while (curr != null && curr.data != targetData) curr = curr.next;
        if (curr == null) {
            System.out.println("Node " + targetData + " tidak ditemukan.");
            return;
        }
        if (curr.prev == null) {
            System.out.println("Tidak ada node sebelum " + targetData + ".");
            return;
        }
        Node toDelete = curr.prev;
        System.out.println("Deleted before node " + targetData + ": " + toDelete.data);
        if (toDelete.prev != null) {
            toDelete.prev.next = curr;
            curr.prev = toDelete.prev;
        } else {
            head = curr;
            curr.prev = null;
        }
    }

    // -------------------------------------------------------
    // 4. Deletion at a specific position (1-based)
    // -------------------------------------------------------
    void deleteAtPosition(int position) {
        if (head == null || position < 1) {
            System.out.println("List kosong atau posisi tidak valid.");
            return;
        }
        Node curr = head;
        for (int i = 1; i < position; i++) {
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
        System.out.println("Deleted at position " + position + ": " + curr.data);
        if (curr.prev != null) curr.prev.next = curr.next;
        else head = curr.next;
        if (curr.next != null) curr.next.prev = curr.prev;
    }

    // -------------------------------------------------------
    // 5. Deletion at the End
    // -------------------------------------------------------
    void deleteAtEnd() {
        if (head == null) {
            System.out.println("List kosong.");
            return;
        }
        Node curr = head;
        while (curr.next != null) curr = curr.next;
        System.out.println("Deleted at end: " + curr.data);
        if (curr.prev != null) curr.prev.next = null;
        else head = null;
    }

    // -------------------------------------------------------
    // MAIN - Demo semua operasi deletion
    // -------------------------------------------------------
    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println("   DELETION IN DOUBLY LINKED LIST");
        System.out.println("============================================================");

        // 1. Deletion at the Beginning
        System.out.println("\n1. Deletion at the Beginning");
        DeletionDoublyLinkedList dll = new DeletionDoublyLinkedList();
        for (int v : new int[]{10, 20, 30, 40, 50}) dll.append(v);
        dll.display();
        dll.deleteAtBeginning();
        dll.display();

        // 2. Deletion after a given node
        System.out.println("\n2. Deletion after a given node (after 20)");
        dll = new DeletionDoublyLinkedList();
        for (int v : new int[]{10, 20, 30, 40, 50}) dll.append(v);
        dll.display();
        dll.deleteAfterNode(20);
        dll.display();

        // 3. Deletion before a given node
        System.out.println("\n3. Deletion before a given node (before 40)");
        dll = new DeletionDoublyLinkedList();
        for (int v : new int[]{10, 20, 30, 40, 50}) dll.append(v);
        dll.display();
        dll.deleteBeforeNode(40);
        dll.display();

        // 4. Deletion at a specific position
        System.out.println("\n4. Deletion at a specific position (position 3)");
        dll = new DeletionDoublyLinkedList();
        for (int v : new int[]{10, 20, 30, 40, 50}) dll.append(v);
        dll.display();
        dll.deleteAtPosition(3);
        dll.display();

        // 5. Deletion at the End
        System.out.println("\n5. Deletion at the End");
        dll = new DeletionDoublyLinkedList();
        for (int v : new int[]{10, 20, 30, 40, 50}) dll.append(v);
        dll.display();
        dll.deleteAtEnd();
        dll.display();

        System.out.println("\n============================================================");
    }
}

// ============================================================
// SINGLY LINKED LIST & INSERTION - Session 9
// Nama  : Faiz Azhar
// Tugas : Latihan Singly Linked List dan Insertion pada Java
// ============================================================

public class SinglyLinkedList {

    // Node menyimpan data dan pointer ke node berikutnya
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    Node head; // pointer ke node pertama
    void insertAtHead(int data) {
        Node newNode = new Node(data);
        newNode.next = head; // node baru menunjuk ke head lama
        head = newNode;      // head diperbarui ke node baru
    }

    void insertAtTail(int data) {
        Node newNode = new Node(data);

        // Jika list kosong, node baru langsung jadi head
        if (head == null) {
            head = newNode;
            return;
        }

        // Cari node terakhir
        Node last = head;
        while (last.next != null) {
            last = last.next;
        }

        // Sambungkan node terakhir ke node baru
        last.next = newNode;
    }

    void insertAfter(int targetData, int newData) {
        Node current = head;

        // Cari node dengan nilai targetData
        while (current != null && current.data != targetData) {
            current = current.next;
        }

        if (current == null) {
            System.out.println("Node " + targetData + " tidak ditemukan.");
            return;
        }

        // Sisipkan node baru setelah node target
        Node newNode = new Node(newData);
        newNode.next = current.next; // node baru menunjuk ke node setelah target
        current.next = newNode;      // target menunjuk ke node baru
    }

    void insertAtPosition(int position, int data) {
        if (position == 0) {
            insertAtHead(data);
            return;
        }

        Node newNode = new Node(data);
        Node current = head;

        // Traversal ke posisi sebelum target
        for (int i = 0; i < position - 1; i++) {
            if (current == null) {
                System.out.println("Posisi " + position + " melebihi panjang list.");
                return;
            }
            current = current.next;
        }

        newNode.next = current.next;
        current.next = newNode;
    }

    void display() {
        if (head == null) {
            System.out.println("List kosong.");
            return;
        }

        Node current = head;
        System.out.print("List: ");
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) System.out.print(" -> ");
            current = current.next;
        }
        System.out.println(" -> NULL");
    }

    // -------------------------------------------------------
    // MAIN - Demo semua jenis insertion
    // -------------------------------------------------------
    public static void main(String[] args) {
        SinglyLinkedList sll = new SinglyLinkedList();

        System.out.println("====== DEMO SINGLY LINKED LIST & INSERTION ======\n");

        // Insert di tail
        System.out.println("-- Insert di tail: 20, 30, 40 --");
        sll.insertAtTail(20);
        sll.insertAtTail(30);
        sll.insertAtTail(40);
        sll.display();

        // Insert di head
        System.out.println("\n-- Insert 10 di head --");
        sll.insertAtHead(10);
        sll.display();

        // Insert setelah node tertentu (di tengah)
        System.out.println("\n-- Insert 25 setelah node 20 (di tengah) --");
        sll.insertAfter(20, 25);
        sll.display();

        // Insert di posisi tertentu
        System.out.println("\n-- Insert 5 di posisi index 0 (head baru) --");
        sll.insertAtPosition(0, 5);
        sll.display();

        System.out.println("\n-- Insert 99 di posisi index 3 --");
        sll.insertAtPosition(3, 99);
        sll.display();
    }
}

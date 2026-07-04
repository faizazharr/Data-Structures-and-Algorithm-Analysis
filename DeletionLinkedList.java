// ============================================================
// DELETION IN LINKED LIST - Session 9
// Nama  : Faiz Azhar
// Tugas : Latihan Deletion pada Singly Linked List Java
// ============================================================

public class DeletionLinkedList {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    Node head;

    // Helper: tambah node di akhir (untuk setup data awal)
    void insertAtTail(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        Node last = head;
        while (last.next != null) last = last.next;
        last.next = newNode;
    }

    void removeFirst() {
        if (head == null) {
            System.out.println("List kosong, tidak ada yang dihapus.");
            return;
        }
        System.out.println("Hapus node pertama: " + head.data);
        head = head.next; // head baru adalah node kedua
    }

    void removeAtPosition(int position) {
        if (head == null) {
            System.out.println("List kosong.");
            return;
        }

        // Jika posisi 0, sama dengan removeFirst
        if (position == 0) {
            removeFirst();
            return;
        }

        Node current = head;
        for (int i = 0; i < position - 1; i++) {
            if (current.next == null) {
                System.out.println("Posisi " + position + " melebihi panjang list.");
                return;
            }
            current = current.next;
        }

        if (current.next == null) {
            System.out.println("Posisi " + position + " melebihi panjang list.");
            return;
        }

        System.out.println("Hapus node di posisi " + position + ": " + current.next.data);
        current.next = current.next.next; // lewati node yang dihapus
    }

    void removeLast() {
        if (head == null) {
            System.out.println("List kosong.");
            return;
        }

        // Jika hanya ada satu node
        if (head.next == null) {
            System.out.println("Hapus node terakhir: " + head.data);
            head = null;
            return;
        }

        // Cari node kedua terakhir
        Node current = head;
        while (current.next.next != null) {
            current = current.next;
        }

        System.out.println("Hapus node terakhir: " + current.next.data);
        current.next = null; // putus sambungan ke node terakhir
    }

    // -------------------------------------------------------
    // TAMPILKAN ISI LIST
    // -------------------------------------------------------
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
    // MAIN - Demo semua jenis deletion
    // -------------------------------------------------------
    public static void main(String[] args) {
        DeletionLinkedList dll = new DeletionLinkedList();

        System.out.println("====== DEMO DELETION IN LINKED LIST ======\n");

        // Setup data awal
        dll.insertAtTail(10);
        dll.insertAtTail(20);
        dll.insertAtTail(30);
        dll.insertAtTail(40);
        dll.insertAtTail(50);
        System.out.println("-- Data awal --");
        dll.display();

        // 1. Hapus node pertama
        System.out.println("\n-- Removing the first node --");
        dll.removeFirst();
        dll.display();

        // 2. Hapus node di tengah (posisi index 1)
        System.out.println("\n-- Removing a node in the middle (posisi index 1) --");
        dll.removeAtPosition(1);
        dll.display();

        // 3. Hapus node terakhir
        System.out.println("\n-- Removing the last node --");
        dll.removeLast();
        dll.display();
    }
}

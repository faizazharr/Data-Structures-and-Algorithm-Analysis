// ============================================================
// CIRCULAR LINKED LIST - Session 9
// Nama  : Faiz Azhar
// Tugas : Latihan Circular Linked List pada Java
// ============================================================

public class CircularLinkedList {

    // Node adalah unit terkecil dari linked list
    // Setiap node menyimpan data dan pointer ke node berikutnya
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // head adalah pointer ke node pertama dalam list
    Node head;

    // -------------------------------------------------------
    // INSERT DI AKHIR LIST
    // Node baru ditambahkan setelah node terakhir,
    // lalu node terakhir diarahkan kembali ke head (melingkar)
    // -------------------------------------------------------
    void insertAtEnd(int data) {
        Node newNode = new Node(data);

        // Jika list masih kosong, node baru menjadi head
        // dan next-nya menunjuk ke dirinya sendiri
        if (head == null) {
            head = newNode;
            newNode.next = head;
            return;
        }

        // Cari node terakhir (yang next-nya menunjuk ke head)
        Node last = head;
        while (last.next != head) {
            last = last.next;
        }

        // Sambungkan node terakhir ke node baru
        last.next = newNode;
        // Node baru menunjuk kembali ke head (melingkar)
        newNode.next = head;
    }

    // -------------------------------------------------------
    // INSERT DI AWAL LIST
    // Node baru menjadi head baru,
    // lalu node terakhir diarahkan ke head baru
    // -------------------------------------------------------
    void insertAtBeginning(int data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = newNode;
            newNode.next = head;
            return;
        }

        // Cari node terakhir untuk diupdate next-nya
        Node last = head;
        while (last.next != head) {
            last = last.next;
        }

        // Node baru menunjuk ke head lama
        newNode.next = head;
        // Node terakhir menunjuk ke head baru
        last.next = newNode;
        // Update head ke node baru
        head = newNode;
    }

    // -------------------------------------------------------
    // DELETE NODE BERDASARKAN VALUE
    // Cari node dengan data tertentu lalu hapus dari list
    // -------------------------------------------------------
    void delete(int data) {
        if (head == null) {
            System.out.println("List kosong, tidak ada yang dihapus.");
            return;
        }

        Node current = head;
        Node prev = null;

        // Kasus: yang dihapus adalah head
        if (head.data == data) {
            // Cari node terakhir
            Node last = head;
            while (last.next != head) {
                last = last.next;
            }

            // Jika hanya ada satu node
            if (head.next == head) {
                head = null;
            } else {
                head = head.next;
                last.next = head;
            }
            System.out.println("Node " + data + " berhasil dihapus.");
            return;
        }

        // Cari node yang ingin dihapus
        do {
            prev = current;
            current = current.next;
            if (current.data == data) {
                prev.next = current.next;
                System.out.println("Node " + data + " berhasil dihapus.");
                return;
            }
        } while (current != head);

        System.out.println("Node " + data + " tidak ditemukan.");
    }

    // -------------------------------------------------------
    // TAMPILKAN SEMUA ISI LIST
    // Mulai dari head, terus sampai kembali ke head
    // -------------------------------------------------------
    void display() {
        if (head == null) {
            System.out.println("List kosong.");
            return;
        }

        Node current = head;
        System.out.print("List: ");
        do {
            System.out.print(current.data);
            if (current.next != head) System.out.print(" -> ");
            current = current.next;
        } while (current != head);
        System.out.println(" -> (kembali ke head: " + head.data + ")");
    }

    // -------------------------------------------------------
    // MAIN - Demo semua operasi Circular Linked List
    // -------------------------------------------------------
    public static void main(String[] args) {
        CircularLinkedList cll = new CircularLinkedList();

        System.out.println("====== DEMO CIRCULAR LINKED LIST ======\n");

        // Insert beberapa data
        System.out.println("-- Insert di akhir: 10, 20, 30, 40 --");
        cll.insertAtEnd(10);
        cll.insertAtEnd(20);
        cll.insertAtEnd(30);
        cll.insertAtEnd(40);
        cll.display();

        // Insert di awal
        System.out.println("\n-- Insert 5 di awal --");
        cll.insertAtBeginning(5);
        cll.display();

        // Hapus node
        System.out.println("\n-- Hapus node 20 --");
        cll.delete(20);
        cll.display();

        System.out.println("\n-- Hapus node 5 (head) --");
        cll.delete(5);
        cll.display();

        System.out.println("\n-- Hapus node 99 (tidak ada) --");
        cll.delete(99);
    }
}

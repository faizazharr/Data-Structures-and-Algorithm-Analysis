// Circular Queue - Session 15 - Faiz Azhar - contoh: antrian printer

public class CircularQueue {
    int[] arr;
    int front = 0, rear = -1, count = 0, capacity;

    CircularQueue(int size) { arr = new int[size]; capacity = size; }

    void enqueue(int data) {
        if (count == capacity) { System.out.println("Antrian penuh."); return; }
        rear = (rear + 1) % capacity; // berputar kembali ke 0 setelah ujung array
        arr[rear] = data;
        count++;
    }

    int dequeue() {
        if (count == 0) { System.out.println("Antrian kosong."); return -1; }
        int val = arr[front];
        front = (front + 1) % capacity; // ruang bekas bisa dipakai lagi
        count--;
        return val;
    }

    void display() {
        System.out.print("Antrian cetak: ");
        for (int i = 0; i < count; i++) System.out.print(arr[(front + i) % capacity] + (i < count - 1 ? " -> " : "\n"));
        if (count == 0) System.out.println("kosong");
    }

    public static void main(String[] args) {
        CircularQueue pq = new CircularQueue(4);
        pq.enqueue(1); pq.enqueue(2); pq.enqueue(3); pq.enqueue(4);
        pq.display();
        pq.dequeue(); pq.dequeue(); // tugas 1 & 2 selesai dicetak
        pq.display();
        pq.enqueue(5); pq.enqueue(6); // ruang bekas dipakai lagi (circular)
        pq.display();
    }
}

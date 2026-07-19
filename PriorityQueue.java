// Priority Queue - Session 15 - Faiz Azhar - contoh: penjadwalan proses OS
// angka prioritas lebih kecil = diproses lebih dulu

public class PriorityQueue {
    static class Item { int data, priority; Item(int d, int p) { data = d; priority = p; } }

    Item[] arr;
    int size = 0, capacity;

    PriorityQueue(int cap) { arr = new Item[cap]; capacity = cap; }

    void enqueue(int data, int priority) {
        if (size == capacity) { System.out.println("Penuh."); return; }
        int i = size - 1;
        while (i >= 0 && arr[i].priority > priority) { arr[i + 1] = arr[i]; i--; }
        arr[i + 1] = new Item(data, priority);
        size++;
    }

    int dequeue() {
        if (size == 0) { System.out.println("Kosong."); return -1; }
        int val = arr[0].data;
        for (int i = 1; i < size; i++) arr[i - 1] = arr[i];
        size--;
        return val;
    }

    void display() {
        System.out.print("Antrian proses: ");
        for (int i = 0; i < size; i++) System.out.print(arr[i].data + "(p" + arr[i].priority + ")" + (i < size - 1 ? " -> " : "\n"));
        if (size == 0) System.out.println("kosong");
    }

    public static void main(String[] args) {
        PriorityQueue pq = new PriorityQueue(5);
        pq.enqueue(101, 3); pq.enqueue(102, 1); pq.enqueue(103, 2); pq.enqueue(104, 5);
        pq.display();
        System.out.println("Dequeue: " + pq.dequeue());
        pq.display();
    }
}

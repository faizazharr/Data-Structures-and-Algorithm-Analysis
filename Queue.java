// Queue (FIFO) - Session 15 - Faiz Azhar

public class Queue {
    int[] arr;
    int front = 0, rear = -1, count = 0, capacity;

    Queue(int size) { arr = new int[size]; capacity = size; }

    void enqueue(int data) {
        if (count == capacity) { System.out.println("Queue penuh."); return; }
        rear = (rear + 1) % capacity;
        arr[rear] = data;
        count++;
    }

    int dequeue() {
        if (count == 0) { System.out.println("Queue kosong."); return -1; }
        int val = arr[front];
        front = (front + 1) % capacity;
        count--;
        return val;
    }

    void display() {
        System.out.print("Queue (front->rear): ");
        for (int i = 0; i < count; i++) System.out.print(arr[(front + i) % capacity] + (i < count - 1 ? " -> " : "\n"));
        if (count == 0) System.out.println("kosong");
    }

    public static void main(String[] args) {
        Queue q = new Queue(4);
        q.enqueue(10); q.enqueue(20); q.enqueue(30);
        q.display();
        System.out.println("Dequeue: " + q.dequeue());
        q.display();
        q.enqueue(40); q.enqueue(50); q.enqueue(60); // enqueue terakhir gagal, queue penuh
        q.display();
    }
}

// Stack (LIFO) - Session 15 - Faiz Azhar

public class Stack {
    int[] arr;
    int top = -1, capacity;

    Stack(int size) { arr = new int[size]; capacity = size; }

    void push(int data) {
        if (top == capacity - 1) { System.out.println("Stack penuh."); return; }
        arr[++top] = data;
    }

    int pop() {
        if (top == -1) { System.out.println("Stack kosong."); return -1; }
        return arr[top--];
    }

    void display() {
        System.out.print("Stack (top->bottom): ");
        for (int i = top; i >= 0; i--) System.out.print(arr[i] + (i > 0 ? " -> " : "\n"));
        if (top == -1) System.out.println("kosong");
    }

    public static void main(String[] args) {
        Stack s = new Stack(4);
        s.push(10); s.push(20); s.push(30);
        s.display();
        System.out.println("Pop: " + s.pop());
        s.display();
        s.push(40); s.push(50); s.push(60); // push terakhir gagal, stack penuh
        s.display();
    }
}

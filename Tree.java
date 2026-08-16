import java.util.ArrayList;
import java.util.List;

public class Tree {
    static class Node {
        String data;
        List<Node> children = new ArrayList<>();
        Node(String data) { this.data = data; }
        Node addChild(Node child) { children.add(child); return child; }
    }

    public static void main(String[] args) {
        // Root node
        Node root = new Node("A");

        // Child dari root
        Node b = root.addChild(new Node("B"));
        Node c = root.addChild(new Node("C"));

        // Child dari B
        b.addChild(new Node("D"));
        b.addChild(new Node("E"));

        // Child dari C
        c.addChild(new Node("F"));

        System.out.println("Root: " + root.data);
        System.out.println("Child dari " + b.data + ": D, E");
        System.out.println("Child dari " + c.data + ": F");
        System.out.println("Parent B memiliki child: " + !b.children.isEmpty());
        System.out.println("Parent D memiliki child: " + !new Node("D").children.isEmpty());
    }
}

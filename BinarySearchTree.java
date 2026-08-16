public class BinarySearchTree {
    static class Node {
        int data;
        Node left, right;
        Node(int data) { this.data = data; }
    }

    Node root;

    void insert(int data) { root = insertRec(root, data); }

    Node insertRec(Node node, int data) {
        if (node == null) return new Node(data);
        if (data < node.data) node.left = insertRec(node.left, data);
        else if (data > node.data) node.right = insertRec(node.right, data);
        return node;
    }

    boolean search(Node node, int key) {
        if (node == null) return false;
        if (node.data == key) return true;
        return key < node.data ? search(node.left, key) : search(node.right, key);
    }

    void inorder(Node node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.data + " ");
        inorder(node.right);
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        int[] values = {50, 30, 70, 20, 40, 60, 80};
        for (int v : values) bst.insert(v);

        System.out.print("Inorder traversal: ");
        bst.inorder(bst.root);

        System.out.println("\nSearch 40: " + bst.search(bst.root, 40));
        System.out.println("Search 100: " + bst.search(bst.root, 100));
    }
}

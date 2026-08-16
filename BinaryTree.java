public class BinaryTree {
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
        else node.right = insertRec(node.right, data);
        return node;
    }

    void inorder(Node node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.data + " ");
        inorder(node.right);
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        int[] values = {50, 30, 70, 20, 40, 60, 80};
        for (int v : values) tree.insert(v);

        System.out.print("Inorder traversal: ");
        tree.inorder(tree.root);
    }
}

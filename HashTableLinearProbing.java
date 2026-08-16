public class HashTableLinearProbing {
    int[] table = new int[8];
    boolean[] used = new boolean[8];

    int hash(int key) {
        return key % 8;
    }

    void insert(int key) {
        int idx = hash(key);
        int start = idx;
        while (used[idx]) {
            idx = (idx + 1) % 8;
            if (idx == start) return; // tabel penuh
        }
        table[idx] = key;
        used[idx] = true;
        System.out.println("Insert " + key + " -> h(" + key + ")=" + hash(key) + ", ditempatkan di indeks " + idx);
    }

    void print() {
        System.out.println("\nIsi Hash Table:");
        for (int i = 0; i < 8; i++) {
            System.out.println(i + " : " + (used[i] ? table[i] : "-"));
        }
    }

    public static void main(String[] args) {
        HashTableLinearProbing ht = new HashTableLinearProbing();
        int[] data = {72, 27, 36, 45, 63, 82, 94, 105};
        for (int k : data) ht.insert(k);
        ht.print();
    }
}

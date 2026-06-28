// ============================================================
// INSERTION - Session 8
// Nama  : Faiz Azhar
// Tugas : Latihan Insertion pada Array Java
// ============================================================

import java.util.Arrays;

public class Insertion {

    // Menyisipkan elemen baru ke posisi tertentu dalam array.
    // Karena ukuran array di Java tetap (fixed-size), array baru
    // dibuat dengan ukuran +1, lalu elemen-elemen setelah posisi
    // penyisipan digeser ke kanan satu langkah.
    static int[] insertAtPosition(int[] arr, int position, int value) {
        int[] result = new int[arr.length + 1];

        // Copy elemen sebelum posisi penyisipan
        for (int i = 0; i < position; i++) {
            result[i] = arr[i];
        }

        // Sisipkan elemen baru
        result[position] = value;

        // Geser sisa elemen ke kanan
        for (int i = position; i < arr.length; i++) {
            result[i + 1] = arr[i];
        }

        return result;
    }

    // Menambahkan elemen baru di akhir array (kasus khusus insertion)
    static int[] insertAtEnd(int[] arr, int value) {
        int[] result = Arrays.copyOf(arr, arr.length + 1);
        result[result.length - 1] = value;
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40, 50};

        System.out.println("====== DEMO INSERTION PADA ARRAY ======\n");

        System.out.println("Array awal       : " + Arrays.toString(arr));

        // Sisipkan nilai 99 di posisi index 2
        int posisi = 2;
        int nilaiBaru = 99;
        int[] hasilSisip = insertAtPosition(arr, posisi, nilaiBaru);
        System.out.println("Sisipkan " + nilaiBaru + " di posisi " + posisi + " : " + Arrays.toString(hasilSisip));

        // Tambahkan nilai 77 di akhir array
        int[] hasilTambah = insertAtEnd(arr, 77);
        System.out.println("Tambahkan 77 di akhir array  : " + Arrays.toString(hasilTambah));
    }
}

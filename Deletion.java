// ============================================================
// DELETION - Session 8
// Nama  : Faiz Azhar
// Tugas : Latihan Deletion pada Array Java
// ============================================================

import java.util.Arrays;

public class Deletion {

    // Menghapus elemen pada posisi tertentu.
    // Karena ukuran array di Java tetap (fixed-size), array baru
    // dibuat dengan ukuran -1, lalu elemen-elemen setelah posisi
    // yang dihapus digeser ke kiri satu langkah untuk mengisi
    // posisi yang kosong.
    static int[] deleteAtPosition(int[] arr, int position) {
        int[] result = new int[arr.length - 1];

        // Copy elemen sebelum posisi yang dihapus
        for (int i = 0; i < position; i++) {
            result[i] = arr[i];
        }

        // Geser elemen setelah posisi yang dihapus ke kiri
        for (int i = position + 1; i < arr.length; i++) {
            result[i - 1] = arr[i];
        }

        return result;
    }

    // Menghapus elemen berdasarkan nilai (menghapus kemunculan pertama).
    // Mencari index nilai tersebut dulu, baru memanggil deleteAtPosition.
    static int[] deleteByValue(int[] arr, int value) {
        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("Nilai " + value + " tidak ditemukan, array tidak berubah.");
            return arr;
        }

        return deleteAtPosition(arr, index);
    }

    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40, 50};

        System.out.println("====== DEMO DELETION PADA ARRAY ======\n");
        System.out.println("Array awal                : " + Arrays.toString(arr));

        // Hapus elemen di posisi index 1 (nilai 20)
        int posisi = 1;
        int[] hasilHapusPosisi = deleteAtPosition(arr, posisi);
        System.out.println("Hapus elemen di posisi " + posisi + "    : " + Arrays.toString(hasilHapusPosisi));

        // Hapus elemen berdasarkan nilai
        int nilaiDihapus = 40;
        int[] hasilHapusNilai = deleteByValue(arr, nilaiDihapus);
        System.out.println("Hapus elemen bernilai " + nilaiDihapus + "  : " + Arrays.toString(hasilHapusNilai));

        // Contoh nilai yang tidak ada dalam array
        int nilaiTidakAda = 99;
        System.out.println("\nCoba hapus nilai " + nilaiTidakAda + " (tidak ada di array):");
        deleteByValue(arr, nilaiTidakAda);
    }
}

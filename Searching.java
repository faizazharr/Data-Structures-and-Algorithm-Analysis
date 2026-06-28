// ============================================================
// SEARCHING - Session 8
// Nama  : Faiz Azhar
// Tugas : Latihan Linear Search & Binary Search pada Array Java
// ============================================================

import java.util.Arrays;

public class Searching {

    // Linear Search: menelusuri array satu per satu dari awal.
    // Bisa dipakai pada array terurut maupun tidak terurut.
    // Kompleksitas waktu: O(n)
    static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i; // ditemukan, kembalikan index
            }
        }
        return -1; // tidak ditemukan
    }

    // Binary Search: hanya valid jika array sudah terurut.
    // Bekerja dengan membagi dua array setiap iterasi dan mencari
    // di bagian yang relevan, sehingga lebih cepat dari linear search.
    // Kompleksitas waktu: O(log n)
    static int binarySearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == target) {
                return mid; // ditemukan
            } else if (arr[mid] < target) {
                low = mid + 1; // cari di bagian kanan
            } else {
                high = mid - 1; // cari di bagian kiri
            }
        }
        return -1; // tidak ditemukan
    }

    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40, 50, 60, 70};

        System.out.println("====== DEMO SEARCHING PADA ARRAY ======\n");
        System.out.println("Array         : " + Arrays.toString(arr));

        // --- Linear Search ---
        int targetLinear = 40;
        int hasilLinear = linearSearch(arr, targetLinear);
        System.out.println("\n[Linear Search] Cari nilai " + targetLinear);
        if (hasilLinear != -1) {
            System.out.println("Ditemukan di index : " + hasilLinear);
        } else {
            System.out.println("Nilai tidak ditemukan.");
        }

        // --- Binary Search (array harus sudah terurut) ---
        int targetBinary = 60;
        int hasilBinary = binarySearch(arr, targetBinary);
        System.out.println("\n[Binary Search] Cari nilai " + targetBinary);
        if (hasilBinary != -1) {
            System.out.println("Ditemukan di index : " + hasilBinary);
        } else {
            System.out.println("Nilai tidak ditemukan.");
        }

        // Contoh nilai yang tidak ada dalam array
        int targetNotFound = 99;
        System.out.println("\n[Linear Search] Cari nilai " + targetNotFound);
        System.out.println("Hasil : " + (linearSearch(arr, targetNotFound) == -1 ? "Tidak ditemukan" : "Ditemukan"));
    }
}

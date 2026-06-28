// ============================================================
// ENCAPSULATION - Session 3
// Nama  : Faiz Azhar
// Tugas : Latihan Encapsulation Java
// ============================================================

// Class Mahasiswa dengan enkapsulasi
class Mahasiswa {
    // Private fields - tidak bisa diakses langsung dari luar class
    private String nim;
    private String nama;
    private double ipk;
    private int semester;

    // Constructor
    Mahasiswa(String nim, String nama, double ipk, int semester) {
        this.nim = nim;
        this.nama = nama;
        setIpk(ipk);         // validasi lewat setter
        setSemester(semester);
    }

    // --- GETTER (Information Hiding: baca data secara terkontrol) ---
    public String getNim() {
        return nim;
    }

    public String getNama() {
        return nama;
    }

    public double getIpk() {
        return ipk;
    }

    public int getSemester() {
        return semester;
    }

    // --- SETTER (Validasi sebelum mengubah data) ---
    public void setNama(String nama) {
        if (nama != null && !nama.isEmpty()) {
            this.nama = nama;
        } else {
            System.out.println("Error: Nama tidak boleh kosong!");
        }
    }

    public void setIpk(double ipk) {
        if (ipk >= 0.0 && ipk <= 4.0) {
            this.ipk = ipk;
        } else {
            System.out.println("Error: IPK harus antara 0.0 - 4.0!");
            this.ipk = 0.0;
        }
    }

    public void setSemester(int semester) {
        if (semester >= 1 && semester <= 14) {
            this.semester = semester;
        } else {
            System.out.println("Error: Semester tidak valid!");
            this.semester = 1;
        }
    }

    // Method untuk menampilkan info
    public void tampilInfo() {
        System.out.println("NIM      : " + nim);
        System.out.println("Nama     : " + nama);
        System.out.println("IPK      : " + ipk);
        System.out.println("Semester : " + semester);
        System.out.println("Status   : " + getStatus());
    }

    // Method internal - logika tersembunyi di dalam class (Modularitas)
    private String getStatus() {
        if (ipk >= 3.5) return "Cumlaude";
        else if (ipk >= 3.0) return "Sangat Memuaskan";
        else if (ipk >= 2.5) return "Memuaskan";
        else return "Cukup";
    }
}

// Main class
public class Encapsulation {
    public static void main(String[] args) {
        System.out.println("====== DEMO ENCAPSULATION ======\n");

        // Membuat objek Mahasiswa
        Mahasiswa mhs1 = new Mahasiswa("2501234567", "Faiz Azhar", 3.75, 4);
        System.out.println("--- Info Mahasiswa ---");
        mhs1.tampilInfo();
        System.out.println();

        // Mengubah data via setter (terkontrol)
        System.out.println("--- Update IPK ---");
        mhs1.setIpk(3.90);
        System.out.println("IPK baru : " + mhs1.getIpk());
        System.out.println();

        // Mencoba set nilai tidak valid
        System.out.println("--- Validasi Data (Enkapsulasi melindungi data) ---");
        mhs1.setIpk(5.0);      // invalid - lebih dari 4.0
        mhs1.setSemester(20);  // invalid - lebih dari 14
        mhs1.setNama("");      // invalid - nama kosong
        System.out.println();

        // Modularitas: objek ke-2 independen
        Mahasiswa mhs2 = new Mahasiswa("2509876543", "Budi Santoso", 2.80, 6);
        System.out.println("--- Info Mahasiswa 2 ---");
        mhs2.tampilInfo();
    }
}

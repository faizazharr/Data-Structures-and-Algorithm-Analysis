// ============================================================
// POLYMORPHISM - Session 3
// Nama  : Faiz Azhar
// Tugas : Latihan Polymorphism Java
// ============================================================

// ---- STATIC BINDING (Overloading) ----
// Method dengan nama sama, parameter berbeda, dalam kelas yang sama
class Kalkulator {
    // Overloading: tambah 2 int
    int tambah(int a, int b) {
        return a + b;
    }

    // Overloading: tambah 3 int
    int tambah(int a, int b, int c) {
        return a + b + c;
    }

    // Overloading: tambah 2 double
    double tambah(double a, double b) {
        return a + b;
    }
}

// ---- DYNAMIC BINDING (Overriding) ----
// Method dengan nama sama di kelas berbeda (butuh inheritance)
class Bentuk {
    void gambar() {
        System.out.println("Menggambar bentuk umum.");
    }
}

class Lingkaran extends Bentuk {
    @Override
    void gambar() {
        System.out.println("Menggambar Lingkaran ⬤");
    }
}

class Persegi extends Bentuk {
    @Override
    void gambar() {
        System.out.println("Menggambar Persegi ■");
    }
}

class Segitiga extends Bentuk {
    @Override
    void gambar() {
        System.out.println("Menggambar Segitiga ▲");
    }
}

// Main class
public class Polymorphism {
    public static void main(String[] args) {
        System.out.println("====== DEMO POLYMORPHISM ======\n");

        // --- Static Binding (Overloading) ---
        System.out.println("--- Static Binding (Method Overloading) ---");
        Kalkulator kalk = new Kalkulator();
        System.out.println("tambah(5, 3)        = " + kalk.tambah(5, 3));
        System.out.println("tambah(5, 3, 2)     = " + kalk.tambah(5, 3, 2));
        System.out.println("tambah(2.5, 1.5)    = " + kalk.tambah(2.5, 1.5));
        System.out.println("(Compiler menentukan method mana yang dipanggil saat compile time)\n");

        // --- Dynamic Binding (Overriding) ---
        System.out.println("--- Dynamic Binding (Method Overriding) ---");

        // Referensi superclass menunjuk ke objek subclass
        Bentuk b1 = new Lingkaran();
        Bentuk b2 = new Persegi();
        Bentuk b3 = new Segitiga();

        // JVM menentukan method yang dipanggil saat runtime (bukan compile time)
        b1.gambar();
        b2.gambar();
        b3.gambar();
        System.out.println("(JVM menentukan method mana yang dipanggil saat runtime)\n");

        // Array bentuk - contoh polimorfisme dinamis
        System.out.println("--- Array Bentuk (Dynamic Polymorphism) ---");
        Bentuk[] bentukArr = { new Lingkaran(), new Persegi(), new Segitiga(), new Lingkaran() };
        for (Bentuk b : bentukArr) {
            b.gambar(); // method yang dipanggil tergantung objek aktual
        }
    }
}

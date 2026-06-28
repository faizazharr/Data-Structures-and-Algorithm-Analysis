// ============================================================
// INHERITANCE - Session 3
// Nama  : Faiz Azhar
// Tugas : Latihan Inheritance Java
// ============================================================

// Superclass (class induk)
class Hewan {
    String nama;
    int umur;

    // Constructor superclass
    Hewan(String nama, int umur) {
        this.nama = nama;
        this.umur = umur;
    }

    void makan() {
        System.out.println(nama + " sedang makan.");
    }

    void info() {
        System.out.println("Nama  : " + nama);
        System.out.println("Umur  : " + umur + " tahun");
    }
}

// Subclass 1 - mewarisi Hewan
class Anjing extends Hewan {
    String ras;

    Anjing(String nama, int umur, String ras) {
        super(nama, umur); // memanggil constructor superclass
        this.ras = ras;
    }

    // Menambahkan behaviour baru
    void menggonggong() {
        System.out.println(nama + " : Guk guk guk!");
    }

    // Override method info() dari superclass
    @Override
    void info() {
        super.info(); // memanggil method superclass
        System.out.println("Ras   : " + ras);
    }
}

// Subclass 2 - mewarisi Hewan
class Kucing extends Hewan {
    String warnaBulu;

    Kucing(String nama, int umur, String warnaBulu) {
        super(nama, umur);
        this.warnaBulu = warnaBulu;
    }

    void mengeong() {
        System.out.println(nama + " : Meong meong!");
    }

    @Override
    void info() {
        super.info();
        System.out.println("Bulu  : " + warnaBulu);
    }
}

// Main class
public class Inheritance {
    public static void main(String[] args) {
        System.out.println("====== DEMO INHERITANCE ======\n");

        // Membuat objek Anjing (subclass)
        Anjing anjing = new Anjing("Buddy", 3, "Golden Retriever");
        System.out.println("--- Info Anjing ---");
        anjing.info();
        anjing.makan();        // method warisan dari Hewan
        anjing.menggonggong(); // method baru di Anjing
        System.out.println();

        // Membuat objek Kucing (subclass)
        Kucing kucing = new Kucing("Milo", 2, "Orange");
        System.out.println("--- Info Kucing ---");
        kucing.info();
        kucing.makan();    // method warisan dari Hewan
        kucing.mengeong(); // method baru di Kucing
        System.out.println();

        // Menunjukkan relasi superclass - subclass
        System.out.println("--- Cek instanceof ---");
        System.out.println("anjing instanceof Hewan : " + (anjing instanceof Hewan));
        System.out.println("kucing instanceof Hewan : " + (kucing instanceof Hewan));
    }
}

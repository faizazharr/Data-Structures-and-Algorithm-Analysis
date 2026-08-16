# LMS Submission Engine & Expression Evaluator

Implementasi Java untuk studi kasus struktur data pada platform LMS (Learning Management System): mengelola alur pengumpulan tugas mahasiswa, dan mesin auto-grading untuk ekspresi matematika (infix → postfix → evaluasi).

## Struktur berkas

```
src/
├── Submission.java           # Model data satu submission tugas
├── SubmissionEngine.java     # Modul 1: antrean, undo, dan pencarian submission
├── ExpressionEvaluator.java  # Modul 2: konversi & evaluasi ekspresi matematika
└── Main.java                 # Program demo yang menjalankan kedua modul
```

## Penjelasan tiap class

### `Submission`
Model data (POJO) untuk satu submission tugas mahasiswa. Menyimpan `submissionID`, `studentID`, `assignmentID`, `timestamp`, dan `answer` (isi jawaban/path file). Semua field bersifat `final` dan hanya bisa dibaca lewat getter, supaya data submission tidak bisa diubah sembarangan setelah dibuat (immutability).

### `SubmissionEngine`
Mengelola alur pengumpulan tugas dengan tiga struktur data yang saling melengkapi tanpa menduplikasi data (hanya menyimpan referensi ke objek `Submission` yang sama):

- `Queue<Submission> submissionQueue` — antrean FIFO untuk memproses submission sesuai urutan kedatangan (`addSubmission`, `processSubmission`).
- `Map<String, Deque<Submission>> undoStacks` — satu stack (LIFO) per mahasiswa untuk fitur undo submission terakhir (`undoSubmission`), dengan batas waktu `UNDO_WINDOW_MS`.
- `Map<String, Submission> submissionIndex` — HashMap untuk pencarian submission secara instan berdasarkan ID (`searchSubmission`), dipakai oleh instruktur/TA.

Submission yang di-undo tidak dihapus langsung dari queue (menghindari operasi remove-in-middle yang mahal), melainkan ditandai lewat penghapusan dari `submissionIndex` lalu dilewati (lazy deletion) saat `processSubmission` dipanggil.

### `ExpressionEvaluator`
Berisi dua method static, keduanya diimplementasikan **iteratif** menggunakan `Deque` sebagai stack eksplisit (bukan rekursi), sehingga aman dari `StackOverflowError` untuk ekspresi yang sangat panjang atau bersarang dalam-dalam:

- `infixToPostfix(String exp)` — mengonversi ekspresi infix (mis. `A + B * C`) ke postfix (`ABC*+`) memakai algoritma shunting-yard.
- `evaluatePostfix(String postfix, Map<Character, Double> values)` — mengevaluasi ekspresi postfix menjadi satu nilai numerik, dengan nilai variabel diberikan lewat parameter `values`.

### `Main`
Program demo yang menjalankan skenario dari studi kasus: menambah beberapa submission, melakukan undo, mencari submission by ID, memproses antrean, lalu mengonversi dan mengevaluasi ekspresi `A + B * C`.

## Cara menjalankan

```bash
cd src
javac *.java
java Main
```

## Status

Sudah dikompilasi dan diuji; keluaran `Main` sudah dicocokkan dengan contoh pada soal (`A + B * C` → `ABC*+`, dan untuk A=2, B=3, C=4 hasilnya 14).

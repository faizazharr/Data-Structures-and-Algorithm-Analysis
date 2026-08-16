// Modul 1 - Assignment Submission Engine
// Struktur data yang dipakai:
//   1. Queue (LinkedList sbg java.util.Queue)  -> processing FIFO sesuai timestamp kedatangan
//   2. Stack (ArrayDeque sbg java.util.Deque)   -> undo submission terakhir (LIFO, per mahasiswa)
//   3. HashMap<submissionID, Submission>        -> pencarian O(1) berdasarkan submissionID
//
// Alasan pemilihan struktur data ada di jawaban pertanyaan (a); kode di sini adalah
// implementasi konkret dari alasan tersebut (pertanyaan b).

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class SubmissionEngine {

    // FIFO queue: submission diproses sesuai urutan kedatangan (fairness by timestamp)
    private final Queue<Submission> submissionQueue = new LinkedList<>();

    // Undo stack per-mahasiswa: hanya submission terakhir milik mahasiswa yg boleh di-undo.
    // Key = studentID, Value = stack submission mahasiswa tsb yang masih ada di antrean.
    private final Map<String, Deque<Submission>> undoStacks = new HashMap<>();

    // Index untuk pencarian cepat berdasarkan submissionID (dipakai instruktur/TA)
    private final Map<String, Submission> submissionIndex = new HashMap<>();

    // Batas waktu undo (mis. 5 menit dalam milidetik), sesuai requirement bisnis #2
    private static final long UNDO_WINDOW_MS = 5 * 60 * 1000L;

    /**
     * Menambahkan submission baru.
     * - enqueue ke submissionQueue -> O(1)
     * - push ke undo stack milik mahasiswa -> O(1)
     * - index ke HashMap by submissionID -> O(1) average
     */
    public void addSubmission(Submission s) {
        submissionQueue.offer(s);
        undoStacks.computeIfAbsent(s.getStudentID(), k -> new ArrayDeque<>()).push(s);
        submissionIndex.put(s.getSubmissionID(), s);
        System.out.println("[ADD] " + s);
    }

    /**
     * Undo submission TERAKHIR milik studentID tertentu, selama masih dalam
     * UNDO_WINDOW_MS sejak submit dan belum diproses (masih ada di queue).
     * Kompleksitas: O(1) untuk pop dari stack; menandai submission sebagai
     * "dibatalkan" via removal dari index -> O(1) average. Submission yang
     * sudah di-undo tetap ada di queue tapi akan dilewati saat diproses
     * (lazy deletion) agar queue tidak perlu operasi remove-in-middle O(n).
     */
    public boolean undoSubmission(String studentID, long now) {
        Deque<Submission> stack = undoStacks.get(studentID);
        if (stack == null || stack.isEmpty()) {
            System.out.println("[UNDO] Tidak ada submission untuk di-undo: " + studentID);
            return false;
        }
        Submission last = stack.peek();
        if (now - last.getTimestamp() > UNDO_WINDOW_MS) {
            System.out.println("[UNDO] Gagal, window undo sudah lewat: " + last.getSubmissionID());
            return false;
        }
        stack.pop();
        submissionIndex.remove(last.getSubmissionID()); // lazy delete: ditandai batal
        System.out.println("[UNDO] Submission dibatalkan: " + last.getSubmissionID());
        return true;
    }

    /**
     * Memproses submission paling depan antrean (FIFO). Submission yang
     * sudah di-undo (tidak ada lagi di submissionIndex) dilewati.
     * Kompleksitas: O(1) amortized per pemanggilan (poll + cek index O(1)).
     */
    public Submission processSubmission() {
        while (!submissionQueue.isEmpty()) {
            Submission head = submissionQueue.poll();
            if (submissionIndex.containsKey(head.getSubmissionID())) {
                System.out.println("[PROCESS] " + head);
                return head;
            }
            System.out.println("[SKIP] " + head.getSubmissionID() + " sudah di-undo, dilewati");
        }
        System.out.println("[PROCESS] Queue kosong.");
        return null;
    }

    /**
     * Pencarian submission berdasarkan submissionID untuk keperluan
     * instruktur/TA (review, investigasi akademik).
     * Kompleksitas: O(1) rata-rata (hash lookup), bukan O(n) seperti linear search.
     */
    public Submission searchSubmission(String submissionID) {
        Submission result = submissionIndex.get(submissionID);
        System.out.println("[SEARCH] " + submissionID + " -> " + result);
        return result;
    }

    public int queueSize() { return submissionQueue.size(); }
}

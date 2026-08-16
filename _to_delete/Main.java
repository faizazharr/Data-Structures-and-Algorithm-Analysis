// Demo / test-drive untuk SubmissionEngine dan ExpressionEvaluator.
// Menjalankan skenario dari studi kasus LO3.

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Modul 1: Assignment Submission Engine ===");
        SubmissionEngine engine = new SubmissionEngine();
        long t0 = System.currentTimeMillis();

        engine.addSubmission(new Submission("S1", "STD01", "A01", t0, "jawaban1.zip"));
        engine.addSubmission(new Submission("S2", "STD02", "A01", t0 + 1000, "jawaban2.zip"));
        engine.addSubmission(new Submission("S3", "STD01", "A01", t0 + 2000, "jawaban3-revisi.zip"));

        // STD01 salah upload file, langsung undo submission terakhirnya (S3)
        engine.undoSubmission("STD01", t0 + 2500);

        // Instruktur mencari submission tertentu
        engine.searchSubmission("S2");

        // Proses submission sesuai urutan FIFO; S3 otomatis dilewati krn sudah di-undo
        engine.processSubmission();
        engine.processSubmission();
        engine.processSubmission();

        System.out.println("\n=== Modul 2: Auto-Grading Expression Evaluation ===");
        String infix = "A + B * C";
        String postfix = ExpressionEvaluator.infixToPostfix(infix);
        System.out.println("Infix   : " + infix);
        System.out.println("Postfix : " + postfix); // ABC*+

        Map<Character, Double> values = new HashMap<>();
        values.put('A', 2.0);
        values.put('B', 3.0);
        values.put('C', 4.0);
        double result = ExpressionEvaluator.evaluatePostfix(postfix, values);
        System.out.println("A=2, B=3, C=4 -> hasil = " + result); // 2 + 3*4 = 14
    }
}

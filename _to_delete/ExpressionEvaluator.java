// Modul 2 - Auto-Grading Expression Evaluation
// Struktur data: Stack (ArrayDeque, iteratif) dipakai baik untuk konversi
// infix -> postfix (shunting-yard) maupun evaluasi postfix.
// Implementasi SENGAJA iteratif (bukan rekursif) agar tidak stack overflow
// pada ekspresi panjang / deeply nested seperti disebutkan di soal (d).

import java.util.ArrayDeque;
import java.util.Deque;

public class ExpressionEvaluator {

    private static int precedence(char op) {
        switch (op) {
            case '+': case '-': return 1;
            case '*': case '/': return 2;
            case '^': return 3;
            default: return -1;
        }
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    /**
     * Konversi infix -> postfix (algoritma shunting-yard, Dijkstra).
     * Struktur data: Stack (menyimpan operator & tanda kurung).
     * Kompleksitas waktu: O(n), setiap karakter/token di-push & di-pop maksimal sekali.
     * Kompleksitas ruang: O(n) untuk stack operator pada kasus terburuk (mis. semua '(' ).
     */
    public static String infixToPostfix(String exp) {
        StringBuilder output = new StringBuilder();
        Deque<Character> opStack = new ArrayDeque<>(); // stack eksplisit, bukan rekursi

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);
            if (Character.isWhitespace(c)) continue;

            if (Character.isLetterOrDigit(c)) {
                output.append(c);
            } else if (c == '(') {
                opStack.push(c);
            } else if (c == ')') {
                while (!opStack.isEmpty() && opStack.peek() != '(') {
                    output.append(opStack.pop());
                }
                if (opStack.isEmpty()) {
                    throw new IllegalArgumentException("Tanda kurung tidak seimbang: " + exp);
                }
                opStack.pop(); // buang '('
            } else if (isOperator(c)) {
                while (!opStack.isEmpty() && opStack.peek() != '(' &&
                       precedence(opStack.peek()) >= precedence(c)) {
                    output.append(opStack.pop());
                }
                opStack.push(c);
            } else {
                throw new IllegalArgumentException("Token tidak valid: '" + c + "'");
            }
        }
        while (!opStack.isEmpty()) {
            char top = opStack.pop();
            if (top == '(') throw new IllegalArgumentException("Tanda kurung tidak seimbang: " + exp);
            output.append(top);
        }
        return output.toString();
    }

    /**
     * Evaluasi ekspresi postfix menggunakan operand map (nilai variabel opsional).
     * Struktur data: Stack (menyimpan operand double).
     * Kompleksitas waktu: O(n). Kompleksitas ruang: O(n) worst-case.
     * Iteratif -> aman untuk ekspresi panjang, tidak berisiko StackOverflowError
     * seperti pendekatan evaluasi rekursif pada sistem lama.
     */
    public static double evaluatePostfix(String postfix, java.util.Map<Character, Double> values) {
        Deque<Double> stack = new ArrayDeque<>();

        for (int i = 0; i < postfix.length(); i++) {
            char c = postfix.charAt(i);
            if (Character.isWhitespace(c)) continue;

            if (Character.isLetter(c)) {
                Double v = values.get(c);
                if (v == null) throw new IllegalArgumentException("Nilai variabel '" + c + "' tidak ditemukan");
                stack.push(v);
            } else if (Character.isDigit(c)) {
                stack.push((double) (c - '0'));
            } else if (isOperator(c)) {
                if (stack.size() < 2) throw new IllegalArgumentException("Ekspresi postfix tidak valid");
                double b = stack.pop();
                double a = stack.pop();
                switch (c) {
                    case '+': stack.push(a + b); break;
                    case '-': stack.push(a - b); break;
                    case '*': stack.push(a * b); break;
                    case '/':
                        if (b == 0) throw new ArithmeticException("Pembagian dengan nol");
                        stack.push(a / b);
                        break;
                    case '^': stack.push(Math.pow(a, b)); break;
                }
            } else {
                throw new IllegalArgumentException("Token tidak valid: '" + c + "'");
            }
        }
        if (stack.size() != 1) throw new IllegalArgumentException("Ekspresi postfix tidak valid");
        return stack.pop();
    }
}

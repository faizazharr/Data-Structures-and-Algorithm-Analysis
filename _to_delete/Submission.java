// Modul 1 - Assignment Submission Engine
// Model data untuk satu submission mahasiswa.

public class Submission {
    private final String submissionID;
    private final String studentID;
    private final String assignmentID;
    private final long timestamp;   // epoch millis, dipakai untuk urutan FIFO & window undo
    private final String answer;    // isi jawaban / path file

    public Submission(String submissionID, String studentID, String assignmentID,
                       long timestamp, String answer) {
        this.submissionID = submissionID;
        this.studentID = studentID;
        this.assignmentID = assignmentID;
        this.timestamp = timestamp;
        this.answer = answer;
    }

    public String getSubmissionID() { return submissionID; }
    public String getStudentID() { return studentID; }
    public String getAssignmentID() { return assignmentID; }
    public long getTimestamp() { return timestamp; }
    public String getAnswer() { return answer; }

    @Override
    public String toString() {
        return "Submission{id=" + submissionID + ", student=" + studentID +
               ", assignment=" + assignmentID + ", ts=" + timestamp + "}";
    }
}

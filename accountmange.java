import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BorrowRecord {
    private String recordId;
    private String userId;
    private String bookId;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private boolean returned;
    private static final int BORROW_DAYS = 30;

    public BorrowRecord(String recordId, String userId, String bookId) {
        this.recordId = recordId;
        this.userId = userId;
        this.bookId = bookId;
        this.borrowDate = LocalDate.now();
        this.dueDate = borrowDate.plusDays(BORROW_DAYS);
        this.returnDate = null;
        this.returned = false;
    }

    public String getRecordId() {
        return recordId;
    }

    public String getUserId() {
        return userId;
    }

    public String getBookId() {
        return bookId;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public void returnBook() {
        this.returnDate = LocalDate.now();
        this.returned = true;
    }

    public boolean isOverdue() {
        if (returned) {
            return returnDate.isAfter(dueDate);
        }
        return LocalDate.now().isAfter(dueDate);
    }

    public int getOverdueDays() {
        if (!isOverdue()) {
            return 0;
        }
        LocalDate compareDate = returned ? returnDate : LocalDate.now();
        return (int) java.time.temporal.ChronoUnit.DAYS.between(dueDate, compareDate);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return "BorrowRecord{" +
                "recordId='" + recordId + '\'' +
                ", userId='" + userId + '\'' +
                ", bookId='" + bookId + '\'' +
                ", borrowDate=" + borrowDate.format(formatter) +
                ", dueDate=" + dueDate.format(formatter) +
                ", returnDate=" + (returnDate != null ? returnDate.format(formatter) : "未归还") +
                ", returned=" + returned +
                '}';
    }
}
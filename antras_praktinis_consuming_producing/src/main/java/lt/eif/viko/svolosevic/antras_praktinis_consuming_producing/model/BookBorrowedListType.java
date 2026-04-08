package lt.eif.viko.svolosevic.antras_praktinis_consuming_producing.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of borrowed books.
 */
@XmlType(name = "bookBorrowedListType")
public class BookBorrowedListType {
    private List<BookBorrowedType> bookBorrowed = new ArrayList<>();

    public BookBorrowedListType() {}

    public BookBorrowedListType(List<BookBorrowedType> bookBorrowed) {
        this.bookBorrowed = bookBorrowed != null ? bookBorrowed : new ArrayList<>();
    }

    @XmlElement(name = "book_borrowed")
    public List<BookBorrowedType> getBookBorrowed() {
        return bookBorrowed;
    }

    public void setBookBorrowed(List<BookBorrowedType> bookBorrowed) {
        this.bookBorrowed = bookBorrowed;
    }
}

package lt.eif.viko.svolosevic.antras_praktinis_consuming_producing.model;

import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bookBorrowedType", propOrder = {"bookBorrowedId", "borrowStartDate", "borrowExpireDate", "book"})
public class BookBorrowedType {
    @XmlElement(name = "id")
    private int bookBorrowedId;

    @XmlElement(name = "borrow_start_date")
    private String borrowStartDate;

    @XmlElement(name = "borrow_expire_date")
    private String borrowExpireDate;

    @XmlElement
    private BookType book;

    public BookBorrowedType() {}

    public BookBorrowedType(int bookBorrowedId, String borrowStartDate, String borrowExpireDate, BookType book) {
        this.bookBorrowedId = bookBorrowedId;
        this.borrowStartDate = borrowStartDate;
        this.borrowExpireDate = borrowExpireDate;
        this.book = book;
    }

    public int getBookBorrowedId() {
        return bookBorrowedId;
    }

    public void setBookBorrowedId(int bookBorrowedId) {
        this.bookBorrowedId = bookBorrowedId;
    }

    public String getBorrowStartDate() {
        return borrowStartDate;
    }

    public void setBorrowStartDate(String borrowStartDate) {
        this.borrowStartDate = borrowStartDate;
    }

    public String getBorrowExpireDate() {
        return borrowExpireDate;
    }

    public void setBorrowExpireDate(String borrowExpireDate) {
        this.borrowExpireDate = borrowExpireDate;
    }

    public BookType getBook() {
        return book;
    }

    public void setBook(BookType book) {
        this.book = book;
    }
}
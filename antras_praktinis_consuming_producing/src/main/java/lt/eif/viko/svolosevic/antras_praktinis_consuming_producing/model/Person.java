package lt.eif.viko.svolosevic.antras_praktinis_consuming_producing.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.xml.bind.annotation.*;

/**
 * Represents a person in the library system.
 */
@Document(collection = "people")
@XmlRootElement(name = "person")
@XmlType(propOrder = {"personId", "firstName", "lastName", "booksBorrowed"})
public class Person {
    @Id
    @XmlTransient
    private Long id; // MongoDB ID, will be mapped separately
    private int personId; // XML-specific ID from schema
    private String firstName;
    private String lastName;
    private BookBorrowedListType booksBorrowed = new BookBorrowedListType();

    public Person() {}

    public Person(int personId, String firstName, String lastName, BookBorrowedListType booksBorrowed) {
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.booksBorrowed = booksBorrowed != null ? booksBorrowed : new BookBorrowedListType();
    }

    @XmlElement(name = "id")
    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    @XmlElement(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @XmlElement(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @XmlElement(name = "books_borrowed")
    public BookBorrowedListType getBooksBorrowed() {
        return booksBorrowed;
    }

    public void setBooksBorrowed(BookBorrowedListType booksBorrowed) {
        this.booksBorrowed = booksBorrowed;
    }

    @XmlTransient
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
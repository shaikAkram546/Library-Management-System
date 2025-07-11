package com.library_management.Library_Management_System.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library_management.Library_Management_System.model.Book;
import com.library_management.Library_Management_System.model.User;
import com.library_management.Library_Management_System.repos.BookRepo;
import com.library_management.Library_Management_System.repos.ReservationRepo;
import com.library_management.Library_Management_System.repos.UserRepo;

@Service
public class AdminService {

    @Autowired
    BookRepo bookRepo;

    @Autowired
    ReservationRepo reservationRepo;

    @Autowired
    UserService userService;

    @Autowired
    UserRepo userRepo;

    public Book addBook(Book book) {
        book.setAvailableCopies(book.getTotalCopies());
        return bookRepo.save(book);
    }
    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    public void deleteBook(Long bookId) {
        Book book = bookRepo.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + bookId));

        // Check if the book has any reservations before deleting
        if( reservationRepo.existsByBookId(bookId)) {
            throw new RuntimeException("Cannot delete book with id " + bookId + " because it has reservations.");
        }
        
        bookRepo.delete(book);
    }

    public Book updateBook(Long bookId, Book bookDetails) {
        Book book = bookRepo.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + bookId));
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setTotalCopies(bookDetails.getTotalCopies());
        book.setAvailableCopies(bookDetails.getAvailableCopies());
        return bookRepo.save(book);
    }
    public Book getBookById(Long bookId) {
        return bookRepo.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + bookId));
    }
    public List<User> getAllUsers() {
       return userRepo.findAll();
    }

    //finding the books reserved by the user.
    public List<Book> getBooksReservedByUser(String username) {
     List<Book> reservedBooks = userService.getReservedBooksByUser(username);
     return reservedBooks;
    }

    
   
}

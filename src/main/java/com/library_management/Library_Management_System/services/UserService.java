package com.library_management.Library_Management_System.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.security.Principal;
import java.time.LocalDate;
import java.util.* ;

import com.library_management.Library_Management_System.model.Book;
import com.library_management.Library_Management_System.model.Reservation;
import com.library_management.Library_Management_System.model.User;
import com.library_management.Library_Management_System.repos.BookRepo;
import com.library_management.Library_Management_System.repos.ReservationRepo;
import com.library_management.Library_Management_System.repos.UserRepo;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    BookRepo bookRepo;
    @Autowired
    ReservationRepo reservationRepo;
   
    User user;
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User saveUser(User user) {
        return userRepo.save(user);
    }

    public ResponseEntity<?> reserveBook(Long bookId, Principal principal) {
        if (principal == null) {
            return ResponseEntity.badRequest().body("User not authenticated");
        }
        String username = principal.getName();
        User user = userRepo.findByUsername(username);
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }

        if (bookId == null) {
            return ResponseEntity.badRequest().body("Book ID is required");
        }
        Book book = bookRepo.findById(bookId).orElseThrow(()-> new RuntimeException("Book not found"));
        if (book.getAvailableCopies() <= 0) {
            return ResponseEntity.badRequest().body("No copies available");
        }

        if (reservationRepo.existsByUserAndBook(user, book)) {
            return ResponseEntity.badRequest().body("Already reserved");
        }

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setBook(book);
        reservation.setReservedAt(LocalDate.now());

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        reservationRepo.save(reservation);
        bookRepo.save(book);

        return ResponseEntity.ok("Book reserved successfully");
    }



    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    public List<Book> getReservedBooksByUser(String name) {
        User user = userRepo.findByUsername(name);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        List<Reservation> reservations = reservationRepo.findByUser(user);
        List<Book> reservedBooks = new ArrayList<>();
        for (Reservation reservation : reservations) {
            reservedBooks.add(reservation.getBook());
        }
        return reservedBooks;

    }

    public boolean existsByUsername(String username) {
        return userRepo.existsByUsername(username);
    }



      

}

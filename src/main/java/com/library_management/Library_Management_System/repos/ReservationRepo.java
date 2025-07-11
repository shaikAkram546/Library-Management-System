package com.library_management.Library_Management_System.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library_management.Library_Management_System.model.Book;
import com.library_management.Library_Management_System.model.Reservation;
import com.library_management.Library_Management_System.model.User;

public interface ReservationRepo extends JpaRepository<Reservation, Long> {
    
    // Custom query to find reservations by user ID
    List<Reservation> findByUserId(Long userId);
    
    // Custom query to find reservations by book ID
    List<Reservation> findByBookId(Long bookId);
    
    // Custom query to find all reservations
    List<Reservation> findByUser(User user);
    

    boolean existsByUserAndBook(User user, Book book);

    
    boolean existsByBookId(Long bookId);
}

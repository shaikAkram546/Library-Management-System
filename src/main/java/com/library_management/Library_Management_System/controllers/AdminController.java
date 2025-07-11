package com.library_management.Library_Management_System.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.library_management.Library_Management_System.model.Book;
import com.library_management.Library_Management_System.model.User;
import com.library_management.Library_Management_System.services.AdminService;
import com.library_management.Library_Management_System.services.CommonService;

@RestController
@RequestMapping("/api/admin") // Changed base path to /api/admin for clarity
@CrossOrigin(origins = "http://localhost:3000") // Allow React frontend to access this
public class AdminController {

    @Autowired private AdminService adminService;
    @Autowired private CommonService commonService;



    //Get all the users
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = adminService.getAllUsers();
        return ResponseEntity.ok(users);
        
    }
    
    
    // Get all books
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = adminService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    // Add a new book
    @PostMapping("/book")
    public ResponseEntity<String> addBook(Book book) {
        adminService.addBook(book);
        return ResponseEntity.ok("Book added");
    }


    // Update an existing book
    @PutMapping("/updateBook/{id}")
    public ResponseEntity<String> updateBook(@PathVariable Long id,Book bookDetails) {
        adminService.updateBook(id, bookDetails);
        return ResponseEntity.ok("Book updated");
    }

    // Delete a book
    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        try {
            adminService.deleteBook(id);
            return ResponseEntity.ok("Book deleted successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(409) // Conflict
                    .body(e.getMessage());
        }
    }

    // Search books
    @GetMapping("/books/search")
    public ResponseEntity<List<Book>> searchBooks(@RequestParam String query) {
        List<Book> books = commonService.searchBooks(query);
        return ResponseEntity.ok(books);
    }

    @PostMapping("/user-books/{username}")
    public ResponseEntity<List<Book>> getAllReservedBooks(@PathVariable String username){
    List<Book> reservedBooks = adminService.getBooksReservedByUser(username);
      return ResponseEntity.ok(reservedBooks);
    }
}

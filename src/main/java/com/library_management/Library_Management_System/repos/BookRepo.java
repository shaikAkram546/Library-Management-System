package com.library_management.Library_Management_System.repos;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.library_management.Library_Management_System.model.Book;

public interface BookRepo extends JpaRepository<Book, Long> {

    // List<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(String keyword, String keyword2);
    // This interface will automatically have methods for CRUD operations
    // like save, findById, findAll, deleteById, etc.

    List<Book> findByTitleContainingIgnoreCase(String query);

    List<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(String keyword, String keyword2);

    // List<Book> findByNameContainingIgnoreCase(String query);

}

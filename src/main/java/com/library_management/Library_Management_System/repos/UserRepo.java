package com.library_management.Library_Management_System.repos;
import org.springframework.data.jpa.repository.JpaRepository;
import com.library_management.Library_Management_System.model.User;



public interface UserRepo extends JpaRepository<User, Long> {
    
    // Method to find a user by username
    User findByUsername(String username);
    
    // Method to check if a user exists by username
    boolean existsByUsername(String username);


    
    // Method to delete a user by username
    void deleteByUsername(String username);

}

package com.library_management.Library_Management_System.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library_management.Library_Management_System.model.Book;
import com.library_management.Library_Management_System.repos.BookRepo;

@Service
public class CommonService {

    @Autowired
    BookRepo bookRepo;
     public List<Book> searchBooks(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return bookRepo.findAll();
        }
        return bookRepo.findByTitleContainingIgnoreCase(keyword);
    }




}

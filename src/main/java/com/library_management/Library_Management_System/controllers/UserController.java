package com.library_management.Library_Management_System.controllers;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
// import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.library_management.Library_Management_System.model.Book;
import com.library_management.Library_Management_System.services.CommonService;
import com.library_management.Library_Management_System.services.UserService;


@Controller
@RequestMapping("api/user")
public class UserController {
  
    @Autowired private UserService userService;
    @Autowired private CommonService commonService;


    // @GetMapping("/home")
    // public String userHome(Principal principal, Model model) {
    //     /*
    //      * show all the books that are available in the library
    //      * and also show the books that are reserved by the user
    //      */
    //     List<Book> allBooks = userService.getAllBooks();
    //     List<Book> reservedBooks = userService.getReservedBooksByUser(principal.getName());
    //     model.addAttribute("books", allBooks);
    //     model.addAttribute("reservedBooks", reservedBooks);
    //     model.addAttribute("hasBooks", !allBooks.isEmpty());
    //     model.addAttribute("hasReservedBooks", !reservedBooks.isEmpty());
    //     return "User/home"; // This should return the name of the HTML template for user home
    // } MAKING THIS TO RESPONSE ENTITY FOR API



    //Rest API for user/home
    @GetMapping("/home")
    public ResponseEntity<?> userHome(Principal principal) {
        /*
         * show all the books that are available in the library
         * and also show the books that are reserved by the user
         */
        List<Book> allBooks = userService.getAllBooks();
        List<Book> reservedBooks = userService.getReservedBooksByUser(principal.getName());
        
      return ResponseEntity.ok(Map.of(
            "books", allBooks,
            "reservedBooks", reservedBooks,
            "hasBooks", !allBooks.isEmpty(),
            "hasReservedBooks", !reservedBooks.isEmpty()
        ));
    }





    @PostMapping("/reserve/{bookId}")
    public ResponseEntity<?> reserveBook(@PathVariable Long bookId, Principal principal) {
         return userService.reserveBook(bookId, principal);
    }

    // @GetMapping("/books")
    // @ResponseBody
    // public List<Book> getBooks() {  
    //     return userService.getAllBooks();
    // }

    @GetMapping("/search")
    public String searchBooks(String query, Model model) {
        List<Book> books = commonService.searchBooks(query);
        // The query parameter is used to search for books by name = title
        // this query may be used to search for books by name, author, or any other attribute for now that is just by name.


        model.addAttribute("books", books);
        model.addAttribute("noBooks", books.isEmpty());
        return "User/search-result"; // This should return the name of the HTML template for search results
    }
}

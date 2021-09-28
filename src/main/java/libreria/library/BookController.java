package main.java.libreria.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/addNewBook")
    public String book(Model model) {
        model.addAttribute("book", new BookDTO());
        return "addNewBook";
    }

    @PostMapping("/addNewBook")
    public String addNewBook(
            @ModelAttribute("book") @Valid BookDTO book,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes ra) {

        if (bindingResult.hasErrors()) {

            for (ObjectError temp : bindingResult.getAllErrors()) {

                System.out.println("Errore trovato: nome " + temp.getObjectName()
                        + "; codice " + temp.getCode()
                        + "; messaggio " + temp.getDefaultMessage());
            }
            return "addNewBook";

        }

        this.bookService.saveBook(book);
        ra.addFlashAttribute("book", book);
        return "redirect:/insertSuccess";
    }

    @GetMapping("/insertSuccess")
    public String risultato(@ModelAttribute("book") BookDTO libro, Model model) {

        model.addAttribute("book", libro);

        return "insertSuccess";
    }


    @GetMapping("/library")
    public String fooresult(@ModelAttribute("libro") BookDTO book, Model model) {
        model.addAttribute("bookList", this.bookService.getAllBooks());
        return "library";
    }


}

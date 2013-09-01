package com.ebookle.controller;

import com.ebookle.entity.*;
import com.ebookle.service.*;
import com.ebookle.service.impl.UserServiceImpl;
import com.ebookle.service.validation.BookValidator;
import com.ebookle.util.UtilInfo;
import com.ebookle.webmodel.BookCreationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 28.08.13
 * Time: 6:51
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class BookEditorController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookValidator bookValidator;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private TagService tagService;


    @Secured("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @RequestMapping(value = "/{userLogin}/bookCreation", method = RequestMethod.GET)
    public String goToBookCreation (@PathVariable("userLogin") String userLogin, ModelMap modelMap) {
        modelMap.addAttribute("categories", categoryService.findAll());
        modelMap.addAttribute("userLogin", userLogin);
        return "create_new_book";
    }


    @Secured("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @RequestMapping(value = "/{userLogin}/createNewBook", method = RequestMethod.POST)
    public String createNewBook (Principal principal,
                                 @ModelAttribute("newBookForm") BookCreationForm bookForm,
                                 @PathVariable("userLogin") String userLogin,
                                 ModelMap modelMap,
                                 BindingResult errors) {

        if (!principal.getName().equals(userLogin)) {
            modelMap.addAttribute("error", "You cannot enter site!");
            return "error";
        }
        String bookTitle = bookForm.getTitle();
        User user = userService.findByLogin(principal.getName());
        bookValidator.validate(bookForm, errors, user);
        if (errors.hasErrors()) {
            modelMap.addAttribute("error", "Bad book title!");
            modelMap.addAttribute("categories", categoryService.findAll());
            modelMap.addAttribute("userLogin", userLogin);
            return "create_new_book";
        }
        Category category = categoryService.findById(bookForm.getCategory());
        Book book = new Book(
                bookTitle,
                bookForm.getDescription(),
                user,
                category
        );
        String bookTag = bookForm.getBookTag();
        if (bookTag != null && !"".equals(bookTag)) {
            book = addTag(bookTag, book);
        }
       /* try {
            bookTitle = UrlUtil.encode(bookTitle, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }     */
        bookService.saveOrUpdate(book);
        return "redirect:/" + userLogin + "/editBook/" + bookTitle + "/1";
    }


    @Secured("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @RequestMapping(value = "/{userLogin}/editBook/{bookTitle}/{chapterNumber}/addTag", method = RequestMethod.POST)
    public String addTag (Principal principal,
                          @PathVariable("chapterNumber") Integer chapterNumber,
                          @PathVariable("userLogin") String userLogin,
                          @PathVariable("bookTitle") String bookTitle,
                          @RequestParam("bookTag") String bookTag,
                          ModelMap modelMap) {
        if (principal == null
                || ! principal.getName().equals(userLogin)) {
            modelMap.addAttribute("error", "Страница недоступна!");
            return "error";
        }
        User user = userService.findByLogin(userLogin);
        Book book = bookService.findByTitleAndUserIdWithTags(bookTitle, user);
        book = addTag(bookTag, book);
        bookService.saveOrUpdate(book);
        return "redirect:/" + userLogin + "/editBook/" + bookTitle + "/" + chapterNumber;
    }

    private Book addTag(String bookTag, Book book) {

        if (bookHasTag(book, bookTag)) {
            return book;
        }
        Tag tag = tagService.findTagByName(bookTag);
        if (tag == null) {
            tag = new Tag();
            tag.setBookTag(bookTag);
            tag.setCounter(0);
            tagService.saveOrUpdate(tag);
        } else {
            tag.setCounter(tag.getCounter() + 1);
            tagService.saveOrUpdate(tag);
        }
        book.getTags().add(tag);
        return book;
    }

    private boolean bookHasTag (Book book, String bookTag) {
        List<Tag> tags = book.getTags();
        for (Tag currentTag : tags) {
            if (currentTag.getBookTag().equals(bookTag)) {
                return true;
            }
        }
        return false;
    }

    @Secured("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @RequestMapping(value = "/{userLogin}/editBook/{bookTitle}/{chapterNumber}", method = RequestMethod.GET)
    public String updateBook (Principal principal,
                              @PathVariable("chapterNumber") Integer chapterNumber,
                              @PathVariable("userLogin") String userLogin,
                              @PathVariable("bookTitle") String bookTitle,
                              ModelMap modelMap) {

        //bookTitle = decodeWithUtf(bookTitle);
        if (principal == null
                || ! principal.getName().equals(userLogin)) {
            modelMap.addAttribute("error", "Страница недоступна!");
            return "error";
        }
        User user = userService.findByLogin(principal.getName());
        Book book = bookService.findByTitleAndUserIdWithChapters(bookTitle, user);
        if (book.getChapters().size() == 0) {
            createChapter(book, 1);
            book = bookService.findByTitleAndUserIdWithChapters(bookTitle, user);
            chapterNumber = 1;
        }
        modelMap.addAttribute("book", book);
        modelMap.addAttribute("userLogin", userLogin);
        modelMap.addAttribute("currentChapter", book.getChapters().get(chapterNumber - 1));
        modelMap.addAttribute("userAction", "edit");
        modelMap.addAttribute("person", "ownUser");
        modelMap.addAttribute("tags", bookService.findByTitleAndUserIdWithTags(bookTitle, user).getTags());
        return "edit_book";
    }


    @Secured("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @RequestMapping(value = "/{userLogin}/editBook/{bookTitle}/createNewChapter", method = RequestMethod.GET)
    public String createNewChapter (Principal principal, @PathVariable("userLogin") String userLogin, @PathVariable("bookTitle") String bookTitle) {

        User user = userService.findByLogin(principal.getName());
        Book book = bookService.findByTitleAndUserIdWithChapters(bookTitle, user);
        createChapter(book, book.getChapters().size() + 1);
        return ("redirect:/" + userLogin + "/editBook/" + bookTitle + "/" + (book.getChapters().size() + 1));
    }

    @Secured("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @RequestMapping(value = "/{userLogin}/editBook/{bookTitle}/{chapterNumber}/save", method = RequestMethod.POST)
    public String saveChapter (@RequestParam("text") String text, Principal principal, @PathVariable("chapterNumber") Integer chapterNumber, @PathVariable("userLogin") String userLogin, @PathVariable("bookTitle") String bookTitle, ModelMap modelMap) {

        User user = userService.findByLogin(principal.getName());
        Book book = bookService.findByTitleAndUserId(bookTitle, user);
        Chapter chapter = chapterService.findByBookAndChapterNumber(book, chapterNumber);
        chapter.setText(text);
        chapterService.saveOrUpdate(chapter);
        return ("redirect:/" + userLogin + "/editBook/" + bookTitle + "/" + chapterNumber);
    }

    @Secured("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @RequestMapping(value = "/{userLogin}/editBook/{bookTitle}/{chapterNumber}/deleteChapter", method = RequestMethod.GET)
    private String deleteChapter(Principal principal,
                                 @PathVariable("chapterNumber") Integer chapterNumber,
                                 @PathVariable("userLogin") String userLogin,
                                 @PathVariable("bookTitle") String bookTitle,
                                 ModelMap modelMap) {
        if (!principal.getName().equals(userLogin)) {
            modelMap.addAttribute("error", "You haven't got access to this page!");
            return "error";
        }
        User user = userService.findByLogin(userLogin);
        Book book = bookService.findByTitleAndUserIdWithChapters(bookTitle,user);
        List<Chapter> chapters = book.getChapters();

        for(Chapter chapter : chapters){
            if (chapterNumber == chapter.getChapterNumber()){
                chapterService.delete(chapter.getId());
            } else if(chapter.getChapterNumber() > chapterNumber){
                chapter.setChapterNumber(chapter.getChapterNumber() - 1);
                if (chapter.getTitle().startsWith(UtilInfo.STANDARD_CHAPTER_NAME)) {
                    chapter.setTitle(UtilInfo.STANDARD_CHAPTER_NAME + chapter.getChapterNumber());
                }
                chapterService.saveOrUpdate(chapter);
            }
        }
        return ("redirect:/" + userLogin + "/editBook/" + bookTitle + "/1");
    }


    @Secured("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @RequestMapping(value = "/{userLogin}/remove/{bookId}", method = RequestMethod.GET)
    public String deleteBook(Principal principal, @PathVariable("userLogin") String userLogin, @PathVariable("bookId")Integer bookId){

        bookService.delete(bookId);
        return "redirect:/";
    }

    private void createChapter (Book book, int number) {
        Chapter chapter = new Chapter(
                UtilInfo.STANDARD_CHAPTER_NAME + number,
                UtilInfo.STANDARD_CHAPTER_TEXT,
                book,
                number
        );
        chapterService.saveOrUpdate(chapter);
    }

}
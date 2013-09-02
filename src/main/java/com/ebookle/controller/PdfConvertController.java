package com.ebookle.controller;

import com.ebookle.entity.Book;
import com.ebookle.entity.Chapter;
import com.ebookle.entity.User;
import com.ebookle.service.BookService;
import com.ebookle.service.ChapterService;
import com.ebookle.service.UserService;
import com.ebookle.util.Encoder;
import com.pdfcrowd.Client;
import com.pdfcrowd.PdfcrowdError;
import com.petebevin.markdown.MarkdownProcessor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 31.08.13
 * Time: 10:51
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class PdfConvertController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private UserService userService;

    @Autowired
    private MarkdownProcessor markdownProcessor;

    @Secured("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @RequestMapping("/{userLogin}/editBook/{bookTitle}/{chapterNumber}/savePdf")
    public void savePdf (@PathVariable("userLogin") String userLogin,
                         @PathVariable("bookTitle") String bookTitle,
                         @PathVariable("chapterNumber") Integer chapterNumber,
                         HttpServletResponse response) {

        try {

            Client client = new Client("lehaSVV2009", "2c972a5566d152c96560e691e91b490c");
            User author = userService.findByLogin(userLogin);
            Book book = bookService.findByTitleAndUserId(bookTitle, author);
            List<Chapter> chapters = chapterService.findAllByBook(book);
            String htmlBook = convertToHtmlBook(bookTitle, author.getName(), chapters);
            writeBookToPdf(client, htmlBook);
            FileInputStream inputStream = new FileInputStream("d://Pdf");
            IOUtils.copy(inputStream, response.getOutputStream());
            inputStream.close();
            response.flushBuffer();
        } catch  (FileNotFoundException e) {
            e.printStackTrace();
        } catch(PdfcrowdError why) {
            System.err.println(why.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=ebookle.pdf");
    }

    private void writeBookToPdf (Client client, String htmlBook) throws IOException {
        ByteArrayOutputStream memStream  = new ByteArrayOutputStream();
        client.convertHtml(htmlBook, memStream);
        FileOutputStream outputStream = new FileOutputStream("d://Pdf/ebookle.pdf");
        outputStream.write(memStream.toByteArray());
        outputStream.close();
    }

    private String convertToHtmlBook (String bookTitle, String authorName, List<Chapter> chapters) {

        StringBuilder htmlBook = new StringBuilder();
        htmlBook.append("<html><body><h1>" + bookTitle + "<h1><br><br><h2>" + Encoder.decode(authorName) + "</h2>");
        for (Chapter chapter : chapters) {
            htmlBook.append(
                    markdownProcessor.markdown(chapter.getText())
            );
        }
        htmlBook.append("</body></html>");
        return htmlBook.toString();
    }

}
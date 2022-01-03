package com.example.bookstore.controller;

import com.example.bookstore.dao.book.AuthorDAO;
import com.example.bookstore.dao.book.BookDAO;
import com.example.bookstore.dao.book.impl.BookDAOImpl;
import com.example.bookstore.dao.book.PublisherDAO;
import com.example.bookstore.dao.book.impl.AuthorDAOImpl;
import com.example.bookstore.dao.book.impl.PublisherDAOImpl;
import com.example.bookstore.dao.file.FileDbDAO;
import com.example.bookstore.dao.file.impl.FileDbDAOImpl;
import com.example.bookstore.model.FileDb;
import com.example.bookstore.model.book.Author;
import com.example.bookstore.model.book.Book;
import com.example.bookstore.model.book.Publisher;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class BookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public BookServlet() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        Book book = null;


        HashMap<String, String> map = new HashMap<String, String>();
        if (isMultipart) {
            // Configure a repository parameter
            ServletContext context = this.getServletConfig().getServletContext();
            String filePath = context.getInitParameter("file-upload");

            // Create a factory for disk-based file items
            DiskFileItemFactory factory = new DiskFileItemFactory();

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);

            try {
                // Parse the request to get file items.
                List<FileItem> items = upload.parseRequest(request);

                // Process the uploaded file items
                Iterator<FileItem> iter = items.iterator();

                int indx = 1;

                while (iter.hasNext()) {
                    FileItem fi = iter.next();
                    if (fi.isFormField()) {
                        // Process a regular form field
                        String otherFieldName = fi.getFieldName();
                        String otherFieldValue = fi.getString();
                        map.put(otherFieldName, otherFieldValue);

                        System.out.println(otherFieldName + " : " + otherFieldValue);
                    } else {
                        // Get the uploaded file parameters

                        String fileName = fi.getName();
                        String fieldName = fi.getFieldName();
                        long sizeInBytes = fi.getSize();

                        if (fi.getName().equals("")) {
                            continue;
                        }
                        // Write the file
                        String path = getServletContext().getInitParameter("file-upload");
                        File file = new File(path + File.separator + fileName);
                        fi.write(file);
                        if (!fileName.equals("")) {
                            System.out.println("fileName: " + fileName);
                        }
                        map.put("coverImageFile","./book_images/".concat(fileName));
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (!map.isEmpty()) {
            book = populateBook(map);
        }
        BookDAO bookDAO = new BookDAOImpl();
        bookDAO.saveBook(book);

        response.sendRedirect(request.getContextPath() + "/AddBookConfirmation.jsp?success=true");
    }

    private Book populateBook(HashMap<String, String> map) {
        PublisherDAO publisherDAO = new PublisherDAOImpl();
        AuthorDAO authorDAO = new AuthorDAOImpl();

        Book book = new Book();
        book.setIsbn(map.get("isbn"));
        book.setTitle(map.get("title"));
        book.setSummary(map.get("summary"));

        Publisher publisher = publisherDAO.getPublisherByName(map.get("publisher"));
        if (Objects.isNull(publisher)) {
            publisher = new Publisher(map.get("publisher"));
            publisherDAO.savePublisher(publisher);
        }
        book.setPublisher(publisher);

        Author author = authorDAO.getAuthorByName(map.get("author"));
        if (Objects.isNull(author)) {
            author = new Author(map.get("author"));
            authorDAO.saveAuthor(author);
        }
        book.setAuthor(author);
        book.setLanguage(map.get("language"));
        book.setStatus(false);
        book.setNumberOfPage(Integer.valueOf(map.get("number_of_page")));
        if (map.containsKey("coverImageFile")) {
            FileDb fileDb = new FileDb(map.get("coverImageFile"));
            FileDbDAO fileDbDAO = new FileDbDAOImpl();
            fileDbDAO.saveFile(fileDb);
            book.getFileDbs().add(fileDb);
        }
        return book;
    }

}

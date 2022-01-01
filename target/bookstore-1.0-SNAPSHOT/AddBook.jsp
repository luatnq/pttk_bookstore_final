<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>
<h1>Add a new book!</h1>

<div>
    <form action="./AddBook" method="post" enctype="multipart/form-data">
        <p>
            <label for="isbn">ISBN:</label>
            <input type="text" name="isbn" id="isbn" required/>
        </p>

        <p>
            <label for="title">Title:</label>
            <input type="text" name="title" id="title" required/>
        </p>

        <p>
            <label for="publisher">Publisher:</label>
            <input type="text" name="publisher" id="publisher" required/>
        </p>

        <p>
            <label for="summary">Summary:</label>
            <input type="text" name="summary" id="summary" required/>
        </p>

        <p>
            <label for="number_of_page">Number of page:</label>
            <input type="text" name="number_of_page" id="number_of_page"/>
        </p>

        <p>
            <label for="language">Language:</label>
            <input type="text" name="language" id="language" required/>
        </p>

        <p>
            <label for="author">Author:</label>
            <input type="text" name="author" id="author"/>
        </p>

        <p>
            <label for="coverImage">Submit a Cover Image</label>
            <input type="file" name="coverImage" id="coverImage"/>
        </p>

        <input type="submit"/>
    </form>

</div>
<%@ include file="footer.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Add Note</title>
    </head>
    <body>
        <c:if test="${addNoteSuccess}">
            <div>Successfully added Note with title: ${savedNote.title}</div>
        </c:if>

        <c:url var="add_note_url" value="/notes/add"/>
        <form:form action="${add_note_url}" method="post" modelAttribute="note">
            <form:label path="title">Title: </form:label> <form:input type="text" path="title"/>
            <form:label path="content">Content: </form:label> <form:input type="text" path="content"/>
            <input type="submit" value="submit"/>
        </form:form>

        <a href="<c:url value="/notes/"/>">Back to Notes</a>
    </body>
</html>
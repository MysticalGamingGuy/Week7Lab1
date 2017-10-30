<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Notes</title>
    </head>
    <body>
        <h1>Manage Notes</h1>
        
        <c:choose>
            <c:when test="${notes.size() > 0 }">
                <h2>Notes</h2>
                <table>
                    <tr>
                        <th>Note ID</th>
                        <th>Date Created</th>
                        <th>Contents</th>
                        <th>Delete Note</th>
                        <th>Select Note</th>
                    </tr>
                    <c:forEach var="note" items="${notes}">
                        <tr>
                            <td>${note.noteid}</td>
                            <td>${note.datecreated}</td>
                            <td>${note.contents}</td>
                            <td>
                                <form action="notes" method="post" >
                                    <input type="submit" value="Delete">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="selectednoteid" value="${note.noteid}">
                                </form>
                            </td>
                            <td>
                                <form action="notes" method="post">
                                    <input type="submit" value="Edit">
                                    <input type="hidden" name="action" value="edit">
                                    <input type="hidden" name="selectednoteid" value="${note.noteid}">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <p>There are currently no notes in the database</p>
            </c:otherwise>
        </c:choose>
        
        <p>${errorMessage}</p>
        
        <tags:noteForm type="${selectedNote == null? 'add': 'update'}"/>
 
    </body>
</html>

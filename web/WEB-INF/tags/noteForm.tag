<%@ tag description="Displays a form for editing notes" pageEncoding="UTF-8"%>
<%@ attribute name="type" required="true" type="java.lang.String" description="dictates action of the form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<h3> <span style="text-transform: capitalize">${fn:toLowerCase(type)}</span> Note </h3>
<form action="notes" method="POST">
    Contents: <input type="text" name="contents"><br>
    <input type="hidden" name="action" value="${type}">
    <input type="submit" value="Submit">
</form>
<jsp:include page="header.jsp"/>

    <br>
    <h2 style="color:red;">${errorMessage}</h2>
    <br>
    <button type="button" class="btn btn-outline-secondary" onclick="document.location='/blockchain/${user.id}/userpage'">Back to UserPage</button>

<jsp:include page="footer.jsp"/>
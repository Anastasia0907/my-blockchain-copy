<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp"/>

    <br>
    <h1 style="color:#4d4d4d;text-align:center">Welcome to the Best Blockchain ever! =)</h1>
    <br>
    <h2 style="color:#4d4d4d;text-align:center">
        The world's most popular way to buy, store and use cryptocurrency
    </h2>
    <br>

    <sec:authorize access="!isAuthenticated()">
        <button type="button" class="btn btn-outline-primary btn-lg" onclick="document.location='/blockchain/register'" style="margin-left:45%">Register</button>
        <button type="button" class="btn btn-outline-success btn-lg" onclick="document.location='/blockchain/login'">Login</button>
    </sec:authorize>



<jsp:include page="footer.jsp"/>
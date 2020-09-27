<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp"/>

    <sec:authorize access="isAuthenticated()">
        <br>
        <h1 style="color:#4d4d4d;text-align:center">Hi, <b><sec:authentication property="principal.username"/></b>!</h1>
            <h3 style="color:#4d4d4d">Email : ${user.email}</h3>
            <h3 style="color:#4d4d4d">Mobile phone number : ${user.mobileNumber}</h3>
            <br>
            <button type="button" class="btn btn-outline-secondary" onclick="document.location='/blockchain/${user.id}/edit-user-info'">Edit User Info</button>
            <button type="button" class="btn btn-outline-success" onclick="document.location='/blockchain/${user.id}/new-transaction'">New Transaction</button>
            <button type="button" class="btn btn-outline-info" onclick="document.location='/blockchain/${user.id}/transactions-list'">My Transactions</button>
            <button type="button" class="btn btn-outline-warning" onclick="document.location='/blockchain/${user.id}/all-transactions-list'">All Transactions</button>

            <br>
            <br>
            <h3 style="color:#4d4d4d">Balance : ${user.wallet.balance}</h3>
            <br>
            <h4 style="color:#4d4d4d">Wallet Private Key</h3>
            <h5 style="color:#4d4d4d;word-wrap:break-word">${user.wallet.privateKeyString}</h3>
            <br>
            <h4 style="color:#4d4d4d">Wallet Public Key</h3>
            <h5 style="color:#4d4d4d;word-wrap:break-word">${user.wallet.publicKeyString}</h3>
    </sec:authorize>

<jsp:include page="footer.jsp"/>
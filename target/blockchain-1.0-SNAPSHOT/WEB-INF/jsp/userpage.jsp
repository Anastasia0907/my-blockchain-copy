<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp"/>

    <sec:authorize access="isAuthenticated()">
        <br>
        <h1 style="color:#4d4d4d;text-align:center">Hi, <b><sec:authentication property="principal.username"/></b>!</h1>
            <h3 style="color:#4d4d4d">Email : ${user.email}</h3>
            <h3 style="color:#4d4d4d">Mobile phone number : ${user.mobileNumber}</h3>
            <br>
            <p>
                <button type="button" class="btn btn-outline-secondary" onclick="document.location='/blockchain/${user.id}/edit-user-info'">Edit User Info</button>
                <button type="button" class="btn btn-outline-warning" onclick="document.location='/blockchain/${user.id}/all-transactions-list'">All Blockchain Transactions</button>
            </p>
            <br>
            <h1>Wallets : </h1>

            <table class="table table-sm table-hover" width="100%">
              <thead>
                <tr>
                  <th scope="col" width="5%" style="text-align:center">#</th>
                  <th scope="col" width="30%" style="text-align:center">Public Key</th>
                  <th scope="col" width="30%" style="text-align:center">Private Key</th>
                  <th scope="col" width="10" style="text-align:center">Balance</th>
                  <th scope="col" width="10" style="text-align:center">Currency</th>
                  <th scope="col" style="text-align:center"></th>
                  <th scope="col" style="text-align:center"></th>
                </tr>
              </thead>
              <tbody>
                <c:forEach items="${wallets}" var="wallet" varStatus="counter">
                <tr>
                  <td style="text-align:center">${counter.count}</td>
                  <td style="word-break: break-all">${wallet.publicKeyString}</th>
                  <td style="word-break: break-all">${wallet.privateKeyString}</td>
                  <td style="text-align:center">${wallet.balance}</td>
                  <td style="text-align:center">${wallet.currency}</td>
                  <td style="text-align:center"><p><button type="button" class="btn btn-outline-info" onclick="document.location='/blockchain/${user.id}/${wallet.walletId}/transactions-list'">Transactions</button></p><td>
                  <td style="text-align:center"><p><button type="button" class="btn btn-outline-success" onclick="document.location='/blockchain/${user.id}/${wallet.walletId}/new-transaction'">New Transaction</button></p><td>
                </tr>
                </c:forEach>
              </tbody>

            </table>

            <br>
            <h1>Create a new wallet : </h1>
            <br>

             <form action="/blockchain/${user.id}/new-wallet" method="post" >

               <div class="form-group">
                     <label for="selectCurrency">Select currency for your new wallet</label>
                     <select class="form-control" id="selectCurrency" name="currency">
                       <option>USD</option>
                       <option>EUR</option>
                       <option>BYN</option>
                     </select>
               </div>

               <button type="submit" class="btn btn-outline-primary">Create New Wallet</button>
             </form>

             <br>
             <br>
             <br>
    </sec:authorize>

<jsp:include page="footer.jsp"/>
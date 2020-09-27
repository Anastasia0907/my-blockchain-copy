<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp"/>

    <h1>All Blockchain Transactions:</h1>

    <table class="table table-hover" width="100%">
      <thead>
        <tr>
          <th scope="col" width="45%">Sender Public Key</th>
          <th scope="col" width="45%">Receiver Public Key</th>
          <th scope="col">Amount</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${transactions}" var="transaction">
                <tr>
                  <td style="word-break: break-all">${transaction.senderPublicKeyString}</th>
                  <td style="word-break: break-all">${transaction.recipientPublicKeyString}</td>
                  <td>${transaction.value}</td>
                </tr>
        </c:forEach>
      </tbody>

    </table>

    <br>
    <br>
    <br>

<jsp:include page="footer.jsp"/>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp"/>

    <h1>All Blockchain Transactions:</h1>

    <table class="table table-sm table-hover" width="100%">
      <thead>
        <tr>
          <th scope="col" width="10%" style="text-align:center">#</th>
          <th scope="col" width="30%" style="text-align:center">Sender Public Key</th>
          <th scope="col" width="30%" style="text-align:center">Receiver Public Key</th>
          <th scope="col" width="10%" style="text-align:center">Amount</th>
          <th scope="col" width="10%" style="text-align:center">Currency</th>
          <th scope="col" width="10%" style="text-align:center">Status</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${transactions}" var="transaction" varStatus="counter">
                <tr>
                  <td style="text-align:center">${counter.count}</td>
                  <td style="word-break: break-all">${transaction.senderPublicKeyString}</td>
                  <td style="word-break: break-all">${transaction.recipientPublicKeyString}</td>
                  <td style="text-align:center">${transaction.value}</td>
                  <td style="text-align:center">${transaction.currency}</td>
                  <td style="text-align:center">
                    <c:if test="${transaction.transactionStatus == 0}">
                        <p style="color:#FF0000">Not confirmed</p>
                    </c:if>
                    <c:if test="${transaction.transactionStatus == 1}">
                        Confirmed
                    </c:if>
                  </td>
                </tr>
        </c:forEach>
      </tbody>

    </table>

    <br>
    <br>
    <br>

<jsp:include page="footer.jsp"/>
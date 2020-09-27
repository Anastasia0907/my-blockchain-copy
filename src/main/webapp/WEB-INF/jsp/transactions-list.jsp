<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp"/>

    <h1>Transaction Inputs:</h1>

    <table class="table table-hover" width="100%">
      <thead>
        <tr>
          <th scope="col" width="45%">Sender Public Key</th>
          <th scope="col" width="45%">Receiver Public Key</th>
          <th scope="col">Amount</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${inputs}" var="input">
                <tr>
                  <td style="word-break: break-all">${input.senderPublicKeyString}</th>
                  <td style="word-break: break-all">${input.recipientPublicKeyString}</td>
                  <td>${input.value}</td>
                </tr>
        </c:forEach>
      </tbody>

    </table>

    <h1>Transaction Outputs:</h1>

    <table class="table table-hover" width="100%">
      <thead>
        <tr>
          <th scope="col" width="45%">Sender Public Key</th>
          <th scope="col" width="45%">Receiver Public Key</th>
          <th scope="col">Amount</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${outputs}" var="output">
                <tr>
                  <td style="word-break: break-all">${output.senderPublicKeyString}</th>
                  <td style="word-break: break-all">${output.recipientPublicKeyString}</td>
                  <td>${output.value}</td>
                </tr>
        </c:forEach>
      </tbody>

    </table>
    <br>
    <br>
    <br>

<jsp:include page="footer.jsp"/>
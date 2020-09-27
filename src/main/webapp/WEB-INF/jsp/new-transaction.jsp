<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp"/>

    <h1>${user.userName}</h1>

        <form action="/blockchain/${user.id}/new-transaction" method="post" >
          <div class="form-group row">
                <label for="senderPublicKey" class="col-sm-2 col-form-label">Sender Public Key</label>
                <div class="col-sm-10">
                    <input type="text" name="senderPublicKey" readonly class="form-control-plaintext" id="senderPublicKey" value="${publicKey}">
                </div>
          </div>
          <div class="form-group row">
                <label for="recipientPublicKey" class="col-sm-2 col-form-label">Recipient Public Key</label>
                <div class="col-sm-10">
                    <input type="text" name="recipientPublicKey" class="form-control" id="recipientPublicKey">
                </div>
          </div>
          <div class="form-group row">
                <label for="value" class="col-sm-2 col-form-label">Transaction amount</label>
                <div class="col-sm-10">
                    <input type="number" name="value" class="form-control" id="recipientPublicKey">
                </div>
          </div>

          <button type="submit" class="btn btn-primary">Submit</button>
        </form>

<jsp:include page="footer.jsp"/>
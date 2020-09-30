<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp"/>

     <br>
     <div class="container">

        <form action="/blockchain/${user.id}/start-mining" method="post" >
          <div class="form-group row">
                <label for="userName" class="col-sm-2 col-form-label">User Name</label>
                <div class="col-sm-10">
                    <input type="text" name="userName" readonly class="form-control-plaintext" id="userName" value="${user.userName}">
                </div>
          </div>

          <button type="submit" class="btn btn-primary">Start Mining</button>
        </form>

     </div>

<jsp:include page="footer.jsp"/>
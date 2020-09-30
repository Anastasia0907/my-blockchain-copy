<jsp:include page="header.jsp"/>

    <div class="container">

    <h1>${user.userName}</h1>

        <form action="/blockchain/${user.id}/edit-user-info" method="post" >
          <div class="form-group">
                <label for="userName">User name</label>
                <input type="text" class="form-control" name="userName" value="${user.userName}" id="userName" placeholder="${user.userName}">
          </div>
          <div class="form-group">
            <label for="email">Email address</label>
            <input type="email" class="form-control" name="email" value="${user.email}" id="email" aria-describedby="emailHelp" placeholder="${user.email}">
            <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
          </div>
          <div class="form-group">
              <label for="mobileNumber">Mobile number</label>
              <input type="text" class="form-control" name="mobileNumber" value="${user.mobileNumber}" id="mobileNumber" placeholder="${user.mobileNumber}">
          </div>

          <button type="submit" class="btn btn-primary">Submit</button>
        </form>

    </div>

<jsp:include page="footer.jsp"/>
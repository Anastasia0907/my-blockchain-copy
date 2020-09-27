<jsp:include page="header.jsp"/>

     <br>
     <div class="container">
      <form class="form-signin" method="post" action="/blockchain/login">
        <h2 class="form-signin-heading">Login with your account</h2>
        <p>
          <label for="username" class="sr-only">Username</label>
          <input type="text" id="username" name="userName" class="form-control" placeholder="Username" required autofocus>
        </p>
        <p>
          <label for="password" class="sr-only">Password</label>
          <input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
        </p>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
      </form>
     </div>

<jsp:include page="footer.jsp"/>
<jsp:include page="header.jsp"/>

     <br>
     <div class="container">
      <form class="form-signin" method="post" action="/blockchain/register">
        <h2 class="form-signin-heading">Please sign up</h2>
        <p>
          <label for="username" class="sr-only">Username</label>
          <input type="text" id="username" name="userName" class="form-control" placeholder="Username" required autofocus>
        </p>
        <p>
          <label for="password" class="sr-only">Password</label>
          <input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
        </p>
        <p>
           <label for="email">Email address</label>
           <input type="email" class="form-control" name="email" id="email" aria-describedby="emailHelp" placeholder="Enter email">
           <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
        </p>
        <p>
           <label for="mobileNumber">Mobile phone</label>
           <input type="text" class="form-control" name="mobileNumber" id="mobileNumber" placeholder="Mobile phone">
        </p>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
      </form>
     </div>

<jsp:include page="footer.jsp"/>
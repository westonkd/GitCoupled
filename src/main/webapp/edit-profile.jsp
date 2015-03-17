<%-- 
    Document   : profile
    Created on : Mar 13, 2015, 9:13:10 AM
    Author     : weston
    --%>

    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>
    <head>
        <meta charset="UTF-8">
        <title>Profile</title>
        <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
        <link rel="stylesheet" href="https://bootswatch.com/cosmo/bootstrap.min.css">
        <link rel="stylesheet" href="css/main.css">
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
        <script src="js/typed.js"></script>
        <script src="js/skrollr.min.js"></script>
    </head>

    <body class="gitcoupled">
        <div class="container">
            <div class="navbar navbar-default navbar-fixed-top coupled">
                <div class="container">
                    <div class="navbar-header">
                        <a href="../" class="navbar-brand">
                            <img id="main-logo" src="images/blacklogo.png" alt="">
                        </a>
                        <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#navbar-main">
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                    </div>
                    <div class="navbar-collapse collapse" id="navbar-main">
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="http://builtwithbootstrap.com/" target="_blank">Settings</a>
                            </li>
                            <li><a href="https://wrapbootstrap.com/?ref=bsw" target="_blank">Login</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <main class="edit-profile">
                <div class="col-sm-12">
                    <form class="form-horizontal">
                      <fieldset>
                      <div class="form-group radio">
                            <input id="maleRadio" type="radio" name="gender" value="male">
                            <label for="maleRadio">Male</label> <br>
                            <input id="femaleRadio" type="radio" name="gender" value="male">
                            <label for="femaleRadio">Female</label>    
                        </div>
                        <legend>Tell us about yourself</legend>
                        <div class="form-group">
                          <label for="inputEmail" class="col-lg-2 control-label">Email</label>
                          <div class="col-lg-10">
                            <input type="text" class="form-control" id="inputEmail" placeholder="Email">
                        </div>
                    </div>

                    <div class="form-group">
                      <label for="inputPassword" class="col-lg-2 control-label">Password</label>
                      <div class="col-lg-10">
                        <input type="password" class="form-control" id="inputPassword" placeholder="Password">
                        <div class="checkbox">
                          <label>
                            <input type="checkbox"> Checkbox
                        </label>
                    </div>
                </div>
            </div>
            <div class="form-group">
              <label for="textArea" class="col-lg-2 control-label">Textarea</label>
              <div class="col-lg-10">
                <textarea class="form-control" rows="3" id="textArea"></textarea>
                <span class="help-block">A longer block of help text that breaks onto a new line and may extend beyond one line.</span>
            </div>
        </div>
        
<div class="form-group">
  <label for="select" class="col-lg-2 control-label">Selects</label>
  <div class="col-lg-10">
    <select class="form-control" id="select">
      <option>1</option>
      <option>2</option>
      <option>3</option>
      <option>4</option>
      <option>5</option>
  </select>
  <br>
  <select multiple="" class="form-control">
      <option>1</option>
      <option>2</option>
      <option>3</option>
      <option>4</option>
      <option>5</option>
  </select>
</div>
</div>
<div class="form-group">
  <div class="col-lg-10 col-lg-offset-2">
    <button type="reset" class="btn btn-default">Cancel</button>
    <button type="submit" class="btn btn-primary">Submit</button>
</div>
</div>
</fieldset>
</form>
</div>
</main>
<footer class="row">
    <div class="col-sm-3">
        <span>© 2015 PureMagic</span>
    </div>
    <div class="col-sm-6 center">
        <img id="footer-logo" src="images/blacklogo.png" alt="">
    </div>
    <div class="col-sm-3 right">    
        <a href="TODO">login/register</a>
    </div>
</footer>
</div>
</body>

</html>

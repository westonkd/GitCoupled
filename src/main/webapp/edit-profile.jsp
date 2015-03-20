<%-- 
    Document   : profile
    Created on : Mar 13, 2015, 9:13:10 AM
    Author     : weston
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <title>GitCoupled - Profile</title>
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
                    <h2>Tell us a bit about yourself <c:out value = "${github.getMyself().getName()}" ></c:out></h2>
                    <img src="<c:out value = "${github.getMyself().getAvatarUrl()}" ></c:out>" id="profile-pic" class="profile pull-right" alt="user-image" />
                    <form role="form" action="" method="GET" id="profile-form">
                        <h4>Gender</h4>
                        <div class="radio">
                            <label><input type="radio" name="gender" required value="male">Male</label>
                        </div>
                        <div class="radio">
                            <label><input type="radio" name="gender" value="female">Female</label>
                        </div>
                        <h4 style="margin-top:50px;">Bio</h4>
                        <p>Let everyone know about yourself here. Hobbies, interests, past projects, etc.</p>
                        <div class="form-group">
                            <textarea required class="form-control" name="bio" rows="5" id="comment" placeholder="I love fighting crime by night, slaying dragons..."></textarea>
                        </div>
                        <h4>Quote</h4>
                        <p>Enter your awesome quote here. It will be displayed with your picture when other users are matched with you.</p>
                        <div class="form-group">
                            <input required type="text" class="form-control" placeholder="I'm pure magic." name="quote" id="quote">
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </div>
                    </form>
                </div>
            </main>
            <footer>
                <div class="col-sm-3">
                    <span>© 2015 PureMagic</span>
                </div>
                <div class="col-sm-6 center">
                    <img id="footer-logo" src="images/blacklogo.png" alt="">
                </div>
                <div class="col-sm-3 right">    
                </div>
            </footer>
        </div>
    </body>
    <script type="text/javascript" src="js/jquery.validate.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#quote').change(function () {
                var current = $(this).val();

                if (current !== "") {
                    $(this).val('"' + current + '"');
                }
            });
            
            $('#profile-form').validate();
        });
    </script>
    
   
</html>

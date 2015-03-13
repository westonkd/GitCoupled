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
            <main class="profile">
                <div class="row">
                    <div class="col-sm-4">
                        <img src="images/ori.jpg" id="profile-pic" />
                        <p class="quote">
                            "I'm one lovable dwarf"  
                        </p>
                    </div>
                    <div class="col-sm-4">
                        <div class="profile-info">
                            <h1 id="profile-name">Ori</h1>
                            <h2>Age: 67</h2>
                            <h2>Gender: Male</h2>
                            <h2>Love Languages</h2>
                            <ol>
                                <li>C++</li>
                                <li>JavaScript</li>
                                <li>Lisp</li>
                            </ol>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <a class="btn btn-primary message-btn btn-lg" id="sign-up" style="opacity: 1;">Message</a>
                    </div>
                </div>
                <div class="row">
                    <div class="bio col-sm-12">
                        <h2>Bio</h2>
                        <p>
                            Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate
                        </p>
                    </div>
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

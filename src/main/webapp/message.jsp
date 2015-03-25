<%-- 
    Document   : message
    Created on : Mar 25, 2015, 9:55:01 AM
    Author     : McKay
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
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
            <main class="message">
                <div class="col-sm-12">
                    <h2>Found your soul mate? Why not let them know!</h2>
                    <h3>From: <c:out value = "${sessionScope.github.getMyself().getName()}" ></c:out></h3>
                    <img src="<c:out value = "${sessionScope.github.getMyself().getAvatarUrl()}" ></c:out>" id="profile-pic" class="profile pull-right" alt="user-image" />
                    <form role="form" action="CreateNewMessage" method="POST" id="message-form">
                        <h3>To: 
                            <select name="sent_to">
                                <option>Soul Mate 1</option>
                                <option>Smeagol</option>
                                <option>Soul Mate 2</option>
                            </select>
                        </h3>
                        <h4>Subject: </h4>
                        <div class="form-group">
                            <input required type="text" class="form-control" placeholder="I'm pure magic." name="Subject" id="subject">
                        </div>
                        <h4>Message: </h4>
                        <div class="form-group">
                            <textarea required class="form-control" name="body" rows="5" id="message-body" placeholder="You could be my precious..."></textarea>
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
</html>

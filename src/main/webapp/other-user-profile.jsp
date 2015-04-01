<%-- 
    Document   : other-user-profile
    Created on : Mar 31, 2015, 8:33:48 PM
    Author     : weston
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

    <body class="gitcoupled user-profile">
        <div class="container">
            <div class="navbar navbar-default navbar-fixed-top coupled">
                <div class="container">
                    <div class="navbar-header">
                        <a href="http://gitcoupled-puremagic.rhcloud.com/" class="navbar-brand">
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
                            <li><a href="Profile">Profile</a>
                            </li>
                            <li>
                                <a href="ViewMessages">
                                    Messages 
                                    <c:if test="${user.getNumMessages() > 0}">
                                        <span class="badge">${user.getNumMessages()}</span>
                                    </c:if>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <main class="profile">
                <div class="row">
                    <div class="col-sm-4">
                        <img src="<c:out value = "${github.getUser(visit.getGithub_username()).getAvatarUrl()}" ></c:out>" id="profile-pic" />
                            <p class="quote">
                            <c:out value = "${visit.quote}" ></c:out>
                            </p>
                        </div>
                        <div class="col-sm-4">
                            <div class="profile-info">
                                <h1 id="profile-name"><c:out value = "${github.getUser(visit.getGithub_username()).getName()}" ></c:out></h1>
                            <h2>Age: <c:out value = "${visit.age}" ></c:out></h2>
                            <h2>Gender: <c:out value = "${visit.gender}" ></c:out></h2>
                                <h2>Love Languages</h2>
                                <ol>
                                    <li><c:out value = "${visit.getFirst_language()}" ></c:out></li>
                                <li><c:out value = "${visit.getSecond_language()}" ></c:out></li>
                                <li><c:out value = "${visit.getThird_language()}" ></c:out></li>
                                </ol>
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <a class="btn btn-primary message-btn btn-lg" style="opacity: 1;" data-toggle="modal" data-target="#send-message">Send Message</a>
                        </div>
                        <div class="col-sm-4">
                            <a class="btn btn-default message-btn btn-lg" style="opacity: 1;" href="Matches">Back to Matches</a>
                        </div>
                    </div>
                    <div class="row">
                        <div class="bio col-sm-12">
                            <h2>Bio</h2>
                            <p>
                            <c:out value = "${visit.bio}" ></c:out>
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
                        <a href="SignIn">login/register</a>
                    </div>
                </footer>
            </div>
            <div class="modal fade" id="send-message">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title">To: ${github.getUser(visit.getGithub_username()).getName()}</h4>
                    </div>
                    <div class="modal-body">
                        <form role="form" action="CreateNewMessage" method="POST" id="message-form">
                            <h4>Subject: </h4>
                            <div class="form-group">
                                <input required type="text" class="form-control" placeholder="Love" name="subject" id="subject">
                            </div>
                            <h4>Message: </h4>
                            <div class="form-group">
                                <textarea required class="form-control" name="body" rows="5" id="message-body" placeholder="Hi there..."></textarea>
                            </div>
                            <div style="display:none;">
                                <input type="text" name="userTo" value="${github.getUser(visit.getGithub_username()).getLogin()}">
                                <input type="text" name="userFrom" value="${github.getMyself().getLogin()}">
                                <input type="text" name="callback" value="ViewMessages">
                            </div>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                            <button type="submit" class="btn btn-primary">Send</button>
                        </form>
                    </div>

                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->
    </body>
</html>


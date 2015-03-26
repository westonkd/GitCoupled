<%-- 
    Document   : message
    Created on : Mar 25, 2015, 9:55:01 AM
    Author     : Weston
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
            <main class="messages-page">
                <div class="col-sm-12">
                    <h2>Messages</h2>
                    <c:forEach items="${messages}" var="message">
                        <form action="action">
                            <div class="message">
                                <h1>${github.getUser(message.getFrom()).getName()}</h1>
                                <h3><strong>Subject:</strong> ${message.getSubject()}</h3>
                                <p>
                                    ${message.getBody()}
                                </p>  
                                <a type="submit" class="btn btn-primary" onclick="$('#<c:out value="${message.getFrom()}"></c:out>').modal()">Reply</a>
                                </div>
                            </form>
                    </c:forEach>
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

        <c:forEach items="${messages}" var="message">
            <div class="modal fade" id="<c:out value="${message.getFrom()}"></c:out>">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title">To: ${github.getUser(message.getFrom()).getName()}</h4>
                        </div>
                        <div class="modal-body">
                            <form role="form" action="CreateNewMessage" method="POST" id="message-form">
                                <h4>Subject: </h4>
                                <div class="form-group">
                                    <input required type="text" class="form-control" placeholder="I'm pure magic." name="Subject" id="subject">
                                </div>
                                <h4>Message: </h4>
                                <div class="form-group">
                                    <textarea required class="form-control" name="body" rows="5" id="message-body" placeholder="You could be my precious..."></textarea>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                            <button type="button" class="btn btn-primary">Send</button>
                        </div>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal-dialog -->
            </div><!-- /.modal -->
        </c:forEach>Ï
    </body>
</html>

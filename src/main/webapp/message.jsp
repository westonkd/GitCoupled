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
        <title>Messages</title>
        <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
        <link rel="icon" type="image/png" href="images/favicon.png">
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
                            <li><a href="Matches">Matches</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <main class="messages-page">
                <div class="col-sm-12">
                    <h2>Messages</h2>
                    <div role="tabpanel">
                        <ul class="nav nav-tabs" role="tablist">
                            <li role="presentation" class="active"><a href="#newMessages" role="tab" data-toggle="tab">New Messages</a></li>
                            <li role="presentation"><a href="#sentMessages" role="tab" data-toggle="tab">Sent Messages</a></li>
                            <li role="presentation"><a href="#deletedMessages" role="tab" data-toggle="tab">Deleted Messages</a></li>
                        </ul>
                        <div class="tab-content">
                            <div role="tabpanel" class="tab-pane fade in active" id="newMessages">
                                <c:forEach items="${messages}" var="message">
                                    <div class="message">
                                        <div class="pull-right delete-message">
                                            <form action="DeleteMessage" method="POST">
                                                <div style="display:none;">
                                                    <input type="text" value="<c:out value="${message.getId()}"></c:out>" name="toDelete" />
                                                    <input type="text" value="ViewMessages" name="callback" />
                                                </div>
                                                <button type="submit" class="delete-message-button"><span class="glyphicon glyphicon-remove "></span></button>
                                            </form>
                                        </div>
                                        <h1>${github.getUser(message.getFrom()).getName()}</h1>

                                        <h3><strong>Subject:</strong> ${message.getSubject()}</h3>
                                        <p>
                                            ${message.getBody()}
                                        </p>  
                                        <a type="submit" class="btn btn-primary" onclick="$('#<c:out value="${message.getFrom()}"></c:out>').modal()">Reply</a>
                                    </div>
                                </c:forEach>
                            </div>
                            <div role="tabpanel" class="tab-pane fade" id="sentMessages">
                                <c:forEach items="${sentMessages}" var="sent">
                                    <div class="message">
                                        <h1>From: ${github.getUser(sent.getFrom()).getName()}</h1>
                                        <h1>To: ${github.getUser(sent.getTo()).getName()}</h1>

                                        <h3><strong>Subject:</strong> ${sent.getSubject()}</h3>
                                        <p>
                                            ${sent.getBody()}
                                        </p>
                                    </div>
                                </c:forEach>
                            </div>
                            <div role="tabpanel" class="tab-pane fade" id="deletedMessages">
                                <c:forEach items="${deletedMessages}" var="deleted">
                                    <div class="message">
                                        <h1>${github.getUser(deleted.getFrom()).getName()}</h1>

                                        <h3><strong>Subject:</strong> ${deleted.getSubject()}</h3>
                                        <p>
                                            ${deleted.getBody()}
                                        </p>  
                                        <a type="submit" class="btn btn-primary" onclick="$('#<c:out value="${deleted.getFrom()}"></c:out>').modal()">Reply</a>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
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

        <c:forEach items="${messages}" var="message">
            <div class="modal fade" id="<c:out value="${message.getFrom()}"></c:out>">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title">To: ${github.getUser(message.getFrom()).getName()}</h4>
                        </div>
                        <div class="modal-body">
                            <form role="form" action="ReplyToMessage" method="POST" id="message-form">
                                <h4>Subject: </h4>
                                <div class="form-group">
                                    <input required type="text" class="form-control" placeholder="Love" name="subject" id="subject">
                                </div>
                                <h4>Message: </h4>
                                <div class="form-group">
                                    <textarea required class="form-control" name="body" rows="5" id="message-body" placeholder="Hi there..."></textarea>
                                </div>
                                <div style="display:none;">
                                    <input type="text" name="userTo" value="${github.getUser(message.getFrom()).getLogin()}">
                                    <input type="text" name="userFrom" value="${github.getMyself().getLogin()}">
                                    <input type="text" name="callback" value="ViewMessages">
                                    <input type="hidden" name="in_reply_to" value="${message.getId()}" >
                                </div>
                                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                                <button type="submit" class="btn btn-primary">Send</button>
                            </form>
                        </div>

                    </div><!-- /.modal-content -->
                </div><!-- /.modal-dialog -->
            </div><!-- /.modal -->
        </c:forEach>
    </body>
</html>

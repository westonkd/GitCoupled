<%-- 
    Document   : matches
    Created on : Mar 13, 2015, 9:13:10 AM
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
        <link rel="icon" type="image/png" href="images/favicon.png">
        <link rel="stylesheet" href="css/jRating.css">
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
        <script src="js/typed.js"></script>
        <script src="js/skrollr.min.js"></script>
        <script src="js/jRating.min.js"></script>
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

            <main class="matches">
                <h1><strong>Possible Matches For:</strong> ${github.getMyself().getName()}</h1>

                <c:forEach items="${matches.keySet()}" var="matchList">
                    <c:forEach items="${matches.get(matchList)}" var="match">
                        <div class="row match">
                            <div class="col-sm-2">
                                <div class="thumbnail">
                                    <img src="${github.getUser(match.getGithub_username()).getAvatarUrl()}" class="profile-image"/>
                                </div>
                            </div>
                            <div class="col-sm-7">
                                <c:set var="userName" value="${github.getUser(match.getGithub_username()).getName()}"></c:set>

                                <c:if test="${empty userName}">
                                    <h2><c:out value="${github.getUser(match.getGithub_username()).getLogin()}"></c:out></h2>
                                </c:if>
                                <c:if test="${not empty userName}">
                                    <h2>${github.getUser(match.getGithub_username()).getName()}</h2>
                                </c:if>

                                <h3>Relationship Match - <strong>${matchList}</strong></h3>
                                <div class="stars" data-average="${matchList}" data-id="1"></div>
                                <h3>Primary Language: ${match.getFirst_language()}</h3>
                                <h3 class="quote">
                                    "${match.quote}"
                                </h3>
                            </div>
                            <div class="col-sm-3">
                                <button class="btn btn-primary message-btn" onclick="$('#<c:out value="${match.getGithub_username()}"></c:out>').modal()">Message</button>
                                    <form action ="ViewProfile" method="GET">
                                        <input type="hidden" name="username" value="${match.getGithub_username()}" />
                                    <button type="submit" class="btn btn-default message-btn">View Profile</button>
                                </form>
                            </div>
                        </div>
                    </c:forEach> 
                </c:forEach>

                <%-- Pagination --%>
                <c:if test="${numPages > 1}">
                    <nav class="pag-container">
                        <ul class="pagination">
                            <c:choose>
                                <c:when test="${curPage == 1}">
                                    <li class="disabled"><a href="" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
                                    </c:when>
                                    <c:otherwise>
                                    <li><a href="Matches?page=${curPage - 1}" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
                                    </c:otherwise>
                                </c:choose>
                                <c:forEach begin="1" end="${numPages}" var="page">
                                    <c:choose>
                                        <c:when test="${page == curPage}">
                                        <li class="active"><a href="">${page}</a></li>
                                        </c:when>
                                        <c:otherwise>
                                        <li><a href="Matches?page=${page}">${page}</a></li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                                <c:choose>
                                    <c:when test="${curPage == numPages}">
                                    <li class="disabled"><a href="" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
                                    </c:when>
                                    <c:otherwise>
                                    <li><a href="Matches?page=${curPage + 1}" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
                                    </c:otherwise>
                                </c:choose>
                        </ul>
                    </nav>
                </c:if>

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

        <c:forEach items="${matches.keySet()}" var="matchList">
            <c:forEach items="${matches.get(matchList)}" var="match">
                <div class="modal fade" id="<c:out value="${match.getGithub_username()}"></c:out>">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title">To: ${github.getUser(match.getGithub_username()).getName()}</h4>
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
                                        <input type="text" name="userTo" value="${github.getUser(match.getGithub_username()).getLogin()}">
                                        <input type="text" name="userFrom" value="${github.getMyself().getLogin()}">
                                        <input type="text" name="callback" value="Matches">
                                    </div>
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                                    <button type="submit" class="btn btn-primary">Send</button>
                                </form>
                            </div>

                        </div><!-- /.modal-content -->
                    </div><!-- /.modal-dialog -->
                </div><!-- /.modal -->
            </c:forEach> 
        </c:forEach> 
        <script type="text/javascript">
            $(document).ready(function () {
                $(".stars").jRating({
                    length: 10, // nb of stars
                    decimalLength: 0, // number of decimal in the rate
                    isDisabled: true,
                    rateMax: 10
                });
            });
        </script>
    </body>
</html>

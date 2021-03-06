<%-- 
    Document   : public-profile
    Created on : Mar 28, 2015, 12:48:58 PM
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
        <link rel="icon" type="image/png" href="images/favicon.png">
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
                        <img src="${github.getUser(visit.getGithub_username()).getAvatarUrl()}" id="profile-pic" />
                        <p class="quote">
                              <c:out value = "${visit.getQuote()}" ></c:out>
                        </p>
                    </div>
                    <div class="col-sm-4">
                        <div class="profile-info">
                            <h1 id="profile-name"><c:out value = "${github.getMyself().getName()}" ></c:out></h1>
                            <h2>Age: <c:out value = "${visit.getAge()}" ></c:out></h2>
                            <h2>Gender: <c:out value = "${visit.getGender()}" ></c:out></h2>
                            <h2>Love Languages</h2>
                            <ol>
                                <li><c:out value="${visit.getFirst_language()}" ></c:out></li>
                                <li><c:out value="${visit.getSecond_language()}" ></c:out></li>
                                <li><c:out value="${visit.getThird_language()}" ></c:out></li>
                            </ol>
                        </div>
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
                </div>
            </footer>
        </div>
    </body>
</html>

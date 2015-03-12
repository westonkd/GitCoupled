<%-- 
    Document   : index
    Created on : Mar 11, 2015, 9:39:29 PM
    Author     : weston
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <title>GitCoupled</title>
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
            <main>
                <section class="row">
                    <div class="col-md-12">
                        <div class="jumbotron">
                            <h1>Welcome</h1>
                            <p>$ <span id="type"></span>
                            </p>
                            </p>
                            <p><a class="btn btn-primary btn-lg" id="sign-up">Sign Up</a>
                            </p>
                        </div>
                    </div>
                </section>
                <section class="row">
                    <div class="col-md-4">
                        <div id="floater">
                            <h2>Find your /match/g</h2>
                            <p>
                                Is your love life lacking? GitCoupled integrates with your GitHub account and uses cool algorithms to find your perfect partner. Find potential matches based on your love languages (C++, Python, etc.), development habits, and project interests.
                                <a data-brackets-id="8481" class="btn btn-primary" id="sign-up-small" style="opacity: 1;">Sign Up</a>
                            </p>
                        </div>
                    </div>
                    <div class="col-md-8">
                        <img id="happy-man" src="images/happy-black-man-with-laptop.jpg" alt="GitCoupled!" />
                    </div>
                </section>
            </main>
            <footer class="row">

            </footer>
        </div>
    </body>

    <script type="text/javascript">
        $(document).ready(function () {
            var s = skrollr.init();

            $("#type").typed({
                strings: ["sudo ^500 apt-get instll significanlother", "sudo apt-get install significant-other"],
                typeSpeed: 0,
                startDelay: 3000,
                callback: function () {
                    $("#sign-up").delay(5000).css('opacity', '1');
                }
            });

        });
    </script>

</html>

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
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
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
                            <li><a href="SignIn">login/register</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <main>
                <section class="row" >
                    <div class="col-md-12">
                        <div class="jumbotron">
                            <h1>Welcome</h1>
                            <p>$ <span id="type"></span>
                            </p>
                            </p>
                            <p><a href="SignIn" class="btn btn-primary btn-lg" id="sign-up">Sign Up</a>
                            </p>
                        </div>
                    </div>
                </section>
                <section class="row">
                    <div class="col-md-4">
                        <div id="floater" data-0="opacity: 0.6;" data-center="opacity:1.0;">
                            <h2>Find your /match/g</h2>
                            <p>
                                Is your love life lacking? GitCoupled integrates with your GitHub account and uses cool algorithms to find your perfect partner. Find potential matches based on your love languages (C++, Python, etc.), development habits, and project interests.
                                <a href="SignIn" class="btn btn-primary" id="sign-up-small" style="opacity: 1;">Sign Up</a>
                            </p>
                        </div>
                    </div>
                    <div class="col-md-8" data-0="opacity: 0.6;" data-center="opacity:1.0;">
                        <img id="happy-man" src="images/happy-black-man-with-laptop.jpg" alt="GitCoupled!" />
                    </div>
                </section>
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
    </body>

    <script type="text/javascript">
        $(document).ready(function () {
            var s = skrollr.init();

            $("#type").typed({
                strings: ["sudo apt-get install significant-other"],
                typeSpeed: 0,
                startDelay: 3000,
                callback: function () {
                    $("#sign-up").delay(5000).css('opacity', '1');
                }
            });

        });
    </script>

</html>

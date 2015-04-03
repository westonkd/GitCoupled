<%-- 
    Document   : why_coupling
    Created on : Apr 1, 2015, 1:39:35 PM
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
        <link rel="icon" type="image/png" href="images/favicon.png">
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
        <script src="js/typed.js"></script>
        <script src="js/skrollr.min.js"></script>
    </head>

    <body class="app-store container">
        <div class="row">
         <div class="col-xs-12">
            <div class="header container-fluid">
                <div class="col-sm-4 logo">
                 <img src="images/Pixel_Heart.png" style="max-width:200px!important;" alt="">
             </div>
             <div class="col-sm-8">
                <h1>GitCoupled</h1>
                <h4>PureMagic - <span class="date">April 3, 2015</span></h4>
                <h4>Category: <span class="date">dating</span></h4>
                <a href="http://gitcoupled-puremagic.rhcloud.com/" class="btn-primary btn">Visit Site</a>

                <div class="seperator">
                    <div class="g-plusone" data-annotation="inline" data-width="300"></div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-xs-12">
        <div class="screenshots">
            <div class="screenshots-inner">
                <div class="screenshot"><img src="images/1.png" alt=""></div>
                <div class="screenshot"><img src="images/2.png" alt=""></div>
                <div class="screenshot"><img src="images/3.png" alt=""></div>
                <div class="screenshot"><img src="images/4.png" alt=""></div>
                <div class="screenshot"><img src="images/5.png" alt=""></div>
                <div class="screenshot"><img src="images/6.png" alt=""></div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-12">
            <div class="seperator">
                <h1>Description</h1>
                <p>Is your love life lacking? GitCoupled integrates with your GitHub account and uses cool algorithms to find your perfect partner. Find potential matches based on your love languages (C++, Python, etc.).</p>

                <p>GitCoupled allows you to view others profiles, top three languages used in ther GitHub repos, and send messages back and forth.</p>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-12">
            <div class="seperator">
                <h1>Additional Information</h1>
                <div class="container-fluid add-info">
                    <div class="col-sm-4">
                        <h4>Updated</h4>
                        April 3, 2015
                    </div>
                    <div class="col-sm-4">
                        <h4>Current Version</h4>
                        1.0.1
                    </div>
                    <div class="col-sm-4">
                        <h4>Requires</h4>
                        Google Chrome, IE 8+, Safri 8.0+, or Firefox 37
                    </div>
                    <div class="col-sm-4">
                        <h4>Content Rating</h4>
                        Clean
                    </div>
                    <div class="col-sm-4">
                        <h4>Offered By</h4>
                        PureMagic
                    </div>
                    <div class="col-sm-4">
                        <h4>Developer</h4>
                        PureMagic <br />
                        <a href="mailto:mckay.smalley@outlook.com">email developers</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Place this tag in your head or just before your close body tag. -->
<script src="https://apis.google.com/js/platform.js" async defer></script>
</body>

<style type="text/css">
    .add-info div {
        text-align: left;
    }

    .add-info h4 {
        font-weight: 400;
        font-size: 22px;
    }

    .screenshots-inner {
        width: 4000px;
    }

    .screenshot {
        display: inline;
        margin-right: 15px;
    }

    .screenshot img {
        height: 420px;
    }

    .screenshots {
        max-width: 100%;
        overflow-x: scroll;
    }

    .header {
        background-color: #e5e5e5;
        padding: 20px;
    }

    h4 {
        font-weight: 600;
    }

    .date {
        font-weight: 200;
    }

    .seperator {
        margin-top: 15px;
        padding-top: 10px;
        border-top: solid 1px darkgrey;
    }

    .logo {
        text-align: center;
    }
</style>
</html>


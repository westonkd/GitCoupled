<%-- 
    Document   : edit-existing-profile
    Created on : Apr 1, 2015, 10:59:35 AM
    Author     : McKay
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <title>GitCoupled - Profile</title>
        <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
        <link rel="stylesheet" href="https://bootswatch.com/cosmo/bootstrap.min.css">
        <link rel="icon" type="image/png" href="images/favicon.png">
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
                            <li><a href="SignIn">Login</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <main class="edit-profile">
                <div class="col-sm-12">
                    <h2>Tell us a bit about yourself <c:out value = "${github.getMyself().getName()}" ></c:out></h2>
                    <img src="${github.getMyself().getAvatarUrl()}" id="profile-pic" class="profile pull-right" alt="user-image" />
                    <form role="form" action="UpdateUser" method="GET" id="profile-form">
                        <h4>Gender</h4>
                        <div class="radio">
                            <label><input type="radio" name="gender" required value="male">Male</label>
                        </div>
                        <div class="radio">
                            <label><input type="radio" name="gender" value="female">Female</label>
                        </div>
                        <h4>Age</h4>
                        <div class="form-group">
                            <select required name="age" id="age">
                                <option value="" selected="selected">(Select Your Age)</option> 
                                <option value="16">16</option>
                                <option value="17">17</option>
                                <option value="18">18</option>
                                <option value="19">19</option>
                                <option value="20">20</option>
                                <option value="21">21</option>
                                <option value="22">22</option>
                                <option value="23">23</option>
                                <option value="24">24</option>
                                <option value="25">25</option>
                                <option value="26">26</option>
                                <option value="27">27</option>
                                <option value="28">28</option>
                                <option value="29">29</option>
                                <option value="30">30</option>
                                <option value="31">31</option>
                                <option value="32">32</option>
                                <option value="33">33</option>
                                <option value="34">34</option>
                                <option value="35">35</option>
                                <option value="36">36</option>
                                <option value="37">37</option>
                                <option value="38">38</option>
                                <option value="39">39</option>
                                <option value="40">40</option>
                                <option value="41">41</option>
                                <option value="42">42</option>
                                <option value="43">43</option>
                                <option value="44">44</option>
                                <option value="45">45</option>
                                <option value="46">46</option>
                                <option value="47">47</option>
                                <option value="48">48</option>
                                <option value="49">49</option>
                                <option value="50">50</option>
                                <option value="51">51</option>
                                <option value="52">52</option>
                                <option value="53">53</option>
                                <option value="54">54</option>
                                <option value="55">55</option>
                                <option value="56">56</option>
                                <option value="57">57</option>
                                <option value="58">58</option>
                                <option value="59">59</option>
                                <option value="50">60</option>
                                <option value="61">61</option>
                                <option value="62">62</option>
                                <option value="63">63</option>
                                <option value="64">64</option>
                                <option value="65">65</option>
                                <option value="66">66</option>
                                <option value="67">67</option>
                                <option value="68">68</option>
                                <option value="69">69</option>
                                <option value="70">70</option>
                                <option value="71">71</option>
                                <option value="72">72</option>
                                <option value="73">73</option>
                                <option value="74">74</option>
                                <option value="75">75</option>
                                <option value="76">76</option>
                                <option value="77">77</option>
                                <option value="78">78</option>
                                <option value="79">79</option>
                                <option value="80">80</option>
                                <option value="81">81</option>
                                <option value="82">82</option>
                                <option value="83">83</option>
                                <option value="84">84</option>
                                <option value="85">85</option>
                                <option value="86">86</option>
                                <option value="87">87</option>
                                <option value="88">88</option>
                                <option value="89">89</option>
                            </select>
                        </div>
                        <h4>Bio</h4>
                        <p>Let everyone know about yourself here. Hobbies, interests, past projects, etc.</p>
                        <div class="form-group">
                            <textarea required class="form-control" name="bio" rows="5" id="comment" placeholder="I love fighting crime by night, slaying dragons..."></textarea>
                        </div>
                        <h4>Quote</h4>
                        <p>Enter your awesome quote here. It will be displayed with your picture when other users are matched with you.</p>
                        <div class="form-group">
                            <input required type="text" class="form-control" placeholder="I'm pure magic." name="quote" id="quote">
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </div>
                    </form>
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
    </body>
    <script type="text/javascript" src="js/jquery.validate.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#quote').change(function () {
                var current = $(this).val();

                if (current !== "") {
                    $(this).val('"' + current + '"');
                }
            });
            $('#profile-form').validate();
        });
    </script>


</html>

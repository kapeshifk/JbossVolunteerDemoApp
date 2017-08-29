<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Kynsna Donations - Login</title>

    <!-- security flag -->
    <meta name="unauthorized" content="true">

    <!-- CSS -->
    <c:url var="urlNormalize" value="/css/normalize.css"/>
    <link rel="stylesheet" type="text/css" href="${urlNormalize}">

    <c:url var="urlBootstrap" value="/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${urlBootstrap}">

    <c:url var="urlFontAwesome" value="/css/font-awesome.min.css"/>
    <link rel="stylesheet" type="text/css" href="${urlFontAwesome}">

    <c:url var="urlAppCss" value="/css/app.css"/>
    <link rel="stylesheet" type="text/css" href="${urlAppCss}">
</head>
<body>
<!-- View for adding the navigation bar -->
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="logo"><h2>Kynsna Donations</h2></div>
    </div>
</nav>

<div class="login clearfix">
    <form method="post" action="j_security_check" name="loginForm" role=form>
        <div class="form-group">
            <label for="username"><span class="icon-user">Username</span></label>
            <input id="username" type="text" name="j_username" placeholder="SA ID Number">
        </div>

        <div class="form-group">
            <label for="password"><span class="icon-lock">Password</span></label>
            <input id="password" type="password" name="j_password" placeholder="">
        </div>

        <input type="submit" class="btn btn-lg btn-block btn-default" value="Login">
    </form>
</div>

</body>
</html>
<%-- 
    Document   : login
    Created on : Apr 6, 2017, 11:39:25 AM
    Author     : petermihok
--%>

<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <% HashMap<String, String> errors = (HashMap<String, String>) request.getAttribute("errors"); %>

<html>
<head>
  <title>Login Page</title>
  
  <%@include file="/views/partial/header.jsp" %>

</head>

<body>

  <%@include file="/views/partial/nav.jsp" %>

  <div class="form">
      
      <div class="tab-content">
        <div id="login">   
          <h1>Welcome!</h1>
          
          <form action="/login" method="post">
          
            <div class="field-wrap">
            <label>
              Email Address<span class="req">*</span><% if (errors != null && errors.containsKey("email")) { %><span class="error"><%= errors.get("email") %></span><% } %>
            </label>
            <input type="email" name="email" required autocomplete="off"/>
          </div>
          
          <div class="field-wrap">
            <label>
              Password<span class="req">*</span><% if (errors != null && errors.containsKey("password")) { %><span class="error"><%= errors.get("password") %></span><% } %>
            </label>
            <input type="password" name="password" required autocomplete="off"/>
          </div>
          
          <button type="submit" class="button button-block">Log In</button>
          
          </form>

        </div>
        
      </div><!-- tab-content -->
      
</div> <!-- /form -->
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
  <!--<script src='/js/index.js'></script>-->
</html>

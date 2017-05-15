<%-- 
    Document   : signup
    Created on : Apr 6, 2017, 11:39:03 AM
    Author     : petermihok
--%>

<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
<head>
  <title>Signup Page</title>
  
  <%@include file="/views/partial/header.jsp" %>
<% HashMap<String, String> errors = (HashMap<String, String>) request.getAttribute("errors"); %>
  
</head>

<body>

  <%@include file="/views/partial/nav.jsp" %>

  <div class="form">
      
      <div class="tab-content">
        <div id="signup">   
          <h1>Sign Up for FOG</h1>
          
          <form action="/signup" method="post">
          
          <div class="top-row">
            <div class="field-wrap">
              <label>
                First Name<span class="req"></span>
                
              </label>
              <input type="text" name="firstName" autocomplete="off" />
            </div>
        
            <div class="field-wrap">
              <label>
                Last Name<span class="req"></span>
              </label>
              <input type="text" name="lastName" value="" autocomplete="off"/>
            </div>
          </div>

          <div class="field-wrap">
            <label>
              Email Address<span class="req">*</span>
              <% if (errors != null && errors.containsKey("email")) { %><span class="error"><%= errors.get("email") %></span><% } %>
            </label>
            <input type="email" name="email" required autocomplete="off"/>
          </div>
          
          <div class="field-wrap">
            <label>
              Password<span class="req">*</span>
              <% if (errors != null && errors.containsKey("password")) { %><span class="error"><%= errors.get("password") %></span><% } %>
            </label>
            <input type="password" name="password" required autocomplete="off"/>
          </div>

          <div class="field-wrap">
            <label>
              Retype Password<span class="req">*</span>
              <% if (errors != null && errors.containsKey("passwordConfirmation")) { %><span class="error"><%= errors.get("passwordConfirmation") %></span><% } %>
            </label>
            <input type="password" name="passwordConfirmation" required autocomplete="off"/>
          </div>
          
          <button type="submit" class="button button-block"/>Sign up</button>
          
          </form>

        </div>
        
      </div><!-- tab-content -->
      
</div> <!-- /form -->
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

</body>
</html>


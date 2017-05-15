<%-- 
    Document   : signup
    Created on : Apr 6, 2017, 11:39:03 AM
    Author     : petermihok
--%>

<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="Web.DTO.SessionKeys"%>
<%@page import="Web.DTO.UserSessionDto"%>


<html>
<head>
  <title>Edit Profile</title>
  
  <%@include file="/views/partial/header.jsp" %>
  
<% HashMap<String, String> errors = (HashMap<String, String>) request.getAttribute("errors"); %>
  
</head>

<body>

  <%@include file="/views/partial/nav.jsp" %>

  <div class="container-fluid main-content">
      
      <div class="form">
      
      <div class="tab-content">
        <div id="signup">   
          <h1>Edit Profile</h1>
          
          <form action="/user/profile" method="post">
          
          <div class="top-row">
            <div class="field-wrap">
              <label>
                  First Name<span class="req">*</span><% if (errors != null && errors.containsKey("firstName")) { %><span class="error"><%= errors.get("firstName") %></span><% } %>
              </label>
              <input type="text" name="firstName" value="<%= sessionUser.getFirstName() %>" required autocomplete="off" />
            </div>
        
            <div class="field-wrap">
              <label>
                Last Name<span class="req"></span><% if (errors != null && errors.containsKey("lastName")) { %><span class="error"><%= errors.get("lastName") %></span><% } %>
              </label>
              <input type="text" name="lastName" value="<%= sessionUser.getLastName() %>" autocomplete="off"/>
            </div>
          </div>

          <div class="field-wrap">
            <label>
              Email Address<span class="req">*</span><% if (errors != null && errors.containsKey("email")) { %><span class="error"><%= errors.get("email") %></span><% } %>
            </label>
            <input type="email" name="email" value="<%= sessionUser.getEmail() %>" required autocomplete="off"/>
          </div>
          
          <div class="field-wrap">
            <label>
              Country<span class="req"></span><% if (errors != null && errors.containsKey("country")) { %><span class="error"><%= errors.get("country") %></span><% } %>
            </label>
            <input type="text" name="country" value="<%= sessionUser.getCountry()%>" autocomplete="off"/>
          </div>

          <div class="field-wrap">
            <label>
               City<span class="req"></span><% if (errors != null && errors.containsKey("city")) { %><span class="error"><%= errors.get("city") %></span><% } %>
            </label>
            <input type="text" name="city" value="<%= sessionUser.getCity() %>" autocomplete="off"/>
          </div>
          
          <div class="field-wrap">
            <label>
               Zipcode<span class="req"></span><% if (errors != null && errors.containsKey("zipcode")) { %><span class="error"><%= errors.get("zipcode") %></span><% } %>
            </label>
            <input type="text" name="zipcode" value="<%= sessionUser.getZipcode() %>" autocomplete="off"/>
          </div>
          
          <div class="field-wrap">
            <label>
               Address<span class="req"></span><% if (errors != null && errors.containsKey("address")) { %><span class="error"><%= errors.get("address") %></span><% } %>
            </label>
            <input type="text" name="address" value="<%= sessionUser.getAddress() %>" autocomplete="off"/>
          </div>
          
          <div class="field-wrap">
            <label>
               Phone<span class="req"></span><% if (errors != null && errors.containsKey("phone")) { %><span class="error"><%= errors.get("phone") %></span><% } %>
            </label>
            <input type="text" name="phone" value="<%= sessionUser.getPhone() %>" autocomplete="off"/>
          </div>
          
          <button type="submit" class="button button-block">Update</button>
          
          </form>

        </div>
        
      </div><!-- tab-content -->
      
</div> <!-- /form -->
      
  </div>
  
  
</body>
</html>


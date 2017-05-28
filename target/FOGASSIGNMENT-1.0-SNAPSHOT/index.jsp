
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
<head>
  <title>Home Page</title>
  
  <%@include file="/views/partial/header.jsp" %>
</head>

<body>

  <%@include file="/views/partial/nav.jsp" %>

<div class="container-fluid main-content">
    <% if (request.getAttribute("success") != null) { %>
    <div class="alert alert-success">
        <strong>Success!</strong> <%= request.getAttribute("success") %>
    </div>
    <% } %>
    <% if (request.getAttribute("error") != null) { %>
    <div class="alert alert-success">
        <strong>Error!</strong> <%= request.getAttribute("error") %>
    </div>
    <% } %>
</div>
  
</body>
</html>


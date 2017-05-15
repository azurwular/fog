<%@page import="Domain.UserRole"%>
<%@page import="Web.DTO.SessionKeys"%>
<%@page import="Web.DTO.UserSessionDto"%>
<% UserSessionDto sessionUser = (UserSessionDto) session.getAttribute(SessionKeys.user); %>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="/">FOG</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="/carport/build">Build carport</a></li>
      <% if (sessionUser != null && !sessionUser.getRole().equals(UserRole.Visitor)) { %>
      <li><a href="/checkout">Checkout</a></li>
      <% } %>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="/cart"><span class="glyphicon glyphicon-shopping-cart"></span> Cart</a></li>
      <% if (sessionUser == null || sessionUser.getRole().equals(UserRole.Visitor)) { %>
      <li><a href="/signup"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
      <li><a href="/login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
      <%} else { %>
      <li><a href="/user/profile">Welcome <%= sessionUser.getFirstName() %>!</a></li>
      <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
      <%}%>
    </ul>
  </div>
</nav>
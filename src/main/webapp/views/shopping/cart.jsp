<%@page import="Web.DTO.ShoppingCartCarportDto"%>
<%@page import="Web.DTO.ShoppingCartCarportItemDto"%>
<%@page import="java.util.List"%>
<%@page import="Web.DTO.ShoppingCartDto"%>
<html>
<head>
  <title>Shopping Cart</title>
  
  <%@include file="/views/partial/header.jsp" %>

  <% ShoppingCartDto shoppingCart = (ShoppingCartDto) session.getAttribute(SessionKeys.shoppingCart); %>
  <% UserSessionDto currentUser = (UserSessionDto) session.getAttribute(SessionKeys.user); %>
</head>

<body>

  <%@include file="/views/partial/nav.jsp" %>
  
  <div class="container-fluid main-content">
      <div class="form">
        <div class="tab-content">
            <div id="build-carport">   
              <h1>Shopping cart</h1>
              
              <% if (shoppingCart == null || shoppingCart.getCarports().isEmpty()) { %>
              <h2>You have no items in your shopping cart. <a href="/carport/build">Add some here</a></h2>
              <% } else { %>
                <form action="/shoppingCart" method="post">
                    <% List<ShoppingCartCarportDto> carports = shoppingCart.getCarports(); %>
                    <% for (int i = 0; i < carports.size(); i++) { %>
                    <h3>Carport <%= i + 1 %> (<%= carports.get(i).getRoofType() %>)</h3>
                        <div class="table-responsive">
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th>Name</th>
                                        <th>Quantity</th>
                                        <th>Price</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% List<ShoppingCartCarportItemDto> productParts = shoppingCart.getCarports().get(i).getProductParts(); %>
                                    <% for (int j = 0; j < productParts.size(); j++) { %>
                                    <tr>
                                        <td><%= productParts.get(j).getName() %></td>
                                        <td><%= productParts.get(j).getQuantity()%> <%= productParts.get(j).getStockUnit() %></td>
                                        <td><%= productParts.get(j).getPrice() %> DKK</td>
                                    </tr>
                                    <% } %>
                                </tbody>
                            </table>
                            <h4>Price: <%= carports.get(i).getPrice() %> DKK</h4>
                        </div>
                    <% } %>

                    <h2>Total price: <%= shoppingCart.getTotalPrice() %> DKK</h2>
                    <% if (currentUser == null || currentUser.getRole().equals(UserRole.Visitor)) { %>
                    <h3>You need to <a href="/login">login</a> to place an order.</h3>
                    <% } else { %>
                    <button id="order" type="submit" class="button button-block">Place order</button>
                    <% } %>
                </form>
              <% } %>
            </div>
        </div>
      </div>
  </div>
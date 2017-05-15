<%@page import="Domain.RoofType"%>
<%@page import="Web.DTO.ProductPartDto"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<html>
<head>
  <title>Edit Profile</title>
  
  <%@include file="/views/partial/header.jsp" %>

<% List<ProductPartDto> woods = (List<ProductPartDto>) request.getAttribute("woods"); %>
<% List<ProductPartDto> woodRoofings = (List<ProductPartDto>) request.getAttribute("woodRoofings"); %>
<% List<ProductPartDto> roofSkeletons = (List<ProductPartDto>) request.getAttribute("roofSkeletons"); %>
<% List<ProductPartDto> fittingsScrews = (List<ProductPartDto>) request.getAttribute("fittingsScrews"); %>
<% RoofType[] roofTypes = (RoofType[]) request.getAttribute("roofTypes"); %>
  
</head>

<body>

  <%@include file="/views/partial/nav.jsp" %>

  <div class="form">
      <div class="tab-content">
          <div id="build-carport">   
            <h1>Build Carport</h1>
          
            <form action="/carport/build" method="post">
          
                <div class="top-row">
                  <div class="field-wrap">
                    <label>
                        Select roof type<span class="req">*</span>
                    </label>
                      <select class="form-control">
                          <option></option>
                          <% for (RoofType roofType : roofTypes) { %>
                          <option><%= roofType.toString() %></option>
                          <% } %>
                      </select>
                  </div>
                      
                  <div id="fittings-screws" class="field-wrap">
                    <label>
                        Select Wood<span class="req">*</span>
                    </label>
                      <select class="form-control">
                          <option></option>
                          <% for (ProductPartDto productPart : woods) { %>
                          <option><%= productPart.getName()%></option>
                          <% } %>
                      </select>
                  </div>
                      
                  <div id="wood-roofing" class="field-wrap">
                    <label>
                        Select Wood Roofing<span class="req">*</span>
                    </label>
                      <select class="form-control">
                          <option></option>
                          <% for (ProductPartDto productPart : woodRoofings) { %>
                          <option><%= productPart.getName()%></option>
                          <% } %>
                      </select>
                  </div>
                      
                  <div id="roof-skeleton" class="field-wrap">
                    <label>
                        Select Roof Skeleton<span class="req">*</span>
                    </label>
                      <select class="form-control">
                          <option></option>
                          <% for (ProductPartDto productPart : roofSkeletons) { %>
                          <option><%= productPart.getName()%></option>
                          <% } %>
                      </select>
                  </div>
                      
                  <div id="fittings-screws" class="field-wrap">
                    <label>
                        Select Fittings & Screws<span class="req">*</span>
                    </label>
                      <select class="form-control">
                          <option></option>
                          <% for (ProductPartDto productPart : fittingsScrews) { %>
                          <option><%= productPart.getName()%></option>
                          <% } %>
                      </select>
                  </div>
                      
                </div>

                <button type="submit" class="button button-block">Add to cart</button>
          
            </form>
        </div>
    </div>
  </div>
      
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
</body>
</html>
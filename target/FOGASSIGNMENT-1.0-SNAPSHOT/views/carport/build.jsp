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
<% String selectedRoofType = (String) request.getAttribute("selectedRoofType"); %>
<% Integer selectedWood = (Integer) request.getAttribute("selectedWood"); %>
<% Integer selectedWoodRoofing = (Integer) request.getAttribute("selectedWoodRoofing"); %>
<% Integer selectedRoofSkeleton = (Integer) request.getAttribute("selectedRoofSkeleton"); %>
<% Integer selectedFittingsScrews = (Integer) request.getAttribute("selectedFittingsScrews"); %>
<% Integer selectedWoodQuantity = (Integer) request.getAttribute("selectedWoodQuantity"); %>
<% Integer selectedWoodRoofingQuantity = (Integer) request.getAttribute("selectedWoodRoofingQuantity"); %>
<% Integer selectedFittingsScrewsQuantity = (Integer) request.getAttribute("selectedFittingsScrewsQuantity"); %>
<% HashMap<String, String> errors = (HashMap<String, String>) request.getAttribute("errors"); %>
  
</head>

<body>

  <%@include file="/views/partial/nav.jsp" %>
  
  <div class="container-fluid main-content">
      
      <div class="form">
      <div class="tab-content">
          <div id="build-carport">   
            <h1>Build Carport</h1>
          
            <form action="/carport/build" method="post">
          
                  <div id="rooftype" class="field-wrap">
                    <label>
                        Select roof type<span class="req">*</span>
                        <% if (errors != null && errors.containsKey("roofType")) { %><span class="error"><%= errors.get("roofType") %></span><% } %>
                    </label>
                        <select name="roofType" class="form-control">
                            <option value='' <% if (selectedRoofType == null){ %> selected <% } %> disabled></option>
                            <% for (int i = 0; i < roofTypes.length; i++) { %>
                            <option value="<%= i %>" <% if (selectedRoofType == roofTypes[i].toString()){ %> selected <% } %>><%= roofTypes[i].toString() %></option>
                            <% } %>
                        </select>
                  </div>
                      
                  <div id="wood" class=" field-wrap hidden">
                    <label>
                        Select Wood<span class="req">*</span>
                        <% if (errors != null && errors.containsKey("woodId")) { %><span class="error"><%= errors.get("woodId") %></span><% } %>
                        <% if (errors != null && errors.containsKey("woodQuantity")) { %><span class="error"><%= errors.get("woodQuantity") %></span><% } %>
                    </label>
                      <div class="form-group form-inline">
                        <select name="woodId" class="form-control">
                            <option value='' selected disabled></option>
                            <% for (ProductPartDto productPart : woods) { %>
                            <option value="<%= productPart.getId() %>" <% if (selectedWood != null && selectedWood == productPart.getId()){ %> selected <% } %>><%= productPart.getName()%></option>
                            <% } %>
                        </select>
                        <input class="form-control" type="number" name="woodQuantity" value="<% if (selectedWoodQuantity == null) { %>1<% } else { %><%=selectedWoodQuantity%><% } %>"/>
                      </div>
                  </div>
                      
                  <div id="wood-roofing" class="field-wrap hidden">
                    <label>
                        Select Wood Roofing<span class="req">*</span>
                        <% if (errors != null && errors.containsKey("woodRoofingId")) { %><span class="error"><%= errors.get("woodRoofingId") %></span><% } %>
                        <% if (errors != null && errors.containsKey("woodRoofingQuantity")) { %><span class="error"><%= errors.get("woodRoofingQuantity") %></span><% } %>
                    </label>
                      <div class="form-group form-inline">
                        <select name="woodRoofingId" class="form-control">
                            <option value='' selected disabled></option>
                            <% for (ProductPartDto productPart : woodRoofings) { %>
                            <option value="<%= productPart.getId() %>" <% if (selectedWoodRoofing != null && selectedWoodRoofing == productPart.getId()){ %> selected <% } %>><%= productPart.getName()%></option>
                            <% } %>
                        </select>
                        <input class="form-control" type="number" name="woodRoofingQuantity" value="<% if (selectedWoodRoofingQuantity == null) { %>1<% } else { %><%=selectedWoodRoofingQuantity%><% } %>"/>
                      </div>
                  </div>
                      
                  <div id="roof-skeleton" class="field-wrap hidden">
                    <label>
                        Select Roof Skeleton<span class="req">*</span>
                        <% if (errors != null && errors.containsKey("roofSkeletonId")) { %><span class="error"><%= errors.get("roofSkeletonId") %></span><% } %>
                    </label>
                      <div class="form-group">
                        <select name="roofSkeletonId" class="form-control">
                            <option value='' selected disabled></option>
                            <% for (ProductPartDto productPart : roofSkeletons) { %>
                            <option value="<%= productPart.getId() %>" <% if (selectedRoofSkeleton != null && selectedRoofSkeleton == productPart.getId()){ %> selected <% } %>><%= productPart.getName()%></option>
                            <% } %>
                        </select>
                      </div>
                  </div>
                      
                  <div id="fittings-screws" class="field-wrap hidden">
                    <label>
                        Select Fittings & Screws<span class="req">*</span>
                        <% if (errors != null && errors.containsKey("fittingsScrewsId")) { %><span class="error"><%= errors.get("fittingsScrewsId") %></span><% } %>
                        <% if (errors != null && errors.containsKey("fittingsScrewsQuantity")) { %><span class="error"><%= errors.get("fittingsScrewsQuantity") %></span><% } %>
                    </label>
                      <div class="form-group form-inline">
                        <select name="fittingsScrewsId" class="form-control">
                            <option value='' selected disabled></option>
                            <% for (ProductPartDto productPart : fittingsScrews) { %>
                            <option value="<%= productPart.getId() %>" <% if (selectedFittingsScrews != null && selectedFittingsScrews == productPart.getId()){ %> selected <% } %>><%= productPart.getName()%></option>
                            <% } %>
                        </select>
                        <input class="form-control" type="number" name="fittingsScrewsQuantity" value="<% if (selectedFittingsScrewsQuantity == null) { %>1<% } else { %><%=selectedFittingsScrewsQuantity%><% } %>"/>
                      </div>
                  </div>
                      

                <button id="add-to-cart" type="submit" class="button button-block hidden">Add to cart</button>
          
            </form>
        </div>
    </div>
  </div>
      
  </div>
  <script src='/js/build.js'></script>
</body>
</html>
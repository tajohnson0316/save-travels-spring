<%--
  Created by IntelliJ IDEA.
  User: arman
  Date: 6/14/2023
  Time: 6:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- c:out ; c:forEach etc. -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Formatting (dates) -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<html>
<head>
  <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
  <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
  
  <title>Save Travels</title>
</head>
<body>
<div class="container p-5">
  <div class="d-flex justify-content-center">
    <div class="card w-100 mb-5">
      <div class="card-header text-center">
        <h1>Expenses Dashboard</h1>
      </div>
      <div class="card-body">
        <table class="table table-bordered table-striped">
          <thead>
          <tr>
            <th scope="col">Expense</th>
            <th scope="col">Vendor</th>
            <th scope="col">Amount</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach var="expense" items="${allExpenses}">
            <tr>
              <td>${expense.name}</td>
              <td>${expense.vendor}</td>
              <fmt:parseNumber
                  var="amount"
                  type="number"
                  value="${expense.amount}"
              />
              <td>
                $${amount}
              </td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
  </div>
  <div class="d-flex justify-content-center">
    <div class="card w-100">
      <div class="card-header text-center fs-3">
        New Expense Form
      </div>
      <div class="card-body">
        <form:form action="/expenses/new" method="post" modelAttribute="expense">
          <div class="mb-3">
            <form:label path="name" class="form-label">Expense name: </form:label>
            <form:input path="name" class="form-control"/>
            <p class="text-danger">
              <form:errors path="name"/>
            </p>
          </div>
          <div class="mb-3">
            <form:label path="vendor" class="form-label">Vendor:</form:label>
            <form:input path="vendor" class="form-control"/>
            <p class="text-danger">
              <form:errors path="vendor"/>
            </p>
          </div>
          <div class="mb-3">
            <form:label path="amount" class="form-label">Amount:</form:label>
            <div class="input-group">
              <span class="input-group-text">$</span>
              <form:input type="number" path="amount" class="form-control" step="0.01" min="0.01"/>
            </div>
            <p class="text-danger">
              <form:errors path="amount"/>
            </p>
          </div>
          <div class="mb-3">
            <form:label path="description" class="form-label">Description:</form:label>
            <form:input path="description" class="form-control"/>
            <p class="text-danger">
              <form:errors path="description"/>
            </p>
          </div>
          <button type="submit" class="btn btn-primary">Submit</button>
        </form:form>
      </div>
    </div>
  </div>
</div>
</body>
</html>
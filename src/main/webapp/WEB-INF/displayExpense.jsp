<%--
  Created by IntelliJ IDEA.
  User: arman
  Date: 6/14/2023
  Time: 10:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<div class="container p-3">
  <div class="d-flex justify-content-center">
    <div class="card w-100 mb-3">
      <div class="card-header text-center fs-3">
        <c:out value="${expense.name}"/>
      </div>
      <div class="card-body">
        <div class="d-flex justify-content-start align-items-center fs-3 gap-3">
          <span class="fw-bold">Vendor:</span>
          <span><c:out value="${expense.vendor}"/></span>
        </div>
        <div class="d-flex justify-content-start align-items-center fs-3 gap-3">
          <span class="fw-bold">Amount:</span>
          <fmt:parseNumber
              var="amount"
              type="number"
              value="${expense.amount}"
          />
          <span>
            $${amount}
          </span>
        </div>
        <div class="d-flex justify-content-start align-items-center fs-3 gap-3">
          <span class="fw-bold">Description:</span>
          <span><c:out value="${expense.description}"/></span>
        </div>
      </div>
    </div>
  </div>
  <div class="d-flex justify-content-end gap-3">
    <a href="/">Go back</a>
  </div>
</div>
</body>
</html>
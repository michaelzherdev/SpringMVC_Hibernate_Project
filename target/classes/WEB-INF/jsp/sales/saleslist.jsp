<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<spring:url value="/sales/add" var="urlAddSale" />
<spring:url value="/products" var="urlProducts" />

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

<nav class="navbar navbar-default">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="${urlProducts}">Products</a>
		</div>
		<div id="navbar">
			<ul class="nav navbar-nav navbar-right">
				<li class="active"><a href="${urlAddSale}">Add Sale</a></li>
			</ul>
		</div>
	</div>
</nav>

<body>

	<div class="container">

		<c:if test="${not empty msg}">
			<div class="alert alert-${css} alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">�</span>
				</button>
				<strong>${msg}</strong>
			</div>
		</c:if>

		<h1>All Sales</h1>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>ID</th>
					<th>Product`s number in sale</th>
					<th>Check sum, $</th>
					<th>Date</th>
				</tr>
			</thead>

			<c:forEach var="s" items="${sales}">
				<tr>
					<td>${s.id}</td>
					<td>${s.itemsSize}</td>
					<td>${s.cost}</td>
					<td><fmt:formatDate value="${s.date}" pattern="dd/MM/yyyy HH:mm" /></td>
					<td><spring:url value="/sales/${s.id}" var="saleUrl" /> <spring:url
							value="/sales/${s.id}/delete" var="deleteSaleUrl" />

						<button class="btn btn-info"
							onclick="location.href='${saleUrl}'">Info</button>
					</td>
				</tr>
			</c:forEach>
		</table>

	</div>
	<jsp:include page="../fragments/footer.jsp" />
</body>
</html>
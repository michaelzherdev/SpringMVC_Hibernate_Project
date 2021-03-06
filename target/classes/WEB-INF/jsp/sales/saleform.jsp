<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

<spring:url value="/sales/addOrderItem" var="urlAddOrderItem" />
<spring:url value="/sales" var="urlSales" />

<nav class="navbar navbar-default">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="${urlSales}">Sales</a>
		</div>
		<div id="navbar">
			<ul class="nav navbar-nav navbar-right">
				<li class="active"><a href="${urlAddOrderItem}">Add Order Item</a></li>
			</ul>
		</div>
	</div>
</nav>

<div class="container">

	<h1>Add Sale</h1>
	<br />

	<spring:url value="/sales" var="saleActionUrl" />

	<form:form class="form-horizontal" method="post"
		modelAttribute="saleForm" action="${saleActionUrl}">

		<form:hidden path="id" />


		<spring:bind path="items">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Product List</label>
				<div class="col-sm-5">
					<form:select path="items" class="form-control" disabled="true">
						<form:options items="${items}" />
					</form:select>
					<form:errors path="items" class="control-label" />
				</div>
			</div>
		</spring:bind>
		
		<spring:bind path="cost">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Cost, $</label>
				<div class="col-sm-5">
					<form:input path="cost" type="number" class="form-control" id="cost"
						placeholder="Cost" disabled="true" />
					
					<form:errors path="cost" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn-lg btn-primary pull-right">Add
						</button>
			</div>
		</div>
	</form:form>
</div>

<jsp:include page="../fragments/footer.jsp" />

</body>
</html>
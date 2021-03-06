<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

<spring:url value="/products" var="urlProducts" />

<nav class="navbar navbar-default ">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="${urlProducts}">Products</a>
		</div>
	</div>
</nav>

<div class="container">

	<c:choose>
		<c:when test="${productForm['new']}">
			<h1>Add Product</h1>
		</c:when>
		<c:otherwise>
			<h1>Update Product</h1>
		</c:otherwise>
	</c:choose>
	<br />

	<spring:url value="/products" var="productActionUrl" />

	<form:form class="form-horizontal" method="post" 
                modelAttribute="productForm" action="${productActionUrl}">

		<form:hidden path="id" />

		<spring:bind path="name">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Name</label>
			<div class="col-sm-10">
				<form:input path="name" type="text" class="form-control" 
                                id="name" placeholder="Name" />
				<form:errors path="name" class="control-label" />
			</div>
		  </div>
		</spring:bind>

		<spring:bind path="price">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Price</label>
			<div class="col-sm-10">
				<form:input path="price" class="form-control" 
                                id="price" placeholder="Price" />
				<form:errors path="price" class="control-label" />
			</div>
		  </div>
		</spring:bind>
		
		<div class="form-group">
		  <div class="col-sm-offset-2 col-sm-10">
			<c:choose>
			  <c:when test="${productForm['new']}">
			     <button type="submit" class="btn-lg btn-primary pull-right">Add
                             </button>
			  </c:when>
			  <c:otherwise>
			     <button type="submit" class="btn-lg btn-primary pull-right">Update
                             </button>
			  </c:otherwise>
			</c:choose>
		  </div>
		</div>
	</form:form>

</div>

<jsp:include page="../fragments/footer.jsp" />

</body>
</html>
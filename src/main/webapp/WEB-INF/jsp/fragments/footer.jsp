<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="container">
	<hr>
	<footer>
		<p>&copy; Mikhail Zherdev 2016</p>
	</footer>
</div>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

<spring:url value="/resources/js/main.js" var="coreJs" />
<spring:url value="/resources/js/bootstrap.min.js"
	var="bootstrapJs" />

<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>
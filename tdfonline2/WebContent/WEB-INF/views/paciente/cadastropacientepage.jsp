<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url var="actionUrl" value="/cadastrarpaciente" />

<form:form action="${actionUrl}" modelAttribute="paciente" method="POST" acceptCharset="UTF-8">
	<form:label path="nome">Nome</form:label>
	<form:input path="nome" />
	<form:errors path="nome" />
	<br> 
	<form:label path="cpf">CPF</form:label>
	<form:input path="cpf" />
	<form:errors path="cpf" />
	<br>
	<form:label path="email">Email</form:label>
	<form:input path="email" />
	<form:errors path="email" />
	<br>
	<form:label path="telefone">Telefone</form:label>
	<form:input path="telefone" />
	<form:errors path="telefone" />
	<br>
    <form:button id="Cadastrar Paciente">Cadastrar Paciente</form:button>
</form:form>
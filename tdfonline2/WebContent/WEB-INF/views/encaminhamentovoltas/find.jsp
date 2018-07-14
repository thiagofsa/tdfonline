<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<spring:url value="/encaminhamentovoltas/find2" var="findUrl" />

	<form:form class="form-horizontal" 
                modelAttribute="encaminhamentoForm" action="${findUrl}">

		
	<spring:bind path="dataviagemvolta">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			
			 <div class="control-group">
        		<form:label cssClass="control-label" path="dataviagem">Data Viagem de Volta:</form:label>
        		<div class="controls">
            	<form:input path="dataviagem" class="date" />
        		</div>
    		</div>
		  </div>
		</spring:bind>


			
		<div class="form-group">
		  <div class="col-sm-offset-2 col-sm-10">
			
			     <button type="submit" class="btn-lg btn-primary pull-right" >Pesquisar
                             </button>
			
		  </div>
		</div>
	</form:form>

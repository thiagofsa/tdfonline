<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<spring:url value="/encaminhamentos/find2" var="findUrl" />

	<form:form class="form-horizontal" 
                modelAttribute="encaminhamentoForm" action="${findUrl}">

		
	<spring:bind path="data">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			
			 <div class="control-group">
        		<form:label cssClass="control-label" path="data">Data:</form:label>
        		<div class="controls">
            	<form:input path="data" class="date" />
        		</div>
    		</div>
		  </div>
		</spring:bind>


	<spring:bind path="vagas">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			
			 <div class="control-group">
        		<form:label cssClass="control-label" path="vagas">Vagas:</form:label>
        		<div class="controls">
            	<form:input path="vagas" class="date" />
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

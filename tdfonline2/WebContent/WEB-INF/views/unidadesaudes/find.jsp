<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<spring:url value="/unidadesaudes/find2" var="findUrl" />

	<form:form class="form-horizontal" 
                modelAttribute="unidadesaudeform" action="${findUrl}">

		
		<spring:bind path="descricao">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Descricao</label>
			<div class="col-sm-10">
				<form:input path="descricao" type="text" class="form-control" 
                                id="descricao" placeholder="Descricao" />
				<form:errors path="descricao" class="control-label" />
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

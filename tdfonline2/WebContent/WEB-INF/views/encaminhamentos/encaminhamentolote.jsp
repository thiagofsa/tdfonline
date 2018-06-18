<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>

<body>

	<div class="container">

		<c:if test="${not empty msg}">
		    <div class="alert alert-${css} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" 
                                aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<strong>${msg}</strong>
		    </div>
		</c:if>
	</div>
		
<spring:url value="/encaminhamentos/lote2" var="acompanhanteUrl" />

<form class="form-horizontal"  action="${acompanhanteUrl}">

<h1>Passo 1 - Selecione a distribuicão </h1>
                
<table border="1">
  
	 <tr>
        <td> Selecione</td>
        <td>ID </td>
        <td>Data Viagem </td>
        <td>Veículo </td>
        <td>Motorista </td>
        <td>Vagas </td>
     </tr>
     
   <c:forEach items="${distribuicaos}" var="distribuicao" varStatus="status">
  <tr>
      
        <td> <input type="radio" id="iddistribuicao" name="iddistribuicao" value="${distribuicao.id}"/></td>
        <td><c:out value="${distribuicao.id}"/> </td>
        <td><c:out value="${distribuicao.dataviagem}"/> </td>
        <td><c:out value="${distribuicao.veiculo.descricao}"/> </td>
        <td><c:out value="${distribuicao.motorista.nome}"/> </td>
        <td><c:out value="${distribuicao.vagas}"/> </td>

        
     
     </tr>
   </c:forEach>
  
</table> 
  

<br><Br>

<h1>Passo 2 - Selecione as marcações </h1>  
  
<table border="1">
  
  <tr>
        <td> Selecione</td>
        <td>ID </td>
        <td>Data Viagem </td>
        <td>Paciente</td>
        <td>Destino - Un. Saúde </td>
        <td>Confirmada</td>
        <td>Total de Vagas </td>
        
     </tr>
     
   <c:forEach items="${marcacaos}" var="marcacao" >
  <tr>
      
        <td>  <input type="checkbox" id="idsmarcacao" name="idsmarcacao" value="${marcacao.id}"/></td>
        <td><c:out value="${marcacao.id}"/> </td>
        <td><c:out value="${marcacao.dataviagem}"/> </td>
        <td><c:out value="${marcacao.paciente.nome}"/> </td>
        <td><c:out value="${marcacao.unidadesaude.descricao}"/> </td>
        <td><c:out value="${marcacao.confirmada}"/> </td>
        <td><c:out value="${marcacao.vagas}"/> </td>
     
     </tr>
   </c:forEach>
  
</table>
  
  <br><Br>
  
  <div class="form-group">
		  <div class="col-sm-offset-2 col-sm-10">
			
			     <button type="submit" class="btn-lg btn-primary pull-right" >Enviar
                             </button>
			
		  </div>
	</div>
  
</form>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>

</body>
</html>
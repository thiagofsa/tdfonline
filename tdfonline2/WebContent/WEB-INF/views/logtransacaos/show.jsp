<%@page import="br.com.tfdonline.dao.TransacaoDAOI"%>
<%@page import="br.com.tfdonline.modelo.LogTransacao"%>
<%@page import="br.com.tfdonline.modelo.Marcacao"%>
<%@page import="br.com.tfdonline.modelo.Beneficio"%>
<%@page import="br.com.tfdonline.modelo.Distribuicao"%>
<%@page import="br.com.tfdonline.modelo.Encaminhamento"%>
<%@page import="br.com.tfdonline.modelo.Usuario"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">


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

	<h1>Log de Transacao - Detalhes </h1>
	<br />

	<table class="table table-striped" border="1">
	<thead>
				<tr>
					<th>#ID</th>
					<th>Data</th>
					<th>Login</th>
					<th>Entidade</th>
					<th>Operacao</th>
					<th>ID entidade</th>
					
					
				</tr>
	</thead>
	
	<%
	
	LogTransacao logtransacao = (LogTransacao) request.getAttribute("logtransacao");
		
	out.println("<td>"+logtransacao.getId()+"</td>");
	out.println("<td>"+logtransacao.getData()+"</td>");
	out.println("<td>"+logtransacao.getUsuario().getLogin()+"</td>");
	out.println("<td>"+logtransacao.getTransacao().getEntidade()+"</td>");
	out.println("<td>"+logtransacao.getTransacao().getOperacao()+"</td>");
	out.println("<td>"+logtransacao.getIdentidade()+"</td>");
	
	%>
	
	</table>	
	
	<br><Br><br>
	
	<table class="table table-striped" border="1">
	
	
	<%
	if (logtransacao.getTransacao().getEntidade()==TransacaoDAOI.ENTIDADE_MARCACAO){
		
		out.print("Detalhamento Marcacao");
		Marcacao marcacao = (Marcacao) request.getAttribute("marcacao");
		out.write("<thead><tr><td>Nome Paciente</td><td>Unidade Saude</td><td>Procedimento</td><td>Data Viagem</td></tr>");
		
		out.write("<thead><tr>");
		
		out.write("<Td>"+ marcacao.getPaciente().getNome()+"</Td>");
		out.write("<Td>"+ marcacao.getUnidadesaude().getDescricao()+"</Td>");
		out.write("<Td>"+marcacao.getProcedimento().getNome()+"</Td>");
		out.write("<Td>"+marcacao.getDataviagem()+"</Td>");
			
		out.write("</thead></tr>");
		
	}
	
	if (logtransacao.getTransacao().getEntidade()==TransacaoDAOI.ENTIDADE_BENEFICIO){
		
		Beneficio beneficio = (Beneficio) request.getAttribute("beneficio");
		
		out.print("Detalhamento Beneficio");
		
		out.write("<thead><tr><td>Nome Paciente</td><td>Data Viagem ida</td><td>Data viagem volta</td><td>Valor</td></tr>");
		
		out.write("<thead><tr>");
		
		out.write("<Td>"+ beneficio.getPaciente().getNome()+"</Td>");
		out.write("<Td>"+ beneficio.getDataviagemida()+"</Td>");
		out.write("<Td>"+ beneficio.getDataviagemvolta()+"</Td>");
		out.write("<Td>"+ beneficio.getValor()+"</Td>");
			
		out.write("</thead></tr>");
		
	}
	
	if (logtransacao.getTransacao().getEntidade()==TransacaoDAOI.ENTIDADE_DISTRIBUICAO){
		
		Distribuicao distribuicao = (Distribuicao) request.getAttribute("distribuicao");
		
		out.print("Detalhamento Distribuicao");
		
		out.write("<thead><tr><td>Nome Motorista</td><td>Veiculo</td><td>Placa</td><td>Data viagem</td></tr>");
		
		out.write("<thead><tr>");
		
		out.write("<Td>"+ distribuicao.getMotorista().getNome()+"</Td>");
		out.write("<Td>"+ distribuicao.getVeiculo().getDescricao()+"</Td>");
		out.write("<Td>"+ distribuicao.getVeiculo().getPlaca()+"</Td>");
		out.write("<Td>"+ distribuicao.getDataviagem()+"</Td>");
		
			
		out.write("</thead></tr>");
		
	}
	
	if (logtransacao.getTransacao().getEntidade()==TransacaoDAOI.ENTIDADE_ENCAMINHAMENTO){
		
		Encaminhamento encaminhamento = (Encaminhamento) request.getAttribute("encaminhamento");
		
		out.print("Detalhamento Encaminhamento");
		out.write("<thead><tr><td>Nome Motorista</td><td>Unidade Saude</td><td>MOtorista</td><td>Data viagem ida</td><td>Data viagem volta</td></tr>");
		
		out.write("<thead><tr>");
		
		out.write("<Td>"+ encaminhamento.getMarcacao().getPaciente().getNome()+"</Td>");
		out.write("<Td>"+ encaminhamento.getMarcacao().getUnidadesaude().getDescricao()+"</Td>");
		out.write("<Td>"+ encaminhamento.getDistribuicao().getMotorista().getNome()+"</Td>");
		out.write("<Td>"+ encaminhamento.getDataviagem()+"</Td>");
		out.write("<Td>"+ encaminhamento.getDataviagemvolta()+"</Td>");
		
			
		out.write("</thead></tr>");
		
	}
	
	if (logtransacao.getTransacao().getEntidade()==TransacaoDAOI.ENTIDADE_USUARIO){
		
		Usuario usuario = (Usuario) request.getAttribute("usuario");
		out.print("Detalhamento Usuario");
		out.write("<thead><tr><td>Nome Usuario</td><td>Login</td></tr>");
		
		out.write("<thead><tr>");
		
		out.write("<Td>"+ usuario.getNome()+"</Td>");
		out.write("<Td>"+ usuario.getLogin()+"</Td>");
		
		
			
		out.write("</thead></tr>");
		
	}
	
	%>
	
	
	
	
	
	</table>
	
</div>


</body>
</html>
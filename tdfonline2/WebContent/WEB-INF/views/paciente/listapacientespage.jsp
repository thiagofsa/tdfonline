<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

 <div align="center">
            <h1>Lista de pacientes</h1>
            <table border="1">
                <th>ID</th>
                <th>Nome</th>
                <th>Email</th>
                 
                <c:forEach var="paciente" items="${listaPacientes}">
                <tr>
                    <td>${paciente.id}</td>
                    <td>${paciente.nome}</td>
                    <td>${paciente.email}</td>
                             
                </tr>
                </c:forEach>             
            </table>
 </div>

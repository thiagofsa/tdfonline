<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<body>
 
<h1>Download do relatório</h1><p>
Click the download links below:</p><c:url value="/main/download?type=xls" var="downloadXls"/>
<a href="${downloadXls}">Download Excel</a>
 
<c:url value="/main/download?type=pdf" var="downloadPdf"/>
<a href="${downloadPdf}">Download PDF</a>
 
<c:url value="/main/download?type=html" var="downloadHtml"/>
<a href="${downloadHtml}">Download HTML</a>
 
<c:url value="/main/download?type=csv" var="downloadCsv"/>
<a href="${downloadCsv}">Download CSV</a>
 
</body>

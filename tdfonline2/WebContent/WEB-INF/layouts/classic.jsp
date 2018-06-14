<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html>

<html lang="pt-br">

  <head>    
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title><tiles:getAsString name="title" /></title>
    
    <link href="resources/css/bootstrap.css"  rel="stylesheet" >
    <link href="resources/css/fontawesome.css"  rel="stylesheet" > 
    
  </head> 
 
  <body>
    	<div id="header">
			<tiles:insertAttribute name="header" />
		</div>		
		
		<div id="menu">
			<tiles:insertAttribute name="menu" />
		</div>
		
		<div id="page" >
			<tiles:insertAttribute name="body" />			
		</div>
		
		<div id="footer">
			<tiles:insertAttribute name="footer" />
		</div>
		
	<script src="resources/js/jquery.min.js"  type="text/javascript"></script>
	<script src="resources/js/popper.min.js"  type="text/javascript"></script>
	<script src="resources/js/bootstrap.js"  type="text/javascript"></script>
		
    </body>
</html>


<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE>

<html lang="pt-br">

  <head> 
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title><tiles:getAsString name="title" /></title>
  </head> 
 
  <body>
		<div id="menu">
			<tiles:insertAttribute name="menu" />
		</div>
		
		<div id="page" >
			<tiles:insertAttribute name="body" />			
		</div>
		
		<div id="footer">
			<tiles:insertAttribute name="footer" />
		</div>
		
    </body>
</html>


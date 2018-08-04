<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>

<html lang="pt-br">

  <head>    
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title><tiles:getAsString name="title" /></title>
    
    <link rel="stylesheet" href= "<c:url value='/resources/css/bootstrap.css'/>" >  
    <link rel="stylesheet" href= "<c:url value='/resources/css/fontawesome.css'/>" >       
    <link rel="stylesheet" href= "<c:url value='/resources/css/contato.css'/>" >
  </head>  
 
<body>

<div class="container">

	<div  class="spacesup"></div>
	<div   class="text-center text-uppercase font-weight-bold">
		<h4  class="h4 font-weight-bold">Suporte</h4>
	</div>	
	<section id="contact" class="section-bg">
        
        <div class="row contact-info">

          <div class="col-md-4">
            <div class="contact-address">
              <i class="fas fa-map-marker-alt fa-3x mb-4"></i>
              <h3>Endereço</h3>
              <address>R. São José 260, São José. Garanhuns-PE</address>
            </div>
          </div>

          <div class="col-md-4">
            <div class="contact-phone">
              <i class="fas fa-phone fa-3x mb-4"></i>
              <h3>Telefone</h3>
              <p>(87) 9 9809-3458 - Flavio Medeiros</p>
              
            </div>
          </div>

          <div class="col-md-4">
            <div class="contact-email">
              <i class="fas fa-envelope fa-3x mb-4"></i>
              <h3>Email</h3>
              <p>springdevti@gmail.com</p>
            </div>
          </div>

        </div>
        
        <div   class="text-center my-5 text-uppercase font-weight-bold">
			<h5  class="h5 font-weight-bold">Abrir Chamado ao Suporte</h5>
		</div>	

        <div class="form">
          <div id="sendmessage">Your message has been sent. Thank you!</div>
          <div id="errormessage"></div>
          <form action="" method="post" role="form" class="contactForm">
            <div class="form-row">
              <div class="form-group col-md-6">
                <input type="text" name="name" class="form-control" id="name" placeholder="Nome" data-rule="minlen:4" data-msg="Please enter at least 4 chars" />
                <div class="validation"></div>
              </div>
              
              <div class="form-group col-md-6">
	              <input type="text" class="form-control" name="subject" id="subject" placeholder="Assunto" data-rule="minlen:4" data-msg="Please enter at least 8 chars of subject" />
	              <div class="validation"></div>
              </div>              
            </div>
            
            <div class="form-group">
              <textarea class="form-control" name="message" rows="5" data-rule="required" data-msg="Please write something for us" placeholder="Mensagem"></textarea>
              <div class="validation"></div>
            </div>
            <div class="text-center">
            	<button type="submit" class="btn btn-outline-dark">Abrir Chamado</button>
            </div>
          </form>
        </div>

      </div>
    </section><!-- #contact -->
	
</div>        
	<script src="<c:url value='/resources/js/jquery.min.js'/>"></script>
	<script src="<c:url value='/resources/js/popper.min.js'/>"></script>
	<script src="<c:url value='/resources/js/bootstrap.js'/>"></script>
</body>
</html>
 
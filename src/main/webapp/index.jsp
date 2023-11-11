<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Seguranca Web</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
        
        <!-- CATCHA V2 -->
        <script type="text/javascript">
      		var onloadCallback = function() {
        		grecaptcha.render('html_element', {
          			'sitekey' : '6LeSugcpAAAAAErDmFetVIxyu8HupViScGnqh_JM'
        		});
      		};
    	</script>
        
    </head>
    <body>
        <div class="m-5 p-2 bg-light container">
            <h1 class="text-center">Autenticação</h1>
            <p class="text-center">Informe o seu usuario e senha.</p>
            
            <c:if test="${param['invalido'] == true}">
                <span class="text-danger">Usuario e/ou senha invalido(s)!</span>
            </c:if>
            <c:if test="${param['proibido'] == true}">
                <span class="text-danger">Voce nao esta autenticado! Autentique-se para acessar esse recurso.</span>
            </c:if>
            <c:if test="${param['recaptcha_invalido'] == true}">
                <span class="text-danger">Complete o desafio reCAPTCHA.</span>
            </c:if>
            
            <form action="${pageContext.request.contextPath}/acessar" method="POST">
                <div class="row">
                    <div class="col-12">
                        <label for="email">E-mail:</label>
                        <input type="email" required="required" name="email" class="form-control" />
                    </div>
                </div>
                <div class="row">
                    <div class="col-12">
                        <label for="password">Senha:</label>
                        <input type="password" required="required" name="senha" class="form-control" />
                    </div>
                </div>
                <br />
                <div class="g-recaptcha" data-sitekey="6LeSugcpAAAAAErDmFetVIxyu8HupViScGnqh_JM"></div>
                <button type="submit" class="btn btn-primary mt-2">Acessar</button>
            </form>
        </div>
        
		<script src="https://www.google.com/recaptcha/api.js" async defer></script>
    </body>
</html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"       prefix="fmt" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=decice-width, initial-scale=1">
	<title>Pagina Inicial</title>
	<!-- Local dos links-->
	<link rel="stylesheet" type="text/css" href="front/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="front/css/estilo.css">
	<link rel="stylesheet" type="text/css" href="front/css/bootstrap-social.css">
<style>
th{
	width: 200px;
	text-align: center;
}
td{
	text-align: center;
}
</style>
</head>
<body>
<header>
	<div class="topo">
		<div class="row">
			<div class="col-xs-12">
				<section class="espaco1"></section>
			</div>
		</div>
		<div class="row">
			<div class="container-fluid">
				<div class="col-xs-2 col-sm-1 col-md-1 col-lg-1"></div>
				<div class="img-topo col-xs-8 col-sm-2 col-md-2 col-lg-1 ">
					<img src="front/img/logo1.png" class="tamanho">
				</div>
				<div class="col-xs-1 col-sm-1 "></div>			
				<div class="form-group col-xs-9 col-sm-4 col-md-5 col-lg-6 pesquisa1">
					<input type="text" placeholder="O que esta procurando?" class="form-control pesquisa">	
				</div>
				<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1" >
					<button type="button" class="btn btn-default psq-btn">OK</button>	
				</div>				
		</div>	
	</div>
</div>
<div class="row">
	<nav class="navbar navbar-inverse menu col-sm-12">
		<div class="container-fluid">
			<div class="navbar-header">	
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
			</div>
			<div class="col-md-1"></div>
			<div class="collapse navbar-collapse menu2" id="myNavbar">
		       <ul class="nav navbar-nav menu2">
		        <li><a href="index.html">Pagina Inicial</a></li>
                <li><a href="produtos.html">Livros</a></li>
              </ul>
                <ul class="nav navbar-nav navbar-right menud">
                    <li>${log }</li>
                </ul>
			</div>
		</div>
	</nav>	
</div>	
</header>
	<form method="post" action="buscar.html">
		<table>
			<tr>
				<td><input type="text" name="nome"></td>
				<td><button type="submit">Buscar</button></td>
			</tr>
		</table>
	</form>
	${msg }
	<c:if test="${fn:length(lista) > 0 }">
    	<table>
    		<tr>
    			<th>Titulo</th>
    			<th>Autor</th>
    			<th>Preco</th>
    		</tr>
    		<c:forEach items="${lista }" var="f">
    			<tr>
    				<td>${f.titulo}</td>
    				<td>${f.autor}</td>
    				<td>${f.preco}</td>
    				<td><a href="http://localhost:8080/Livraria/Carrinho?livro=${f.id }">Compra</a></td>
    			</tr>
    		</c:forEach>
    	</table>
    </c:if>
<!--PARTE DO RODAPÉ-->
<footer class="rodapecima">
	<div class="row">
		<div class="container-fluid centro">
			<div class="col-xs-1 col-sm-0 col-md-1"></div>
			<div class="col-xs-12 col-sm-5 col-md-5">
				<h3>DÚVIDAS FREQUENTES</h3>	
				<h5>Privacidade e Segurança</h5>
				<h5>Termos de Uso</h5>	
				<h5>Trocas e Devoluções</h5>
				<h5>Acompanhe seu pedido</h5>				
			</div>
			<div class="col-xs-12 col-sm-5 col-md-5">
				<h3>ATENDIMENTO</h3>
				<h4>(21) 2222-2222</h4>
				<h4>(21) 4444-4444</h4>
				<h5>Hórario de atendimento das 8h às 20h</h5>
				<h5>de segunda a sábado(exeto feriados)</h5>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-1 col-sm-1 col-md-1"></div>
			<div class="col-xs-9 col-sm-5 col-md-7 traco"></div>
		</div>
		<div class="row">
			<div class="container-fluid rodapebaixo">
				<div class="col-xs-1 col-sm-1 col-md-2"></div>
				<div class="col-xs-12 col-sm-12 col-md-3">
					<h3>CONECTE-SE CONOSCO</h3>
				</div>
				<div class="col-xs-12 col-sm-12 col-md-4">
					<h3>FORMAS DE PAGAMENTO</h3>
				</div>
				<div class="col-xs-12 col-sm-12 col-md-2">
					<h3>SITE SEGURO</h3>
				</div>
			</div>
		</div>	
		<div class="row">
			<div class="col-xs-1 col-md-1"></div>
			<div class="col-xs-12 col-md-7 text-center textofinal">
				<h5>Livraria S/A | Av.Presidente Vargas, 2300, 10° andar, Rio de Janeiro - RJ - Brasil CEP 20233-300| CNPJ 86.765.764/0007-87| Livraria, todos os direitos reservados. Copyrights 2017</h5>
			</div>
		</div>
	</div>
</footer>
</body>
<!-- Local dos JS-->
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/jquery-3.2.1.js"></script>
</html>
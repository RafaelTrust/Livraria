<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=decice-width, initial-scale=1">
    <title>Cadastro</title>
    <!-- Local dos links-->
    <link rel="stylesheet" type="text/css" href="front/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="front/css/estilo.css">
	<link rel="stylesheet" type="text/css" href="front/css/bootstrap-social.css">
</head>
<body>
<!--PARTE DO TOPO-->
<header >
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

<!--PARTE DO MENU-->
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

<div class="row">
    <div class="col-sm-3"></div>
    <div class="col-sm-6">
        <div id="container" >
        ${msg }
            <form id="cadastro" method="post" action="cadastrar.html">
                <h2>Cadastre-se</h2>
                <div class="form-group">
                    <label for="nome">Email:</label>
                    <input type="text" class="form-control f-pes" name="email" required />
                </div>
                <div class="form-group">    
                    <label for="senha:">Senha:</label>
                    <input type="password" class="form-control f-pes" name="none"  required /> 
                </div>  

                <div class="form-group">    
                    <label for="senha:">Confirmar senha:</label>
                    <input type="password" class="form-control f-pes" name="senha" oninput="check(this)" required /> 
                </div>  
                <div class="form-group">
                 <label for="nome">Nome:</label>
                    <input type="text" class="form-control f-pes" name="nome" required/>
                </div>

                <div class="form-group">        
                    <label for="email">Telefone:</label>
                    <input type="text" class="form-control f-pes" name="telefone"   required  />       
                </div>
                <div class="form-group">    
                    <label for="celular">Celular:</label>
                    <input type="text" class="form-control f-pes" name="celular"  required />
                </div> 

                <div class="form-group">    
                    <label for="senha:">CPF:</label>
                    <input type="text" class="form-control f-pes" name="cpf"  required /> 
                </div>  
                   <div class="form-group">
                    <label for="senha:">Cep:</label>
                    <input type="text" class="form-control f-pes" name="cep"  required /> 
                </div> 

                <div class="form-group">    
                    <label for="senha:">Endereço:</label>
                    <input type="text" class="form-control f-pes" name="end1"  required /> 
                </div>  

                 <div class="form-group">
                    <label for="senha:">Bairro:</label>
                    <input type="text" class="form-control f-pes" name="bairro"  required />
                </div>  

                <div class="form-group">
                    <label for="senha:">Cidade:</label>
                    <input type="text" class="form-control f-pes" name="cidade"  required />
                </div>  

                <div class="form-group">
                   <label for="senha:">Estado:</label>
                   <input type="text" class="form-control f-pes" name="estado"  required />
                </div>  
                           
                <input type="submit" id="enviar" value="Enviar" />
                <input type="reset" id="enviar" value="Limpar" />  
            </form>
        </div>
    </div> 
</div>


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

<script type="text/javascript">
    function check(input) {
        if (input.value != document.getElementById('none').value) {
            input.setCustomValidity('E-mail não bate.');
        } else {
            // input is valid -- reset the error message
            input.setCustomValidity('');
        }
    }
</script>



<!-- Local dos JS-->
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/jquery-3.2.1.js"></script>


</body>

<hr>
<!--PARTE DO RODAPÉ-->

</html>
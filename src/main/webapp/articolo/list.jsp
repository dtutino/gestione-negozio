<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="/header.jsp" />
	<title>Pagina dei risultati</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="/navbar.jsp" />
	
	<main role="main" class="container">
	
		<div class="alert alert-success alert-dismissible fade show ${successMessage==null?'d-none': ''}" role="alert">
		  ${successMessage}
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
		  ${errorMessage}
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		<div class="alert alert-info alert-dismissible fade show d-none" role="alert">
		  Aggiungere d-none nelle class per non far apparire
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Lista dei risultati</h5> 
		    </div>
		    <div class='card-body'>
		    	<a class="btn btn-primary " href="PrepareInsertArticoloServlet">Add New</a>
		    
		        <div class='table-responsive'>
		            <table class='table table-striped ' >
		                <thead>
		                    <tr>
		                        <th>Nome</th>
		                        <th>Prezzo</th>
		                        <th>Data Inserimento</th>
		                        <th>Categoria</th>
		                        <th>Stato</th>
		                        <th>Azioni</th>
		                    </tr>
		                </thead>
		                <tbody>
		                	<c:forEach items="${articoli_list_attribute }" var="articoloItem">
								<tr>
									<td>${articoloItem.nome }</td>
									<td>${articoloItem.prezzo }</td>
									<td><fmt:formatDate type = "date" value = "${artioloItem.dataInserimento }" /></td>
									<td>
										<c:forEach items="${articoloItem.categorie }" var="categoriaItem">
											${categoriaItem.descrizione }
										</c:forEach>
									</td>
									<td>${articoloItem.stato }</td>
									<td>
										<a class="btn  btn-sm btn-outline-secondary" href="ExecuteVisualizzaArticoloServlet?idArticolo=${articoloItem.id }">Visualizza</a>
										<a class="btn  btn-sm btn-outline-primary ml-2 mr-2" href="PrepareModificaArticoloServlet?idArticolo=${articoloItem.id }">Edit</a>
										<a class="btn btn-outline-danger btn-sm" href="PrepareEliminaArticoloServlet?idArticolo=${articoloItem.id }">Delete</a>
								</tr>
							</c:forEach>
		                </tbody>
		            </table>
		        </div>
		   
			<!-- end card-body -->			   
		    </div>
		</div>	
	
	
	<!-- end container -->	
	</main>
	<jsp:include page="/footer.jsp" />
	
</body>
</html>
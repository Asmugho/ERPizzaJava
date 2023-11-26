<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="models.Produto" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Lista de Clientes</title>
    <!-- Adicione os links para os arquivos do Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/erp-pizza">Meu App</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="produtos">Lista de Produtos</a>
            </li>
        </ul>
    </div>
</nav>

<%
    if(request.getAttribute("produto") != null) {
        Produto produto = (Produto) request.getAttribute("produto");
%>
        <div class="container">
            <h2>Editar Produto</h2>
            <form action="cadastroProduto" method="post">
                <div class="form-group">
                    <label for="nome">Nome:</label>
                    <input type="text" class="form-control" id="nome" name="nome" value="<%= produto.getNome() %>" required>
                </div>
                <div class="form-group">
                    <label for="preco">Preço:</label>
                    <input type="number" class="form-control" id="preco" name="preco" value="<%= produto.getPreco() %>" required>
                </div>
                <input type="hidden" name="id" id="id" value="<%= produto.getId()%>">
                <button type="submit" class="btn btn-primary">Atualizar</button>
            </form>
        </div>
<%
    } else {
%>
        <div class="container mt-4">
            <h2>Cadastro de Produtos</h2>
            <form action="cadastroProduto" method="post">
                <div class="form-group">
                    <label for="nome">Nome:</label>
                    <input type="text" class="form-control" id="nome" name="nome" required>
                </div>
                <div class="form-group">
                    <label for="preco">Preço:</label>
                    <input type="number" class="form-control" id="preco" name="preco" required>
                </div>
                <button type="submit" class="btn btn-primary">Cadastrar</button>
            </form>
        </div>
<%
    }
%>
<!-- Adicione os scripts do Bootstrap no final do corpo para melhorar o tempo de carregamento -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>

</body>
</html>

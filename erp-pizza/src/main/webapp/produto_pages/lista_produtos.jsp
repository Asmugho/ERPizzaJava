<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="models.Produto" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Lista de Produtos</title>
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
                <a class="nav-link" href="cadastroProduto">Cadastro Produto</a>
            </li>
        </ul>
    </div>
</nav>

<div class="container mt-4">
    <h2>Lista de Produtos</h2>
    <table class="table">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Preco</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
            <% List<Produto> listaDeProdutos = (List<Produto>) request.getAttribute("listaDeProdutos");
                if (listaDeProdutos != null && !listaDeProdutos.isEmpty()) {
                    for (Produto produto : listaDeProdutos) { %>
                        <tr>
                            <td><%= produto.getId() %></td>
                            <td><%= produto.getNome() %></td>
                            <td><%= produto.getPreco() %></td>
                            	<td><a class="btn btn-success"
                            						href="<%=String.format("/erp-pizza/cadastroProduto?id=%s", produto.getId())%>">EDITAR</a>
                            						<a class="btn btn-danger"
                            						href="<%=String.format("/erp-pizza/deletarProduto?id=%s", produto.getId())%>">Deletar</a></td>
                            					</td>
                        </tr>
            <%
                    }
                }
            %>
        </tbody>
    </table>
</div>

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

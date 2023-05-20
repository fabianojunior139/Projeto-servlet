<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@page import="com.br.loja.Model.Produto" %>
<%@page import="java.util.ArrayList" %>

<%
    @SuppressWarnings("unchecked")
    ArrayList<Produto> lista = (ArrayList<Produto>) request.getAttribute("listarProdutos");
%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="./assets/loja.png" type="image/x-icon">
    <link rel="stylesheet" href="./styles/tabelaProduto.css">
    <title>Produtos Cadastrados</title>
</head>

<body>

<div class="retornar-home">
    <a href="home" class="voltar">&#x1F814 Voltar a página principal</a>
</div>

<table class="tabela">
    <caption>
        <h1 class="titulo-tabela">Produtos Cadastrados</h1>
    </caption>

    <thead>
    <tr>
        <th>Código</th>
        <th>Nome</th>
        <th>Categoria</th>
        <th>Valor</th>
        <th>Quantidade</th>
        <th>Ações</th>
    </tr>
    </thead>

    <tbody>
    <%for (int i=0; i < lista.size(); i++) { %>
    <tr>
        <td>
            <%=lista.get(i).getCodigo() %>
        </td>
        <td>
            <%=lista.get(i).getNome() %>
        </td>
        <td>
            <%=lista.get(i).getCategoria() %>
        </td>
        <td>
            <%=lista.get(i).getValor() %>
        </td>
        <td>
            <%=lista.get(i).getQuantidade() %>
        </td>
        <td>
            <a href="listar?id=<%=lista.get(i).getId()%>" class="botao-editar">Editar</a>
            <a href="excluir?id=<%=lista.get(i).getId()%>" class="botao-excluir">Excluir</a>
        </td>
    </tr>
    <%} %>
    </tbody>
</table>
</body>

</html>
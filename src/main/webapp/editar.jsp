<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="./assets/loja.png" type="image/x-icon">
    <link rel="stylesheet" href="./styles/editar.css">
    <title>Editar Produtos</title>
</head>

<body>
<div class="container">
    <form action="editar" method="post" class="card">
        <h1 class="cadastroProduto-titulo">Editar produto</h1>

        <label for="codigo" class="formulario-label">Código do produto</label>
        <input type="text" name="codigo" id="codigo" class="formulario-input" required
               value="<%out.print(request.getAttribute("codigo"));%>">

        <label for="nome" class="formulario-label">Nome do produto</label>
        <input type="text" name="nome" id="nome" class="formulario-input" required
               value="<%out.print(request.getAttribute("nome"));%>">

        <label for="categoria" class="formulario-label">Categoria</label>
        <input type="text" name="categoria" id="categoria" class="formulario-input"
               value="<%out.print(request.getAttribute("categoria"));%>">

        <label for="valor" class="formulario-label">Valor</label>
        <input type="text" name="valor" id="valor" class="formulario-input" required
               value="<%out.print(request.getAttribute("valor"));%>">

        <label for="quantidade" class="formulario-label">Quantidade</label>
        <input type="text" name="quantidade" id="quantidade" class="formulario-input" required
               value="<%out.print(request.getAttribute("quantidade"));%>">

        <input type="hidden" name="id" id="id" readonly value="<%out.print(request.getAttribute("id"));%>">

        <input type="submit" name="Editar" value="Editar" class="botao-cadastro-produto">

        <a href="home" class="retornar-home">Retornar à página principal</a>
    </form>
</div>
</body>

</html>
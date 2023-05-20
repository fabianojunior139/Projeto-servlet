package com.br.loja.Controller;

import com.br.loja.Dao.ProdutoDAO;
import com.br.loja.Model.Produto;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(urlPatterns = {"/inserir", "/listarTodosProdutos", "/listar", "/home", "/add", "/editar", "/excluir", "/login"})
public class Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Servlet() throws SQLException {
    }

    //Criando instância da classe produtoDAO de conexão com o banco de dados
    ProdutoDAO produtoDAO = new ProdutoDAO();

    //Método que gerencia o tipo de requisição e encaminha o path para o doGet ou doPost
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getMethod();
        if (method.equals("POST")) {
            System.out.println("POST");
            doPost(request, response);
        } else if (method.equals("GET")) {
            System.out.println("GET");
            doGet(request, response);
        }
    }

    //Método responsável por geralmente fazer pesquisas no banco de dados
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //O path que vem do resultado da função service
        String path = request.getServletPath();

        PrintWriter out = response.getWriter();

        //Definindo o diretório default da aplicação
        if (path.equals("/home")) {
            response.sendRedirect("home.html");
        }

        //Path utilizado para listar especificamente um produto
        if (path.equals("/listar")) {

            //Resgatando o id enviado pela url
            Long id = Long.valueOf(request.getParameter("id"));
            try {
                Produto produto = produtoDAO.selecionarProdutoPorId(id);
                request.setAttribute("id", produto.getId());
                request.setAttribute("codigo", produto.getCodigo());
                request.setAttribute("nome", produto.getNome());
                request.setAttribute("categoria", produto.getCategoria());
                request.setAttribute("valor", produto.getValor());
                request.setAttribute("quantidade", produto.getQuantidade());

                RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
                rd.forward(request, response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        //Path que é utilizado para retornar todos os produtos cadastrados na base de dados
        if (path.equals("/listarTodosProdutos")) {
            try {
                request.setAttribute("listarProdutos", listarProdutos());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            RequestDispatcher rd = request.getRequestDispatcher("produto.jsp");
            rd.forward(request, response);
        }

        //Path responsável por execluir um produto da base de dados
        if (path.equals("/excluir")) {
            Long id = Long.valueOf(request.getParameter("id"));
            produtoDAO.removerProduto(id);
            response.sendRedirect("listarTodosProdutos");
        }
    }

    //Método que geralmente é utilizado para fazer alterações dentro da base de dados
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();

        //Método utilizado dentro de Servlets para conseguir printar algo na tela quando executado
        PrintWriter out = response.getWriter();

        //Caminho responsável por redirecionar o usuário até a página de cadastro de produtos
        if (path.equals("/add")) {
            response.sendRedirect("cadastrar.html");
        }

        //Path utilizado para inserir um novo produto a base de dados
        if (path.equals("/inserir")) {
            Long codigo = Long.parseLong(request.getParameter("codigo"));
            String nomeProduto = request.getParameter("nomeProduto");
            String categoria = request.getParameter("categoria");
            Double valor = Double.valueOf(request.getParameter("valor"));
            int quantidade = Integer.parseInt(request.getParameter("quantidade"));

            Produto produto = new Produto(codigo, nomeProduto, categoria, valor, quantidade);
            try {
                produtoDAO.inserirProduto(produto);
                request.setAttribute("listarProdutos", listarProdutos());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            RequestDispatcher rd = request.getRequestDispatcher("produto.jsp");
            rd.forward(request, response);
        }

        //Path responsável por editar as informações de um produto cadastrado
        if (path.equals("/editar")) {
            Long id = Long.valueOf(request.getParameter("id"));
            Long codigo = Long.valueOf(request.getParameter("codigo"));
            String nome = (request.getParameter("nome"));
            String categoria = (request.getParameter("categoria"));
            Double valor = Double.valueOf((request.getParameter("valor")));
            int quantidade = Integer.parseInt((request.getParameter("quantidade")));

            Produto produtoAtualizado = new Produto(id, codigo, nome, categoria, valor, quantidade);

            try {
                produtoDAO.alterarProduto(produtoAtualizado);
                response.sendRedirect("listarTodosProdutos");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        //Path responsável por autenticar um usuário na tela de login
        if (path.equals("/login")) {
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");

            String alertScript = "<script type=\"text/javascript\">" +
                    "alert('Usuário ou senha incorretos!');" +
                    "</script>";

            if (email.equals("fabiano@gmail.com") && senha.equals("12345")) {
                response.sendRedirect("home");
            } else {
                out.println(alertScript);
            }
        }
    }

    //Método responsável por montar uma lista com todos os produtos cadastrados na base de dados
    protected List<Produto> listarProdutos() throws SQLException {
        List<Produto> listarProdutos = new ArrayList<>();
        listarProdutos = produtoDAO.ListarTodosOsProdutos();
        return listarProdutos;
    }

}
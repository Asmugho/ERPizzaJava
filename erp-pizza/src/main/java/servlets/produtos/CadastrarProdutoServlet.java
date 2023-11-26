package servlets.produtos;

import dao.cliente.ClienteDAO;
import dao.produto.ProdutoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Cliente;
import models.Produto;

import java.io.IOException;

@WebServlet(name = "CadastroProduto", urlPatterns = "/cadastroProduto")
public class CadastrarProdutoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = req.getParameter("id") != null ? Long.parseLong(req.getParameter("id")): 0;
        Produto produto = null;

        try {
            if(id > 0 )
                produto = ProdutoDAO.buscarProdutoPorId(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            req.setAttribute("produto",produto);
            req.getRequestDispatcher("/produto_pages/cadastro_produto.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = req.getParameter("id") != null ? Long.parseLong(req.getParameter("id")): 0;
        String nome = req.getParameter("nome") != null ? req.getParameter("nome"): "";
        double preco = req.getParameter("preco") != null ? Double.parseDouble(req.getParameter("preco")): 0;
        boolean resultado = false;
        try{
            if(id > 0){
                Produto produtoNovo = new Produto(id,nome,preco);
                resultado = ProdutoDAO.atualizarProdutoPorId(produtoNovo);
            }else {
                Produto produtoNovo = new Produto(nome, preco);
                resultado = ProdutoDAO.cadastrarProduto(produtoNovo);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            if(resultado)
                resp.sendRedirect("produtos");
            else
                resp.sendRedirect("erro.jsp");
        }
    }

}

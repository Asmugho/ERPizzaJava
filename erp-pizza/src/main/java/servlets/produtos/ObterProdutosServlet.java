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
import java.util.List;

@WebServlet(name = "ObterProdutos", urlPatterns = "/produtos")
public class ObterProdutosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Produto> produtos = null;
        try{
            produtos = ProdutoDAO.obterTodosOsProdutos();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            req.setAttribute("listaDeProdutos",produtos);
            req.getRequestDispatcher("/produto_pages/lista_produtos.jsp").forward(req,resp);
        }

    }
}

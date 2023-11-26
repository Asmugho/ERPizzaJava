package servlets.pedidos;

import dao.pedido.PedidoDAO;
import dao.produto.ProdutoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "DeletarPedido", urlPatterns = "/deletarPedido")
public class DeletarPedidoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = req.getParameter("id") != null ? Long.parseLong(req.getParameter("id")): 0;
        boolean resultado = false;
        try{
            if(id > 0)
                resultado = PedidoDAO.deletarPedidoPorId(id);
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            if(resultado)
                resp.sendRedirect("pedidos");
            else
                resp.sendRedirect("erro.jsp");
        }
    }
}

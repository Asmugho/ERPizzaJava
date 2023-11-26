package servlets.pedidos;

import dao.pedido.PedidoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.dtos.PedidoDTO;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ObterPedidos", urlPatterns = "/pedidos")
public class ObterPedidosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<PedidoDTO> pedidos = null;
        try{
            pedidos = PedidoDAO.obterTodosOsPedidos();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            req.setAttribute("listaDePedidos",pedidos);
            req.getRequestDispatcher("/pedido_pages/lista_pedidos.jsp").forward(req,resp);
        }

    }
}

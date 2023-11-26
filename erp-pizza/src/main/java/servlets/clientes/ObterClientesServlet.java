package servlets.clientes;

import dao.cliente.ClienteDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Cliente;
import java.util.List;

import java.io.IOException;

@WebServlet(name = "ObterClientes", urlPatterns = "/clientes")
public class ObterClientesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Cliente> clientes = null;
        try{
            clientes = ClienteDAO.obterTodosOsClientes();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            req.setAttribute("listaDeClientes",clientes);
            req.getRequestDispatcher("/cliente_pages/lista_clientes.jsp").forward(req,resp);
        }

    }
}

package servlets.clientes;

import dao.cliente.ClienteDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Cliente;

import java.io.IOException;

@WebServlet(name = "DeletarCliente", urlPatterns = "/deletarCliente")
public class DeletarClienteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = req.getParameter("id") != null ? Long.parseLong(req.getParameter("id")): 0;
        boolean resultado = false;
        try{
            if(id > 0)
                resultado = ClienteDAO.deletarClientePorId(id);
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            if(resultado)
                resp.sendRedirect("clientes");
            else
                resp.sendRedirect("erro.jsp");
        }
    }
}

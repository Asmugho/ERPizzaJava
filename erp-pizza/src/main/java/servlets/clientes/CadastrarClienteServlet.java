package servlets.clientes;

import dao.cliente.ClienteDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Cliente;

import java.io.IOException;

@WebServlet(name = "CadastroCliente", urlPatterns = "/cadastroCliente")
public class CadastrarClienteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = req.getParameter("id") != null ? Long.parseLong(req.getParameter("id")): 0;
        Cliente cliente = null;
        try {
            if(id > 0 )
                cliente = ClienteDAO.buscarClientePorId(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            req.setAttribute("cliente",cliente);
            req.getRequestDispatcher("/cliente_pages/cadastro_cliente.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nome = req.getParameter("nome") != null ? req.getParameter("nome"): "";
        String endereco = req.getParameter("endereco") != null ? req.getParameter("endereco"): "";
        long id = req.getParameter("id") != null ? Long.parseLong(req.getParameter("id")): 0;
        boolean resultado = false;
        try{
            if(id >0){
                Cliente clienteNovo = new Cliente(nome, endereco,id);
                resultado = ClienteDAO.atualizarClientePorId(clienteNovo);
            }else {
                Cliente clienteNovo = new Cliente(nome, endereco);
                resultado = ClienteDAO.cadastrarCliente(clienteNovo);
            }
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

package servlets.pedidos;

import dao.cliente.ClienteDAO;
import dao.pedido.PedidoDAO;
import dao.produto.ProdutoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Cliente;
import models.Pedido;
import models.Produto;
import models.dtos.PedidoDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CadastroPedido", urlPatterns = "/cadastroPedido")
public class CadastrarPedidoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Cliente> clientes = new ArrayList<>();
        List<Produto> produtos = new ArrayList<>();
        try {
            clientes = ClienteDAO.obterTodosOsClientes();
            produtos = ProdutoDAO.obterTodosOsProdutos();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            req.setAttribute("listaDeClientes",clientes);
            req.setAttribute("listaDeProdutos",produtos);
            req.getRequestDispatcher("/pedido_pages/cadastro_pedido.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = req.getParameter("id") != null ? Long.parseLong(req.getParameter("id")): 0;
        long clienteId = req.getParameter("clienteId") != null ? Long.parseLong(req.getParameter("clienteId")): 0;
        long produtoId = req.getParameter("produtoId") != null ? Long.parseLong(req.getParameter("produtoId")): 0;
        boolean resultado = false;
        try{
            if(id > 0){
                Pedido pedidoNovo = new Pedido(id,clienteId,produtoId);
                resultado = PedidoDAO.atualizarPedidoPorId(pedidoNovo);
            }else {
                Pedido produtoNovo = new Pedido(clienteId, produtoId);
                resultado = PedidoDAO.cadastrarPedido(produtoNovo);
            }
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

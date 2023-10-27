package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidad.Cliente;
import entidad.Cuenta;
import entidad.Movimiento;
import entidad.Prestamo;
import entidad.Prestamo.Estado;
import entidad.TipoMovimiento;
import negocio.CuentaNegocio;
import negocio.MovimientoNegocio;
import negocio.PrestamoNegocio;

/**
 * Servlet implementation class ServletPrestamo
 */
@WebServlet("/ServletPrestamo")
public class ServletPrestamo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CuentaNegocio cuentaNegocio = new CuentaNegocio();
    
    public ServletPrestamo() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("pedirPrestamo") != null) {

		    HttpSession session = request.getSession();
		    
		    session.setAttribute("cuentaPrestamo", null);

		    int numeroCuenta = Integer.parseInt(request.getParameter("pedirPrestamo"));            
		    Cuenta cuenta = cuentaNegocio.ObtenerPorNumeroCuenta(numeroCuenta);


		    session.setAttribute("cuentaPrestamo", cuenta);

		    response.sendRedirect("PedirPrestamo.jsp");
		}
		if(request.getParameter("lista")!= null) {
			PrestamoNegocio prestamoNegocio = new PrestamoNegocio();
			ArrayList<Prestamo> listaPrestamos = prestamoNegocio.ListarPendientes();
			
			request.setAttribute("listaPrestamo", listaPrestamos);
			RequestDispatcher dispatcher = request.getRequestDispatcher("ListarPrestamosAdmin.jsp");
	        dispatcher.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    if (request.getParameter("btnPedirPrestamo") != null) {
	        HttpSession session = request.getSession();
	        Cuenta cuenta = (Cuenta) session.getAttribute("cuentaPrestamo");
	        Cliente cliente = (Cliente)session.getAttribute("cliente");
	        
	        Prestamo prestamo = new Prestamo();
	        
	        prestamo.setNumeroCuenta(cuenta.getNumero());
	        
	        prestamo.setIdCliente(cliente.getIdCliente());
	        
	        int cuotas = Integer.parseInt(request.getParameter("cuotas"));
	        prestamo.setCuotas(cuotas);
	        
	        double importe = Double.parseDouble(request.getParameter("importe"));
	        prestamo.setImportePedido(importe);
	        
	        PrestamoNegocio prestamoNegocio = new PrestamoNegocio();
	        
	        boolean exito = prestamoNegocio.PedirPrestamo(prestamo);
	        
	        if(exito) {
	        	session.setAttribute("cuentaPrestamo", null);
	        	response.sendRedirect("ListadoCuentasDeCliente.jsp");
	        }        
	    }
	    if(request.getParameter("btnAprobar")!=null) {
	    	PrestamoNegocio prestamoNegocio = new PrestamoNegocio();
	    	int idPrestamo = Integer.parseInt(request.getParameter("idPrestamo"));
	    	
	    	prestamoNegocio.CambiarEstadoPrestamo(idPrestamo, Estado.APROBADO);
	    	
	    	Prestamo prestamo = prestamoNegocio.ObtenerPrestamoPorId(idPrestamo);
	    	
	    	Movimiento movimiento = new Movimiento();
	    	movimiento.setDetalle("Suma por préstamo aprobado");
	    	movimiento.setIdTipoMovimiento(new TipoMovimiento(2));
	    	movimiento.setImporte(prestamo.getImportePedido());
	    	movimiento.setNumeroCuenta(prestamo.getNumeroCuenta());
	    	
	    	MovimientoNegocio movimientoNegocio = new MovimientoNegocio();
	    	movimientoNegocio.Agregar(movimiento);
	    	
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("Inicio.jsp");
	        dispatcher.forward(request, response);
	    }
	}


}

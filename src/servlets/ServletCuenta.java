package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cuenta;
import entidad.TipoCuenta;
import negocio.CuentaNegocio;

@WebServlet("/ServletCuenta")
public class ServletCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CuentaNegocio cuentaNegocio = new CuentaNegocio();

    public ServletCuenta() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("AgregarId") != null) {

	        int idCliente = Integer.parseInt(request.getParameter("AgregarId"));

	        request.setAttribute("idCliente", idCliente);

	        ArrayList<TipoCuenta> tiposCuenta = cuentaNegocio.ListarTipoCuenta();

	        request.setAttribute("tiposCuenta", tiposCuenta);

	        RequestDispatcher dispatcher = request.getRequestDispatcher("AltaCuenta.jsp");
	        dispatcher.forward(request, response);
	    }
		if (request.getParameter("lista") != null) {
			
			ArrayList<Cuenta> Cuentas = cuentaNegocio.ListarCuentasActivas();
			request.setAttribute("listaCuentas", Cuentas);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("ListadoCuentas.jsp");
	        dispatcher.forward(request, response);
		}
		if(request.getParameter("idCliente") != null) {
			
			ArrayList<Cuenta> cuentas = cuentaNegocio.ListarPorIdCliente(Integer.parseInt(request.getParameter("idCliente")));
			request.setAttribute("listaCuentas", cuentas);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("ListadoCuentas.jsp");
	        dispatcher.forward(request, response);
		}
		if (request.getParameter("EliminarNumeroCuenta") != null) {
		    int numeroCuentaAEliminar = Integer.parseInt(request.getParameter("EliminarNumeroCuenta"));
		    boolean exitoEliminacion = cuentaNegocio.EliminarCuenta(numeroCuentaAEliminar);

		    // Establecer un atributo en la solicitud para indicar si la eliminación fue exitosa
		    request.setAttribute("exitoEliminacion", exitoEliminacion);

		    ArrayList<Cuenta> Cuentas = cuentaNegocio.ListarCuentasActivas();
		    request.setAttribute("listaCuentas", Cuentas);

		    RequestDispatcher dispatcher = request.getRequestDispatcher("ListadoCuentas.jsp");
		    dispatcher.forward(request, response);
		}
		if (request.getParameter("ModificarNumero") != null) {

	        int numeroCuenta = Integer.parseInt(request.getParameter("ModificarNumero"));
	        
	        Cuenta cuenta = cuentaNegocio.ObtenerPorNumeroCuenta(numeroCuenta);

	        request.setAttribute("cuenta", cuenta);

	        ArrayList<TipoCuenta> tiposCuenta = cuentaNegocio.ListarTipoCuenta();

	        request.setAttribute("tiposCuenta", tiposCuenta);

	        RequestDispatcher dispatcher = request.getRequestDispatcher("ModificarCuenta.jsp");
	        dispatcher.forward(request, response);
	    }

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    if (request.getParameter("btnAgregar") != null) {
	        // Obtener los datos del formulario
	        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
	        int numero = Integer.parseInt(request.getParameter("numero"));
	        String CBU = "";
	        int idTipoCuenta = Integer.parseInt(request.getParameter("idTipoCuenta"));

	        // Crear un objeto Cuenta con los datos
	        Cuenta cuenta = new Cuenta();
	        cuenta.setIdCliente(idCliente);
	        cuenta.setNumero(numero);
	        cuenta.setCBU(CBU);
	        
	        // Crear un objeto TipoCuenta con el id seleccionado
	        TipoCuenta tipoCuenta = new TipoCuenta();
	        tipoCuenta.setIdTipoCuenta(idTipoCuenta);
	        cuenta.setTipoCuenta(tipoCuenta);

	        // Llamar al método Agregar del negocio y verificar si fue exitoso
	        CuentaNegocio cuentaNegocio = new CuentaNegocio();
	        boolean exito = cuentaNegocio.Agregar(cuenta);

	        if (exito) {
	            // Redirigir a Inicio.jsp si fue exitoso
	            response.sendRedirect("ServletCuenta?lista=1");
	        } else {
	            // Redirigir a error.jsp si no fue exitoso
	            response.sendRedirect("error.jsp");
	        }
	    }
	    if (request.getParameter("btnModificar") != null) {
	    		int idCliente = Integer.parseInt(request.getParameter("idCliente"));
		        int numero = Integer.parseInt(request.getParameter("numero"));
		        String CBU = request.getParameter("CBU");
		        int idTipoCuenta = Integer.parseInt(request.getParameter("idTipoCuenta"));
		        double saldo = Double.parseDouble(request.getParameter("saldo"));

		        // Crear un objeto Cuenta con los datos
		        Cuenta cuenta = new Cuenta();
		        cuenta.setIdCliente(idCliente);
		        cuenta.setNumero(numero);
		        cuenta.setCBU(CBU);
		        cuenta.setSaldo(saldo);
		        cuenta.setActivo(1);
		        
		        // Crear un objeto TipoCuenta con el id seleccionado
		        TipoCuenta tipoCuenta = new TipoCuenta();
		        tipoCuenta.setIdTipoCuenta(idTipoCuenta);
		        cuenta.setTipoCuenta(tipoCuenta);

		        // Llamar al método Agregar del negocio y verificar si fue exitoso
		        cuentaNegocio = new CuentaNegocio();
		        boolean exito = cuentaNegocio.ModificarCuenta(cuenta);

		        if (exito) {
		            // Redirigir a Inicio.jsp si fue exitoso
		            response.sendRedirect("ServletCuenta?lista=1");
		        } else {
		            // Redirigir a error.jsp si no fue exitoso
		            response.sendRedirect("error.jsp");
		        }
	    }
	}


}

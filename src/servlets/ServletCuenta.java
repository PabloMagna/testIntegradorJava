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
import entidad.TipoCuenta;
import entidad.TipoMovimiento;
import negocio.CuentaNegocio;
import negocio.MovimientoNegocio;

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

		    RequestDispatcher dispatcher = request.getRequestDispatcher("Inicio.jsp");
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
		//CLIENTESSSSSSSSS
		if (request.getParameter("listaPorId") != null) {
		    // Obtener el cliente de la sesión
		    Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
		    
		    if (cliente != null) {
		        int idCliente = cliente.getIdCliente();
		        
		        // Usar el CuentaNegocio para listar las cuentas del cliente por su ID
		        ArrayList<Cuenta> cuentas = cuentaNegocio.ListarPorIdCliente(idCliente);
		        
		        // Guardar la lista de cuentas en el atributo "listaCuentas" para enviarla a la vista
		        request.setAttribute("listaCuentas", cuentas);
		        
		        // Redirigir a la página de listado de cuentas
		        RequestDispatcher dispatcher = request.getRequestDispatcher("ListadoCuentasDeCliente.jsp");
		        dispatcher.forward(request, response);
		    } else {
		        response.sendRedirect("error.jsp");
		    }
		}
		if (request.getParameter("historial") != null) {
			int numeroCuenta = Integer.parseInt(request.getParameter("historial"));
			MovimientoNegocio movimientoNegocio = new MovimientoNegocio();
			
			ArrayList<Movimiento> movimientos = movimientoNegocio.ListarPorNumeroCuenta(numeroCuenta);
			request.setAttribute("movimientos", movimientos);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("Historial.jsp");
	        dispatcher.forward(request, response);
		}
		if (request.getParameter("transferencia") != null) {
		    int numeroCuenta = Integer.parseInt(request.getParameter("transferencia"));
		    Cuenta cuentaOrigen = cuentaNegocio.ObtenerPorNumeroCuenta(numeroCuenta);

		    // Obtiene la sesión actual o crea una nueva si no existe
		    HttpSession session = request.getSession();

		    // Remueve el atributo "cuentaOrigen" de la sesión si existe
		    session.removeAttribute("cuentaOrigen");

		    // Guarda el objeto cuentaOrigen en la sesión
		    session.setAttribute("cuentaOrigen", cuentaOrigen);

		    // Luego, puedes redirigir a la página de transferencia
		    response.sendRedirect("Transferencia.jsp");
		}

		


		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("btnAgregar") != null) {
		    // Obtener los datos del formulario
		    int idCliente = Integer.parseInt(request.getParameter("idCliente"));
		    int idTipoCuenta = Integer.parseInt(request.getParameter("idTipoCuenta"));

		    // Crear un objeto Cuenta con los datos
		    Cuenta cuenta = new Cuenta();
		    cuenta.setIdCliente(idCliente);
		 // Crear un objeto TipoCuenta con el id seleccionado
	        TipoCuenta tipoCuenta = new TipoCuenta();
	        tipoCuenta.setIdTipoCuenta(idTipoCuenta);
	        cuenta.setTipoCuenta(tipoCuenta);

		    // Llamar al método Agregar del negocio y verificar si fue exitoso
		    CuentaNegocio cuentaNegocio = new CuentaNegocio();
		    int numeroCuenta = cuentaNegocio.Agregar(cuenta);

		    if (numeroCuenta > 0) {
		        Movimiento movimiento = new Movimiento();

		        movimiento.setNumeroCuenta(numeroCuenta);
		        movimiento.setImporte(10000); // Establece el importe en 10000
		        movimiento.setIdTipoMovimiento(new TipoMovimiento(1)); // Establece el tipo en 1
		        movimiento.setDetalle("Alta de cuenta");

		        // Llama al método Agregar del negocio de movimiento y verifica si fue exitoso
		        MovimientoNegocio movimientoNegocio = new MovimientoNegocio();
		        boolean exitoMovimiento = movimientoNegocio.Agregar(movimiento);

		        if (exitoMovimiento) {
		            // Redirigir a Inicio.jsp si ambos fueron exitosos
		            response.sendRedirect("ServletCuenta?lista=1");
		        } else {
		            // Maneja el caso en que la inserción de movimiento falló
		            response.sendRedirect("error.jsp");
		        }
		    } else {
		        // Redirigir a error.jsp si la inserción de cuenta falló
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
	  	
	  	    if (request.getParameter("btnTransferir") != null) {
	  	        // Obtener el importe y la cuenta de origen de la solicitud
	  	        double importe = Double.parseDouble(request.getParameter("importe"));
	  	        Cuenta cuentaOrigen = (Cuenta) request.getSession().getAttribute("cuentaOrigen");
	  	        
	  	        // Obtener el CBU de la cuenta destino desde la solicitud
	  	        String cbuDestino = request.getParameter("cbu");

	  	        // Llamar al método para obtener la cuenta de destino por CBU
	  	        Cuenta cuentaDestino = cuentaNegocio.ObtenerPorCbu(cbuDestino);
	  	        
	  	        // Verificar si se encontró la cuenta destino
	  	        if (cuentaDestino == null) {
	  	            // Mostrar un mensaje de error
	  	            request.setAttribute("errorMensaje", "El CBU de destino no se encontró.");
	  	            RequestDispatcher dispatcher = request.getRequestDispatcher("Transferencia.jsp");
	  	            dispatcher.forward(request, response);
	  	            return; // Detener la ejecución
	  	        }
	  	        
	  	        if (cuentaOrigen.getNumero() == cuentaDestino.getNumero()) {
	  	        	// Mostrar un mensaje de error
	  	        	request.setAttribute("errorMensaje", "No se puede transferir a la misma cuenta.");
	  	        	RequestDispatcher dispatcher = request.getRequestDispatcher("Transferencia.jsp");
	  	        	dispatcher.forward(request, response);
	  	        	return; // Detener la ejecución
	  	        }
	  	        

	  	        // Verificar si hay fondos suficientes para la transferencia
	  	        if (cuentaOrigen.getSaldo() < importe) {
	  	            // Mostrar un mensaje de error
	  	            request.setAttribute("errorMensaje", "Saldo insuficiente para realizar la transferencia.");
	  	            RequestDispatcher dispatcher = request.getRequestDispatcher("Transferencia.jsp");
	  	            dispatcher.forward(request, response);
	  	            return; // Detener la ejecución
	  	        }


	  	        // Deduct the transfer amount from the account of origin
	  	        cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - importe);

	  	        // Add the transfer amount to the destination account
	  	        cuentaDestino.setSaldo(cuentaDestino.getSaldo() + importe);
	  	        
	  	        cuentaNegocio.ModificarCuenta(cuentaOrigen);
	  	        cuentaNegocio.ModificarCuenta(cuentaDestino);

	  	        // Create a movement for the origin account (Resta por transferencia)
	  	        Movimiento movimientoOrigen = new Movimiento();
	  	        movimientoOrigen.setNumeroCuenta(cuentaOrigen.getNumero());
	  	        movimientoOrigen.setImporte(-importe); // Importe negativo
	  	        movimientoOrigen.setIdTipoMovimiento(new TipoMovimiento(1)); // Establece el tipo
	  	        movimientoOrigen.setDetalle("Resta por transferencia");

	  	        // Create a movement for the destination account (Suma por transferencia)
	  	        Movimiento movimientoDestino = new Movimiento();
	  	        movimientoDestino.setNumeroCuenta(cuentaDestino.getNumero());
	  	        movimientoDestino.setImporte(importe);
	  	        movimientoDestino.setIdTipoMovimiento(new TipoMovimiento(1));
	  	        movimientoDestino.setDetalle("Suma por transferencia");

	  	        // Llama a los métodos para agregar estos movimientos en la base de datos
	  	        MovimientoNegocio movimientoNegocio = new MovimientoNegocio();
	  	        movimientoNegocio.Agregar(movimientoOrigen);
	  	        movimientoNegocio.Agregar(movimientoDestino);
	  	        
	  	        // Después de la transferencia exitosa
	  	        request.setAttribute("errorMensaje", "Transferencia exitosa.");
	  	        RequestDispatcher dispatcher = request.getRequestDispatcher("Transferencia.jsp");
	  	        dispatcher.forward(request, response);
	  	    }
	}


}

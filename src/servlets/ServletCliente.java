package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import entidad.Cliente;
import entidad.DatosCliente;
import entidad.PruebaFecha;
import negocio.ClienteNegocio;
import negocio.DatosClienteNegocio;
import negocio.PruebaFechaNegocio;

import java.sql.Date;


/**
 * Servlet implementation class ServletCliente
 */
@WebServlet("/ServletCliente")
public class ServletCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession sesion;

    public ServletCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ClienteNegocio clienteNegocio = new ClienteNegocio();
		if(request.getParameter("lista")!=null) {
			ArrayList<Cliente> lista = clienteNegocio.ListarClientesActivos();
			request.setAttribute("lista", lista);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("ListadoClientes.jsp");
	        dispatcher.forward(request, response);
		}
		if (request.getParameter("DetalleId") != null) {
	        // Obtener el ID del cliente para ver detalles
	        int detalleId = Integer.parseInt(request.getParameter("DetalleId"));
	        DatosClienteNegocio datosNegocio = new DatosClienteNegocio();

	        // Obtener los datos del cliente a partir de su ID
	        Cliente clienteDetalle = clienteNegocio.ObtenerPorIdCliente(detalleId);
	        DatosCliente datos = datosNegocio.BuscarPorIdCliente(detalleId);

	        if (clienteDetalle != null) {
	            // Establecer los atributos del cliente en el request para ver detalles (modo viewOnly)
	            request.setAttribute("clienteModificar", clienteDetalle);
	            request.setAttribute("datosCliente", datos);
	            request.setAttribute("viewOnly", true);
	        }

	        // Redirigir a la página de detalles del cliente
	        RequestDispatcher dispatcher = request.getRequestDispatcher("AltaCliente.jsp");
	        dispatcher.forward(request, response);
	    }
		if (request.getParameter("ModifId") != null) {
	        // Obtener el ID del cliente a modificar
	        int modifId = Integer.parseInt(request.getParameter("ModifId"));
	        DatosClienteNegocio datosNegocio = new DatosClienteNegocio();

	        // Obtener los datos del cliente a partir de su ID
	        Cliente clienteModificar = clienteNegocio.ObtenerPorIdCliente(modifId);
	        DatosCliente datos = datosNegocio.BuscarPorIdCliente(modifId);

	        if (clienteModificar != null) {
	            // Establecer los atributos del cliente en el request para precargar el formulario
	            request.setAttribute("clienteModificar", clienteModificar);
	            request.setAttribute("datosCliente", datos);
	        }

	        // Redirigir a la página de alta/modificación de cliente
	        RequestDispatcher dispatcher = request.getRequestDispatcher("AltaCliente.jsp");
	        dispatcher.forward(request, response);
	    }
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ClienteNegocio clienteNegocio = new ClienteNegocio();
		if(request.getParameter("btnLogin")!=null) {
		String usuario = request.getParameter("username");
		String contrasenia = request.getParameter("password");
		Cliente cliente =  clienteNegocio.Login(usuario, contrasenia);
		if(cliente != null) {
			sesion = request.getSession();
			sesion.setAttribute("cliente", cliente);			
			System.out.println("exito");
			response.sendRedirect("Inicio.jsp");
		}
		else {
			System.out.println("mal");
			response.sendRedirect("Inicio.jsp");
		}
		}
		if (request.getParameter("btnGuardar") != null) {
	        // Primero, agregamos el cliente
	        Cliente cliente = new Cliente();
	        cliente.setUsuario(request.getParameter("usuario"));
	        cliente.setContrasena(request.getParameter("contrasena"));
	        cliente.setActivo(request.getParameter("activo") != null ? 1 : 0); // Establecer activo basado en el checkbox

	        int clienteId = clienteNegocio.Agregar(cliente); // Agregar cliente y obtener el ID generado automáticamente

	        if (clienteId > 0) {
	            // El cliente se agregó correctamente y tenemos el ID generado automáticamente

	            // Luego, agregamos los datos del cliente
	            DatosCliente datosCliente = new DatosCliente();
	            datosCliente.setIdCliente(clienteId); // Asignar el ID del cliente a la relación PK-FK
	            datosCliente.setDni(Integer.parseInt(request.getParameter("dni")));
	            datosCliente.setCuil(request.getParameter("cuil"));
	            datosCliente.setNombre(request.getParameter("nombre"));
	            datosCliente.setApellido(request.getParameter("apellido"));
	            datosCliente.setSexo(request.getParameter("sexo"));
	            datosCliente.setNacionalidad(request.getParameter("nacionalidad"));

	            try {
	                datosCliente.setFechaNacimientoFromString(request.getParameter("fechaNacimiento"));
	            } catch (ParseException e) {
	                // Manejar la excepción aquí (por ejemplo, redirigir a una página de error)
	                response.sendRedirect("Error.jsp");
	                return;
	            }

	            datosCliente.setDireccion(request.getParameter("direccion"));
	            datosCliente.setLocalidad(request.getParameter("localidad"));
	            datosCliente.setProvincia(request.getParameter("provincia"));
	            datosCliente.setCorreo(request.getParameter("correo"));
	            datosCliente.setTelefono(request.getParameter("telefono"));

	            DatosClienteNegocio datosClienteNegocio = new DatosClienteNegocio();
	            datosClienteNegocio.Agregar(datosCliente);

	            // Redirige a una página de éxito o realiza alguna otra acción después de agregar los datos
	            response.sendRedirect("Inicio.jsp");
	        } else {
	            // Manejar el caso en el que la inserción del cliente falló
	            response.sendRedirect("Error.jsp");
	        }
	    }
		if (request.getParameter("btnModificar") != null) {
		    // Obtén los valores del formulario
		    int clienteId = Integer.parseInt(request.getParameter("idCliente"));
		    String usuario = request.getParameter("usuario");
		    String contrasena = request.getParameter("contrasena");
		    // Otros campos del formulario

		    // Crea un objeto Cliente con los nuevos valores
		    Cliente clienteModificado = new Cliente();
		    clienteModificado.setIdCliente(clienteId);
		    clienteModificado.setUsuario(usuario);
		    clienteModificado.setContrasena(contrasena);
		    // Setear otros campos del cliente

		    // Crea un objeto DatosCliente con los nuevos valores
		    DatosCliente datosCliente = new DatosCliente();
		    datosCliente.setIdCliente(clienteId);
            datosCliente.setDni(Integer.parseInt(request.getParameter("dni")));
            datosCliente.setCuil(request.getParameter("cuil"));
            datosCliente.setNombre(request.getParameter("nombre"));
            datosCliente.setApellido(request.getParameter("apellido"));
            datosCliente.setSexo(request.getParameter("sexo"));
            datosCliente.setNacionalidad(request.getParameter("nacionalidad"));

            try {
                datosCliente.setFechaNacimientoFromString(request.getParameter("fechaNacimiento"));
            } catch (ParseException e) {
                // Manejar la excepción aquí (por ejemplo, redirigir a una página de error)
                response.sendRedirect("Error.jsp");
                return;
            }

            datosCliente.setDireccion(request.getParameter("direccion"));
            datosCliente.setLocalidad(request.getParameter("localidad"));
            datosCliente.setProvincia(request.getParameter("provincia"));
            datosCliente.setCorreo(request.getParameter("correo"));
            datosCliente.setTelefono(request.getParameter("telefono"));


		    // Llama a la función de negocio para modificar el cliente
		    DatosClienteNegocio datosClienteNegocio = new DatosClienteNegocio();

		    boolean exitoCliente = clienteNegocio.ModificarCliente(clienteModificado);
		    boolean exitoDatosCliente = datosClienteNegocio.ModificarDatosCliente(datosCliente);

		    if (exitoCliente && exitoDatosCliente) {
		        // La modificación se realizó con éxito, puedes redirigir a una página de éxito
		        response.sendRedirect("ServletCliente?lista=1");
		    } else {
		        // La modificación falló, puedes redirigir a una página de error o realizar alguna otra acción
		        response.sendRedirect("Error.jsp");
		    }
		}
		if (request.getParameter("ElimId") != null) {
	        int clienteId = Integer.parseInt(request.getParameter("ElimId"));

	        // Realiza la eliminación lógica del cliente
	        boolean filasEliminadas = clienteNegocio.EliminarCliente(clienteId);

	        // Verifica si la eliminación fue exitosa
	        if (filasEliminadas) {
	            // Redirige a la página de listado de clientes actualizada
	            response.sendRedirect("ServletCliente?lista=1");
	        } else {
	            // Maneja el caso en que la eliminación falló
	            response.sendRedirect("Error.jsp");
	        }
	    }
		

	}

}

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
import java.time.LocalDate;
import java.util.ArrayList;

import entidad.Cliente;
import entidad.DatosCliente;
import entidad.Localidad;
import entidad.Provincia;
import entidad.PruebaFecha;
import entidad.TipoCuenta;
import negocio.ClienteNegocio;
import negocio.DatosClienteNegocio;
import negocio.LocalidadNegocio;
import negocio.ProvinciaNegocio;
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
		if(request.getParameter("alta")!=null) {
			CargarDescolgables(request,response);
			RequestDispatcher dispatcher = request.getRequestDispatcher("AltaCliente.jsp");
	        dispatcher.forward(request, response);
		}
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

	        if (clienteDetalle != null) {
	            // Establecer los atributos del cliente en el request para ver detalles (modo viewOnly)
	            request.setAttribute("clienteModificar", clienteDetalle);
	            request.setAttribute("viewOnly", true);
	        }

	        // Redirigir a la página de detalles del cliente
	        RequestDispatcher dispatcher = request.getRequestDispatcher("AltaCliente.jsp");
	        dispatcher.forward(request, response);
	    }
		if (request.getParameter("ModifId") != null) {
		    
		    int modifId = Integer.parseInt(request.getParameter("ModifId"));
		    clienteNegocio = new ClienteNegocio();
		    
		    CargarDescolgables(request,response);

		    // Obtener los datos del cliente a partir de su ID
		    Cliente clienteModificar = clienteNegocio.ObtenerPorIdCliente(modifId);

		    if (clienteModificar != null) {
		        // Establecer los atributos del cliente en el request para precargar el formulario
		        request.setAttribute("clienteModificar", clienteModificar);

		        // Redirigir a la página de modificación de cliente
		        RequestDispatcher dispatcher = request.getRequestDispatcher("AltaCliente.jsp");
		        dispatcher.forward(request, response);
		    }
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
		if (request.getParameter("btnCerrarSesion") != null) {
		    HttpSession sesion = request.getSession(false); 
		    if (sesion != null) {
		        sesion.invalidate();
		    }

		    response.sendRedirect("Inicio.jsp");
		}

		if (request.getParameter("btnGuardar") != null) {
            Cliente cliente = new Cliente();

            cliente.setUsuario(request.getParameter("usuario"));
            cliente.setContrasena(request.getParameter("contrasena"));
            cliente.setDni(Integer.parseInt(request.getParameter("dni")));
            cliente.setCuil(request.getParameter("cuil"));
            cliente.setNombre(request.getParameter("nombre"));
            cliente.setApellido(request.getParameter("apellido"));
            cliente.setSexo(Integer.parseInt(request.getParameter("sexo")));
            cliente.setNacionalidad(request.getParameter("nacionalidad"));
            
            // Ajusta la fecha de nacimiento según tu formato
            LocalDate fechaCreacion = LocalDate.now();
            LocalDate fechaNacimiento = LocalDate.parse(request.getParameter("fechaNacimiento"));
            cliente.setFechaNacimiento(fechaNacimiento);
            cliente.setFechaCreacion(fechaCreacion);
            
            cliente.setDireccion(request.getParameter("direccion"));
            
            // Añade la lógica para configurar los objetos Localidad y Provincia
            Localidad localidad = new Localidad();
            localidad.setId(Integer.parseInt(request.getParameter("localidad")));
            localidad.setIdProvincia(Integer.parseInt(request.getParameter("provincia")));
            cliente.setLocalidad(localidad);
            
            Provincia provincia = new Provincia();
            provincia.setId(Integer.parseInt(request.getParameter("provincia"))); // Ajusta según tus necesidades
            cliente.setProvincia(provincia);
            
            cliente.setCorreo(request.getParameter("correo"));

            // Llama al método Agregar de ClienteNegocio para insertar el cliente
            int idCliente = clienteNegocio.Agregar(cliente);

            if (idCliente > 0) {
                // Redirige a la página de éxito
                response.sendRedirect("ServletCliente?lista=1.jsp");
            } else {
                // Maneja el caso en que la inserción falló
                response.sendRedirect("Error.jsp");
            }
        }
		if (request.getParameter("btnModificar") != null) {
		    // Obtén el ID del cliente a modificar
		    int clienteId = Integer.parseInt(request.getParameter("idCliente"));

		    // Obtén el cliente existente
		    clienteNegocio = new ClienteNegocio();
		    Cliente clienteExistente = clienteNegocio.ObtenerPorIdCliente(clienteId);

		    if (clienteExistente != null) {
		        // Obtén los valores del formulario
		        String usuario = request.getParameter("usuario");
		        LocalDate fechaCreacion = clienteExistente.getFechaCreacion(); // Mantener la fecha de creación existente
		        int idTipo = clienteExistente.getTipoCliente().ordinal(); // Mantener el ID de tipo existente

		        // Crear un nuevo objeto Cliente con los nuevos valores
		        Cliente clienteModificado = new Cliente();
		        clienteModificado.setIdCliente(clienteId);
		        clienteModificado.setUsuario(usuario);
		        clienteModificado.setFechaCreacion(fechaCreacion);
		        clienteModificado.setTipoCliente(idTipo);

		        // Setear otros campos del cliente desde el formulario
		        // Por ejemplo:
		        clienteModificado.setContrasena(request.getParameter("contrasena"));
		        clienteModificado.setDni(Integer.parseInt(request.getParameter("dni")));
		        clienteModificado.setCuil(request.getParameter("cuil"));
		        // Setear otros campos según corresponda

		        // Llama al método ModificarCliente de ClienteNegocio para actualizar el cliente
		        boolean exitoCliente = clienteNegocio.ModificarCliente(clienteModificado);

		        if (exitoCliente) {
		            // La modificación se realizó con éxito, puedes redirigir a una página de éxito
		            response.sendRedirect("ServletCliente?lista=1");
		        } else {
		            // La modificación falló, puedes redirigir a una página de error o realizar alguna otra acción
		            response.sendRedirect("Error.jsp");
		        }
		    } else {
		        // Maneja el caso en que no se pudo obtener el cliente existente
		        response.sendRedirect("Error.jsp");
		    }
		}

		if (request.getParameter("btnEliminar") != null) {
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
	private void CargarDescolgables(HttpServletRequest request, HttpServletResponse response) {
		ProvinciaNegocio provinciaNegocio = new ProvinciaNegocio();
		ArrayList<Provincia> provincias = provinciaNegocio.Listar();

        request.setAttribute("provincias", provincias);
        
        LocalidadNegocio localidadNegocio = new LocalidadNegocio();
		ArrayList<Localidad> localidades = localidadNegocio.Listar();

        request.setAttribute("localidades", localidades);
	}

}

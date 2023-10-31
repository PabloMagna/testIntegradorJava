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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import entidad.Cliente;
import entidad.DatosCliente;
import entidad.Localidad;
import entidad.Provincia;
import entidad.Telefono;
import entidad.TipoCuenta;
import negocio.ClienteNegocio;
import negocio.DatosClienteNegocio;
import negocio.LocalidadNegocio;
import negocio.ProvinciaNegocio;
import negocio.TelefonoNegocio;

import java.sql.Date;


/**
 * Servlet implementation class ServletCliente
 */
@WebServlet("/ServletCliente")
public class ServletCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession sesion;
	private TelefonoNegocio telefonoNegocio = new TelefonoNegocio();

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
			String buscar = null;
			ArrayList<Cliente> lista = clienteNegocio.ListarClientesActivos(buscar);
			request.setAttribute("lista", lista);
						
			ArrayList<Telefono> listaTelefonos = telefonoNegocio.ListarTelefonoPorIdCliente(0);//lista todos
			request.setAttribute("listaTelefonos", listaTelefonos);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("ListadoClientes.jsp");
	        dispatcher.forward(request, response);
	        
	        //valor filtros
	        request.setAttribute("operadorEdad", "mayor"); 
	        request.setAttribute("busqueda", "");
	        request.setAttribute("edad", 0);
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
		    Cliente cliente = clienteNegocio.ObtenerPorIdCliente(modifId);
		    ArrayList<Telefono> telefonos = telefonoNegocio.ListarTelefonoPorIdCliente(modifId);
		    request.setAttribute("telefonos", telefonos);

		    CargarDescolgables(request, response);

		    Cliente clienteModificar = clienteNegocio.ObtenerPorIdCliente(modifId);

		    if (clienteModificar != null) {
		        request.setAttribute("clienteModificar", clienteModificar);

		        LocalDate fechaNacimientoCliente = clienteModificar.getFechaNacimiento();
		        String fechaNacimientoString = fechaNacimientoCliente.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		        request.setAttribute("fechaNacimiento", fechaNacimientoString);

		        RequestDispatcher dispatcher = request.getRequestDispatcher("AltaCliente.jsp");
		        dispatcher.forward(request, response);
		    }
		}
		if (request.getParameter("btnBusqueda") != null) {
		    String busqueda = request.getParameter("busqueda");

		    ArrayList<Cliente> lista = clienteNegocio.ListarClientesActivos(busqueda);

		    request.setAttribute("lista", lista);
		    ArrayList<Telefono> listaTelefonos = telefonoNegocio.ListarTelefonoPorIdCliente(0);//lista todos
			request.setAttribute("listaTelefonos", listaTelefonos);

		    RequestDispatcher dispatcher = request.getRequestDispatcher("ListadoClientes.jsp");
		    dispatcher.forward(request, response);
		}
		if (request.getParameter("btnFiltrar") != null) {
	        String tipoFiltro = request.getParameter("operadorEdad");
	        int numeroFiltro = Integer.parseInt(request.getParameter("edad"));
	        String busqueda = request.getParameter("busqueda");

	        ArrayList<Cliente> lista = clienteNegocio.ListarClientesActivos(busqueda);
	        lista = clienteNegocio.filtrarLista(lista, tipoFiltro, numeroFiltro);
	        
		    ArrayList<Telefono> listaTelefonos = telefonoNegocio.ListarTelefonoPorIdCliente(0);//lista todos
			request.setAttribute("listaTelefonos", listaTelefonos);

	        request.setAttribute("lista", lista);
	        
	        request.setAttribute("operadorEdad",tipoFiltro);
	        request.setAttribute("busqueda", busqueda);
	        request.setAttribute("edad", numeroFiltro);

	        RequestDispatcher dispatcher = request.getRequestDispatcher("ListadoClientes.jsp");
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
            
            LocalDate fechaCreacion = LocalDate.now();
            LocalDate fechaNacimiento = LocalDate.parse(request.getParameter("fechaNacimiento"));
            cliente.setFechaNacimiento(fechaNacimiento);
            cliente.setFechaCreacion(fechaCreacion);         
            cliente.setDireccion(request.getParameter("direccion"));
            
            Localidad localidad = new Localidad();
            localidad.setId(Integer.parseInt(request.getParameter("localidad")));
            localidad.setIdProvincia(Integer.parseInt(request.getParameter("provincia")));
            cliente.setLocalidad(localidad);
            
            Provincia provincia = new Provincia();
            provincia.setId(Integer.parseInt(request.getParameter("provincia"))); // Ajusta según tus necesidades
            cliente.setProvincia(provincia);
            
            cliente.setCorreo(request.getParameter("correo"));

            int idCliente = clienteNegocio.Agregar(cliente);

            if (idCliente > 0) {
            	TelefonoNegocio telefonoNegocio = new TelefonoNegocio();
            	 String[] telefonos = request.getParameterValues("telefonos");
     		    if(telefonos!=null)
     		    	telefonoNegocio.AgregarTelefonos(cliente.getIdCliente(), telefonos);
     		    else{
     		    	telefonoNegocio.AgregarTelefonos(cliente.getIdCliente(), null);//solo los borra
     		    }
                response.sendRedirect("ServletCliente?lista=1.jsp");
            } else {
                response.sendRedirect("Error.jsp");
            }
        }
		if (request.getParameter("btnModificar") != null) {
		    int clienteId = Integer.parseInt(request.getParameter("idCliente"));

		    clienteNegocio = new ClienteNegocio();
		    TelefonoNegocio telefonoNegocio = new TelefonoNegocio();
		    Cliente clienteExistente = clienteNegocio.ObtenerPorIdCliente(clienteId);
		    String[] telefonos = request.getParameterValues("telefonos");
		    if(telefonos!=null)
		    	telefonoNegocio.AgregarTelefonos(clienteExistente.getIdCliente(), telefonos);
		    else{
		    	telefonoNegocio.AgregarTelefonos(clienteExistente.getIdCliente(), null);//solo los borra
		    }

		    if (clienteExistente != null) {
		        String usuario = request.getParameter("usuario");
		        LocalDate fechaCreacion = clienteExistente.getFechaCreacion(); // Mantener la fecha de creación existente
		        int idTipo = clienteExistente.getTipoCliente().ordinal(); // Mantener el ID de tipo existente

		        Cliente clienteModificado = new Cliente();
		        clienteModificado.setIdCliente(clienteId);
		        clienteModificado.setUsuario(request.getParameter("usuario"));
		        clienteModificado.setContrasena(request.getParameter("contrasena"));
		        clienteModificado.setDni(Integer.parseInt(request.getParameter("dni")));
		        clienteModificado.setCuil(request.getParameter("cuil"));
		        clienteModificado.setNombre(request.getParameter("nombre"));
		        clienteModificado.setApellido(request.getParameter("apellido"));
		        clienteModificado.setSexo(Integer.parseInt(request.getParameter("sexo")));
		        clienteModificado.setDireccion(request.getParameter("direccion"));
		        clienteModificado.setNacionalidad(request.getParameter("nacionalidad"));
		        clienteModificado.setActivo(1);
		        clienteModificado.setFechaCreacion(fechaCreacion);
		        clienteModificado.setCorreo(request.getParameter("correo"));
		        
	            LocalDate fechaNacimiento = LocalDate.parse(request.getParameter("fechaNacimiento"));
	            clienteModificado.setFechaNacimiento(fechaNacimiento);
	            
	            Localidad localidad = new Localidad();
	            localidad.setId(Integer.parseInt(request.getParameter("localidad")));
	            localidad.setIdProvincia(Integer.parseInt(request.getParameter("provincia")));
	            clienteModificado.setLocalidad(localidad);
	            
	            Provincia provincia = new Provincia();
	            provincia.setId(Integer.parseInt(request.getParameter("provincia"))); // Ajusta según tus necesidades
	            clienteModificado.setProvincia(provincia);
	            
		        boolean exitoCliente = clienteNegocio.ModificarCliente(clienteModificado);

		        if (exitoCliente) {
		            response.sendRedirect("ServletCliente?lista=1");
		        } else {
		            response.sendRedirect("Error.jsp");
		        }
		    } else {
		        response.sendRedirect("Error.jsp");
		    }
		}

		if (request.getParameter("btnEliminar") != null) {
	        int clienteId = Integer.parseInt(request.getParameter("ElimId"));
	        boolean filasEliminadas = clienteNegocio.EliminarCliente(clienteId);

	        if (filasEliminadas) {

	            response.sendRedirect("ServletCliente?lista=1");
	        } else {

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

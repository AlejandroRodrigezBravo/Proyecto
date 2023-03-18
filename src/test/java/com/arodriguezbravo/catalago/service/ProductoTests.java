//package com.arodriguezbravo.catalago.service;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertNull;
//import static org.junit.Assert.assertTrue;
//
//import java.util.Date;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import javax.servlet.http.HttpSession;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.test.annotation.Rollback;
//
//import com.arodriguezbravo.catalago.enums.RolNombre;
//import com.arodriguezbravo.catalago.model.entity.Cliente;
//import com.arodriguezbravo.catalago.model.entity.Producto;
//import com.arodriguezbravo.catalago.model.entity.Rol;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = Replace.NONE)
//@TestMethodOrder(OrderAnnotation.class)
//class ProductoTests {
//
//	@Autowired()
//	private IProductoService productoService;
//
//	@Autowired
//	private IClienteService usuarioService;
//
//	@Autowired
//	private PasswordEncoder passwordEncoder;
//
//	@Autowired
//	private IRolService rolService;
//
//	@MockBean
//	private HttpSession session;
//
//	Cliente usuario;
//
//	//Creo cliente antes de lanzar las pruebas para asignarle un producto
//	@BeforeEach
//	public void inicializar() {
//		usuario = new Cliente();
//		String passwordEncoded = passwordEncoder.encode("admin1");
//		usuario.setNombreUsuario("admin1");
//		usuario.setPassword(passwordEncoded);
//		usuario.setNombre("Alejandro");
//		usuario.setApellido("Rodríguez Bravo");
//		usuario.setDireccion("Calle Concepción Nª 107");
//		usuario.setTelefono("686177070");
//		usuario.setEmail("damAdmin@hotmail.com");
//		usuario.setCreateAt(new Date());
//		Rol rolAdmin = rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get();
//		Set<Rol> roles = new HashSet<>();
//		roles.add(rolAdmin);
//		usuario.setRoles(roles);
//		usuarioService.save(usuario);
//
//	}
//
//	@Test
//	@Rollback(false)
//	@Order(1)
//	void testGuardarProducto() {
//		Producto prueba = new Producto(3L, "Florero", 10.0, new Date(), "foto", "Decoración de salón", 1, usuario);
//
//		Producto productoGuardado = productoService.save(prueba);
//
//		assertNotNull(productoGuardado);
//	}
//
//	/*
//	 * @Test void testBuscarProductoId() { Producto pruebaBuscar = new Producto(7L,
//	 * "Jarrón", 10.0, new Date(), "foto", "Decoración de salón", 1, usuario);
//	 * Producto productoGuardado1 = productoService.save(pruebaBuscar);
//	 * productoGuardado1 = productoService.findOne(pruebaBuscar.getId());
//	 * 
//	 * assertThat(productoGuardado1.getId()).isEqualTo(pruebaBuscar.getId()); }
//	 * 
//	 * @Test void testBuscarProductoNombre() { String nombre = "Jarrón"; boolean
//	 * producto = productoService.existsByNombre(nombre);
//	 * assertThat(producto).isEqualTo(nombre); }
//	 */
//	 
//
//	@Test
//	@Order(2)
//	void testBuscarProductoNombre() {
//		//String nombre = "Salon";
//		String nombre = "Florero"; // variable a buscar en base de datos del nombre de producto.
//		Producto producto = productoService.findByNombre(nombre); //busca el nombre
//		assertThat(producto.getNombre()).isEqualTo(nombre); //si es el mismo que tenemos en base de datops devuelve true, en caso contrario false
//	}
//	
//	@Test
//	@Order(3)
//	void testBuscarProductoNombreNull() {
//		String nombre = "Jarrón"; //variable con nombre de producto que no existe en la base de datos
//		//String nombre = "Jarrón";
//		Producto producto = productoService.findByNombre(nombre);
//		assertNull(producto); //Como es null devuelve true
//	}
//	
//	@Test
//	@Rollback(false)
//	@Order(4)
//	void testActuralizarProducto() {
//		String nombre = "Florero Oferta"; //Nombre a actualizar
//		
//		Producto prueba = new Producto(3L, nombre, 10.0, new Date(), "foto", "Decoración de salón", 1, usuario);
//		prueba.setId(1L); //Id del producto actualizar, 10L es el número incrementado de mi base de datos para las pruebas
//		productoService.save(prueba);
//
//		Producto productoActualizado = productoService.findByNombre(nombre);
//		assertThat(productoActualizado.getNombre()).isEqualTo(nombre);
//	}
//
//	@Test
//	@Order(5)
//	void testListarProductos() {
//		List<Producto> productos = productoService.findAll();
//		
//		for(Producto p : productos) { //Foreach para mostrar lista de productos en BD de prueba
//			System.out.println(p);
//		}
//		assertThat(productos).size().isPositive(); //Si la lista es positiva devuelve true, en caso contrario false
//	}
//	
//	@Test
//	@Rollback(false)
//	@Order(6)
//	void testEliminarProducto() {
//		Long id = 3L;
//		
//		boolean esExitenteAntesDeEliminar = productoService.get(id).isPresent(); //variables para comprobar antes de eliminar
//		
//		productoService.delete(id); //Elimina el id que contiene la base de datos de prueba
//		
//		boolean noExitenteDespuesDeEliminar = productoService.get(id).isPresent(); //variables para comprobar después de eliminar
//		
//		assertTrue(esExitenteAntesDeEliminar); //verdadero si existe el id
//		
//		assertFalse(noExitenteDespuesDeEliminar); //verdadero ya que no existe el id
//	}
//}

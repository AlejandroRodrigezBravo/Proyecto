//package com.arodriguezbravo.catalago.service;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertTrue;
//
//import java.util.Date;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Optional;
//import java.util.Set;
//
//import javax.servlet.http.HttpSession;
//
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
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
//import com.arodriguezbravo.catalago.model.entity.Rol;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = Replace.NONE)
//@TestMethodOrder(OrderAnnotation.class)
//class ClienteTests {
//
//
//	@Autowired
//	private IClienteService clienteService;
//	
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
//	
//
//	
//	@Test
//	@Rollback(false)
//	@Order(1)
//	void testGuardarCliente() {
//		Cliente c = new Cliente();
//		String passwordEncoded = passwordEncoder.encode("admin1");
//		c.setNombreUsuario("test");
//		c.setPassword(passwordEncoded);
//		c.setNombre("test cliente");
//		c.setApellido("Rodríguez Bravo");
//		c.setDireccion("Calle Concepción Nª 107");
//		c.setTelefono("686177070");
//		c.setEmail("damAdmin@hotmail.com");
//		c.setCreateAt(new Date());
//		Rol rolAdmin = rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get();
//		Set<Rol> roles = new HashSet<>();
//		roles.add(rolAdmin);
//		c.setRoles(roles);
//		
//		Cliente productoGuardado = clienteService.save(c);
//
//		assertNotNull(productoGuardado);
//	}
//	
//	@Test
//	@Order(2)
//	void testBuscarNombreUsuario() {
//		//String nombre = "Salon";
//		String nombre = "admin"; // variable a buscar en base de datos del nombre de producto.
//		Optional<Cliente> cliente = clienteService.getByNombreUsuario(nombre); //busca el nombre
//		assertThat(cliente.get().getNombreUsuario()).isEqualTo(nombre); //si es el mismo que tenemos en base de datops devuelve true, en caso contrario false
//	}
//	
//	@Test
//	@Rollback(false)
//	@Order(3)
//	void testActuralizarCliente() {
//		String nombre = "prueba"; //Nombre a actualizar
//		String passwordEncoded = passwordEncoder.encode("admin1");
//		Cliente prueba = new Cliente();
//		prueba.setId(3L);
//		prueba.setNombreUsuario(nombre);
//		prueba.setPassword(passwordEncoded);
//		prueba.setNombre("test cliente");
//		prueba.setApellido("Rodríguez Bravo");
//		prueba.setDireccion("Calle Concepción Nª 107");
//		prueba.setTelefono("686177070");
//		prueba.setEmail("damAdmin@hotmail.com");
//		clienteService.update(prueba);
//		
//		Optional<Cliente> productoActualizado = clienteService.getByNombreUsuario(nombre);
//		assertThat(productoActualizado.get().getNombreUsuario()).isEqualTo(nombre);
//	}
//
//	@Test
//	@Order(4)
//	void testListarProductos() {
//		List<Cliente> clientes = clienteService.findAll();
//		
//		for(Cliente c : clientes) { //Foreach para mostrar lista de productos en BD de prueba
//			System.out.println(c);
//		}
//		assertThat(clientes).size().isPositive(); //Si la lista es positiva devuelve true, en caso contrario false
//	}
//	
//	@Test
//	@Rollback(false)
//	@Order(5)
//	void testEliminarCliente() {
//		Long id = 3L;
//		
//		boolean esExitenteAntesDeEliminar = clienteService.findOne(id).isPresent(); //variables para comprobar antes de eliminar
//		
//		clienteService.delete(id); //Elimina el id que contiene la base de datos de prueba
//		
//		boolean noExitenteDespuesDeEliminar =  clienteService.findOne(id).isPresent(); //variables para comprobar después de eliminar
//		
//		assertTrue(esExitenteAntesDeEliminar); //verdadero si existe el id
//		
//		assertFalse(noExitenteDespuesDeEliminar); //verdadero ya que no existe el id
//	}
//
//}

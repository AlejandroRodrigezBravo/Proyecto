//package com.arodriguezbravo.catalago.restcontroller;
//
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.Date;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import com.arodriguezbravo.catalago.model.entity.Producto;
//import com.arodriguezbravo.catalago.service.IClienteService;
//import com.arodriguezbravo.catalago.service.IFacturaItemsService;
//import com.arodriguezbravo.catalago.service.IFacturaService;
//import com.arodriguezbravo.catalago.service.IProductoService;
//import com.arodriguezbravo.catalago.service.IRolService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//
//@WebMvcTest(ProductoRestController.class)
//class ProductoRestControllerTests {
//
//	@Autowired
//	private MockMvc mockMvc;
//
//	@MockBean
//	private IProductoService productoService;
//
//	@MockBean
//	private IClienteService clienteS;
//	
//	@MockBean
//	private IRolService rolService;
//	
//	@MockBean
//	private IFacturaService factura;
//	
//	@MockBean
//	private IFacturaItemsService items;
//	
//	ObjectMapper objectMapper;
//	
//	@BeforeEach
//	void configurar() {
//		objectMapper = new ObjectMapper();
//	}
//
//	@Test
//	void testVerProductos() throws Exception {
//
//		when(productoService.findOne(1L)).thenReturn(crearProducto());
//
//		mockMvc.perform(get("/api/producto/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
//				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
//				.andExpect(jsonPath("$.nombre").value("Florero"))
//				.andExpect(jsonPath("$.descripcion").value("Decoración de salón"));
//
//		verify(productoService).findOne(1L);
//
//	}
//	
//	/*
//	 * @Test void testCrearProducto() throws Exception { Producto prueba = new
//	 * Producto(); prueba.setId(2L); prueba.setNombre("oferta");
//	 * prueba.setPrecio(12.0); prueba.setCreateAt(new Date());
//	 * prueba.setFoto("mario.jpg"); prueba.setDescripcion("Decoración");
//	 * prueba.setCantidad(1);
//	 * 
//	 * System.out.println(objectMapper.writeValueAsString(prueba));
//	 * 
//	 * Map<String, Object> respuesta = new HashMap<>(); respuesta.put("status",
//	 * "OK"); respuesta.put("mensaje", "Se ha creado con exito");
//	 * respuesta.put("prueba", prueba);
//	 * 
//	 * System.out.println(objectMapper.writeValueAsString(prueba));
//	 * 
//	 * 
//	 * mockMvc.perform(post("api/producto").contentType(MediaType.APPLICATION_JSON)
//	 * .content(objectMapper.writeValueAsString(prueba)))
//	 * .andExpect(status().isOk())
//	 * .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//	 * .andExpect(jsonPath("$.mensaje").value("Se ha creado con exito"))
//	 * .andExpect(jsonPath("$.prueba").value(prueba.getNombre()))
//	 * .andExpect(content().json(objectMapper.writeValueAsString(respuesta))); }
//	 */
//
//	public static Producto crearProducto() {
//		return new Producto(1L, "Florero", 10.0, new Date(), "foto", "Decoración de salón", 1);
//
//	}
//
//}

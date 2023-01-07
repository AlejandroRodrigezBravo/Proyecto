package com.arodriguezbravo.catalago.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;

import com.arodriguezbravo.catalago.model.entity.Factura;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class facturaTests {

	@Autowired
	private IFacturaService facturaSerivce;

	@MockBean
	private HttpSession session;

	@Test
	@Rollback(false)
	void testGuardarFactura() {
		Factura prueba = new Factura(1L, "000001", "factura de test", new Date(), new Date(), 10.0);

		Factura productoGuardado = facturaSerivce.save(prueba);

		assertNotNull(productoGuardado);
	}

	@Test
	void testBuscarFacturaId() {
		Factura pruebaBuscar = new Factura(1L, "000001", "factura de test", new Date(), new Date(), 10.0);
		Factura productoGuardado1 = facturaSerivce.save(pruebaBuscar);
		Long id = productoGuardado1.getId();
		facturaSerivce.findOne(id);

		assertThat(id).isEqualTo(pruebaBuscar.getId());
	}

	@Test
	void testListarFactura() {
		List<Factura> facturas = facturaSerivce.findAll();

		for (Factura f : facturas) { // Foreach para mostrar lista de productos en BD de prueba
			System.out.println(f);
		}
		assertThat(facturas).size().isPositive(); // Si la lista es positiva devuelve true, en caso contrario false
	}

}

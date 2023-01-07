package com.arodriguezbravo.catalago.service;

import static org.junit.Assert.assertNotNull;
import javax.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;

import com.arodriguezbravo.catalago.model.entity.ItemFactura;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class ItemsFacturaTests {

	@Autowired
	private IFacturaItemsService itemsService;

	@MockBean
	private HttpSession session;

	@Test
	@Rollback(false)
	void testGuardarItems() {
		ItemFactura prueba = new ItemFactura(12L, "Florero", 100, 10.0, 10.0);

		ItemFactura productoGuardado = itemsService.save(prueba);

		assertNotNull(productoGuardado);
	}
}

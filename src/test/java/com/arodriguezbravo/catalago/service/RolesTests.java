package com.arodriguezbravo.catalago.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;

import com.arodriguezbravo.catalago.enums.RolNombre;
import com.arodriguezbravo.catalago.model.entity.Rol;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class RolesTests {

	@Autowired
	private IRolService rolService;

	@MockBean
	private HttpSession session;



	@Test
	@Rollback(false)
	void testGuardarRol() {
		Rol rolAdmin = new Rol(3L,RolNombre.ROLE_TEST); //constructor para guardar el rol
		Rol prueba = rolService.save(rolAdmin);
		assertNotNull(prueba); //comprueba que el rol se guarda
	}
	
	@Test
	void testBuscarRolNombre() {
		Optional<Rol> rolAdmin = rolService.getByRolNombre(RolNombre.ROLE_ADMIN); //rol a buscar y asignarle el rol encontrado
		assertThat(rolAdmin.get().getRolNombre()).isEqualTo(RolNombre.ROLE_ADMIN); //si es el mismo que tenemos en base de datops devuelve true, en caso contrario false
	}

}

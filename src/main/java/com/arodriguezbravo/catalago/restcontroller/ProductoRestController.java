package com.arodriguezbravo.catalago.restcontroller;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arodriguezbravo.catalago.model.entity.Producto;
import com.arodriguezbravo.catalago.service.IProductoService;

@CrossOrigin
@RestController
@RequestMapping("/api/producto")
public class ProductoRestController {
	
	@Autowired
	private IProductoService productoService;
	
	@GetMapping("")
	public List<Producto> index() {
		return productoService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Producto producto = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			producto = productoService.findOne(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(producto == null) {
			response.put("mensaje", "El producto ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(producto, HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<Producto> post(@RequestBody @Valid Producto item, BindingResult result) {
		ResponseEntity<Producto> res;
		
		try {
			//Hay errores de validación
			//El método guardar también podría registrar errores
			if (result.hasErrors()) {
				res = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();				
			} else {
				//Resultado OK
				res = ResponseEntity.ok().body(productoService.save(item));
			}
		} catch (Exception ex) {
			
			res = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}		
		
		return res;
	}

	
	@PutMapping(value="/{id}")
	public ResponseEntity<Object> put( @PathVariable Long id, @RequestBody @Valid Producto item) {
		ResponseEntity<Object> res;
		
		try {
			Producto old = productoService.findOne(id);
			if (old == null) {
				//No se encuentra
				res = ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
			}  else {
				//Resultado OK
				res = ResponseEntity.ok().body(productoService.save(item));
			}
		} catch (AccessDeniedException ex) {
		
			res = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		} catch (Exception ex) {
		
			res = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}		
		
		return res;
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		ResponseEntity<Object> res;
		
		try {
			if (productoService.findOne(id) == null) {
				//No se encuentra
				res = ResponseEntity.noContent().build();
			} else {
				productoService.delete(id);
				res = ResponseEntity.ok().build();
			}
		} catch (AccessDeniedException ex) {
			res = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		} catch (Exception ex) {
			res = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}		
		
		return res;
	}
}

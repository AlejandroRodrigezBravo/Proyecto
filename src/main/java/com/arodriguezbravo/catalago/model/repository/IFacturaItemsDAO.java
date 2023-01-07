package com.arodriguezbravo.catalago.model.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.arodriguezbravo.catalago.model.entity.ItemFactura;
/**
 * Dao de items de factura
 * @author bravo
 * @version 01/05/2022 1.0.0
 */

@Repository
public interface IFacturaItemsDAO extends JpaRepository<ItemFactura, Long>{
	
	
}

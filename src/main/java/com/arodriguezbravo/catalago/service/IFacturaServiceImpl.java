package com.arodriguezbravo.catalago.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.arodriguezbravo.catalago.model.entity.Cliente;
import com.arodriguezbravo.catalago.model.entity.Factura;
import com.arodriguezbravo.catalago.model.entity.Producto;
import com.arodriguezbravo.catalago.model.repository.IFacturaDAO;
import com.lowagie.text.DocumentException;

/**
 * Implementación de  factura
 * @author bravo
 * @version 01/05/2022 1.0.0
 */
@Service
public class IFacturaServiceImpl  implements IFacturaService{

	@Autowired
	private IFacturaDAO facturaDao;
	
	@Autowired
    private JavaMailSender javaMailSender;
	
	@Override
	public List<Factura> findAll() {
		return  facturaDao.findAll();
	}

	@Override
	public Optional<Factura> findOne(Long id) {
		return facturaDao.findById(id);
	}

	@Override
	public Factura save(Factura factura) {
		return facturaDao.save(factura);
	}

	@Override
	public String generarNumero() {
		int numero=0;
		String numeroConcatenado="";
		
		List<Factura> ordenes = findAll();
		
		List<Integer> numeros= new ArrayList<>();
		
		ordenes.stream().forEach(o -> numeros.add(Integer.parseInt( o.getNumero())));
		
		if (ordenes.isEmpty()) {
			numero=1;
		}else {
			numero= numeros.stream().max(Integer::compare).get();
			numero++;
		}
		
		if (numero<10) { //0000001000
			numeroConcatenado="000000000"+String.valueOf(numero);
		}else if(numero<100) {
			numeroConcatenado="00000000"+String.valueOf(numero);
		}else if(numero<1000) {
			numeroConcatenado="0000000"+String.valueOf(numero);
		}else if(numero<10000) {
			numeroConcatenado="0000000"+String.valueOf(numero);
		}		
		
		return numeroConcatenado;
	}

	@Override
	public List<Factura> findByCliente(Cliente cliente) {
		return facturaDao.findByCliente(cliente);
	}

	@Override
	public void enviarFacturaPorCorreo(HttpServletResponse response, String destinatario) throws MessagingException, DocumentException, IOException {
		MimeMessage mensaje = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mensaje, true);
		helper.setTo("INTRODUCIR_EMAIL_DEL CLIENTE_@hotmail.com");//RECUPERAR EMAIL DE CLIENTE BBDD
		helper.setSubject("Tu cocina");
		helper.setText("Presupuesto cocina: ");//RECUPERAR NOMBRE DE CLIENTE BBDD
		
		PDDocument document = new PDDocument();
		PDPage page = new PDPage();
		document.addPage(page);
		
		PDPageContentStream contentStream = new PDPageContentStream(document, page);
		// Cargar la imagen del icono desde el archivo en resources
        PDImageXObject image = PDImageXObject.createFromFile("src/main/resources/static/images/tienda.jpg", document);

		// Datos de ejemplo para el cliente y el producto
        Cliente cliente = new Cliente(null,"PRUEBA", " PRUEBA", "PRUEBA@hotmail.com", "Avd. PRUEBA", null, null,null,null,null,null);
        Producto producto = new Producto(null,"Cocina",3000.5,null,null,null,1);

        // Dibujar la imagen en el PDF
        contentStream.drawImage(image, 0, 20, image.getWidth() / 2, image.getHeight() / 2);
        // Agregar contenido al PDF
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
        contentStream.beginText();
        float xStart = 50;
        float yStart = page.getMediaBox().getHeight() - 50; // Ajusta la altura de inicio según tus necesidades
        float lineHeight = 20; // Espacio entre líneas

        // Datos de la factura
        contentStream.newLineAtOffset(xStart, yStart);
        contentStream.showText("Factura");
        contentStream.newLineAtOffset(0, -lineHeight);
        //contentStream.endText();
        contentStream.showText("Cliente: " + cliente.getNombre().concat(cliente.getApellido()));
        contentStream.newLineAtOffset(0, -lineHeight);
        contentStream.showText("Dirección: " + cliente.getDireccion());
        contentStream.newLineAtOffset(0, -lineHeight);
        contentStream.showText("Email: " + cliente.getEmail());
        
        contentStream.newLineAtOffset(0, -2 * lineHeight); // Doble espaci
        contentStream.showText("Producto: " + producto.getNombre());
        contentStream.newLineAtOffset(0, -lineHeight);
        contentStream.showText("Cantidad: " + producto.getCantidad());
        contentStream.newLineAtOffset(0, -2 * lineHeight); // Doble espaci
        contentStream.showText("Total: $" + (producto.getPrecio() * producto.getCantidad()));
        contentStream.endText();

        contentStream.close();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		document.save(outputStream);
		byte[] pdfBytes = outputStream.toByteArray();
		
		helper.addAttachment("factura.pdf", new ByteArrayResource(pdfBytes));
		document.close();

		javaMailSender.send(mensaje);
		
	}

}

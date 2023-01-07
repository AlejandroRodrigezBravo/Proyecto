
package com.arodriguezbravo.catalago.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Implementación de files/imagenes
 * @author bravo
 * @version 01/05/2022 1.0.0
 */
@Service
public class UploadFileServiceImpl {
	
	/** variable de tipo string ruta de carpeta */
	private String folder = "uploads//";

	/**
	 * Ruta para guardar archivo
	 * @param file a guardar en ruta
	 * @return devuelve elemento encontrado, null en caso contrario
	 * @throws IOException excepcion de I/O
	 */
	public String saveImage(MultipartFile file) throws IOException {
		if (!file.isEmpty()) {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(folder + file.getOriginalFilename());
			Files.write(path, bytes);
			return file.getOriginalFilename();
		}
		return "default.jpg";
	}

	/**
	 * Elimina archivo encontrado
	 * @param nombre a eliminar
	 */
	public void deleteImage(String nombre) {
		String ruta = "uploads//";
		File file = new File(ruta + nombre);
		file.delete();
	}
}

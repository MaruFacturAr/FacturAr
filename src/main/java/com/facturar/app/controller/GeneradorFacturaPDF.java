package com.facturar.app.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.facturar.app.utils.PdfUtils;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.codec.Base64;

@RestController
@RequestMapping("/api/facturas")
public class GeneradorFacturaPDF {

	@PostMapping("/create-pdf")
	public ResponseEntity<?> generateFacturaPdf() {

		String nombrePDF = "facturaWordPro";
		String pathInputPdf = "";
		String response = "";
		
		System.out.println(System.getProperty("user.dir"));
		
		if (!nombrePDF.isEmpty() || !nombrePDF.equals("")) {
			pathInputPdf = "src/"+nombrePDF+".pdf";
		} else {
			String msgError = "Nombre del pdf vacio";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msgError);
		}

		try {

			HashMap<String, String> datosPDF = new HashMap<>();
			datosPDF.put("domicilio_comercial", "TOMAS ANGELERI");
			datosPDF.put("letra", "A");

			PDDocument documento = PDDocument.load(new File("C:\\Users\\Windows 10\\Documents\\GitHub\\FacturAr\\facturaWordPro.pdf"));
			documento.setAllSecurityToBeRemoved(true); // Para algunos pdf que tienen cosas encriptadas
			PdfUtils.completarPDF(documento, datosPDF, nombrePDF);

			ByteArrayOutputStream out = new ByteArrayOutputStream();

			documento.save(out);
			documento.close();

			out.toByteArray();

			PdfReader pdfReader = new PdfReader(out.toByteArray());

			ByteArrayOutputStream out2 = new ByteArrayOutputStream();
			PdfStamper pdfStamper = new PdfStamper(pdfReader, out2);

			pdfStamper.setEncryption("".getBytes(), "".getBytes(), PdfWriter.ALLOW_PRINTING,PdfWriter.ENCRYPTION_AES_128);

			pdfStamper.close();

			String encoded = Base64.encodeBytes(out2.toByteArray());
			response = new String(encoded);

		} catch (Exception e) {
			String msgError = "Ha ocurrido un error en el autocompletado de la factura";
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msgError);
		}

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}

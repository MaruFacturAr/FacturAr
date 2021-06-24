package com.facturar.app.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.facturar.app.entity.DatosFacturaRequest;
import com.facturar.app.utils.PdfUtils;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.codec.Base64;

@RestController
@RequestMapping("/api/facturas")
public class GeneradorFacturaPDF {

	@PostMapping("/create-pdf")
	public ResponseEntity<?> generateFacturaPdf(@RequestBody DatosFacturaRequest datosFactura) {

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
			//Cabezera de la factura
			datosPDF.put("tipo_factura", datosFactura.getTipoFactura());
			datosPDF.put("codigo_factura_letra", datosFactura.getCodigoFacturaLetra());
			//Datos factura
			datosPDF.put("razon_social", datosFactura.getRazonSocial());
			datosPDF.put("domicilio_comercial", datosFactura.getDomicilioComercial());
			datosPDF.put("condicion_iva", datosFactura.getCondicionIva());
			datosPDF.put("punto_venta", datosFactura.getPuntoVenta());
			datosPDF.put("comp_nro", datosFactura.getCompNro());
			datosPDF.put("fecha_emision", datosFactura.getFechaEmision());
			datosPDF.put("cuit", datosFactura.getCuit());
			datosPDF.put("ingresos_brutos", datosFactura.getIngresosBrutos());
			datosPDF.put("fecha_inicio_actividades", datosFactura.getFechaInicioActividades());
			//Fechas factura
			datosPDF.put("periodo_facturado_desde", datosFactura.getPeriodoFacturadoDesde());
			datosPDF.put("periodo_facturado_hasta", datosFactura.getPeriodoFacturadoHasta());
			datosPDF.put("fecha_vto_pago", datosFactura.getFechaVencimientoPago());
			//Datos cliente
			datosPDF.put("cuit", datosFactura.getCuit());
			datosPDF.put("condicion_iva", datosFactura.getCondicionIva());
			datosPDF.put("condicion_venta", datosFactura.getCondicionVenta());
			datosPDF.put("razon_social", datosFactura.getRazonSocial());
			datosPDF.put("domicilio_comercial", datosFactura.getDomicilioComercial());
			//Datos producto
			datosPDF.put("codigo_producto", datosFactura.getCodigoProduto());
			datosPDF.put("nombre_producto", datosFactura.getNombreProducto());
			datosPDF.put("cantidad_producto", datosFactura.getCantidadProducto());
			datosPDF.put("precio_unitario", datosFactura.getPrecioUnitario());
			datosPDF.put("subtotal_factura", datosFactura.getSubtotalFactura());
			//Datos Afip
			datosPDF.put("cae_nro", datosFactura.getCaeNro());
			datosPDF.put("fecha_vto_cae", datosFactura.getFechaVencimientoCae());
			//Subtotales factura
			datosPDF.put("subtotal_factura", datosFactura.getSubtotalFactura());
			datosPDF.put("importe_otros_tributos", datosFactura.getImporteOtrosAtributos());
			datosPDF.put("importe_total", datosFactura.getImporteTotal());

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
			//pdfStamper.setEncryption("".getBytes(), "".getBytes(), PdfWriter.ALLOW_PRINTING,PdfWriter.ENCRYPTION_AES_128);

			pdfStamper.close();

			String encoded = Base64.encodeBytes(out2.toByteArray());
			response = new String(encoded);

		} catch (Exception e) {
			String msgError = "Ha ocurrido un error en el autocompletado de la factura";
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msgError + e);
		}

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}

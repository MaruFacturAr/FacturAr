package com.facturar.app.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.facturar.app.entity.InvoiceEntity;
import com.facturar.app.entity.InvoiceItem;
import com.facturar.app.entity.ItemEntity;
import com.facturar.app.entity.TaxpayerType;
import com.facturar.app.service.ItemService;
import com.facturar.app.service.TaxpayerTypeService;
import com.facturar.app.utils.PdfUtils;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.codec.Base64;

@RestController
@RequestMapping("/api/facturas")
public class GeneradorFacturaPDF {
	
	@Autowired
	private TaxpayerTypeService taxpayerTypeService;
	
	@Autowired
    private ItemService itemService;

	@PostMapping("/create-pdf")
	public ResponseEntity<?> generateFacturaPdf(@RequestBody InvoiceEntity invoiceEntity) {

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
			datosPDF.put("tipo_factura",invoiceEntity.getLetter());
			datosPDF.put("codigo_factura_letra", invoiceEntity.getCounterfoil().getVoucher_type_id().toString());
			//Datos factura
			datosPDF.put("razon_social", invoiceEntity.getCompany().getBillingData().getLegal_name());
			datosPDF.put("domicilio_comercial", invoiceEntity.getCompany().getBillingData().getAddress().getStreet());
			datosPDF.put("condicion_iva", getCondicionIva(invoiceEntity.getCompany().getBillingData().getTaxpayer_type_id()));
			//datosPDF.put("punto_venta", invoiceEntity.getSale_point());
			datosPDF.put("comp_nro", invoiceEntity.getNumber().toString());
			datosPDF.put("fecha_emision", invoiceEntity.getEmision_date().toString());
			datosPDF.put("cuit", invoiceEntity.getCompany().getBillingData().getDocument().getNumber());
			datosPDF.put("ingresos_brutos", invoiceEntity.getCompany().getIibb_code());
			datosPDF.put("fecha_inicio_actividades", invoiceEntity.getCompany().getInitial_date().toString());
			//Fechas factura
			datosPDF.put("periodo_facturado_desde", conversorFecha(invoiceEntity.getService_date_from()));
			datosPDF.put("periodo_facturado_hasta", conversorFecha(invoiceEntity.getService_date_to()));
			datosPDF.put("fecha_vto_pago", conversorFecha(invoiceEntity.getExpiration_date()));
			//Datos cliente
			datosPDF.put("cuit", invoiceEntity.getCompany().getBillingData().getDocument().getNumber());
			datosPDF.put("condicion_iva", getCondicionIva(invoiceEntity.getCompany().getBillingData().getTaxpayer_type_id()));
			datosPDF.put("condicion_venta", "Contado");
			datosPDF.put("razon_social",  invoiceEntity.getCompany().getBillingData().getLegal_name());
			datosPDF.put("domicilio_comercial", invoiceEntity.getCompany().getBillingData().getAddress().getStreet());
			//Datos producto
			datosPDF.put("codigo_producto", invoiceEntity.getInvoiceItemList().get(0).getItem().getCode());
			datosPDF.put("nombre_producto", invoiceEntity.getInvoiceItemList().get(0).getItem().getName());
			datosPDF.put("cantidad_producto", invoiceEntity.getInvoiceItemList().get(0).getQuantity().toString());
			datosPDF.put("precio_unitario", getProducto(invoiceEntity.getInvoiceItemList().get(0).getItem_id()).getPrice().toString());
			datosPDF.put("subtotal_factura", getProducto(invoiceEntity.getInvoiceItemList().get(0).getItem_id()).getPrice().toString());
			//Datos Afip
			datosPDF.put("cae_nro", invoiceEntity.getCAE());
			datosPDF.put("fecha_vto_cae", conversorFecha(invoiceEntity.getCAE_expiration_date()));
			//Subtotales factura
			datosPDF.put("subtotal_factura",  invoiceEntity.getAmount_net().toString());
			datosPDF.put("importe_otros_tributos", invoiceEntity.getTotal_taxes().toString());
			datosPDF.put("importe_total",invoiceEntity.getTotal_amount().toString());

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
	
	private String getCondicionIva(Integer idTaxpayer) {

		List<TaxpayerType> condicionIvaList = (List<TaxpayerType>) taxpayerTypeService.findAll();
		for (TaxpayerType taxpayerType : condicionIvaList) {
			if(taxpayerType.getId().equals(idTaxpayer)) {
				return taxpayerType.getName();
			}
		}
		return "Condicion Iva No disponible";
	}
	
	
	private String conversorFecha(Date date) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String fechaFormat = sdf.format(date);
		
		return fechaFormat;
	}
	
	private ItemEntity getProducto(Integer id) {
		Long idCode = new Long(id);
		ItemEntity itemEntity = itemService.findById(idCode).get();
		return itemEntity;
	}
	
}

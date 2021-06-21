package com.facturar.app.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDCheckBox;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.pdmodel.interactive.form.PDTextField;

public class PdfUtils {

	public static void completarPDF(PDDocument documento, HashMap<String, String> datosPDF, String nombrePdf)
			throws Exception {

		String nombreCampoPDF = "";
		String valorAInsertar = "";
		PDField campoPDF = null;

		try {
			PDDocumentCatalog docCatalog = documento.getDocumentCatalog();
			PDAcroForm acroForm = docCatalog.getAcroForm();
			for (Map.Entry<String, String> datoRecibido : datosPDF.entrySet()) {
				nombreCampoPDF = datoRecibido.getKey();
				valorAInsertar = datoRecibido.getValue();
				if (valorAInsertar == null) {
					String msgError = "El dato" + nombreCampoPDF + "tiene valor null para insertar en el PDF "+ nombrePdf;
					throw new Exception(msgError);
				}
				campoPDF = acroForm.getField(nombreCampoPDF);
				if (campoPDF == null) {
					String msgError = "El dato" + nombreCampoPDF + "no tiene un mapeo existente para el PDF"+ nombrePdf;
					throw new Exception(msgError);
				}

				if (campoPDF instanceof PDTextField) {
					campoPDF.setValue(valorAInsertar);
				}

				// Por si el pdf cambia llega a tener algun CheckBox
				if (campoPDF instanceof PDCheckBox) {
					PDCheckBox campoPdfCheckbox = (PDCheckBox) campoPDF;
					if (Boolean.TRUE.toString().equals(valorAInsertar)) {
						campoPdfCheckbox.check();
					} else if (Boolean.FALSE.toString().equals(valorAInsertar)) {
						campoPdfCheckbox.unCheck();
					}
				}

			}

		} catch (Exception e) {
			String msgError = "Error al intentar autocompletar el PDF";
			throw new Exception(msgError);
		}

	}

}

package com.facturar.app.entity;

import java.io.Serializable;

public class DatosFacturaRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private String tipoFactura;
	private String codigoFacturaLetra;
	private String razonSocial;
	private String domicilioComercial;
	private String condicionIva;
	private String fechaEmision;
	private String cuit;
	private String ingresosBrutos;
	private String fechaInicioActividades;
	private String periodoFacturadoDesde;
	private String periodoFacturadoHasta;
	private String fechaVencimientoPago;
	private String condicionVenta;
	private String codigoProduto;
	private String nombreProducto;
	private String cantidadProducto;
	private String precioUnitario;
	private String subtotalFactura;
	private String caeNro;
	private String fechaVencimientoCae;
	private String subTotalFactura;
	private String importeOtrosAtributos;
	private String importeTotal;
	private String puntoVenta;
	private String compNro;

	public String getTipoFactura() {
		return tipoFactura;
	}

	public void setTipoFactura(String tipoFactura) {
		this.tipoFactura = tipoFactura;
	}

	public String getCodigoFacturaLetra() {
		return codigoFacturaLetra;
	}

	public void setCodigoFacturaLetra(String codigoFacturaLetra) {
		this.codigoFacturaLetra = codigoFacturaLetra;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getDomicilioComercial() {
		return domicilioComercial;
	}

	public void setDomicilioComercial(String domicilioComercial) {
		this.domicilioComercial = domicilioComercial;
	}

	public String getCondicionIva() {
		return condicionIva;
	}

	public void setCondicionIva(String condicionIva) {
		this.condicionIva = condicionIva;
	}

	public String getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getIngresosBrutos() {
		return ingresosBrutos;
	}

	public void setIngresosBrutos(String ingresosBrutos) {
		this.ingresosBrutos = ingresosBrutos;
	}

	public String getFechaInicioActividades() {
		return fechaInicioActividades;
	}

	public void setFechaInicioActividades(String fechaInicioActividades) {
		this.fechaInicioActividades = fechaInicioActividades;
	}

	public String getPeriodoFacturadoDesde() {
		return periodoFacturadoDesde;
	}

	public void setPeriodoFacturadoDesde(String periodoFacturadoDesde) {
		this.periodoFacturadoDesde = periodoFacturadoDesde;
	}

	public String getPeriodoFacturadoHasta() {
		return periodoFacturadoHasta;
	}

	public void setPeriodoFacturadoHasta(String periodoFacturadoHasta) {
		this.periodoFacturadoHasta = periodoFacturadoHasta;
	}

	public String getFechaVencimientoPago() {
		return fechaVencimientoPago;
	}

	public void setFechaVencimientoPago(String fechaVencimientoPago) {
		this.fechaVencimientoPago = fechaVencimientoPago;
	}

	public String getCondicionVenta() {
		return condicionVenta;
	}

	public void setCondicionVenta(String condicionVenta) {
		this.condicionVenta = condicionVenta;
	}

	public String getCodigoProduto() {
		return codigoProduto;
	}

	public void setCodigoProduto(String codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public String getCantidadProducto() {
		return cantidadProducto;
	}

	public void setCantidadProducto(String cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}

	public String getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(String precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public String getSubtotalFactura() {
		return subtotalFactura;
	}

	public void setSubtotalFactura(String subtotalFactura) {
		this.subtotalFactura = subtotalFactura;
	}

	public String getCaeNro() {
		return caeNro;
	}

	public void setCaeNro(String caeNro) {
		this.caeNro = caeNro;
	}

	public String getFechaVencimientoCae() {
		return fechaVencimientoCae;
	}

	public void setFechaVencimientoCae(String fechaVencimientoCae) {
		this.fechaVencimientoCae = fechaVencimientoCae;
	}

	public String getSubTotalFactura() {
		return subTotalFactura;
	}

	public void setSubTotalFactura(String subTotalFactura) {
		this.subTotalFactura = subTotalFactura;
	}

	public String getImporteOtrosAtributos() {
		return importeOtrosAtributos;
	}

	public void setImporteOtrosAtributos(String importeOtrosAtributos) {
		this.importeOtrosAtributos = importeOtrosAtributos;
	}

	public String getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(String importeTotal) {
		this.importeTotal = importeTotal;
	}

	public String getPuntoVenta() {
		return puntoVenta;
	}

	public void setPuntoVenta(String puntoVenta) {
		this.puntoVenta = puntoVenta;
	}

	public String getCompNro() {
		return compNro;
	}

	public void setCompNro(String compNro) {
		this.compNro = compNro;
	}

}

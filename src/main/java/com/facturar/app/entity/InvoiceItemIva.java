package com.facturar.app.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "invoce_item_ivas")
public class InvoiceItemIva implements Serializable {

    private static final long serialVersionUID = -6222621032391327748L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer iva_type_id;

    private Double base_amount;

    private Double amount;

    @ManyToOne
    @JoinColumn(name = "invoice_item_id", nullable = false, updatable = false)
    private InvoiceItem invoiceItem;

    public InvoiceItemIva(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIva_type_id() {
        return iva_type_id;
    }

    public void setIva_type_id(Integer iva_type_id) {
        this.iva_type_id = iva_type_id;
    }

    public Double getBase_amount() {
        return base_amount;
    }

    public void setBase_amount(Double base_amount) {
        this.base_amount = base_amount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public InvoiceItem getInvoiceItem() {
        return invoiceItem;
    }

    public void setInvoiceItem(InvoiceItem invoiceItem) {
        this.invoiceItem = invoiceItem;
    }
}

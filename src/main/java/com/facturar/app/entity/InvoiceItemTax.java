package com.facturar.app.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "invoice_item_taxes")
public class InvoiceItemTax implements Serializable {

    private static final long serialVersionUID = -2452431997526472215L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long tax_type_id;

    private Double base_amount;

    private Double aliquot;

    private Double amount;

    @ManyToOne
    @JoinColumn(name = "invoice_item_id", nullable = false, updatable = false)
    private InvoiceItem invoiceItem;

    public InvoiceItemTax(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTax_type_id() {
        return tax_type_id;
    }

    public void setTax_type_id(Long tax_type_id) {
        this.tax_type_id = tax_type_id;
    }

    public Double getBase_amount() {
        return base_amount;
    }

    public void setBase_amount(Double base_amount) {
        this.base_amount = base_amount;
    }

    public Double getAliquot() {
        return aliquot;
    }

    public void setAliquot(Double aliquot) {
        this.aliquot = aliquot;
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

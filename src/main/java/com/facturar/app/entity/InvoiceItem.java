package com.facturar.app.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "invoice_items")
public class InvoiceItem implements Serializable {

    private static final long serialVersionUID = 1679211592152805324L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer item_id;

    private  Integer quantity;

    private Double amount_item;

    private Double amount_ivas;

    private Double amount_taxes;

    private Double amount_op_ex;

    private Long invoice_id;
    
    @OneToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id",  insertable=false, updatable=false)
    private ItemEntity item;
    
    

    //@ManyToOne
   // @JoinColumn(name = "invoice_id", nullable = false, updatable = false)
    //private InvoiceEntity invoice;

     //@OneToMany(cascade = CascadeType.ALL, mappedBy = "invoiceItem")
    //private Set<InvoiceItemIva> invoiceItemIvaList;

    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "invoiceItem")
    //private Set<InvoiceItemTax> invoiceItemTaxList;

    public ItemEntity getItem() {
		return item;
	}

	public void setItem(ItemEntity item) {
		this.item = item;
	}

	public  InvoiceItem(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Integer getItem_id() {
        return item_id;
    }

    public void setItem_id(Integer item_id) {
        this.item_id = item_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getAmount_item() {
        return amount_item;
    }

    public void setAmount_item(Double amount_item) {
        this.amount_item = amount_item;
    }

    public Double getAmount_ivas() {
        return amount_ivas;
    }

    public void setAmount_ivas(Double amount_ivas) {
        this.amount_ivas = amount_ivas;
    }

    public Double getAmount_taxes() {
        return amount_taxes;
    }

    public void setAmount_taxes(Double amount_taxes) {
        this.amount_taxes = amount_taxes;
    }

    public Double getAmount_op_ex() {
        return amount_op_ex;
    }

    public void setAmount_op_ex(Double amount_op_ex) {
        this.amount_op_ex = amount_op_ex;
    }

   // public InvoiceEntity getInvoice() {
    //    return invoice;
    //}

    //public void setInvoice(InvoiceEntity invoice) {
     //   this.invoice = invoice;
    //}

    //public Set<InvoiceItemIva> getInvoiceItemIvaList() {
      //  return invoiceItemIvaList;
    //}

    //public void setInvoiceItemIvaList(Set<InvoiceItemIva> invoiceItemIvaList) {
   //     this.invoiceItemIvaList = invoiceItemIvaList;
    //}

   // public Set<InvoiceItemTax> getInvoiceItemTaxList() {
    //    return invoiceItemTaxList;
    //}

   // public void setInvoiceItemTaxList(Set<InvoiceItemTax> invoiceItemTaxList) {
    //    this.invoiceItemTaxList = invoiceItemTaxList;
    //}

    public Long getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(Long invoice_id) {
        this.invoice_id = invoice_id;
    }
}

package com.facturar.app.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "invoices")
public class InvoiceEntity implements Serializable {

    private static final long serialVersionUID = 717915249041109075L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long company_id;

    private Long customer_id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created_date;

    @Temporal(TemporalType.TIMESTAMP)
    private Date emision_date;

    private Long sale_condition_id;

    private Long voucher_id;

    private Long sale_point_id;

    private Long counterfoils_id;

    private Integer number;

    private  Long currencyId;

    private Double total_amount;

    @Temporal(TemporalType.TIMESTAMP)
    private Date expiration_date;

    private String letter;

    private Long invoice_status_type_id;

    private Double total_amount_conc;

    private Double amount_net;

    private Double amount_op_ex;

    private Double total_taxes;

    private Double total_ivas;

    @Temporal(TemporalType.TIMESTAMP)
    private Date service_date_from;

    @Temporal(TemporalType.TIMESTAMP)
    private Date service_date_to;

    private Integer total_records;

    private String CAE;

    @Temporal(TemporalType.TIMESTAMP)
    private Date CAE_expiration_date;

    private String observations;

    @Column(name = "user_id")
    private Long userId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "invoice")
    private List<InvoiceItem> invoiceItemList;

    public InvoiceEntity(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Long company_id) {
        this.company_id = company_id;
    }

    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public Date getEmision_date() {
        return emision_date;
    }

    public void setEmision_date(Date emision_date) {
        this.emision_date = emision_date;
    }

    public Long getSale_condition_id() {
        return sale_condition_id;
    }

    public void setSale_condition_id(Long sale_condition_id) {
        this.sale_condition_id = sale_condition_id;
    }

    public Long getVoucher_id() {
        return voucher_id;
    }

    public void setVoucher_id(Long voucher_id) {
        this.voucher_id = voucher_id;
    }

    public Long getSale_point_id() {
        return sale_point_id;
    }

    public void setSale_point_id(Long sale_point_id) {
        this.sale_point_id = sale_point_id;
    }

    public Long getCounterfoils_id() {
        return counterfoils_id;
    }

    public void setCounterfoils_id(Long counterfoils_id) {
        this.counterfoils_id = counterfoils_id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public Double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(Double total_amount) {
        this.total_amount = total_amount;
    }

    public Date getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(Date expiration_date) {
        this.expiration_date = expiration_date;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public Long getInvoice_status_type_id() {
        return invoice_status_type_id;
    }

    public void setInvoice_status_type_id(Long invoice_status_type_id) {
        this.invoice_status_type_id = invoice_status_type_id;
    }

    public Double getTotal_amount_conc() {
        return total_amount_conc;
    }

    public void setTotal_amount_conc(Double total_amount_conc) {
        this.total_amount_conc = total_amount_conc;
    }

    public Double getAmount_net() {
        return amount_net;
    }

    public void setAmount_net(Double amount_net) {
        this.amount_net = amount_net;
    }

    public Double getAmount_op_ex() {
        return amount_op_ex;
    }

    public void setAmount_op_ex(Double amount_op_ex) {
        this.amount_op_ex = amount_op_ex;
    }

    public Double getTotal_taxes() {
        return total_taxes;
    }

    public void setTotal_taxes(Double total_taxes) {
        this.total_taxes = total_taxes;
    }

    public Double getTotal_ivas() {
        return total_ivas;
    }

    public void setTotal_ivas(Double total_ivas) {
        this.total_ivas = total_ivas;
    }

    public Date getService_date_from() {
        return service_date_from;
    }

    public void setService_date_from(Date service_date_from) {
        this.service_date_from = service_date_from;
    }

    public Date getService_date_to() {
        return service_date_to;
    }

    public void setService_date_to(Date service_date_to) {
        this.service_date_to = service_date_to;
    }

    public Integer getTotal_records() {
        return total_records;
    }

    public void setTotal_records(Integer total_records) {
        this.total_records = total_records;
    }

    public String getCAE() {
        return CAE;
    }

    public void setCAE(String CAE) {
        this.CAE = CAE;
    }

    public Date getCAE_expiration_date() {
        return CAE_expiration_date;
    }

    public void setCAE_expiration_date(Date CAE_expiration_date) {
        this.CAE_expiration_date = CAE_expiration_date;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<InvoiceItem> getInvoiceItemList() {
        return invoiceItemList;
    }

    public void setInvoiceItemList(List<InvoiceItem> invoiceItemList) {
        this.invoiceItemList = invoiceItemList;
    }
}

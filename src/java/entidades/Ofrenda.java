/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Rene Alas
 */
@Entity
@Table(name = "ofrenda", catalog = "trs", schema = "")
@NamedQueries({
    @NamedQuery(name = "Ofrenda.findAll", query = "SELECT o FROM Ofrenda o")})
public class Ofrenda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDOFRENDA")
    private Integer idofrenda;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "BILLETES100")
    private Integer billetes100;
    @Column(name = "RESULTADO100")
    private Integer resultado100;
    @Column(name = "BILLETES50")
    private Integer billetes50;
    @Column(name = "RESULTADO50")
    private Integer resultado50;
    @Column(name = "BILLETES20")
    private Integer billetes20;
    @Column(name = "RESULTADO20")
    private Integer resultado20;
    @Column(name = "BILLETES10")
    private Integer billetes10;
    @Column(name = "RESULTADO10")
    private Integer resultado10;
    @Column(name = "BILLETES5")
    private Integer billetes5;
    @Column(name = "RESULTADO5")
    private Integer resultado5;
    @Column(name = "BILLETES2")
    private Integer billetes2;
    @Column(name = "RESULTADO2")
    private Integer resultado2;
    @Column(name = "BILLETES1")
    private Integer billetes1;
    @Column(name = "RESULTADO1")
    private Integer resultado1;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SUBTOTAL")
    private int subtotal;
    @Column(name = "CHEQUEINTERNO")
    private double chequeinterno;
    @Column(name = "CHEQUEEXTERNO")
    private double chequeexterno;
    @Column(name = "GIROS")
    private double giros;
     @Column(name = "TOTALPAPEL")
    private double totalpapel;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANTIDAD1")
    private double cantidad1;
    @Column(name = "CANTIDAD2")
    private double cantidad2;
    @Column(name = "TOTAL")
    private double total;
    @JoinColumn(name = "IDIGLESIA", referencedColumnName = "IDIGLESIA")
    @ManyToOne(optional = false)
    private Iglesia idiglesia;
    @JoinColumn(name = "IDUSUARIO", referencedColumnName = "IDUSUARIO")
    @ManyToOne(optional = false)
    private Usuario idusuario;
    @JoinColumn(name = "IDCULTO", referencedColumnName = "IDCULTO")
    @ManyToOne(optional = false)
    private Culto idculto;

    public Ofrenda() {
        this.billetes100 = 0;
        this.resultado100 = 0;
        this.billetes50 = 0;
        this.resultado50 = 0;
        this.billetes20 = 0;
        this.resultado20 = 0;
        this.billetes10 = 0;
        this.resultado10 = 0;
        this.billetes5 = 0;
        this.resultado5 = 0;
        this.billetes2 = 0;
        this.resultado2 = 0;
        this.billetes1 = 0;
        this.resultado1 = 0;
        this.subtotal = 0;
        this.chequeinterno = 0;
        this.chequeexterno = 0;
        this.giros = 0;
        this.totalpapel = 0;
        this.cantidad1 = 0;
        this.cantidad2 = 0;
        this.total = 0;
    }

    public Ofrenda(Integer idofrenda, Date fecha, Integer billetes100, Integer resultado100, Integer billetes50, Integer resultado50, Integer billetes20, Integer resultado20, Integer billetes10, Integer resultado10, Integer billetes5, Integer resultado5, Integer billetes2, Integer resultado2, Integer billetes1, Integer resultado1, int subtotal, double chequeinterno, double chequeexterno, double giros, double totalpapel, double cantidad1, double cantidad2, double total, Iglesia idiglesia, Usuario idusuario, Culto idculto) {
        this.idofrenda = idofrenda;
        this.fecha = fecha;
        this.billetes100 = billetes100;
        this.resultado100 = resultado100;
        this.billetes50 = billetes50;
        this.resultado50 = resultado50;
        this.billetes20 = billetes20;
        this.resultado20 = resultado20;
        this.billetes10 = billetes10;
        this.resultado10 = resultado10;
        this.billetes5 = billetes5;
        this.resultado5 = resultado5;
        this.billetes2 = billetes2;
        this.resultado2 = resultado2;
        this.billetes1 = billetes1;
        this.resultado1 = resultado1;
        this.subtotal = subtotal;
        this.chequeinterno = chequeinterno;
        this.chequeexterno = chequeexterno;
        this.giros = giros;
        this.totalpapel = totalpapel;
        this.cantidad1 = cantidad1;
        this.cantidad2 = cantidad2;
        this.total = total;
        this.idiglesia = idiglesia;
        this.idusuario = idusuario;
        this.idculto = idculto;
    }
    
    

    public Ofrenda(Integer idofrenda) {
        this.idofrenda = idofrenda;
    }

    public Integer getIdofrenda() {
        return idofrenda;
    }

    public void setIdofrenda(Integer idofrenda) {
        this.idofrenda = idofrenda;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getBilletes100() {
        return billetes100;
    }

    public void setBilletes100(Integer billetes100) {
        this.billetes100 = billetes100;
    }

    public Integer getResultado100() {
        return resultado100;
    }

    public void setResultado100(Integer resultado100) {
        this.resultado100 = resultado100;
    }

    public Integer getBilletes50() {
        return billetes50;
    }

    public void setBilletes50(Integer billetes50) {
        this.billetes50 = billetes50;
    }

    public Integer getResultado50() {
        return resultado50;
    }

    public void setResultado50(Integer resultado50) {
        this.resultado50 = resultado50;
    }

    public Integer getBilletes20() {
        return billetes20;
    }

    public void setBilletes20(Integer billetes20) {
        this.billetes20 = billetes20;
    }

    public Integer getResultado20() {
        return resultado20;
    }

    public void setResultado20(Integer resultado20) {
        this.resultado20 = resultado20;
    }

    public Integer getBilletes10() {
        return billetes10;
    }

    public void setBilletes10(Integer billetes10) {
        this.billetes10 = billetes10;
    }

    public Integer getResultado10() {
        return resultado10;
    }

    public void setResultado10(Integer resultado10) {
        this.resultado10 = resultado10;
    }

    public Integer getBilletes5() {
        return billetes5;
    }

    public void setBilletes5(Integer billetes5) {
        this.billetes5 = billetes5;
    }

    public Integer getResultado5() {
        return resultado5;
    }

    public void setResultado5(Integer resultado5) {
        this.resultado5 = resultado5;
    }

    public Integer getBilletes2() {
        return billetes2;
    }

    public void setBilletes2(Integer billetes2) {
        this.billetes2 = billetes2;
    }

    public Integer getResultado2() {
        return resultado2;
    }

    public void setResultado2(Integer resultado2) {
        this.resultado2 = resultado2;
    }

    public Integer getBilletes1() {
        return billetes1;
    }

    public void setBilletes1(Integer billetes1) {
        this.billetes1 = billetes1;
    }

    public Integer getResultado1() {
        return resultado1;
    }

    public void setResultado1(Integer resultado1) {
        this.resultado1 = resultado1;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

    public double getChequeinterno() {
        return chequeinterno;
    }

    public void setChequeinterno(double chequeinterno) {
        this.chequeinterno = chequeinterno;
    }

    public double getChequeexterno() {
        return chequeexterno;
    }

    public void setChequeexterno(double chequeexterno) {
        this.chequeexterno = chequeexterno;
    }

    public double getGiros() {
        return giros;
    }

    public void setGiros(double giros) {
        this.giros = giros;
    }

    public double getTotalpapel() {
        return totalpapel;
    }

    public void setTotalpapel(double totalpapel) {
        this.totalpapel = totalpapel;
    }
     
       public double getCantidad1() {
        return cantidad1;
    }

    public void setCantidad1(double cantidad1) {
        this.cantidad1 = cantidad1;
    }

    public double getCantidad2() {
        return cantidad2;
    }

    public void setCantidad2(double cantidad2) {
        this.cantidad2 = cantidad2;
    }

      public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Iglesia getIdiglesia() {
        return idiglesia;
    }

    public void setIdiglesia(Iglesia idiglesia) {
        this.idiglesia = idiglesia;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    public Culto getIdculto() {
        return idculto;
    }

    public void setIdculto(Culto idculto) {
        this.idculto = idculto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idofrenda != null ? idofrenda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ofrenda)) {
            return false;
        }
        Ofrenda other = (Ofrenda) object;
        if ((this.idofrenda == null && other.idofrenda != null) || (this.idofrenda != null && !this.idofrenda.equals(other.idofrenda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Ofrenda[ idofrenda=" + idofrenda + " ]";
    }
    
}

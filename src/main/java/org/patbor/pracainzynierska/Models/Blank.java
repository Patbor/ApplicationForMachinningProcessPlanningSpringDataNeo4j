package org.patbor.pracainzynierska.Models;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.math.BigDecimal;

@Node("BLANK")
public class Blank {

    @GeneratedValue
    @Id
    private Long id;
    @Property("IDBL")
    private String idbl;
    @Property("IDPart")
    private String idp;
    @Property("Material")
    private String material;
    @Property("Dmax")
    private Double dmax;
    @Property("Dpf")
    private Integer dpf;
    @Property("TDpf")
    private Double tdpf;
    @Property("ITpf")
    private Double itpf;
    @Property("Rapf")
    private Integer rapf;
    @Property("Lpf")
    private Integer lpf;
    @Property("TLpf")
    private Integer tlpf;
    @Property("Np")
    private Integer np;
    @Property("Weight")
    private Double weight;
    @Property("Price")
    private String price;

    public Long getId() {
        return id;
    }

    public Double getItpf() {
        return itpf;
    }

    public void setItpf(Double itpf) {
        this.itpf = itpf;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdbl() {
        return idbl;
    }

    public void setIdbl(String idbl) {
        this.idbl = idbl;
    }

    public String getIdp() {
        return idp;
    }

    public void setIdp(String idp) {
        this.idp = idp;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Double getDmax() {
        return dmax;
    }

    public void setDmax(Double dmax) {
        this.dmax = dmax;
    }

    public Integer getDpf() {
        return dpf;
    }

    public void setDpf(Integer dpf) {
        this.dpf = dpf;
    }

    public Double getTdpf() {
        return tdpf;
    }

    public void setTdpf(Double tdpf) {
        this.tdpf = tdpf;
    }

    public Integer getRapf() {
        return rapf;
    }

    public void setRapf(Integer rapf) {
        this.rapf = rapf;
    }

    public Integer getLpf() {
        return lpf;
    }

    public void setLpf(Integer lpf) {
        this.lpf = lpf;
    }

    public Integer getTlpf() {
        return tlpf;
    }

    public void setTlpf(Integer tlpf) {
        this.tlpf = tlpf;
    }

    public Integer getNp() {
        return np;
    }

    public void setNp(Integer np) {
        this.np = np;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}

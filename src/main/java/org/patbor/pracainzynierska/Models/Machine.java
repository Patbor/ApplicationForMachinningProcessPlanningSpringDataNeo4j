package org.patbor.pracainzynierska.Models;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.math.BigDecimal;

@Node("MACHINE")
public class Machine {
    @GeneratedValue
    @Id
    private Long  id;

    @Property("PKIDMT")
    private String pkidmt;
    @Property("Method")
    private String method;
    @Property("SubType")
    private String subType;
    @Property("Producer")
    private String producer;
    @Property("Depart")
    private String depart;
    @Property("MTDescription")
    private String mTDescription;
    @Property("Length")
    private Integer length;
    @Property("Width")
    private Integer width;
    @Property("Height")
    private Integer height;
    @Property("Mass")
    private Integer mass;
    @Property("Power")
    private Double power;
    @Property("n_min")
    private Integer nMin;
    @Property("n_max")
    private Integer nMax;
    @Property("ToolSocket")
    private String toolSocket;
    @Property("WorkSocket")
    private String workSocket;

    @Property("Cz")
    private BigDecimal cz;
    @Property("Kw")
    private BigDecimal kw;
    @Property("Kp")
    private BigDecimal kp;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPkidmt() {
        return pkidmt;
    }

    public void setPkidmt(String pkidmt) {
        this.pkidmt = pkidmt;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getmTDescription() {
        return mTDescription;
    }

    public void setmTDescription(String mTDescription) {
        this.mTDescription = mTDescription;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getMass() {
        return mass;
    }

    public void setMass(Integer mass) {
        this.mass = mass;
    }

    public Double getPower() {
        return power;
    }

    public void setPower(Double power) {
        this.power = power;
    }

    public Integer getnMin() {
        return nMin;
    }

    public void setnMin(Integer nMin) {
        this.nMin = nMin;
    }

    public Integer getnMax() {
        return nMax;
    }

    public void setnMax(Integer nMax) {
        this.nMax = nMax;
    }

    public String getToolSocket() {
        return toolSocket;
    }

    public void setToolSocket(String toolSocket) {
        this.toolSocket = toolSocket;
    }

    public String getWorkSocket() {
        return workSocket;
    }

    public void setWorkSocket(String workSocket) {
        this.workSocket = workSocket;
    }

    public BigDecimal getCz() {
        return cz;
    }

    public void setCz(BigDecimal cz) {
        this.cz = cz;
    }

    public BigDecimal getKw() {
        return kw;
    }

    public void setKw(BigDecimal kw) {
        this.kw = kw;
    }

    public BigDecimal getKp() {
        return kp;
    }

    public void setKp(BigDecimal kp) {
        this.kp = kp;
    }
}

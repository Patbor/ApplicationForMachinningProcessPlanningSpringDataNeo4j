package org.patbor.pracainzynierska.Models;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Node("FEATURE")
public class Feature {

    @GeneratedValue
    @Id
    private Long id;

    @Property("IDFtr")
    private String idftr;
    @Property("IDPart")
    private String idPart;
    @Property("FtrNo")
    private String ftrno;
    @Property("FtrType")
    private String ftrtype;
    @Property("Diameter")
    private Double diameter;
    @Property("Length")
    private Double length;
    @Property("Depth")
    private Double depth;
    @Property("Width")
    private Double width;
    @Property("Radius")
    private Integer radius;
    @Property("Angle")
    private Integer angle;
    @Property("Fit")
    private String fit;
    @Property("IT")
    private Integer it;
    @Property("Ra")
    private Double ra;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdftr() {
        return idftr;
    }

    public void setIdftr(String idftr) {
        this.idftr = idftr;
    }

    public String getIdPart() {
        return idPart;
    }

    public void setIdPart(String idPart) {
        this.idPart = idPart;
    }

    public String getFtrno() {
        return ftrno;
    }

    public void setFtrno(String ftrno) {
        this.ftrno = ftrno;
    }

    public String getFtrtype() {
        return ftrtype;
    }

    public void setFtrtype(String ftrtype) {
        this.ftrtype = ftrtype;
    }

    public Double getDiameter() {
        return diameter;
    }

    public void setDiameter(Double diameter) {
        this.diameter = diameter;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getDepth() {
        return depth;
    }

    public void setDepth(Double depth) {
        this.depth = depth;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Integer getRadius() {
        return radius;
    }

    public void setRadius(Integer radius) {
        this.radius = radius;
    }

    public Integer getAngle() {
        return angle;
    }

    public void setAngle(Integer angle) {
        this.angle = angle;
    }

    public String getFit() {
        return fit;
    }

    public void setFit(String fit) {
        this.fit = fit;
    }

    public Integer getIt() {
        return it;
    }

    public void setIt(Integer it) {
        this.it = it;
    }

    public Double getRa() {
        return ra;
    }

    public void setRa(Double ra) {
        this.ra = ra;
    }
}

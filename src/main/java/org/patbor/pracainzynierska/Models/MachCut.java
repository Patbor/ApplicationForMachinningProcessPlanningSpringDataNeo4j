package org.patbor.pracainzynierska.Models;

import org.springframework.data.neo4j.core.schema.*;

@Node("TREATMENT")
public class MachCut {

    @GeneratedValue
    @Id
    private Long  id;
    @Property("IDMC")
    private String idmc;
    @Property("IDSET")
    private String idset;
    @Property("MCNo")
    private Integer mcno;
    @Property("PathNo")
    private Integer pathNo;
    @Property("TypeDT")
    private String typeDT;
    @Property("Description")
    private String description;
    @Property("ToolID")
    private String toolID;
    @Property("IDPFt")
    private String idpft;
    @Property("Dp")
    private Double dp;
    @Property("Dk")
    private Double dk;
    @Property("L")
    private Integer l;
    @Property("ap")
    private Double ap;
    @Property("F")
    private Double f;
    @Property("Vc")
    private Integer vc;
    @Property("n")
    private Integer n;
    @Property("tg")
    private Double tg;

    @Relationship(type = "IS_USED", direction = Relationship.Direction.INCOMING)
    Tool tool;
    @Relationship(type = "FEATURE_OF", direction = Relationship.Direction.INCOMING)
   private Feature feature;
    @Relationship(type = "ADTR_OF", direction = Relationship.Direction.INCOMING)
    private ADTR adtr;

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    public ADTR getAdtr() {
        return adtr;
    }

    public void setAdtr(ADTR adtr) {
        this.adtr = adtr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdmc() {
        return idmc;
    }

    public void setIdmc(String idmc) {
        this.idmc = idmc;
    }

    public String getIdset() {
        return idset;
    }

    public void setIdset(String idset) {
        this.idset = idset;
    }

    public Integer getMcno() {
        return mcno;
    }

    public void setMcno(Integer mcno) {
        this.mcno = mcno;
    }

    public Integer getPathNo() {
        return pathNo;
    }

    public void setPathNo(Integer pathNo) {
        this.pathNo = pathNo;
    }

    public String getTypeDT() {
        return typeDT;
    }

    public void setTypeDT(String typeDT) {
        this.typeDT = typeDT;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getToolID() {
        return toolID;
    }

    public void setToolID(String toolID) {
        this.toolID = toolID;
    }

    public String getIdpft() {
        return idpft;
    }

    public void setIdpft(String idpft) {
        this.idpft = idpft;
    }

    public Double getDp() {
        return dp;
    }

    public void setDp(Double dp) {
        this.dp = dp;
    }

    public Double getDk() {
        return dk;
    }

    public void setDk(Double dk) {
        this.dk = dk;
    }

    public Integer getL() {
        return l;
    }

    public void setL(Integer l) {
        this.l = l;
    }

    public Double getAp() {
        return ap;
    }

    public void setAp(Double ap) {
        this.ap = ap;
    }

    public Double getF() {
        return f;
    }

    public void setF(Double f) {
        this.f = f;
    }

    public Integer getVc() {
        return vc;
    }

    public void setVc(Integer vc) {
        this.vc = vc;
    }

    public Integer getN() {
        return n;
    }

    public void setN(Integer n) {
        this.n = n;
    }

    public Double getTg() {
        return tg;
    }

    public void setTg(Double tg) {
        this.tg = tg;
    }

    public Tool getTool() {
        return tool;
    }

    public void setTool(Tool tool) {
        this.tool = tool;
    }
}

package org.patbor.pracainzynierska.Models;

import org.springframework.data.neo4j.core.schema.*;

import java.util.ArrayList;
import java.util.List;

@Node("PART")
public class Part {
    @GeneratedValue
    @Id
    private Long  id;
    @Property("IDPart")
    private String idPart;
    @Property("PartName")
    private String partName;
    @Property("IDMat")
    private String idMat;
    @Property("Mass")
    private Double mass;
    @Relationship(type = "BLANK_OF", direction = Relationship.Direction.INCOMING)
    Blank blank;
    @Relationship(type = "CREATED_BY", direction = Relationship.Direction.OUTGOING)
    Process process;
    @Relationship(type = "WITH_FEATURE", direction = Relationship.Direction.OUTGOING)
    List<Feature> features = new ArrayList<>();

    public Blank getBlank() {
        return blank;
    }

    public void setBlank(Blank blank) {
        this.blank = blank;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdPart() {
        return idPart;
    }

    public void setIdPart(String idPart) {
        this.idPart = idPart;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getIdMat() {
        return idMat;
    }

    public void setIdMat(String idMat) {
        this.idMat = idMat;
    }

    public Double getMass() {
        return mass;
    }

    public void setMass(Double mass) {
        this.mass = mass;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }
}

package org.patbor.pracainzynierska.Models;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Node("EQUIPMENT")
public class Equipment {

    @GeneratedValue
    @Id
    private Long id;
    @Property("IDEQ")
    private String ideq;
    @Property("EQCode")
    private String eqCode;
    @Property("EQName")
    private String eqName;
    @Property("Description")
    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdeq() {
        return ideq;
    }

    public void setIdeq(String ideq) {
        this.ideq = ideq;
    }

    public String getEqCode() {
        return eqCode;
    }

    public void setEqCode(String eqCode) {
        this.eqCode = eqCode;
    }

    public String getEqName() {
        return eqName;
    }

    public void setEqName(String eqName) {
        this.eqName = eqName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

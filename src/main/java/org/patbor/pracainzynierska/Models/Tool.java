package org.patbor.pracainzynierska.Models;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Node("TOOL")
public class Tool {

    @GeneratedValue
    @Id
    private Long id;

    @Property("IDCT")
    private String idct;
    @Property("CTCode")
    private String cTCode;
    @Property("CTName")
    private String cTName;
    @Property("Description")
    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdct() {
        return idct;
    }

    public void setIdct(String idct) {
        this.idct = idct;
    }

    public String getcTCode() {
        return cTCode;
    }

    public void setcTCode(String cTCode) {
        this.cTCode = cTCode;
    }

    public String getcTName() {
        return cTName;
    }

    public void setcTName(String cTName) {
        this.cTName = cTName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

package org.patbor.pracainzynierska.Models;

import org.springframework.data.neo4j.core.schema.*;

import java.util.ArrayList;
import java.util.List;

@Node("EQ_NEED")
public class ListOfEq {

    @GeneratedValue
    @Id
    private Long  id;
    @Property("IDPEQ")
    private Integer idpeq;
    @Property("IDEQ")
    private String ideq;
    @Property("IDSET")
    private String idset;

    @Relationship(type = "NEED_TOOL", direction = Relationship.Direction.OUTGOING)
    List<Equipment> equipment = new ArrayList<>();



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdpeq() {
        return idpeq;
    }

    public void setIdpeq(Integer idpeq) {
        this.idpeq = idpeq;
    }

    public String getIdeq() {
        return ideq;
    }

    public void setIdeq(String ideq) {
        this.ideq = ideq;
    }

    public String getIdset() {
        return idset;
    }

    public void setIdset(String idset) {
        this.idset = idset;
    }

    public List<Equipment> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<Equipment> equipment) {
        this.equipment = equipment;
    }

}

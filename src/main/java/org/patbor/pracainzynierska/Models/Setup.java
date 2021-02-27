package org.patbor.pracainzynierska.Models;

import org.springframework.data.neo4j.core.schema.*;

import java.util.ArrayList;
import java.util.List;

@Node("SETUP")
public class Setup {
    @GeneratedValue
    @Id
    private Long id;
    @Property("IDSET")
    private String idset;
    @Property("IDOP")
    private String idop;
    @Property("SETNo")
    private Integer setNo;
    @Property("SETType")
    private String setType;
    @Property("Description")
    private String description;


    @Relationship(type = "PERFORM_WITH", direction = Relationship.Direction.INCOMING)
   private List<MachCut> listOfMachCuts = new ArrayList<>();

    @Relationship(type = "NEEDS", direction = Relationship.Direction.OUTGOING)
   private List<ListOfEq> listOfEqs= new ArrayList<>();

    @Relationship(type = "WITH_ADPU",direction = Relationship.Direction.OUTGOING)
   private ADPU adpu;

    public ADPU getAdpu() {
        return adpu;
    }

    public void setAdpu(ADPU adpu) {
        this.adpu = adpu;
    }

    public List<ListOfEq> getListOfEqs() {
        return listOfEqs;
    }

    public void setListOfEqs(List<ListOfEq> listOfEqs) {
        this.listOfEqs = listOfEqs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdset() {
        return idset;
    }

    public void setIdset(String idset) {
        this.idset = idset;
    }

    public String getIdop() {
        return idop;
    }

    public void setIdop(String idop) {
        this.idop = idop;
    }

    public Integer getSetNo() {
        return setNo;
    }

    public void setSetNo(Integer setNo) {
        this.setNo = setNo;
    }

    public String getSetType() {
        return setType;
    }

    public void setSetType(String setType) {
        this.setType = setType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MachCut> getListOfMachCuts() {
        return listOfMachCuts;
    }

    public void setListOfMachCuts(List<MachCut> listOfMachCuts) {
        this.listOfMachCuts = listOfMachCuts;
    }
}

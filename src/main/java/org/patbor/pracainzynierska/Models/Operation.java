package org.patbor.pracainzynierska.Models;

import org.springframework.data.neo4j.core.schema.*;

import java.util.ArrayList;
import java.util.List;

@Node("OPERATION")
public class Operation {

    @GeneratedValue
    @Id
    private Long  id;

    @Property("IDOP")
    private String idop;
    @Property("IDPRJ")
    private String idprj;
    @Property("OPNo")
    private Integer opno;
    @Property("OPType")
    private String opType;
    @Property("Department")
    private String department;
    @Property("Workstation")
    private String workstation;
    @Property("Description")
    private String description;
    @Property("Tpz")
    private Integer Tpz;
    @Property("Tj")
    private Double tj;

    @Relationship(type = "USES", direction = Relationship.Direction.OUTGOING)
    Machine machineList;
    @Relationship(type = "WITH_SETUP", direction = Relationship.Direction.OUTGOING)
    List<Setup> setups = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdop() {
        return idop;
    }

    public void setIdop(String idop) {
        this.idop = idop;
    }

    public String getIdprj() {
        return idprj;
    }

    public void setIdprj(String idprj) {
        this.idprj = idprj;
    }

    public Integer getOpno() {
        return opno;
    }

    public void setOpno(Integer opno) {
        this.opno = opno;
    }

    public String getOpType() {
        return opType;
    }

    public void setOpType(String opType) {
        this.opType = opType;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getWorkstation() {
        return workstation;
    }

    public void setWorkstation(String workstation) {
        this.workstation = workstation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTpz() {
        return Tpz;
    }

    public void setTpz(Integer tpz) {
        Tpz = tpz;
    }

    public Double getTj() {
        return tj;
    }

    public void setTj(Double tj) {
        this.tj = tj;
    }

    public Machine getMachineList() {
        return machineList;
    }

    public void setMachineList(Machine machineList) {
        this.machineList = machineList;
    }

    public List<Setup> getSetups() {
        return setups;
    }

    public void setSetups(List<Setup> setups) {
        this.setups = setups;
    }
}

package org.patbor.pracainzynierska.Models;

import org.springframework.data.neo4j.core.schema.*;

import java.util.ArrayList;
import java.util.List;

@Node("PROCESS")
public class Process {

    @GeneratedValue
    @Id
    private Long id;
    @Property("IDPRJ")
    private String idprj;
    @Property("IDPart")
    private String idPart;
    @Property("IDBlank")
    private String idBlank;
    @Property("DevelopedBy")
    private String developedBy;
    @Property("CheckedBy")
    private String checkedBy;
    @Property("ApprovedBy")
    private String approvedBy;
    @Relationship(type = "CONSIST_OF", direction = Relationship.Direction.OUTGOING)
    List<Operation> operations = new ArrayList<>();
    @Relationship(type = "CREATES_FROM", direction = Relationship.Direction.OUTGOING)
    Blank blank;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdprj() {
        return idprj;
    }

    public void setIdprj(String idprj) {
        this.idprj = idprj;
    }

    public String getIdPart() {
        return idPart;
    }

    public void setIdPart(String idPart) {
        this.idPart = idPart;
    }

    public String getIdBlank() {
        return idBlank;
    }

    public void setIdBlank(String idBlank) {
        this.idBlank = idBlank;
    }

    public String getDevelopedBy() {
        return developedBy;
    }

    public void setDevelopedBy(String developedBy) {
        this.developedBy = developedBy;
    }

    public String getCheckedBy() {
        return checkedBy;
    }

    public void setCheckedBy(String checkedBy) {
        this.checkedBy = checkedBy;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public Blank getBlank() {
        return blank;
    }

    public void setBlank(Blank blank) {
        this.blank = blank;
    }
}

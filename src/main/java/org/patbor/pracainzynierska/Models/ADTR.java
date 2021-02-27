package org.patbor.pracainzynierska.Models;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Node
public class ADTR {

    @GeneratedValue
    @Id
    private Long id;
    @Property("IDADTR")
    private String idadtr;
    @Property("ADT1")
    private String adt1;
    @Property("ADT2")
    private String adt2;
    @Property("ADT3")
    private String adt3;
    @Property("ADT4")
    private String adt4;
    @Property("ADT5")
    private String adt5;
    @Property("ADTRDescription")
    private String adtrDes;

    public Long getId() {
        return id;
    }

    public String getAdt5() {
        return adt5;
    }

    public void setAdt5(String adt5) {
        this.adt5 = adt5;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdadtr() {
        return idadtr;
    }

    public void setIdadtr(String idadtr) {
        this.idadtr = idadtr;
    }

    public String getAdt1() {
        return adt1;
    }

    public void setAdt1(String adt1) {
        this.adt1 = adt1;
    }

    public String getAdt2() {
        return adt2;
    }

    public void setAdt2(String adt2) {
        this.adt2 = adt2;
    }

    public String getAdt3() {
        return adt3;
    }

    public void setAdt3(String adt3) {
        this.adt3 = adt3;
    }

    public String getAdt4() {
        return adt4;
    }

    public void setAdt4(String adt4) {
        this.adt4 = adt4;
    }

    public String getAdtrDes() {
        return adtrDes;
    }

    public void setAdtrDes(String adtrDes) {
        this.adtrDes = adtrDes;
    }
}

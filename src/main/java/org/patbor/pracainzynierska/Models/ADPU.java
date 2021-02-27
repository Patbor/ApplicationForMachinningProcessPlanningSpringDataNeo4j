package org.patbor.pracainzynierska.Models;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Node("ADPU")
public class ADPU {

    @GeneratedValue
    @Id
    private Long id;
    @Property("IDADPU")
    private String idadpu;
    @Property("CodeDPU")
    private String codeDPU;
    @Property("ADPU1")
    private String adpu1;
    @Property("ADPU2")
    private String adpu2;
    @Property("ADPU3")
    private String adpu3;
    @Property("ADPU4")
    private String adpu4;
    @Property("ADPUDescription")
    private String adpuDes;
    @Property("Resource")
    private String resource;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdadpu() {
        return idadpu;
    }

    public void setIdadpu(String idadpu) {
        this.idadpu = idadpu;
    }

    public String getCodeDPU() {
        return codeDPU;
    }

    public void setCodeDPU(String codeDPU) {
        this.codeDPU = codeDPU;
    }

    public String getAdpu1() {
        return adpu1;
    }

    public void setAdpu1(String adpu1) {
        this.adpu1 = adpu1;
    }

    public String getAdpu2() {
        return adpu2;
    }

    public void setAdpu2(String adpu2) {
        this.adpu2 = adpu2;
    }

    public String getAdpu3() {
        return adpu3;
    }

    public void setAdpu3(String adpu3) {
        this.adpu3 = adpu3;
    }

    public String getAdpu4() {
        return adpu4;
    }

    public void setAdpu4(String adpu4) {
        this.adpu4 = adpu4;
    }

    public String getAdpuDes() {
        return adpuDes;
    }

    public void setAdpuDes(String adpuDes) {
        this.adpuDes = adpuDes;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }
}

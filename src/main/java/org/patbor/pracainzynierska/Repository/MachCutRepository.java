package org.patbor.pracainzynierska.Repository;

import org.patbor.pracainzynierska.Models.MachCut;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MachCutRepository extends Neo4jRepository<MachCut,Long> {

    List<MachCut> findAllByIdset(String idset);

    Optional<MachCut> findByIdmc(String idmc);

    @Query("MATCH(t:TREATMENT {IDMC: $0}),\n" +
            "(s:SETUP {IDSET: $1})\n" +
            "MERGE (t)-[:PERFORM_WITH]->(s)")
    void createRelationshipBetweenTreatmentAndSetup(String idmc,String idset);

    @Query("MATCH(t:TOOL {IDCT: $0}),\n" +
            "(tr:TREATMENT {IDMC: $1})\n" +
            "MERGE (t)-[:IS_USED]->(tr)")
    void createRelationshipBetweenToolAndTreatment(String idct,String idmc);

    @Query("MATCH(t:TOOL {IDCT: $0}),\n" +
            "(tr:TREATMENT {IDMC: $1})\n" +
            "MERGE (t)-[r:IS_USED]->(tr)\n" +
            "DELETE r")
    void deleteRelationshipBetweenToolAndTreatment(String idct,String idmc);

    @Query("MATCH(f:FEATURE {IDFtr: $0}),\n" +
            "(tr:TREATMENT {IDMC: $1}),\n" +
            "(adtr:ADTR {IDADTR: $2})\n"+
            "MERGE (f)-[:FEATURE_OF]->(tr)<-[:ADTR_OF]-(adtr)")
    void createRelationshipBetweenFeatureAndTreatmentAndADTR(String idftr,String idmc, String idadtr);

    @Query("MATCH (f:FEATURE {IDFtr: $0})-[fr:FEATURE_OF]->(tr:TREATMENT {IDMC: $1})\n" +
            "DELETE fr")
    void deleteRelationshipBetweenFeatureAndTreatment(String idftr,String idmc);

    @Query("MATCH(f:FEATURE {IDFtr: $0}),\n" +
            "(tr:TREATMENT {IDMC: $1})\n" +
            "MERGE (f)-[:FEATURE_OF]->(tr)")
    void createRelationshipBetweenFeatureAndTreatment(String idftr,String idmc);

    @Query("MATCH(t:TREATMENT {IDMC: $0}) \n" +
            "Set t.PathNo = $1,t.Description = $2,t.IDPFt = $3,t.Dp = $4,t.Dk = $5,t.L= $6,t.ap= $7,t.F= $8, t.Vc=$9, t.n=$10, t.tg=$11")
    void updateTreatment(String idmc, Integer pathNo, String description, String idpft, Double dp, Double dk, Integer l, Double ap, Double f,Integer vc,Integer n,Double tg);

    @Query("MATCH(t:TREATMENT {IDMC : $0}) DETACH DELETE t")
    void deleteTreatment(String idmc);
}

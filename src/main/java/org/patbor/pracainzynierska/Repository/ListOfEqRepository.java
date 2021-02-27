package org.patbor.pracainzynierska.Repository;

import org.patbor.pracainzynierska.Models.Equipment;
import org.patbor.pracainzynierska.Models.ListOfEq;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListOfEqRepository extends Neo4jRepository<ListOfEq, Long> {

    List<ListOfEq> findAllByIdset(String idset);

    @Query("MATCH(s:SETUP {IDSET: $0}),\n" +
            "(e:EQ_NEED {IDPEQ: $1})\n" +
            "MERGE (s)-[:NEEDS]->(e)")
    void createRelationshipBetweenSetupAndEqNeed(String idset, Integer idpeq);

    @Query("MATCH(eq:EQUIPMENT {IDEQ: $0}),\n" +
            "(e:EQ_NEED {IDPEQ: $1})\n" +
            "MERGE (eq)-[:NEED_TOOL]->(e)")
    void createRelationshipBetweenEquipmentAndEqNeed(String ideq, Integer idepq);

    @Query("MATCH(e:EQ_NEED {IDSET:$0,IDEQ:$1}) DETACH DELETE e")
    void deleteListOfEq(String idset, String ideq);



}

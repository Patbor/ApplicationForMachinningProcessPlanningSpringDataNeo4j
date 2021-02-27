package org.patbor.pracainzynierska.Repository;

import org.patbor.pracainzynierska.Models.Process;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProcessRepository extends Neo4jRepository<Process,Long> {

    Optional<Process> findProcessByIdprj(String idprj);
    Optional<Process> findProcessByIdPart(String idPart);

    @Query("MATCH(p:PART {IDPart: $0}),\n" +
    "(pr:PROCESS {IDPRJ: $1})\n" +
    "MERGE (p)-[:CREATED_BY]->(pr)")
    void createRelationshipBetweenPartAndProcess(String idop,String idprj);

    @Query("MATCH(p:PROCESS {IDPRJ: $0}),\n" +
            "(b:BLANK {IDBL: $1})\n" +
            "MERGE (p)-[:CREATES_FROM]->(b)")
    void createRelationshipBetweenProcessAndBlank(String idprj,String idbl);


    @Query("MATCH(p:PROCESS {IDPRJ: $0}) \n" +
            "Set p.DevelopedBy = $1, p.CheckedBy = $2, p.ApprovedBy = $3")
    void updateProcess(String idprj,String developedBy, String checkedBy, String approvedBy);
}

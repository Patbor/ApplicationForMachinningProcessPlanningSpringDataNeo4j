package org.patbor.pracainzynierska.Repository;

import org.patbor.pracainzynierska.Models.Operation;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OperationRepository extends Neo4jRepository<Operation,Long> {
    List<Operation> findByIdprj(String id);

    Optional<Operation> findByIdop(String idop);

    @Query("MATCH(p:PROCESS {IDPRJ: $0}),\n" +
            "(o:OPERATION {IDOP: $1})\n" +
            "MERGE (p)-[:CONSIST_OF]->(o)")
    void createRelationshipBetweenProcessAndOperation(String idprj,String idop);

    @Query("MATCH(o:OPERATION {IDOP: $0}),\n" +
            "(m:MACHINE {PKIDMT: $1})\n" +
            "MERGE (o)-[:USES]->(m)")
    void createRelationshipBetweenOperationAndMachine(String idop,String pkidmt);

    @Query("MATCH(o:OPERATION {IDOP: $0}),\n" +
            "(m:MACHINE {PKIDMT: $1})\n" +
            "MERGE (o)-[r:USES]->(m) \n" +
            "DELETE r")
    void deleteRelationshipBetweenOperationAndMachine(String idop,String pkidmt);

    @Query("MATCH(operation:OPERATION {IDOP: $0}) \n" +
            "Set operation.OPType = $1,operation.Department = $2, " +
            "operation.Workstation = $3,operation.Description = $4, operation.Tpz = $5, operation.Tj = $6")
    void updateOperation(String idop, String opType, String department, String workstation, String description, int tpz, double tj);

    @Query("MATCH(n {IDOP:$0}) DETACH DELETE n")
    void deleteThirdNeighborhoodPart(String idop);

    @Query("MATCH(n {IDSET:$0}) DETACH DELETE n")
    void deleteFourthNeighborhoodPart(String idset);
}

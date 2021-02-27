package org.patbor.pracainzynierska.Repository;

import org.patbor.pracainzynierska.Models.Setup;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface SetupRepository extends Neo4jRepository<Setup,Long> {
    List<Setup> findByIdop(String idop);

    Optional<Setup> findByIdset(String idset);

    @Query("MATCH(o:OPERATION {IDOP: $0}),\n" +
            "(s:SETUP {IDSET: $1})\n" +
            "MERGE (o)-[:WITH_SETUP]->(s)")
    void createRelationshipBetweenOperationAndSetup(String idop,String idset);

    @Query("MATCH(s:SETUP {IDSET: $0}),\n" +
            "(a:ADPU {IDADPU: $1})\n" +
            "MERGE (s)-[:WITH_ADPU]->(a)")
    void createRelationshipBetweenSetupAndADPU(String idset,String idadpu);

    @Query("MATCH(s:SETUP {IDSET: $0}),\n" +
            "(a:ADPU {PKIDMT: $1})\n" +
            "MERGE (s)-[r:WITH_ADPU]->(a)\n" +
            "DELETE r")
    void deleteRelationshipBetweenSetupAndADPU(String idset,String idadpu);

    @Query("MATCH(s:SETUP {IDSET: $0}) \n" +
            "Set s.SETType = $1, s.Description = $2")
    void updateSetup(String idset, String SETType, String description);

    @Query("MATCH(n {IDSET:$0}) DETACH DELETE n")
    void deleteFourthNeighborhoodPart(String idset);
}

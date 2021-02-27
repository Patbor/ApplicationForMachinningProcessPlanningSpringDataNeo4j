package org.patbor.pracainzynierska.Repository;

import org.patbor.pracainzynierska.Models.Blank;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Optional;

public interface BlankRepository extends Neo4jRepository<Blank, Long> {

    Optional<Blank> findByIdbl(String idbl);

    @Query("MATCH(p:PART {IDPart: $0}),\n" +
            "(b:BLANK {IDBL: $1})\n" +
            "MERGE (b)-[:BLANK_OF]->(p)")
    void createRelationshipBetweenBlankAndPart(String idop, String idbl);

    @Query("MATCH(blank:BLANK {IDBL: $0}) \n" +
            "Set blank.Material = $1, blank.Dmax = $2, blank.Dpf = $3, blank.TDpf = $4, blank.ITpf = $5, \n" +
            "blank.Rapf = $6, blank.Lpf = $7,blank.TLpf = $8,blank.Np = $9, blank.Weight = $10, blank.Price = $11;")
    void updateBlank(String idbl,String material, Double dMax, Integer dpf, Double tdpf, Double itpf, Integer rapf,
                     Integer lpf, Integer tlpf, Integer np, Double weight, String Price);
}

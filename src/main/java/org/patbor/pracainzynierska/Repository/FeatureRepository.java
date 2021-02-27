package org.patbor.pracainzynierska.Repository;

import org.patbor.pracainzynierska.Models.Feature;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;
import java.util.Optional;

public interface FeatureRepository extends Neo4jRepository<Feature,Long> {

    Optional<Feature> findByIdftr(String idftr);

    List<Feature> findAllByIdPart(String idPart);

    @Query("MATCH(p:PART {IDPart: $0}),\n" +
            "(f:FEATURE {IDFtr: $1})\n" +
            "MERGE (p)-[:WITH_FEATURE]->(f)")
    void createRelationshipBetweenPartAndFeature(String idop, String idftr);

    @Query("MATCH(feature:FEATURE {IDFtr:$0}) \n" +
            "Set feature.FtrType = $1, feature.Diameter = $2,feature.Length = $3,feature.Depth=$4,feature.Width = $5, " +
            "feature.Radius= $6, feature.Angle = $7,feature.Fit = $8,feature.IT=$9,feature.Ra=$10")
    void updateFeature(String idftr, String ftrType, Double diameter, Double length, Double depth,
                       Double width, Integer radius, Integer angle, String fit, Integer it, Double ra);

    @Query("MATCH(p:PART {IDPart: $0}),\n" +
            "(f:FEATURE {IDFtr: $1})\n" +
            "MERGE (p)-[:WITH_FEATURE]->(f)")
    void deleteRelationshipBetweenPartAndFeature(String idop, String idftr);

    @Query("MATCH(f:FEATURE {IDFtr: $0}) DETACH DELETE f")
    void deleteById(String id);
}


package org.patbor.pracainzynierska.Repository;

import org.patbor.pracainzynierska.Models.Equipment;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

public interface EquipmentRepository extends Neo4jRepository<Equipment, Long> {

    Optional<Equipment> findByIdeq(String ideq);

    List<Equipment> findAllByIdeq(String ideq);


    @Query("MATCH(s:SETUP {IDSET: $0})-[:NEEDS]->(e:EQ_NEED {IDSET: $0}) <-[:NEED_TOOL]-(eq:EQUIPMENT)\n" +
            "RETURN eq")
    List<Equipment> findEquipment(String idset);



}

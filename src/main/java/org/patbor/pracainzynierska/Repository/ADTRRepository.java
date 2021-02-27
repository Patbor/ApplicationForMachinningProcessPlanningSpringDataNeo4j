package org.patbor.pracainzynierska.Repository;

import org.patbor.pracainzynierska.Models.ADTR;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ADTRRepository extends Neo4jRepository<ADTR,Long> {

}

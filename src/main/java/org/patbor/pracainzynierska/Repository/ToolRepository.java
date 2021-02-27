package org.patbor.pracainzynierska.Repository;

import org.patbor.pracainzynierska.Models.Tool;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToolRepository extends Neo4jRepository<Tool,Long> {

}

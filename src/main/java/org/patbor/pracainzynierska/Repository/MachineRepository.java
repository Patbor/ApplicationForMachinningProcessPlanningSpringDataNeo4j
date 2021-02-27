package org.patbor.pracainzynierska.Repository;

import org.patbor.pracainzynierska.Models.Machine;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import javax.crypto.Mac;
import java.util.Optional;

@Repository
public interface MachineRepository extends Neo4jRepository<Machine,Long> {
    Optional<Machine> findByPkidmt(String id);


}

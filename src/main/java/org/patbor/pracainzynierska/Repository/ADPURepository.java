package org.patbor.pracainzynierska.Repository;

import org.patbor.pracainzynierska.Models.ADPU;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;
import java.util.Optional;

public interface ADPURepository extends Neo4jRepository<ADPU,Long> {

    Optional<ADPU> findByIdadpu(String idadpu);


}

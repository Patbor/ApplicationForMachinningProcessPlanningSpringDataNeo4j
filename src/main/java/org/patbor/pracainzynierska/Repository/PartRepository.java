package org.patbor.pracainzynierska.Repository;

import org.patbor.pracainzynierska.Models.Part;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PartRepository extends Neo4jRepository<Part,Long> {

    Optional<Part> getByIdPart(String id);

    @Query("MATCH(n) WITH max(ID(n)) AS max  RETURN max")
    long getMaxIdOfNode();

    @Query("MATCH(part:PART {IDPart: $0}) \n" +
    "SET part.PartName = $1, part.IDMat = $2, part.Mass = $3;")
    void updatePart(String idpart, String partName, String idMat, Double Mass);

    @Query("MATCH(n {IDPart:$0}) DETACH DELETE n")
    void deleteFirstNeighborhoodPart(String idPart);

    @Query("MATCH(n {IDPRJ:$0}) DETACH DELETE n")
    void deleteSecondNeighborhoodPart(String idprj);

    @Query("MATCH(n {IDOP:$0}) DETACH DELETE n")
    void deleteThirdNeighborhoodPart(String idop);

    @Query("MATCH(n {IDSET:$0}) DETACH DELETE n")
    void deleteFourthNeighborhoodPart(String idset);
}

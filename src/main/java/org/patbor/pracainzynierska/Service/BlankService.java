package org.patbor.pracainzynierska.Service;

import org.patbor.pracainzynierska.Models.Blank;
import org.patbor.pracainzynierska.Models.Part;
import org.patbor.pracainzynierska.Repository.BlankRepository;
import org.patbor.pracainzynierska.Repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlankService {
    BlankRepository blankRepository;
    PartService partService;
    PartRepository partRepository;
    Blank finalBlank;

    @Autowired
    public BlankService(BlankRepository blankRepository, PartService partService, PartRepository partRepository) {
        this.blankRepository = blankRepository;
        this.partService = partService;
        this.partRepository = partRepository;
    }


    public void saveBlank(Blank blank) {
        Part part = partService.getPart();
        String partId = part.getIdPart();
        if (blank.getId() == null) {
            Optional<Blank> tempBlank = blankRepository.findByIdbl(blank.getIdbl());
            if (tempBlank.isPresent() || part.getBlank() != null) {
                throw new RuntimeException("Blank already exists or already has a blank");
            } else {
                long id = partRepository.getMaxIdOfNode() + 1;
                blank.setIdp(partId);
                blank.setId(id);
                blankRepository.save(blank);
                blankRepository.createRelationshipBetweenBlankAndPart(partId, blank.getIdbl());
            }
        } else {
            finalBlank = findBlankById(blank.getId());
            blankRepository.updateBlank(blank.getIdbl(), blank.getMaterial(),
                    blank.getDmax(), blank.getDpf(), blank.getTdpf(), blank.getItpf(),
                    blank.getRapf(), blank.getLpf(), blank.getTlpf(),
                    blank.getNp(), blank.getWeight(), blank.getPrice());
        }
    }

    public Blank findBlankById(Long id) {
        return blankRepository.findById(id).get();
    }

    public Blank getFinalBlank() {
        return finalBlank;
    }
}

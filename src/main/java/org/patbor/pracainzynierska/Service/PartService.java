package org.patbor.pracainzynierska.Service;

import org.apache.tomcat.jni.Proc;
import org.patbor.pracainzynierska.Models.Operation;
import org.patbor.pracainzynierska.Models.Part;
import org.patbor.pracainzynierska.Models.Process;
import org.patbor.pracainzynierska.Models.Setup;
import org.patbor.pracainzynierska.Repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PartService {
    private PartRepository partRepository;
    private Part part;
    private ProcessService processService;
    private OperationService operationService;
    private SetupService setupService;

    @Autowired
    public PartService(PartRepository partRepository, ProcessService processService, OperationService operationService, SetupService setupService) {
        this.partRepository = partRepository;
        this.processService = processService;
        this.operationService = operationService;
        this.setupService = setupService;
    }

    public List<Part> getAllParts() {
        return partRepository.findAll().stream()
                .sorted(Comparator.comparing(Part::getIdPart))
                .collect(Collectors.toList());
    }

    public Part getPartByPartId(String id) {
        Optional<Part> part = partRepository.getByIdPart(id);
        if (part.isPresent()) {
            this.part = part.get();

            return this.part;
        } else {
            throw new RuntimeException("Part with ID: " + id + "doesn't exist");
        }
    }

    public void savePart(Part part) {
        String partId = part.getIdPart();
        if (part.getId() == null) {
            long nextIdNode = partRepository.getMaxIdOfNode() + 1;
            part.setId(nextIdNode);
            partRepository.save(part);
        } else {
            Part check = getPartById(part.getId());
            if (!(check == null)) {
                partRepository.updatePart(partId, part.getPartName(), part.getIdMat(), part.getMass());
            }
        }
    }

    public Part getPartById(Long id) {
        Optional<Part> part = partRepository.findById(id);
        return part.get();
    }

    public Part getPart() {
        return this.part;
    }

    public void deletePart(Long id) {
        Part part = partRepository.findById(id).get();
        Process tempProcess = processService.findProcessByPartId(part.getIdPart());
        partRepository.deleteFirstNeighborhoodPart(part.getIdPart());
        if (tempProcess != null) {
            List<Operation> tempOperations = operationService.getOperationWithIDPRJ(tempProcess.getIdprj());
            partRepository.deleteSecondNeighborhoodPart(tempProcess.getIdprj());
            if (tempOperations != null) {
                for (Operation o : tempOperations) {
                    List<Setup> setups = setupService.findSetupWithIdop(o.getIdop());
                    partRepository.deleteThirdNeighborhoodPart(o.getIdop());
                    if(setups != null) {
                        for (Setup s : setups) {
                            partRepository.deleteFourthNeighborhoodPart(s.getIdset());
                        }
                    }
                }
            }
        }
    }
}




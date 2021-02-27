package org.patbor.pracainzynierska.Service;

import org.patbor.pracainzynierska.Models.Blank;
import org.patbor.pracainzynierska.Models.Part;
import org.patbor.pracainzynierska.Models.Process;
import org.patbor.pracainzynierska.Repository.PartRepository;
import org.patbor.pracainzynierska.Repository.ProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProcessService {
    ProcessRepository processRepository;
    PartRepository partRepository;
    Blank blank;

    @Autowired
    public ProcessService(ProcessRepository processRepository, PartRepository partRepository) {
        this.processRepository = processRepository;
        this.partRepository = partRepository;
    }

    public List<Process> getAllProcess() {
        return processRepository.findAll();
    }

    public void saveProcess(Part part, Process process) {
        String idpo = part.getIdPart();
        String idprj = process.getIdprj();
        String idbl = blank.getIdbl();
        Optional<Process> tempPro = processRepository.findProcessByIdprj(idprj);
        if (process.getId() == null) {
            if (tempPro.isPresent() || part.getProcess() != null) {
                throw new RuntimeException("Process already exists or already has a process");
            } else {
                long id = partRepository.getMaxIdOfNode() + 1;
                process.setId(id);
                process.setIdPart(idpo);
                process.setIdBlank(idbl);
                processRepository.save(process);
                processRepository.createRelationshipBetweenPartAndProcess(idpo, idprj);
                processRepository.createRelationshipBetweenProcessAndBlank(idprj, idbl);
            }
        } else {
            processRepository.updateProcess(process.getIdprj(), process.getDevelopedBy(), process.getCheckedBy(), process.getApprovedBy());
        }
    }

    public Process findProcessById(Long id) {
        Optional<Process> process = processRepository.findById(id);
        return process.get();

    }

    public Process findProcessByIdprj(String idprj) {
        Optional<Process> process = processRepository.findProcessByIdprj(idprj);

        return process.get();
    }

    public Process findProcessByPartId(String partId) {
        Optional<Process> optProcess = processRepository.findProcessByIdPart(partId);
        if(optProcess.isPresent()) {
            return optProcess.get();
        } else {
            return null;
        }
    }
    public void setBlank(Blank blank) {
        this.blank = blank;
    }
}

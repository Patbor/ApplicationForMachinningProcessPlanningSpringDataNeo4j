package org.patbor.pracainzynierska.Service;

import org.patbor.pracainzynierska.Models.ADTR;
import org.patbor.pracainzynierska.Models.Tool;
import org.patbor.pracainzynierska.Repository.ToolRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToolService {
    ToolRepository toolRepository;

    public ToolService(ToolRepository toolRepository) {
        this.toolRepository = toolRepository;
    }

    public List<Tool> findAllTools() {
        List<Tool> adtrs = toolRepository.findAll();
        return adtrs;
    }
}

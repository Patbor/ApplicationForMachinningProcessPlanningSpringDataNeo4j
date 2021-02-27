package org.patbor.pracainzynierska.Service;

import org.patbor.pracainzynierska.Models.ADTR;
import org.patbor.pracainzynierska.Repository.ADTRRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ADTRService {
    ADTRRepository adtrRepository;

    public ADTRService(ADTRRepository adtrRepository) {
        this.adtrRepository = adtrRepository;
    }

    public List<ADTR> findAllADTR() {
        List<ADTR> adtrs = adtrRepository.findAll();
        return adtrs;
    }
}

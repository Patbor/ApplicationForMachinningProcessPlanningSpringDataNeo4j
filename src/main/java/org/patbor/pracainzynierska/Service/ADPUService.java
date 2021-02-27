package org.patbor.pracainzynierska.Service;

import org.patbor.pracainzynierska.Models.ADPU;
import org.patbor.pracainzynierska.Repository.ADPURepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ADPUService {
    ADPURepository adpuReposiotry;

    public ADPUService(ADPURepository adpuReposiotry) {
        this.adpuReposiotry = adpuReposiotry;
    }

    public ADPU findADPUById(String idadpu) {
        Optional<ADPU> adpu = adpuReposiotry.findByIdadpu(idadpu);
        if(!adpu.isPresent()) {
            throw new RuntimeException("ADPU WITH ID " + idadpu + " doesn't exist");
        }
       return adpu.get();
    }

    public List<ADPU> findAllADPUs() {
       List<ADPU> adpus = adpuReposiotry.findAll();
       return adpus;
    }
}

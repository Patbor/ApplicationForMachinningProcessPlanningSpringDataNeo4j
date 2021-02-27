package org.patbor.pracainzynierska.Service;


import org.patbor.pracainzynierska.Models.ListOfEq;
import org.patbor.pracainzynierska.Models.Setup;
import org.patbor.pracainzynierska.Repository.ListOfEqRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListOfEqService {
    ListOfEqRepository listOfEqRepository;
    SetupService setupService;

    @Autowired
    public ListOfEqService(ListOfEqRepository listOfEqRepository, SetupService setupService) {
        this.listOfEqRepository = listOfEqRepository;
        this.setupService = setupService;
    }

    public List<ListOfEq> findListOfEqByIDSet(String idset) {
        List<ListOfEq> temp = listOfEqRepository.findAllByIdset(idset);
        return temp;
    }
    public void saveListOfEq(String ideq,String idset) {
        List<ListOfEq> all = listOfEqRepository.findAll();
        all.sort(Comparator.comparing(ListOfEq::getIdpeq).reversed());
        ListOfEq listOfEq = new ListOfEq();
        if(all.size() == 0) {
            listOfEq.setIdpeq(1);
        } else {
            listOfEq.setIdpeq(all.get(0).getIdpeq() + 1);
        }
        listOfEq.setIdeq(ideq);
        listOfEq.setIdset(idset);
        listOfEqRepository.save(listOfEq);
        listOfEqRepository.createRelationshipBetweenSetupAndEqNeed(idset,listOfEq.getIdpeq());
        listOfEqRepository.createRelationshipBetweenEquipmentAndEqNeed(ideq,listOfEq.getIdpeq());
    }

     public void deleteByID(String idset, String ideq) {
        listOfEqRepository.deleteListOfEq(idset, ideq);
     }
}

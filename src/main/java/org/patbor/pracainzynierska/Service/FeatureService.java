package org.patbor.pracainzynierska.Service;

import org.patbor.pracainzynierska.Models.Feature;
import org.patbor.pracainzynierska.Models.Part;
import org.patbor.pracainzynierska.Repository.FeatureRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FeatureService {
    FeatureRepository featureRepository;
    PartService partService;

    public FeatureService(FeatureRepository featureRepository, PartService partService) {
        this.featureRepository = featureRepository;
        this.partService = partService;
    }

    public void saveFeature(Feature feature) {
        Part part = partService.getPart();
        String idPart = part.getIdPart();
        if (feature.getId() == null) {
            if (checkIfExists(feature)) {
                String idftr = createIDFtr(part);
                feature.setIdftr(idftr);
                feature.setIdPart(idPart);
                feature.setFtrno("#" + sizeOfFeatureList(part));
                featureRepository.save(feature);
                featureRepository.createRelationshipBetweenPartAndFeature(idPart, idftr);
            }
        } else {
            Feature ft = featureRepository.findById(feature.getId()).get();
            featureRepository.updateFeature(ft.getIdftr(), feature.getFtrtype(), feature.getDiameter(),
                    feature.getLength(), feature.getDepth(), feature.getWidth(), feature.getRadius(),
                    feature.getAngle(), feature.getFit(), feature.getIt(), feature.getRa());
        }
    }

    private boolean checkIfExists(Feature feature) {
        Optional<Feature> tempF = featureRepository.findByIdftr(feature.getIdftr());
        if (tempF.isPresent()) {
            throw new RuntimeException("Feature already exist");
        } else {
            return true;
        }
    }

    private String findNumberOfPart(String idPart) {
        String number = idPart.substring(idPart.indexOf("t") + 1);
        return number;

    }

    private String createIDFtr(Part part) {
        StringBuilder idftr = new StringBuilder("P");
        idftr.append(findNumberOfPart(part.getIdPart()));
        idftr.append("F");
        idftr.append(createEndOfIDFtr(part));

        return idftr.toString();
    }

    private int sizeOfFeatureList(Part part) {
        List<Feature> features = part.getFeatures();

        if (features == null) {
            return 1;
        }
        return features.size() + 1;
    }

    private String createEndOfIDFtr(Part part) {
        StringBuilder temp = new StringBuilder();
        List<Feature> features = part.getFeatures();
        int size = features.size();
        if (size == 0) {

            return temp.append("001").toString();
        }
        if (size < 10) {
            temp.append("00");
            temp.append(size + 1);
        } else if (size < 100) {
            temp.append("0");
            temp.append(size + 1);
        } else {
            temp.append(size + 1);
        }
        return temp.toString();
    }

    public List<Feature> findAllFeatures() {
        List<Feature> features = featureRepository.findAll().
                stream().
                sorted(Comparator.comparing(Feature::getIdftr)).
                collect(Collectors.toList());
        return features;
    }

    public Feature findFeatureById(Long id) {
        return featureRepository.findById(id).get();
    }

    public void deleteFeature(Long id) {

        featureRepository.deleteById(findFeatureById(id).getIdftr());
    }
}

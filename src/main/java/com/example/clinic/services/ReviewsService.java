package com.example.clinic.services;

import com.example.clinic.dto.GetReviewsDTO;
import com.example.clinic.dto.ReviewsDTO;
import com.example.clinic.entity.Clinic;
import com.example.clinic.entity.Patient;
import com.example.clinic.entity.Reviews;
import com.example.clinic.exception.ResourceNotFound;
import com.example.clinic.mapper.ReviewsMapper;
import com.example.clinic.persistence.ClinicRepository;
import com.example.clinic.persistence.PatientRepository;
import com.example.clinic.persistence.ReviewsRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewsService {

    private  ReviewsMapper reviewsMapper=new ReviewsMapper();
private final ReviewsRepository reviewsRepository;
private final PatientRepository patientRepository;
private final ClinicRepository clinicRepository;
    public ReviewsService(ReviewsRepository reviewsRepository, PatientRepository patientRepository, ClinicRepository clinicRepository) {
        this.reviewsRepository = reviewsRepository;
        this.patientRepository = patientRepository;
        this.clinicRepository = clinicRepository;
    }
    public ReviewsDTO addReviews(ReviewsDTO reviewsDTO)throws ResourceNotFound {
String lastName=reviewsDTO.getLastName();
String firstName= reviewsDTO.getFirstName();
String clinicName=reviewsDTO.getClinicName();
Optional<Patient>patientOptional=patientRepository.findByLastNameAndFirstName(lastName,firstName);
Patient patient=patientOptional.orElseThrow(()->new ResourceNotFound("Object not found"));
Optional<Clinic>clinicOptional=clinicRepository.findByClinicName(clinicName);
Clinic clinic=clinicOptional.orElseThrow(()->new ResourceNotFound("cannot find Clinic with name:"+clinicName));
reviewsDTO.setDateOfReviews(LocalDate.now());
Reviews reviews=reviewsMapper.toEntity(reviewsDTO);
reviews.setPatient(patient);
patient.getReviewsList().add(reviews);
        reviews.setClinic(clinic);
        reviewsRepository.save(reviews);

        return reviewsDTO;

}

public List<GetReviewsDTO> showAllReviews()throws ResourceNotFound{
    ModelMapper modelMapper=new ModelMapper();
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

    List<Reviews>reviewsList=reviewsRepository.findAll(Sort.by(Sort.Direction.ASC).by("reviewDate"));
    if (reviewsList.isEmpty()){
        new ResourceNotFound("List is Empty");
    }
    List<GetReviewsDTO>reviewsDTOList=new ArrayList<>();
    reviewsList.forEach(reviews -> {
        reviewsDTOList.add(modelMapper.map(reviews,GetReviewsDTO.class));
    });
    return reviewsDTOList;
}
}

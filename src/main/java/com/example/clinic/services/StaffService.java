package com.example.clinic.services;

import com.example.clinic.dto.StaffDTO;
import com.example.clinic.entity.Address;
import com.example.clinic.entity.Staff;
import com.example.clinic.exception.ResourceNotFound;
import com.example.clinic.mapper.StaffMapper;
import com.example.clinic.persistence.AddressRepository;
import com.example.clinic.persistence.StaffRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StaffService {

   final private StaffRepository staffRepository;
   final private AddressRepository addressRepository;
    private StaffMapper staffMapper=new StaffMapper();

    public StaffService(StaffRepository staffRepository, AddressRepository addressRepository) {
        this.staffRepository = staffRepository;
        this.addressRepository = addressRepository;
    }


    //
    public ResponseEntity addNewMember(StaffDTO staffDTO){
        List<Staff>staffList=staffRepository.findAll();
        for (Staff staff:staffList){
            if (staffDTO.getLastName().equalsIgnoreCase(staff.getLastName()))
                if (staffDTO.getFirstName().equalsIgnoreCase(staff.getFirstName())){
                    return new ResponseEntity("Staff with name :"+staff.getFirstName()+" "+staff.getLastName(), HttpStatus.FOUND);
                }
        }
        Address address=new Address();
        address.setCity(staffDTO.getCity());
        address.setCountry(staffDTO.getCountry());
        address.setState(staffDTO.getState());
        address.setStreet(staffDTO.getStreet());
        address.setZipcode(staffDTO.getZipcode());
        addressRepository.save(address);
        Staff staff=staffMapper.toEntity(staffDTO);
        staff.setAddress(address);
        staff.setDateOfRegistration(LocalDate.now());
        return new ResponseEntity<>(staffRepository.save(staff),HttpStatus.CREATED);
    }
    //get all Staff
   public List<StaffDTO>findAll()throws ResourceNotFound{
        List<Staff>staffList=staffRepository.findAll(Sort.by("lastName"));
        if (staffList.isEmpty()){
            new ResourceNotFound("List is Empty");
        }
        return staffMapper.toDto(staffList);
   }
    // find by name and Date of Birth
  public StaffDTO findMemberByNameAndDateOfBirth(String lastName, String firstName, LocalDate date)throws ResourceNotFound{
        Optional<Staff> staff=staffRepository.findByLastNameAndFirstNameAndDateOfBirth(lastName, firstName, date);
        Staff staff1=staff.orElseThrow(()->new ResourceNotFound("Object not found !"));
        return staffMapper.toDto(staff1);



  }
  public List<StaffDTO>findStaffsByJob(String job,int page,int size)throws ResourceNotFound{
      Pageable pageable= PageRequest.of(page, size);
        List<Staff>staffList=staffRepository.findAllByJob(job,pageable);
        if (staffList.isEmpty()){
            new ResourceNotFound("List is empty!");
        }
        return staffMapper.toDto(staffList);
  }

    public void deleteStaffById(Long id)throws ResourceNotFound{
        Staff staff=staffRepository.findById(id).stream().findFirst().orElseThrow(()->new ResourceNotFound("Staff with Id:"+id+" not found"));
        staffRepository.delete(staff);
    }
    public StaffDTO updateStaffInfo(StaffDTO staffDTO,Long id)throws ResourceNotFound{
     Staff staff=staffRepository.findById(id).map(staff1 -> {
         staff1.setJob(staffDTO.getJob());
         staff1.getAddress().setCity(staffDTO.getCity());
         staff1.getAddress().setStreet(staffDTO.getStreet());
         staff1.getAddress().setState(staffDTO.getState());
         staff1.getAddress().setCountry(staffDTO.getCountry());
         staff1.getAddress().setZipcode(staffDTO.getZipcode());
         staff1.setPhoneNumber(staffDTO.getPhoneNumber());
         return staffRepository.save(staff1);
     }).orElseThrow(()->new ResourceNotFound("Staff with Id:"+id+" not found"));
     return staffMapper.toDto(staff);
    }
}

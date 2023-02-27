package com.example.clinic.controllers;

import com.example.clinic.dto.StaffDTO;
import com.example.clinic.services.StaffService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class StaffResource {

    private final StaffService staffService;

    public StaffResource(StaffService staffService) {
        this.staffService = staffService;
    }

    @PostMapping(value = "/staff",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addNewResource(@RequestBody StaffDTO staffDTO){
        return staffService.addNewMember(staffDTO);
    }
    @GetMapping(value = "/staffs")
    public List<StaffDTO>showAllStaff(){
        return staffService.findAll();
    }
    @GetMapping(value = "staffs/")
    public StaffDTO findByNameAndBirthDay(@RequestParam("lastName")String lastName, @RequestParam("firstName")String firstName, @RequestParam("dateOfBirth")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE,pattern ="dd/MM/yyyy" )LocalDate date){
        return staffService.findMemberByNameAndDateOfBirth(lastName, firstName, date);
    }
    @GetMapping(value = "/Staffs/")
    public List<StaffDTO>findByJob(@RequestParam("job")String job,@RequestParam("size")int size,@RequestParam("pageNo")int pageNo){
        return staffService.findStaffsByJob(job, size, pageNo);
    }
    @PutMapping(value = "/update/{id}/staff")
    public StaffDTO changeStaffInfo(@PathVariable("id")Long id,StaffDTO staffDTO){
        return staffService.updateStaffInfo(staffDTO, id);
    }
    @DeleteMapping(value = "/delete/{id}")
    public void deleteResourceById(@PathVariable("id")Long id){
        staffService.deleteStaffById(id);
    }
}

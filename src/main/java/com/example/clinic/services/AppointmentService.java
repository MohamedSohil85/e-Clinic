package com.example.clinic.services;

import com.example.clinic.dto.AppointmentDTO;
import com.example.clinic.entity.Appointment;
import com.example.clinic.entity.Patient;
import com.example.clinic.exception.ResourceNotFound;
import com.example.clinic.mapper.AppointmentMapper;
import com.example.clinic.persistence.AppointmentRepository;
import com.example.clinic.persistence.PatientRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    private final AppointmentMapper appointmentMapper =new AppointmentMapper();
    final  AppointmentRepository appointmentRepository;
    final PatientRepository patientRepository;

    public AppointmentService(AppointmentRepository appointmentRepository,  PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;

    }

    public ResponseEntity saveNewAppointment(AppointmentDTO appointmentDTO)throws ResourceNotFound {
        String lastName= appointmentDTO.getLastName();
        String firstName=appointmentDTO.getFirstName();
        Optional<Patient>optionalPatient=patientRepository.findByLastNameAndFirstName(lastName,firstName);
        if (!optionalPatient.isPresent()){
            new ResourceNotFound("Patient cannot found");
        }
        Patient patient=optionalPatient.get();
        LocalDate appointmentDate=appointmentDTO.getAppointmentDate();
        LocalTime appointmentTime=appointmentDTO.getAppointmentTime();
        Appointment appointment_=appointmentRepository.findAppointmentByAppointmentDateAndAppointmentTime(appointmentDate,appointmentTime);
        if (appointment_.getId() !=null){
            new ResponseEntity<>(HttpStatus.FOUND);
        }
        Optional<Appointment> app=appointmentRepository.findAppointmentByAppointmentDateAndPatient(appointmentDate,patient);
        if (app.isPresent()){
            new ResponseEntity<>("you have Appointment today",HttpStatus.FOUND);
        }
        Appointment appointment=appointmentMapper.toEntity(appointmentDTO);
        appointment.setPatient(patient);
        patient.setAppointments(Arrays.asList(appointment));

        return new ResponseEntity<>(appointmentRepository.save(appointment),HttpStatus.CREATED);
    }
   public List<AppointmentDTO>showAllAppointments(LocalTime begin, LocalTime end, LocalDate date){
        List<Appointment>appointments=appointmentRepository.findAppointmentsByAppointmentTimeGreaterThanEqualAndAppointmentTimeLessThanEqualAndAppointmentDate(begin, end, date);
       return appointmentMapper.toDto(appointments);
    }
    public List<AppointmentDTO>showAllAppointmentsByDate(LocalDate date,int page,int size){
        Pageable pageable= PageRequest.of(page, size);
        List<Appointment>appointments=appointmentRepository.findAppointmentsByAppointmentDate(date,pageable);
        return appointmentMapper.toDto(appointments);
    }
    public ResponseEntity findAppointmentByDateAndTime(LocalDate date,LocalTime time){
        Appointment appointment=appointmentRepository.findAppointmentByAppointmentDateAndAppointmentTime(date, time);
        if(appointment.getId()==null){
            return new ResponseEntity("Appointment is not reserved", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(appointmentMapper.toDto(appointment),HttpStatus.FOUND);
    }

    public ResponseEntity findAppointmentByPatient(String lastName,String firstName,LocalDate date){

        Optional<Patient> patient=patientRepository.findByLastNameAndFirstName(lastName, firstName);

        if (!patient.isPresent()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        Patient patient_=patient.get();
        Optional<Appointment> appointment=appointmentRepository.findAppointmentByAppointmentDateAndPatient(date,patient_);
        return new ResponseEntity<>(appointmentMapper.toDto(appointment.get()),HttpStatus.FOUND);
    }
    public void cancelAppointment(String lastName,String firstName,LocalDate date)throws ResourceNotFound{
        Optional<Patient>optionalPatient=patientRepository.findByLastNameAndFirstName(lastName, firstName);
        Patient patient=optionalPatient.orElseThrow(()->new ResourceNotFound("Patient could not found"));
        appointmentRepository.deleteAppointmentByPatientAndAppointmentDate(patient,date);

    }
    public List<AppointmentDTO>findAppointsByPatientName(String lastName,String firstName)throws ResourceNotFound{
        Optional<Patient>optionalPatient=patientRepository.findByLastNameAndFirstName(lastName, firstName);
        Patient patient=optionalPatient.orElseThrow(()->new ResourceNotFound("Patient could not found"));
        List<Appointment>appointments=appointmentRepository.findAppointmentsByPatientOrderByAppointmentDate(patient);
        return appointmentMapper.toDto(appointments);
    }

    // change Appointment date by id
   public AppointmentDTO changeAppointmentById(Long id,AppointmentDTO appointmentDTO)throws ResourceNotFound{
        Optional<Appointment>optionalAppointment=appointmentRepository.findById(id);
        Appointment appointment_=optionalAppointment.map(appointment ->{
            appointment.setAppointmentDate(appointmentDTO.getAppointmentDate());
            appointment.setAppointmentTime(appointmentDTO.getAppointmentTime());
            appointment.setReason(appointment.getReason());
            return appointmentRepository.save(appointment);
        } ).orElseThrow(()->new ResourceNotFound("Appointment could not found"));
        return appointmentMapper.toDto(appointment_);
   }

    // change Appointment date by patientname and date of appointment

    public AppointmentDTO changeAppointmentByPatientName(String lastName,String firstName,LocalDate date,AppointmentDTO appointmentDTO)throws ResourceNotFound{
        Optional<Patient>optionalPatient=patientRepository.findByLastNameAndFirstName(lastName, firstName);
        Patient patient=optionalPatient.orElseThrow(()->new ResourceNotFound("Patient not found!"));
        Appointment appointment_=appointmentRepository.findAppointmentByAppointmentDateAndPatient(date,patient).map(appointment ->{
            appointment.setAppointmentDate(appointmentDTO.getAppointmentDate());
            appointment.setAppointmentTime(appointmentDTO.getAppointmentTime());
            appointment.setReason(appointmentDTO.getReason());
            return appointmentRepository.save(appointment);
        } ).orElseThrow(()->new ResourceNotFound("Object not found!"));
        return appointmentMapper.toDto(appointment_);
    }
}

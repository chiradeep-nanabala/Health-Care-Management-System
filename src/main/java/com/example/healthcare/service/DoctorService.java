package com.example.healthcare.service;

import com.example.healthcare.model.Doctor;
import com.example.healthcare.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;
    public List<Doctor> getAllDoctors(){
        return doctorRepository.findAll();
    }
    public Doctor getDoctorById(Long id){
        return doctorRepository.findById(id).orElse(null);
    }
    public void saveDoctor(Doctor doctor){
        doctorRepository.save(doctor);
    }
    public void deleteDoctor(Long id){
        doctorRepository.deleteById(id);
    }

}

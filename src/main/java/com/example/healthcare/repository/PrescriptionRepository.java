package com.example.healthcare.repository;

import com.example.healthcare.model.Appointment;
import com.example.healthcare.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    List<Prescription> findByPatientId(Long patientId);
    List<Appointment> findByDoctorId(Long DoctorId);
}

package com.example.healthcare.controller;

import com.example.healthcare.model.Prescription;
import com.example.healthcare.service.PrescriptionService;
import com.example.healthcare.service.DoctorService;
import com.example.healthcare.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/prescriptions")
public class PrescriptionController {
    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public String listPrescriptions(Model model) {
        model.addAttribute("prescriptions", prescriptionService.getAllPrescriptions());
        return "prescriptions/list";
    }

    @GetMapping("/new")
    public String newPrescriptionForm(Model model) {
        model.addAttribute("prescription", new Prescription());
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "prescriptions/new";
    }

    @PostMapping
    public String savePrescription(@ModelAttribute Prescription prescription) {
        prescriptionService.savePrescription(prescription);
        return "redirect:/prescriptions";
    }

    @GetMapping("/edit/{id}")
    public String editPrescriptionForm(@PathVariable Long id, Model model) {
        Prescription prescription = prescriptionService.getPrescriptionById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid prescription Id:" + id));
        model.addAttribute("prescription", prescription);
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "prescriptions/edit";
    }

    @PostMapping("/update/{id}")
    public String updatePrescription(@PathVariable Long id, @ModelAttribute Prescription prescription) {
        prescription.setId(id);
        prescriptionService.savePrescription(prescription);
        return "redirect:/prescriptions";
    }

    @GetMapping("/delete/{id}")
    public String deletePrescription(@PathVariable Long id) {
        prescriptionService.deletePrescription(id);
        return "redirect:/prescriptions";
    }
}

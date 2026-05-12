package com.example.citas.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.citas.dto.CitaRequestDTO;
import com.example.citas.dto.CitaResponseDTO;
import com.example.citas.model.Cita;
import com.example.citas.model.EstadoCita;
import com.example.citas.repository.CitaRepository;

@Service
public class CitaService {

    private final CitaRepository citaRepository;

    public CitaService(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    @Transactional
    public CitaResponseDTO agendar(CitaRequestDTO req) {
        LocalDateTime fechaHora = req.getFechaHora();
        boolean ocupado = citaRepository.existsByDoctorIdAndFechaHoraAndEstado(req.getDoctorId(), fechaHora, EstadoCita.AGENDADA);
        if (ocupado) throw new IllegalStateException("Slot no disponible");

        Cita cita = new Cita();
        cita.setDoctorId(req.getDoctorId());
        cita.setPacienteId(req.getPacienteId());
        cita.setFechaHora(fechaHora);
        cita.setMotivo(req.getMotivo());
        cita.setEstado(EstadoCita.AGENDADA);

        Cita saved = citaRepository.save(cita);

        return toResponse(saved);
    }

    @Transactional(readOnly = true)
    public List<CitaResponseDTO> listar() {
        List<Cita> citas = citaRepository.findAll();
        List<CitaResponseDTO> response = new ArrayList<>();

        for (Cita c : citas) {
            response.add(toResponse(c));
        }

        return response;
    }

    @Transactional(readOnly = true)
    public List<LocalTime> disponibilidad(Long doctorId, LocalDate fecha) {
        List<LocalTime> slots = new ArrayList<>();
        LocalTime start = LocalTime.of(8,0);
        LocalTime end = LocalTime.of(17,0);
        int step = 30;

        LocalTime cursor = start;
        while (!cursor.isAfter(end.minusMinutes(step))) {
            LocalDateTime slotDateTime = LocalDateTime.of(fecha, cursor);
            boolean ocupado = citaRepository.existsByDoctorIdAndFechaHoraAndEstado(doctorId, slotDateTime, EstadoCita.AGENDADA);
            if (!ocupado) slots.add(cursor);
            cursor = cursor.plusMinutes(step);
        }
        return slots;
    }

    @Transactional
    public void cancelar(Long citaId) {
        Cita cita = citaRepository.findById(citaId).orElseThrow(() -> new IllegalArgumentException("Cita no encontrada"));
        cita.setEstado(EstadoCita.CANCELADA);
        citaRepository.save(cita);
    }

    private CitaResponseDTO toResponse(Cita cita) {
        CitaResponseDTO r = new CitaResponseDTO();
        r.setId(cita.getId());
        r.setPacienteId(cita.getPacienteId());
        r.setDoctorId(cita.getDoctorId());
        r.setFechaHora(cita.getFechaHora());
        r.setEstado(cita.getEstado());
        r.setMotivo(cita.getMotivo());
        return r;
    }
}

package com.example.citas.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.citas.model.Cita;
import com.example.citas.model.EstadoCita;

public interface CitaRepository extends JpaRepository<Cita, Long> {

    boolean existsByDoctorIdAndFechaHoraAndEstado(Long doctorId, LocalDateTime fechaHora, EstadoCita estado);
}

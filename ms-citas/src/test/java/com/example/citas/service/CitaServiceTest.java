package com.example.citas.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.citas.dto.CitaRequestDTO;
import com.example.citas.dto.CitaResponseDTO;
import com.example.citas.model.Cita;
import com.example.citas.model.EstadoCita;
import com.example.citas.repository.CitaRepository;

@ExtendWith(MockitoExtension.class)
class CitaServiceTest {

    @Mock
    private CitaRepository citaRepository;

    @InjectMocks
    private CitaService citaService;

    @Test
    void agendarGuardaYDevuelveCita() {
        CitaRequestDTO request = new CitaRequestDTO();
        request.setPacienteId(1L);
        request.setDoctorId(2L);
        request.setFechaHora(LocalDateTime.of(2026, 6, 16, 9, 0));
        request.setMotivo("dolor de pecho");

        Cita saved = new Cita();
        saved.setId(7L);
        saved.setPacienteId(request.getPacienteId());
        saved.setDoctorId(request.getDoctorId());
        saved.setFechaHora(request.getFechaHora());
        saved.setMotivo(request.getMotivo());
        saved.setEstado(EstadoCita.AGENDADA);

        when(citaRepository.existsByDoctorIdAndFechaHoraAndEstado(2L, request.getFechaHora(), EstadoCita.AGENDADA)).thenReturn(false);
        when(citaRepository.save(org.mockito.ArgumentMatchers.any(Cita.class))).thenReturn(saved);

        CitaResponseDTO response = citaService.agendar(request);

        assertEquals(7L, response.getId());
        assertEquals(1L, response.getPacienteId());
        assertEquals(2L, response.getDoctorId());
        assertEquals(request.getFechaHora(), response.getFechaHora());
        assertEquals(EstadoCita.AGENDADA, response.getEstado());

        ArgumentCaptor<Cita> captor = ArgumentCaptor.forClass(Cita.class);
        verify(citaRepository).save(captor.capture());
        assertEquals("dolor de pecho", captor.getValue().getMotivo());
    }

    @Test
    void disponibilidadOmitioLosSlotsOcupados() {
        LocalDate fecha = LocalDate.of(2026, 6, 16);
        when(citaRepository.existsByDoctorIdAndFechaHoraAndEstado(1L, LocalDateTime.of(fecha, LocalTime.of(8, 0)), EstadoCita.AGENDADA)).thenReturn(true);
        when(citaRepository.existsByDoctorIdAndFechaHoraAndEstado(1L, LocalDateTime.of(fecha, LocalTime.of(8, 30)), EstadoCita.AGENDADA)).thenReturn(false);
        when(citaRepository.existsByDoctorIdAndFechaHoraAndEstado(1L, LocalDateTime.of(fecha, LocalTime.of(9, 0)), EstadoCita.AGENDADA)).thenReturn(false);
        when(citaRepository.existsByDoctorIdAndFechaHoraAndEstado(1L, LocalDateTime.of(fecha, LocalTime.of(9, 30)), EstadoCita.AGENDADA)).thenReturn(false);
        when(citaRepository.existsByDoctorIdAndFechaHoraAndEstado(1L, LocalDateTime.of(fecha, LocalTime.of(10, 0)), EstadoCita.AGENDADA)).thenReturn(false);
        when(citaRepository.existsByDoctorIdAndFechaHoraAndEstado(1L, LocalDateTime.of(fecha, LocalTime.of(10, 30)), EstadoCita.AGENDADA)).thenReturn(false);
        when(citaRepository.existsByDoctorIdAndFechaHoraAndEstado(1L, LocalDateTime.of(fecha, LocalTime.of(11, 0)), EstadoCita.AGENDADA)).thenReturn(false);
        when(citaRepository.existsByDoctorIdAndFechaHoraAndEstado(1L, LocalDateTime.of(fecha, LocalTime.of(11, 30)), EstadoCita.AGENDADA)).thenReturn(false);
        when(citaRepository.existsByDoctorIdAndFechaHoraAndEstado(1L, LocalDateTime.of(fecha, LocalTime.of(12, 0)), EstadoCita.AGENDADA)).thenReturn(false);
        when(citaRepository.existsByDoctorIdAndFechaHoraAndEstado(1L, LocalDateTime.of(fecha, LocalTime.of(12, 30)), EstadoCita.AGENDADA)).thenReturn(false);
        when(citaRepository.existsByDoctorIdAndFechaHoraAndEstado(1L, LocalDateTime.of(fecha, LocalTime.of(13, 0)), EstadoCita.AGENDADA)).thenReturn(false);
        when(citaRepository.existsByDoctorIdAndFechaHoraAndEstado(1L, LocalDateTime.of(fecha, LocalTime.of(13, 30)), EstadoCita.AGENDADA)).thenReturn(false);
        when(citaRepository.existsByDoctorIdAndFechaHoraAndEstado(1L, LocalDateTime.of(fecha, LocalTime.of(14, 0)), EstadoCita.AGENDADA)).thenReturn(false);
        when(citaRepository.existsByDoctorIdAndFechaHoraAndEstado(1L, LocalDateTime.of(fecha, LocalTime.of(14, 30)), EstadoCita.AGENDADA)).thenReturn(false);
        when(citaRepository.existsByDoctorIdAndFechaHoraAndEstado(1L, LocalDateTime.of(fecha, LocalTime.of(15, 0)), EstadoCita.AGENDADA)).thenReturn(false);
        when(citaRepository.existsByDoctorIdAndFechaHoraAndEstado(1L, LocalDateTime.of(fecha, LocalTime.of(15, 30)), EstadoCita.AGENDADA)).thenReturn(false);
        when(citaRepository.existsByDoctorIdAndFechaHoraAndEstado(1L, LocalDateTime.of(fecha, LocalTime.of(16, 0)), EstadoCita.AGENDADA)).thenReturn(false);
        when(citaRepository.existsByDoctorIdAndFechaHoraAndEstado(1L, LocalDateTime.of(fecha, LocalTime.of(16, 30)), EstadoCita.AGENDADA)).thenReturn(false);

        List<LocalTime> slots = citaService.disponibilidad(1L, fecha);

        assertEquals(17, slots.size());
        org.junit.jupiter.api.Assertions.assertFalse(slots.contains(LocalTime.of(8, 0)));
        org.junit.jupiter.api.Assertions.assertTrue(slots.contains(LocalTime.of(8, 30)));
    }

    @Test
    void cancelarCambiaEstadoACancelada() {
        Cita cita = new Cita();
        cita.setId(10L);
        cita.setEstado(EstadoCita.AGENDADA);
        when(citaRepository.findById(10L)).thenReturn(Optional.of(cita));
        when(citaRepository.save(org.mockito.ArgumentMatchers.any(Cita.class))).thenAnswer(invocation -> invocation.getArgument(0));

        citaService.cancelar(10L);

        ArgumentCaptor<Cita> captor = ArgumentCaptor.forClass(Cita.class);
        verify(citaRepository).save(captor.capture());
        assertEquals(EstadoCita.CANCELADA, captor.getValue().getEstado());
    }

    @Test
    void cancelarCuandoNoExisteLanzaExcepcion() {
        when(citaRepository.findById(10L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> citaService.cancelar(10L));
    }
}
package com.antonio.skybase.services;

import com.antonio.skybase.dtos.AircraftAssignmentDTO;
import com.antonio.skybase.entities.Aircraft;
import com.antonio.skybase.entities.AircraftAssignment;
import com.antonio.skybase.entities.AircraftAssignmentId;
import com.antonio.skybase.entities.Flight;
import com.antonio.skybase.exceptions.BadRequestException;
import com.antonio.skybase.exceptions.NotFoundException;
import com.antonio.skybase.repositories.AircraftAssignmentRepository;
import com.antonio.skybase.repositories.AircraftRepository;
import com.antonio.skybase.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AircraftAssignmentService {
    @Autowired
    private AircraftAssignmentRepository aircraftAssignmentRepository;

    @Autowired
    private AircraftRepository aircraftRepository;

    @Autowired
    private FlightRepository flightRepository;

    public AircraftAssignment create(AircraftAssignmentDTO aircraftAssignmentDTO) {
        Aircraft aircraft = aircraftRepository.findById(aircraftAssignmentDTO.getAircraftId())
                .orElseThrow(() -> new NotFoundException("Aircraft with ID " + aircraftAssignmentDTO.getAircraftId() + " not found"));
        Flight flight = flightRepository.findById(aircraftAssignmentDTO.getFlightId())
                .orElseThrow(() -> new NotFoundException("Flight with ID " + aircraftAssignmentDTO.getFlightId() + " not found"));

        validateAircraftAvailability(aircraftAssignmentDTO.getAircraftId(), aircraftAssignmentDTO.getDate());
        validateAircraftRange(aircraft.getRange(), flight.getDistance());

        AircraftAssignmentId id = new AircraftAssignmentId();
        id.setAircraftId(aircraftAssignmentDTO.getAircraftId());
        id.setFlightId(aircraftAssignmentDTO.getFlightId());
        id.setDate(aircraftAssignmentDTO.getDate());

        AircraftAssignment aircraftAssignment = new AircraftAssignment();
        aircraftAssignment.setId(id);
        aircraftAssignment.setAircraft(aircraft);
        aircraftAssignment.setFlight(flight);

        return aircraftAssignmentRepository.save(aircraftAssignment);
    }

    public List<AircraftAssignment> getAll() {
        return aircraftAssignmentRepository.findAll();
    }

    public AircraftAssignment getById(AircraftAssignmentId id) {
        return aircraftAssignmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("AircraftAssignment with ID " + id + " not found"));
    }

    public List<AircraftAssignment> getAssignmentsByDate(LocalDate date) {
        return aircraftAssignmentRepository.findByIdDate(date);
    }

    public List<AircraftAssignment> getAssignmentsByAircraftAndDateRange(Integer aircraftId, LocalDate startDate, LocalDate endDate) {
        return aircraftAssignmentRepository.findByIdAircraftIdAndIdDateBetween(aircraftId, startDate, endDate);
    }

    public List<AircraftAssignment> getAssignmentsByFlightAndDateRange(Integer flightId, LocalDate startDate, LocalDate endDate) {
        return aircraftAssignmentRepository.findByIdFlightIdAndIdDateBetween(flightId, startDate, endDate);
    }

    public void delete(AircraftAssignmentId id) {
        aircraftAssignmentRepository.deleteById(id);
    }

    private void validateAircraftAvailability(Integer aircraftId, LocalDate date) {
        List<AircraftAssignment> assignments = aircraftAssignmentRepository.findByIdAircraftIdAndIdDateBetween(aircraftId, date, date);
        if (!assignments.isEmpty()) {
            throw new BadRequestException("Aircraft with ID " + aircraftId + " is not available on " + date);
        }
    }

    private void validateAircraftRange(Integer aircraftRange, Integer flightDistance) {
        if (aircraftRange < flightDistance) {
            throw new BadRequestException("Aircraft range is not enough for this flight");
        }
    }
}
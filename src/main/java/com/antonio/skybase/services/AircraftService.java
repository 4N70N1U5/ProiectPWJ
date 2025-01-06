package com.antonio.skybase.services;

import com.antonio.skybase.entities.Aircraft;
import com.antonio.skybase.exceptions.NotFoundException;
import com.antonio.skybase.repositories.AircraftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AircraftService {
    @Autowired
    private AircraftRepository aircraftRepository;

    public Aircraft create(Aircraft aircraft) {
        return aircraftRepository.save(aircraft);
    }

    public List<Aircraft> getAll() {
        return aircraftRepository.findAll();
    }

    public Aircraft getById(Integer id) {
        return aircraftRepository.findById(id).orElseThrow(() -> new NotFoundException("Aircraft with ID " + id + " not found"));
    }

    public Aircraft update(Integer id, Aircraft aircraft) {
        Aircraft aircraftToUpdate = aircraftRepository.findById(id).orElseThrow(() -> new NotFoundException("Aircraft with ID " + id + " not found"));
        aircraftToUpdate.setType(aircraft.getType());
        aircraftToUpdate.setRange(aircraft.getRange());
        aircraftToUpdate.setCapacity(aircraft.getCapacity());
        return aircraftRepository.save(aircraftToUpdate);
    }

    public void delete(Integer id) {
        aircraftRepository.deleteById(id);
    }
}
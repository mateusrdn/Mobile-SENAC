package com.mobile.pwa.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mobile.pwa.exception.EntityException;
import com.mobile.pwa.model.Reservation;
import com.mobile.pwa.model.Station;
import com.mobile.pwa.repository.StationRepository;

@Service
public class StationService {

	@Autowired
	private StationRepository repository;
	
	@Transactional(readOnly = true)
	public Page<Station> findAllStationPage(Pageable pageable) {	
		 return repository.findAll(pageable); 
	}

	@Transactional(readOnly = true)
	public Station findByIdStation(Long id) {
		Optional<Station> opt = repository.findById(id);
		Station station = opt.orElseThrow(() -> new EntityException("Reservation " + id + " not exist"));
		return station;
	}

	@Transactional
	public Station insertStation(Station station) {
		repository.save(station);
		return station;
	}

	@Transactional
	public Station updateStation(Long id, Station station) {

		try {
			repository.save(station);
			return station;
		} catch (EntityNotFoundException e) {
			throw new EntityException("Id not found " + id);
		}
	}

	public void deleteStation(Long id) {

		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntityException("Id not found " + id);
		}
	}

	
}

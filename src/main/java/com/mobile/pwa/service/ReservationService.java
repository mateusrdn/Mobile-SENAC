package com.mobile.pwa.service;

import java.time.LocalDate;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mobile.pwa.exception.EntityException;
import com.mobile.pwa.exception.ReservationException;
import com.mobile.pwa.model.Reservation;
import com.mobile.pwa.repository.ReservationRepository;
import com.mobile.pwa.repository.StationRepository;

@Service
public class ReservationService {

	@Autowired
	private ReservationRepository repository;

	@Autowired
	private StationRepository stationRepository;

	@Transactional(readOnly = true)
	public Page<Reservation> findAllReservagionPage(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public Reservation findByIdReservation(Long id) {
		Optional<Reservation> opt = repository.findById(id);
		Reservation reservation = opt.orElseThrow(() -> new EntityException("Reservation " + id + " not exist"));
		return reservation;
	}

	@Transactional
	public Reservation insertReservation(Reservation reservation) {

		checkIdStationExist(reservation.getStation().getId());

		checkExistReservationUserdate(reservation.getReservationBy().getId(), reservation.getDateReservation());

		checkReservationExist(reservation.getDateReservation(), reservation.getStation().getId());

		repository.save(reservation);
		return reservation;

	}

	@Transactional
	public Reservation updateReservation(Long idReservation, Reservation reservation) {

		checkIdStationExist(reservation.getStation().getId());

		checkExistReservationUserdate(reservation.getReservationBy().getId(), reservation.getDateReservation());

		checkReservationExist(reservation.getDateReservation(), reservation.getStation().getId());
		
		try {
			repository.save(reservation);
			return reservation;
		} catch (EntityNotFoundException e) {
			throw new EntityException("Id not found " + idReservation);
		}

	}

	@Transactional
	public void canceledReservation(Long id) {
		repository.findById(id).ifPresentOrElse(reservation -> {
			reservation.setCanceled(true);
			repository.save(reservation);
		}, () -> {
			throw new EntityException("Id not found " + id);
		});
	}

	private Long checkIdStationExist(Long id) {

		Long idStation = id;

		if (!stationRepository.existsById(idStation)) {
			throw new ReservationException("The station with id: " + idStation + " does not exist");
		}

		return idStation;
	}

	private Reservation checkExistReservationUserdate(Long id, LocalDate date) {

		Reservation checkExistReservationUserdate = repository.checkExistReservationUserDate(id, date);

		if (checkExistReservationUserdate != null) {
			throw new ReservationException("You have already booked a station on that date");
		}

		return checkExistReservationUserdate;
	} 

	private Reservation checkReservationExist(LocalDate date, Long id) {

		Reservation checkReservationExist = repository.findByReservation(date, id);

		if (checkReservationExist != null) {
			throw new ReservationException("There is already a reservation for that date at that station");
		}

		return checkReservationExist;
	}    

}

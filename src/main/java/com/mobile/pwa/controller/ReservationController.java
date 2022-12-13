package com.mobile.pwa.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mobile.pwa.model.Reservation;
import com.mobile.pwa.service.ReservationService;

@RestController
@RequestMapping("/api")
public class ReservationController {

	@Autowired
	private ReservationService service;

	@GetMapping(value = "/reservations")
	public ResponseEntity<Page<Reservation>> findAll(Pageable pageable) {
		Page<Reservation> paged = service.findAllReservagionPage(pageable);
		return ResponseEntity.ok().body(paged);
	}

	@GetMapping(value = "/reservation/{id}")
	public ResponseEntity<Reservation> findById(@PathVariable Long id) {
		Reservation reservation = service.findByIdReservation(id);
		return ResponseEntity.ok().body(reservation);
	}

	@PostMapping(value = "/reservation")
	public ResponseEntity<Reservation> insert(@Valid @RequestBody Reservation reservation) {
		service.insertReservation(reservation);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(reservation.getId()).toUri();
		return ResponseEntity.created(uri).body(reservation);
	}

	@PutMapping(value = "/reservation/{id}")
	public ResponseEntity<Reservation> update(@PathVariable Long id, @Valid @RequestBody Reservation reservation) {
		service.updateReservation(id, reservation);
		return ResponseEntity.ok().body(reservation);
	}

	@PutMapping(value = "/reservation/canceled/{id}")
	public ResponseEntity<Void> canceledReservatio(@PathVariable Long id) {
		service.canceledReservation(id);
		return ResponseEntity.ok().build();
	}
	
}

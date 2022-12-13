package com.mobile.pwa.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mobile.pwa.model.Station;
import com.mobile.pwa.service.StationService;

@RestController
@RequestMapping("/api")
public class StationController {

	@Autowired
	private StationService service;
	
	@GetMapping(value = "/stations")
	public ResponseEntity<Page<Station>> findAllPage(Pageable pageable) {
		Page<Station> paged = service.findAllStationPage(pageable);
		return ResponseEntity.ok().body(paged);
	}

	@GetMapping(value = "/station/{id}")
	public ResponseEntity<Station> findById(@PathVariable Long id) {
		Station station = service.findByIdStation(id);
		return ResponseEntity.ok().body(station);
	}

	@PostMapping(value = "/station")
	public ResponseEntity<Station> insert(@Valid @RequestBody Station station) {
		service.insertStation(station);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(station.getId()).toUri();
		return ResponseEntity.created(uri).body(station);
	}

	@PutMapping(value = "/station/{id}")
	public ResponseEntity<Station> update(@PathVariable Long id, @Valid @RequestBody Station station) {
		service.updateStation(id, station);
		return ResponseEntity.ok().body(station);
	}

	@DeleteMapping(value = "/station/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.deleteStation(id);
		return ResponseEntity.noContent().build();
	}
}

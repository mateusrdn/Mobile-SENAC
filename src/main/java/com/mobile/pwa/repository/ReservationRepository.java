package com.mobile.pwa.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mobile.pwa.model.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>{
	
	@Query(value = "SELECT r FROM tb_reservation r WHERE r.dateReservation = :date AND r.station.id = :id AND r.isCanceled = false") 
	Reservation findByReservation(LocalDate date, Long id);  
	
	@Query(value = "SELECT r FROM tb_reservation r WHERE r.reservationBy.id = :id AND r.dateReservation = :date AND r.isCanceled = false")
	Reservation checkExistReservationUserDate(Long id, LocalDate date);
	
}

package com.mobile.pwa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mobile.pwa.model.Station;

@Repository
public interface StationRepository extends JpaRepository<Station, Long>{

}

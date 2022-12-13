package com.mobile.pwa.model;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity(name = "tb_reservation")
public class Reservation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message = "Required field")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dateReservation;
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant dateCreated;
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant dateUpdated;
	@Column
	private boolean isCanceled;
	@ManyToOne(optional = false)
	private User reservationBy;
	@ManyToOne(optional = false)
	private Station station;

	@PrePersist
	public void prePersist() {
		dateCreated = Instant.now();
	}

	@PreUpdate
	public void preUpdate() {
		dateUpdated = Instant.now();
	}

}

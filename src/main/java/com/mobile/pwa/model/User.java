package com.mobile.pwa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mobile.pwa.ennum.ProfileEnum;

import lombok.Data;

@Data
@Entity(name = "tb_user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "Required field")
	private String name;
	@NotBlank(message = "Required field")
	private String lastName;
	@Column(unique = true)
	@Email(message = "Wrong email body")
	private String email;
	@Column
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	@Column
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Required field")
	private ProfileEnum profile;

}

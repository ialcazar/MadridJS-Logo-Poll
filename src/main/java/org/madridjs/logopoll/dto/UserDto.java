package org.madridjs.logopoll.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USERS")
public class UserDto {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String email;
	private String name;
	private String surname;
	
	public UserDto() {
		super();
		
	}

	public UserDto(String email, String name, String surname) {
		super();
		this.email = email;
		this.name = name;
		this.surname = surname;
	}

	public String getEmail() {
		// TODO Auto-generated method stub
		return this.email;
	}
	
	

}

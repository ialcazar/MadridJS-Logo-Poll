package org.madridjs.logopoll.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name="USERS")
public class UserDto {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String email;
	private VoteDto vote;
	
	
	public UserDto() {
		super();
		
	}

	

	public UserDto(String email) {
		this.email = email;
	}

	public UserDto(Long id, String email) {
		this.id = id;
		this.email = email;
	}
	
	

	public String getEmail() {
		return this.email;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public Long getId() {
		
		return this.id;
	}



	public void addVote(VoteDto voteDto) {
		this.vote = voteDto;
		
	}
	
	

}

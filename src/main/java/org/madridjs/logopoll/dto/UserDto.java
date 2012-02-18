package org.madridjs.logopoll.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name="USERS")
public class UserDto {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_id")
	private Long userId;
	private String email;
	
	@ManyToMany
	private Set<VoteDto> votes;
	
	
	public UserDto() {
		super();
		
	}

	

	public UserDto(String email) {
		this.email = email;
	}

	public UserDto(Long id, String email) {
		this.userId = id;
		this.email = email;
	}
	
	

	public String getEmail() {
		return this.email;
	}
	@Override
	public String toString() {
		return  String.format("[id=%d, email=%s]", userId,email);
	}

	public Long getUserId() {
		
		return this.userId;
	}
	
	



	public Set<VoteDto> getVotes() {
		return votes;
	}



	public void addVote(VoteDto voteDto) {
		
		if(this.votes == null)
			this.votes = new HashSet<VoteDto>();
		this.votes.add(voteDto);
		
	}
	
	

}

package org.madridjs.logopoll.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="VOTES")
public class VoteDto {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@ManyToMany(mappedBy="votes")
	private Set<UserDto> users;
	
	public VoteDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VoteDto(Long voteId) {
		this.id = voteId;

		
	}

	public void addUser(UserDto userDto) {
		
		
		if(users == null)
			this.users = new HashSet<UserDto>();
		this.users.add(userDto);
		
		userDto.getVotes().add(this);

		
	}

}

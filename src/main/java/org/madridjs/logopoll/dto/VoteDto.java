package org.madridjs.logopoll.dto;

public class VoteDto {
	private Long id;
	private UserDto user;
	public VoteDto(Long voteId) {
		this.id = voteId;

		
	}

	public void addUser(UserDto userDto) {
		userDto.addVote(this);
		this.user= userDto;
		
	}

}

package org.madridjs.logopoll.converters;

import org.madridjs.logopoll.dto.UserDto;
import org.madridjs.logopoll.rest.UserRest;
import org.springframework.stereotype.Component;

@Component
public class UserConverterImpl implements UserConverter {

	public UserDto toDto(UserRest userRest) {
		Long value = null;
		if(userRest == null)
			throw new IllegalArgumentException("Expected param userRest");
		
		if(userRest.getId() != null)
			value = Long.valueOf(userRest.getId());
		
		UserDto newUserDto = new UserDto(value, userRest.getEmail());
		return newUserDto;
	}

	public UserRest toRest(UserDto userDto) {
		String id = null;
		String email = null;
		
		if(userDto == null)
			throw new IllegalArgumentException("Expected param userDto");
		if(userDto.getUserId() != null)
			id = userDto.getUserId().toString();
		if(userDto.getEmail() != null)
			email = userDto.getEmail();
			
		UserRest userRest = new UserRest(id, email);
		return userRest;
	}

}

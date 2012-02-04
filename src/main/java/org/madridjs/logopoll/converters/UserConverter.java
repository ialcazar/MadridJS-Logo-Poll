package org.madridjs.logopoll.converters;

import org.madridjs.logopoll.dto.UserDto;
import org.madridjs.logopoll.rest.UserRest;

public interface UserConverter {

	UserDto toDto(UserRest userRest);

	UserRest toRest(UserDto userDto);

}

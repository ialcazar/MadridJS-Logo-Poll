package org.madridjs.logopoll.daos;

import org.madridjs.logopoll.dto.UserDto;

public interface UsersDao {

	UserDto findOne(String email);

}

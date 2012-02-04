package org.madridjs.logopoll.daos;

import java.util.List;

import org.madridjs.logopoll.dto.UserDto;
import org.springframework.data.repository.PagingAndSortingRepository;



public interface UserRepository extends PagingAndSortingRepository<UserDto, Long>{

	List<UserDto> findByEmail(String email);

}

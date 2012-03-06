package org.madridjs.logopoll.daos;

import java.util.List;

import org.madridjs.logopoll.dto.UserDto;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends PagingAndSortingRepository<UserDto, Long>{

	List<UserDto> findByEmail(String email);
	UserDto findByTimeStampsAndUserId(String timeStamp, Long userId);

}

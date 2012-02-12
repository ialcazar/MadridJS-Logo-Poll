package org.madridjs.logopoll.daos;


import org.madridjs.logopoll.dto.VoteDto;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface VoteRepository extends PagingAndSortingRepository<VoteDto, Long>{

}

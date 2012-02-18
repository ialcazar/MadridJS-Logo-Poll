package org.madridjs.logopoll.daos;


import org.madridjs.logopoll.dto.VoteDto;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface VoteRepository extends PagingAndSortingRepository<VoteDto, Long>{

}

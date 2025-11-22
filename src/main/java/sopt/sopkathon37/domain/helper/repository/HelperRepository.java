package sopt.sopkathon37.domain.helper.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HelperRepository extends JpaRepository<Helper, Integer> {
	@Query("""
			select distinct h from Helper h
			left join fetch h.offers hso
			left join fetch hso.serviceOffer so
			where h.id = :id
			""")
	Optional<Helper> findByIdWithOffers(@Param(value = "id") long id);
}

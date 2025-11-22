package sopt.sopkathon37.domain.helper.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HelperRepository extends JpaRepository<Helper, Integer> {
	@Query("""
			select h from Helper h
			left join fetch h.offers hso
			left join fetch hso.serviceOffer so
			""")
	Optional<Helper> findByIdWithOffers(long id);
}

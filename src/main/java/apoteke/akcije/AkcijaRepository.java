package apoteke.akcije;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AkcijaRepository extends JpaRepository<Akcija,Integer> {
}

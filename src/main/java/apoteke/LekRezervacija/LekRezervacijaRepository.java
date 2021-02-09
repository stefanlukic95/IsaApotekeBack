package apoteke.LekRezervacija;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LekRezervacijaRepository extends JpaRepository<LekRezervacija,Integer> {
}

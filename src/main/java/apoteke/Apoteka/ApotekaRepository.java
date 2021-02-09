package apoteke.Apoteka;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApotekaRepository extends JpaRepository<Apoteka,Integer> {
}

package apoteke.Lek;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LekRepository extends JpaRepository<Lek,Integer> {
}

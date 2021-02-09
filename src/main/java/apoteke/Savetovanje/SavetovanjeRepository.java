package apoteke.Savetovanje;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavetovanjeRepository extends JpaRepository<Savetovanje,Integer> {
}

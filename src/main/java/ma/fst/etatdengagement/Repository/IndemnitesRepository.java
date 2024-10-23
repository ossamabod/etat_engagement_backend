package ma.fst.etatdengagement.Repository;

import ma.fst.etatdengagement.Models.Indemnites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndemnitesRepository extends JpaRepository<Indemnites, Long> {
}

package ma.tpsj2ee.hopitaltp3.repository;

import ma.tpsj2ee.hopitaltp3.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    Page<Patient> findByNomContains(String keyword, Pageable pageable);
    //Pageable utilisee pour la pagination,permet de transmettre le num de la page et size
    //elle est obligatoire quand la methode retourne Page

  //  @Query("select p from Patient where p.nom like :x")
   // Page<Patient> chercher(@Param("x") String keyword, Pageable pageable);
}

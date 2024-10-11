package fr.thomas.car_management.repository;

import fr.thomas.car_management.entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicesRepository extends JpaRepository<Services, Long> {
    List<Services> findAllByOrderByNomServiceAsc();
    List<Services> findAllByOrderByDateDebutAsc();
}

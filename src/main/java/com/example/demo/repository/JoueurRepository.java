package com.example.demo.repository;

        import com.example.demo.entities.Equipe;
        import com.example.demo.entities.Joueur;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;

        import java.util.List;
        import java.util.Optional;

@Repository
public interface JoueurRepository extends JpaRepository<Joueur,Long> {

     //   Optional<Equipe> findByIdAndInstructorId(Long id, Long equipeId);

}

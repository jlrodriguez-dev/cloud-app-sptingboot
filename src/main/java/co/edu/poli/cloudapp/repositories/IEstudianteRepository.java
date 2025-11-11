package co.edu.poli.cloudapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.poli.cloudapp.entities.Estudiante;

public interface IEstudianteRepository extends JpaRepository<Estudiante, Long> {

}

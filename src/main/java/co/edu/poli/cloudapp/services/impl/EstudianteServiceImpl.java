package co.edu.poli.cloudapp.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.poli.cloudapp.dto.EstudianteDTO;
import co.edu.poli.cloudapp.entities.Estudiante;
import co.edu.poli.cloudapp.repositories.IEstudianteRepository;
import co.edu.poli.cloudapp.services.IEstudianteService;

@Service
@Transactional
public class EstudianteServiceImpl implements IEstudianteService{

    @Autowired
    private IEstudianteRepository repoEstudiante;
    
    private ModelMapper modelMapper;

    public EstudianteServiceImpl(IEstudianteRepository repository, 
                            ModelMapper modelMapper){
        this.modelMapper = modelMapper;
        this.repoEstudiante = repository;                         
    }

    @Override
    public EstudianteDTO create(EstudianteDTO estudianteDTO) {
         Estudiante entidad = modelMapper.map(estudianteDTO, Estudiante.class);
         Estudiante saved = repoEstudiante.save(entidad);
         return modelMapper.map(saved, EstudianteDTO.class);     
    }

    @Override
    public EstudianteDTO update(Long id, EstudianteDTO estudianteDTO) {
        Estudiante existente = repoEstudiante.findById(id).
                orElseThrow(() -> new 
                    RuntimeException("Estudiante no encontrado"));
        existente.setNomEstudiante(estudianteDTO.getNomEstudiante());
        existente.setApeEstudiante(estudianteDTO.getApeEstudiante());
        existente.setFechaNacimiento(estudianteDTO.getFechaNacimiento());
        existente.setEmail(estudianteDTO.getEmail());

        Estudiante saved = repoEstudiante.save(existente);
        return modelMapper.map(saved, EstudianteDTO.class);
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public EstudianteDTO findById(Long id) {
        Estudiante e = repoEstudiante.findById(id).orElseThrow(
            () -> new 
            RuntimeException("Estudiante no encontrado")
        ); 
        return modelMapper.map(e, 
            EstudianteDTO.class);   
    }

    @Override
    public List<EstudianteDTO> findAll() {
        return repoEstudiante.findAll().stream()
        .map(e -> modelMapper.map(e, EstudianteDTO.class))
        .collect(Collectors.toList());           
    }

}

package com.example.demo.Controllers;

import com.example.demo.model.Estudiante;
import com.example.demo.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("rest")
public class apiEstudiante {
    @Autowired
    EstudianteRepository estudianteRepository;
    @GetMapping("/saludar")
    public String saludar(){
    return "Hola mundo";
    }
@GetMapping("/")
    public String index(){
        return "index";
}
/*
@GetMapping("/all")
public List<Estudiante> getEstudiante(){
        return estudianteRepository.findAll();
}*/
@GetMapping("/all/{cedula}")
public List<Estudiante> getEstudiantes(@PathVariable String cedula) {
    Optional<Estudiante> optionalEstudiante = estudianteRepository.findById(cedula);
    return optionalEstudiante.map(Collections::singletonList).orElse(Collections.emptyList());
}
@PostMapping("/save/{cedula}")
    public Estudiante addEstudiante(@PathVariable String cedula,@RequestBody Estudiante estudiante){
        estudiante.setCedula(cedula);
       return estudianteRepository.saveAndFlush(estudiante);
}
@PutMapping("/edit/{cedula}")
public Estudiante editEstudiante(@PathVariable String cedula ,@RequestBody Estudiante estudiante){
   estudiante.setCedula(cedula);
    return estudianteRepository.saveAndFlush(estudiante);
    }
    @DeleteMapping("/delete/{cedula}")
public void deleteEstudiante(@PathVariable String cedula){
        estudianteRepository.deleteById(cedula);
    }


}

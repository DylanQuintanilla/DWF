package com.example.controller;

import com.example.entities.Persona;
import com.example.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.util.List;

@Controller
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping
    public String listarPersonas(Model model) {
        List<Persona> personas = personaService.obtenerTodos();
        model.addAttribute("listaPersonas",personas);
        return "listar";
    }
    @GetMapping("/nueva")
    public String mostrarFormularioDeNuevaPersona(Model model){
        model.addAttribute("persona",new Persona());
        model.addAttribute("accion","/personas/nueva");
        return "formulario";
    }

    @PostMapping("/nueva")
    public String guardarNuevaPersona(@ModelAttribute Persona persona){
        personaService.crearPersona(persona);
        return "redirect:/personas";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFomularioEditarPersona(@PathVariable Long id, @ModelAttribute Persona persona, Model model){
        model.addAttribute("persona",persona);
        model.addAttribute("accion","/personas/editar/"+id);
        return "formulario";
    }

    @PostMapping("/editar/{id}")
    public String actualizarPersona(@PathVariable Long id,@ModelAttribute Persona persona){
        personaService.actualizarPersona(id,persona);
        return "redirect:/personas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarPersona(@PathVariable Long id){
        personaService.eliminarPersona(id);
        return "redirect:/personas";
    }

}

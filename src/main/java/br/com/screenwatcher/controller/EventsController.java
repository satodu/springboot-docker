package br.com.screenwatcher.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import br.com.screenwatcher.model.EventsModel;
import br.com.screenwatcher.repository.EventsRepository;

import java.util.List;

@Controller
public class EventsController {

    private final EventsRepository eventRepository;

    @Autowired
    public EventsController(EventsRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping("/events")
    public String listEvents(Model model) {
        List<EventsModel> events = eventRepository.findAll();
        model.addAttribute("events", events);
        return "events"; // Nome do arquivo HTML do Thymeleaf para a lista de eventos
    }

    @GetMapping("/events/new")
    public String showAddEventForm(Model model) {
        EventsModel event = new EventsModel();
        model.addAttribute("event", event);
        return "add-event"; // Nome do arquivo HTML do Thymeleaf para o formulário de adição de evento
    }

    @PostMapping("/events")
    public String addEvent(EventsModel event, Model model) {
        eventRepository.save(event);
        return "redirect:/events"; // Redireciona para a lista de eventos após a criação
    }

    @GetMapping("/event/{id}")
    public String getEventById(@PathVariable("id") Long id, Model model) {
        EventsModel event = eventRepository.findById(id).orElse(null);
        model.addAttribute("event", event);
        return "event"; // Nome do arquivo HTML do Thymeleaf para visualizar um evento
    }

    @DeleteMapping("/event/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteEventById(@PathVariable("id") Long id) {
        EventsModel event = eventRepository.findById(id).orElse(null);
        if (event != null) {
            eventRepository.delete(event);
            // Retorna um status de sucesso, JavaScript irá lidar com o redirecionamento
            return ResponseEntity.ok().build();
        } else {
            // Retorna um status de erro, se necessário
            return ResponseEntity.notFound().build();
        }
    }
}
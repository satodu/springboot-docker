package br.com.screenwatcher.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import br.com.screenwatcher.model.UserModel;
import br.com.screenwatcher.repository.UserRepository;

import java.util.List;

@Controller
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<UserModel> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/users/new")
    public String showSignUpForm(Model model) {
        UserModel user = new UserModel();
        model.addAttribute("user", user);
        return "add-user"; // Nome do arquivo HTML do Thymeleaf para o formulário de adição
    }

    @PostMapping("/users")
    public String addUser(UserModel user, Model model) {
        userRepository.save(user);
        return "redirect:/users"; // Redireciona para a lista de usuários após a criação
    }

    @GetMapping("/user/{id}")
    public String getUserById(@PathVariable("id") Long id, Model model) {
        UserModel user = userRepository.findById(id).orElse(null);
        model.addAttribute("user", user);
        return "user";
    }

    @DeleteMapping("/user/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteUserById(@PathVariable("id") Long id) {
        UserModel user = userRepository.findById(id).orElse(null);
        if (user != null) {
            userRepository.delete(user);
            // Retorna um status de sucesso, JavaScript irá lidar com o redirecionamento
            return ResponseEntity.ok().build();
        } else {
            // Retorna um status de erro, se necessário
            return ResponseEntity.notFound().build();
        }
    }
}
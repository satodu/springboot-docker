package br.com.screenwatcher.repository;

import br.com.screenwatcher.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    // Aqui você pode adicionar métodos de consulta personalizados
}

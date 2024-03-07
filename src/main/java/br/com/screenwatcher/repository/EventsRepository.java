package br.com.screenwatcher.repository;

import br.com.screenwatcher.model.EventsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventsRepository extends JpaRepository<EventsModel, Long> {
    // Aqui você pode adicionar métodos de consulta personalizados
}

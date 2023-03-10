package soares.oliveira.thamyris.testebtg.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soares.oliveira.thamyris.testebtg.dominio.Item;

@Repository
public interface ItemRepositorio extends JpaRepository<Item, Long> {
}

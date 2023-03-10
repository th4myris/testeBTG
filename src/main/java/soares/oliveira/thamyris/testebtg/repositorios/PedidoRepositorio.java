package soares.oliveira.thamyris.testebtg.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soares.oliveira.thamyris.testebtg.dominio.Pedido;


import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepositorio extends JpaRepository<Pedido, Long> {
    List<Pedido> findAllByCodigoCliente(int idCodigoCleinte);

    Optional<Pedido> findByCodigoPedido(int codigoPedido);
}

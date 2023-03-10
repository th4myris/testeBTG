package soares.oliveira.thamyris.testebtg.apresentacao;
import ch.qos.logback.core.net.server.Client;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import soares.oliveira.thamyris.testebtg.dominio.Item;
import soares.oliveira.thamyris.testebtg.dominio.Pedido;
import soares.oliveira.thamyris.testebtg.repositorios.ItemRepositorio;
import soares.oliveira.thamyris.testebtg.repositorios.PedidoRepositorio;
import soares.oliveira.thamyris.testebtg.service.RabbitmqService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedido")
public class PedidoControle {

    @Autowired
    RabbitmqService rabbitmqService;

    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    @Autowired
    private ItemRepositorio itemRepositorio;

    @GetMapping("/total/{idPedido}")
    public Double getTotalPedido(@PathVariable("idPedido") Integer idPedido){
        Optional<Pedido> optPedido = pedidoRepositorio.findByCodigoPedido(idPedido);
        if(optPedido.isPresent()){
            return optPedido.get().
                    itens.stream().map( x -> x.preco * x.quantidade)
                    .reduce(0.0, Double::sum);
        }
        return 0.0;
    }

    @GetMapping("/quantidade/{idClient}")
    public int getQuantidadePedido(@PathVariable("idClient") Integer idClient){
        List<Pedido> pedidos = pedidoRepositorio.findAllByCodigoCliente(idClient);
        return pedidos.size();
    }

    @GetMapping("/{idClient}")
    public List<Pedido> getPedidosFromCliente(@PathVariable("idClient") Integer idClient){
        List<Pedido> pedidos = pedidoRepositorio.findAllByCodigoCliente(idClient);
        return pedidos;
    }

}

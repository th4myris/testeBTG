package soares.oliveira.thamyris.testebtg.connections;

import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.stereotype.Component;
import soares.oliveira.thamyris.testebtg.constantes.RabbitmqConstantes;

@Component
public class RabbitMQConnection {
    private static final String NOME_EXCHANGE = "amq.direct";
    private AmqpAdmin amqpAdmin;

    public RabbitMQConnection(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }
    private Queue fila(String nomeFila){
        return new Queue(nomeFila, true, false, false);
    }

    private DirectExchange trocaDireta() {
        return new DirectExchange(NOME_EXCHANGE);
    }

    private Binding relacionamento(Queue fila, DirectExchange troca){
        return new Binding(fila.getName(), Binding.DestinationType.QUEUE, troca.getName(), fila.getName(), null);
    }

    @PostConstruct //assim que a classe for construída, vai executar esse método
    private void adicionar(){
        Queue filaCliente = this.fila(RabbitmqConstantes.FILA_CLIENTE);
        Queue filaPedido = this.fila(RabbitmqConstantes.FILA_PEDIDO);

    DirectExchange troca = this.trocaDireta();

    Binding ligacaoCliente = this.relacionamento(filaCliente, troca);
    Binding ligacaoPedido = this.relacionamento(filaPedido, troca);

    //Criação das filas no RabbitMQ
    this.amqpAdmin.declareQueue(filaCliente);
    this.amqpAdmin.declareQueue(filaPedido);

    this.amqpAdmin.declareExchange(troca);

    this.amqpAdmin.declareBinding(ligacaoCliente);
    this.amqpAdmin.declareBinding(ligacaoPedido);

    }
}

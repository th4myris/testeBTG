package soares.oliveira.thamyris.testebtg.dominio;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pedido implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int codigoPedido;

    public int codigoCliente;

    @OneToMany(cascade = CascadeType.ALL , orphanRemoval = true)
    @JoinColumn(name = "codigo_pedido")
    public List<Item> itens;

    public Pedido(int codigoPedido, int codigoCliente, List<Item> itens) {
        this.codigoPedido = codigoPedido;
        this.codigoCliente = codigoCliente;
        this.itens = itens;
    }

    public Pedido() {
    }

    public int getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(int codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }
}

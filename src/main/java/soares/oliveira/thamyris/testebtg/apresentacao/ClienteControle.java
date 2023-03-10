package soares.oliveira.thamyris.testebtg.apresentacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soares.oliveira.thamyris.testebtg.service.RabbitmqService;

@RestController
@RequestMapping(value = "cliente")
public class ClienteControle {

    @Autowired
    RabbitmqService rabbitmqService;


}

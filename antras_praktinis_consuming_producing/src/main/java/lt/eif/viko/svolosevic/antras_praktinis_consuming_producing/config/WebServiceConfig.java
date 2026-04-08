package lt.eif.viko.svolosevic.antras_praktinis_consuming_producing.config;

import lt.eif.viko.svolosevic.antras_praktinis_consuming_producing.ws.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import jakarta.xml.ws.Endpoint;

@Configuration
public class WebServiceConfig {

    @Autowired
    private LibraryService libraryService;

    @Bean
    public Endpoint endpoint() {
        return Endpoint.publish("http://localhost:8081/libraryService", libraryService);
    }
}

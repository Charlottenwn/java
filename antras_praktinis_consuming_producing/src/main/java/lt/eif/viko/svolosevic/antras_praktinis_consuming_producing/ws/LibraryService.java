package lt.eif.viko.svolosevic.antras_praktinis_consuming_producing.ws;

import lt.eif.viko.svolosevic.antras_praktinis_consuming_producing.model.Person;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService
public interface LibraryService {
    @WebMethod
    Person getPersonById(@WebParam(name = "id") Long id);

    @WebMethod
    String generatePersonReport(@WebParam(name = "id") Long id);
}
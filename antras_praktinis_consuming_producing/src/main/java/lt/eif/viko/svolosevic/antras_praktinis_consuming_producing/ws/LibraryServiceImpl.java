package lt.eif.viko.svolosevic.antras_praktinis_consuming_producing.ws;

import lt.eif.viko.svolosevic.antras_praktinis_consuming_producing.model.Person;
import lt.eif.viko.svolosevic.antras_praktinis_consuming_producing.repository.PersonRepository;
import lt.eif.viko.svolosevic.antras_praktinis_consuming_producing.transform.TransformerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.jws.WebService;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

@Service
@WebService(endpointInterface = "lt.eif.viko.svolosevic.antras_praktinis_consuming_producing.ws.LibraryService")
public class LibraryServiceImpl implements LibraryService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person getPersonById(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    @Override
    public String generatePersonReport(Long id) {
        try {
            Person person = personRepository.findById(id).orElseThrow(() -> new RuntimeException("Person not found"));
            String pdfPath = "report-" + id + ".pdf";
            TransformerUtil.transformToPdfFromClasspath(person, pdfPath);
            byte[] pdfBytes = Files.readAllBytes(Paths.get(pdfPath));
            return Base64.getEncoder().encodeToString(pdfBytes);
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate PDF report: " + e.getMessage());
        }
    }
}

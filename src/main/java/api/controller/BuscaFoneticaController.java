package api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model.Busca;
import com.service.bean.ProcessadorFoneticoService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class BuscaFoneticaController {

	private ProcessadorFoneticoService processadorFoneticoService = new ProcessadorFoneticoService();

	@RequestMapping(value = "/buscafonetica", method = RequestMethod.POST)
	public ResponseEntity<String> search(@RequestBody Busca search) {

		try {

			String result = processadorFoneticoService.processSimilarityPhonetic(search);
			return new ResponseEntity<String>(result, HttpStatus.OK);

		} catch (IllegalArgumentException ex) {
			
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

}

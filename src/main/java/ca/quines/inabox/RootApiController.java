package ca.quines.inabox;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.quines.inabox.dao.BoxRepository;
import ca.quines.inabox.dto.Box;
import ca.quines.inabox.dto.TextRequest;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Allows all devices to connect regardless of its IP
public class RootApiController {

	@Autowired
	private BoxRepository repository;

	@GetMapping("/overview")
	public List<Box> overview() {
		// Last 10 boxes inserted into the DB, by ID.
		return repository.findTop10ByOrderByIdDesc();
	}

	@PostMapping("/getBoxByName")
	public Box getBoxByName(@Validated @RequestBody TextRequest request) {
		return repository.findByName(request.content());
	}

}

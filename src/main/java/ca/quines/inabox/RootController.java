package ca.quines.inabox;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.quines.inabox.dao.BoxRepository;
import ca.quines.inabox.dto.Box;

@RestController
public class RootController {

	@Autowired
	private BoxRepository repository;

	@GetMapping("/")
	public String index() {
		System.out.println("Let's connect to MongoDB:");
		Box byName = repository.findByName("Miscellaneous 1");
		System.out.println(byName);

		List<Box> lastInsertedBoxes = repository.findTop10ByOrderByIdDesc();
		for (Box box : lastInsertedBoxes) {
			System.out.println(box);
		}

		return "The root page goes here.";
	}

}

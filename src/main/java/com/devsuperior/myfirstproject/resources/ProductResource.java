package com.devsuperior.myfirstproject.resources;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.myfirstproject.entities.Product;
import com.devsuperior.myfirstproject.repositories.ProductRepository;
import com.devsuperior.myfirstproject.resources.exceptions.StandartError;
import com.devsuperior.myfirstproject.services.ProductService;
import com.devsuperior.myfirstproject.services.exceptions.EntityNotFoundException;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

	@Autowired
	private ProductService productService;
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		try {
			Product obj = productService.findById(id);
			return ResponseEntity.ok().body(obj);
	} catch (EntityNotFoundException e) {
		StandartError err = new StandartError();
		err.setTimestramp(Instant.now());
		err.setStatus(HttpStatus.NOT_FOUND.value());
		err.setError("Resource not found");
		err.setMessage(e.getMessage());
		err.setPath("test");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
}
}

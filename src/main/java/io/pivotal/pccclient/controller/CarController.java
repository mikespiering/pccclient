package io.pivotal.pccclient.controller;

import io.micrometer.core.annotation.Timed;
import io.pivotal.pccclient.region.Car;
import io.pivotal.pccclient.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CarController {

	@Autowired
	private CarService carService;

	@GetMapping("/all")
	@Timed(value = "getAllF1Cars")
	public List<Car> getAllF1Cars() {
		return carService.findAll();
	}

	@GetMapping("/{brand}")
	@Timed(value = "getF1CarByBrand")
	public Car getF1CarByBrand(@PathVariable String brand) {
		return getAllF1Cars().stream()
				.filter(car -> car.getBrand().equalsIgnoreCase(brand)).findFirst()
				.orElse(new Car());
	}

}

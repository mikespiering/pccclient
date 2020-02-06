package io.pivotal.pccclient.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.annotation.Timed;
import io.pivotal.pccclient.region.Car;
import io.pivotal.pccclient.service.CarService;

@RestController
public class CarController {

	private final CarService carService;

	CarController(CarService carService) {
		this.carService = carService;
	}

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

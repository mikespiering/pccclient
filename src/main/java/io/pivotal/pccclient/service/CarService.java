package io.pivotal.pccclient.service;

import io.pivotal.pccclient.region.Car;
import io.pivotal.pccclient.repository.CarRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

	private CarRepository carRepository;

	CarService(CarRepository carRepository) {
		this.carRepository = carRepository;
	}

	@CacheEvict(value = { "cars" })
	public void save(Car car) {
		carRepository.save(car);
	}

	public List<Car> findByWinsGreaterThan(int count) {
		return carRepository.findByWinsGreaterThan(count);
	}

	@Cacheable(value = "cars")
	public List<Car> findAll() {
		return carRepository.findAll();
	}
}

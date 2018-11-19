package io.pivotal.pccclient;

import io.pivotal.pccclient.region.Car;
import io.pivotal.pccclient.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class PccclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(PccclientApplication.class, args);
	}

	@Autowired
	private CarService carService;

	@PostConstruct
	public void init()
	{
		carService.save(new Car("Ferrari", "Italy", "SF70H", 234));
        carService.save(new Car("Mercedes", "Germany", "W08", 86));
        carService.save(new Car("Redbull", "UK", "RB14", 59));
        carService.save(new Car("Mclaren", "UK", "RE18", 182));
	}

}

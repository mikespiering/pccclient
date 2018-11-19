package io.pivotal.pccclient.repository;


import io.pivotal.pccclient.region.Car;
import org.springframework.data.gemfire.repository.query.annotation.Trace;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends CrudRepository<Car, String> {

    @Trace
    Car findByBrandIgnoreCase(String brand);

    @Trace
    List<Car> findByEngineAndWinsGreaterThan(String engine, int count);

    @Trace
    List<Car> findByWinsGreaterThan(int count);

    @Trace
    List<Car> findAll();

}

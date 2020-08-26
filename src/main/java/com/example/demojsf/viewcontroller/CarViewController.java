package com.example.demojsf.viewcontroller;

import com.example.demojsf.models.Car;
import com.example.demojsf.repositories.CarRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import java.util.List;

@Named
@ViewScoped
@Data
public class CarViewController {

    private CarRepository carRepository;
    private List<Car> cars;
    private Car car;

    // injection de dependance par setter
    @Autowired
    public void setCarRepository(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    // s'excute avant le constructeur
    @PostConstruct
    public void init() {
        car = new Car();
        // recuperer la liste des cars avant l'execution du constructeur
        cars = carRepository.findAll();
    }

    public String gotoCreateCar() {
        return "create-car.xhtml?faces-redirect=true";
    }

    public String saveCar() {
        carRepository.save(car);
        cars = carRepository.findAll();
        return "index.xhtml?faces-redirect=true";
    }
}

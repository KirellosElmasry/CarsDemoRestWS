/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evolvice.controller;

import com.evolvice.dao.CarDAO;
import com.evolvice.entity.Car;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Kirellos
 */
@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarDAO carDAO;

    @Autowired
    public CarController(CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    @GetMapping("/getAllData")
    public List<Car> getAllCars(@RequestHeader("apikey") String api_key) {
        if (api_key.equals("1234")) {

            return carDAO.findAll();

        } else {
            return new ArrayList<>();
        }
    }

    @GetMapping("/insertData")
    public String insertCarsData(@RequestHeader("apikey") String api_key) {
        if (api_key.equals("1234")) {
            try {
                for (int i = 1; i < 10; i++) {
                    carDAO.saveAndFlush(new Car("brand " + i, "model " + i, "productionYear " + i, "modelDetails " + i));
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "Error when inserting Cars data";
            }
            return "Inserted successfully";
        } else {
            return "Wrong apiKey";
        }
    }

    @GetMapping("/get/{id}")
    public Car getCarById(@RequestHeader("apikey") String api_key, @PathVariable("id") long id) {
        if (api_key.equals("1234")) {
            return carDAO.findById(id).get();
        } else {
            return new Car();
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteCarById(@RequestHeader("apikey") String api_key, @PathVariable("id") long id) {
        if (api_key.equals("1234")) {
            try {
                carDAO.deleteById(id);
            } catch (Exception e) {
                e.printStackTrace();
                return "Error when deleting Car with Id : " + id;
            }
            return "Car with Id : " + id + " deleted successfully!";
        } else {
            return "Wrong apiKey";
        }
    }

    @RequestMapping(value = {"/update"}, method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String updateCar(@RequestHeader("apikey") String api_key, @RequestBody Car car) {
        if (api_key.equals("1234")) {
            try {
                carDAO.save(car);
            } catch (Exception e) {
                e.printStackTrace();
                return "Error when inserting Car";
            }
            return "Updated successfully";
        } else {
            return "Wrong apiKey";
        }
    }

    @RequestMapping(value = {"/add"}, method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String addCar(@RequestHeader("apikey") String api_key, @RequestBody Car car) {
        if (api_key.equals("1234")) {

            try {
                carDAO.saveAndFlush(car);
            } catch (Exception e) {
                e.printStackTrace();
                return "error when adding new Car";
            }
            return "Car inserted successfully";
        } else {
            return "Wrong apiKey";
        }
    }
}

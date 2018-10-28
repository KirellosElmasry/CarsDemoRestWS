/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evolvice.controller;

import com.evolvice.dao.CarDAO;
import com.evolvice.entity.Car;
import java.util.List;
import java.util.NoSuchElementException;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Car>> getAllCars(@RequestHeader("apikey") String api_key) {
        if (api_key.equals("1234")) {
            try {
                return new ResponseEntity<>(carDAO.findAll(), HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/insertData")
    public ResponseEntity<String> insertCarsData(@RequestHeader("apikey") String api_key) {
        if (api_key.equals("1234")) {
            try {
                for (int i = 1; i < 10; i++) {
                    carDAO.saveAndFlush(new Car("brand " + i, "model " + i, "productionYear " + i, "modelDetails " + i));
                }
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>("Error when inserting Cars data", HttpStatus.EXPECTATION_FAILED);
            }
            return new ResponseEntity<>("Inserted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Car> getCarById(@RequestHeader("apikey") String api_key, @PathVariable("id") long id) {
        if (api_key.equals("1234")) {
            try {
                return new ResponseEntity<>(carDAO.findById(id).get(), HttpStatus.OK);
            } catch (NoSuchElementException e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<String> deleteCarById(@RequestHeader("apikey") String api_key, @PathVariable("id") long id) {
        if (api_key.equals("1234")) {
            try {
                carDAO.deleteById(id);
            } catch (NoSuchElementException e) {
                e.printStackTrace();
                return new ResponseEntity<>(" Car with Id : " + id + " not found in database!", HttpStatus.NOT_FOUND);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>("Error when deleting Car with Id : " + id, HttpStatus.EXPECTATION_FAILED);
            }
            return new ResponseEntity<>("Car with Id : " + id + " deleted successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = {"/update"}, method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public ResponseEntity<String> updateCar(@RequestHeader("apikey") String api_key, @RequestBody Car car) {
        if (api_key.equals("1234")) {
            try {
                Car newCar = carDAO.findById(car.getCarId()).get();

                if (newCar != null) {
                    carDAO.saveAndFlush(car);
                }
            }
            catch (NoSuchElementException e) {
                e.printStackTrace();
                 return new ResponseEntity<>(" Car with Id : " + car.getCarId() + " not found in database!", HttpStatus.NOT_FOUND);             
            } 
            catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>("Error when inserting Car", HttpStatus.EXPECTATION_FAILED);
            }
            return new ResponseEntity<>("Updated successfully", HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = {"/add"}, method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public ResponseEntity<String> addCar(@RequestHeader("apikey") String api_key, @RequestBody Car car) {
        if (api_key.equals("1234")) {

            try {
                carDAO.saveAndFlush(car);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>("error when adding new Car", HttpStatus.EXPECTATION_FAILED);
            }
            return new ResponseEntity<>("Car inserted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}

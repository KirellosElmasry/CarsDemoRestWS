/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evolvice.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Kirellos
 */
@Entity
@Table(name = "Cars")
public class Car implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "car_id")
    private Long carId;
    @Column(name="brand")
    private String brand;
    @Column(name = "model")
    private String model;
    @Column(name = "production_year")
    private String productionYear;
    @Column(name = "model_details")
    private String modelDetails;

    public Car() {
    }

    public Car(String brand, String model, String productionYear, String modelDetails) {
        this.brand = brand;
        this.model = model;
        this.productionYear = productionYear;
        this.modelDetails = modelDetails;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(String productionYear) {
        this.productionYear = productionYear;
    }

    public String getModelDetails() {
        return modelDetails;
    }

    public void setModelDetails(String modelDetails) {
        this.modelDetails = modelDetails;
    }

    @Override
    public String toString() {
        return "Cars{" + "carId=" + carId + ", brand=" + brand + ", model=" + model + ", productionYear=" + productionYear + ", modelDetails=" + modelDetails + '}';
    }
    
}

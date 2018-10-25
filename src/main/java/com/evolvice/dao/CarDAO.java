/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evolvice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.evolvice.entity.*;

/**
 *
 * @author Kirellos
 */
public interface CarDAO extends JpaRepository<Car, Long>{
    
}

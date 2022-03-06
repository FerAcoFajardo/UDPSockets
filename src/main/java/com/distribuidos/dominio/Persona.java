/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.distribuidos.dominio;

import java.lang.Math;

/**
 *
 * @author mavel
 */
public class Persona {
    
    String name;
    float weight;
    float height;

    public Persona() {
    }

    public Persona(String name, float weight, float height) {
        this.name = name;
        this.weight = weight;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
    
    public float getBMI(){
        float weight = this.weight;
        float height = this.height/100; 
        return (float) (weight/Math.pow(height, 2));
    }
    
    
}

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
    
    private String name;
    private float weight;
    private float height;
    private String bmi;

    public Persona() {
    }

    public Persona(String name, float weight, float height) {
        this.name = name;
        this.weight = weight;
        this.height = height;
    }

    public String getBmi() {
        return bmi;
    }

    public void setBmi(String bmi) {
        this.bmi = bmi;
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
    
    /**
    * Calculate the bmi
    */
    public void calculateBMI(){
        float weight = this.weight;
        float height = this.height; 
        float bmi = (float) (weight/Math.pow(height, 2));
        if (bmi <= 18.5 ){
            this.bmi = "Thin";
        }else if (bmi >= 18.6 && bmi>= 24.9){
            this.bmi = "Healthy";
        }else if(bmi >= 25 && bmi>=29.9){
            this.bmi = "Overweight";
        }else if(bmi >= 30){
            this.bmi = "Obese";
        }
    }
    
    
}

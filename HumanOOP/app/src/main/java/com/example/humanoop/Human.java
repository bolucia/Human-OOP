package com.example.humanoop;

import android.util.Log;

public class Human {
    private String name;
    public int weight;
    public int age;

    public Human (String name, int weight,int age) {
        this.name= name;
        this.weight= weight;
        this.age= age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void eat() {
        Log.d(tag:"eat", msg:"I am eating this food");
    }

    public void sleep() {
        Log.d(tag:"sleep", msg:"ZZZZZZZZZ");
    }

    public void sleep(int hours){
        Log.d(tag:"sleep", msg:"I am sleeping for"+hours+"hours");
    }

    public void speak(String speech){Log.d(tag:"speak", msg:"I will come tomorrow");
    }

    public int birthday(){
        age=age+1;
        return age;
    }
}
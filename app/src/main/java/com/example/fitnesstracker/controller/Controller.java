package com.example.fitnesstracker.controller;

import com.example.fitnesstracker.model.User;

public class Controller {
    private static User user;
    private static Controller instance=null;
    private Controller(){super();}
    public void createUser(String username,String password,int age,int gender,float longeur,float poids,String activitys){
        user=new User(username,password,age,gender,longeur,poids,activitys);
    }
    public float getCalResult(){return user.getCalories();}
    public float getIMCResult(){return  user.calculerIMC();}
    public String getIMCType(){ return user.interpreterIMC(user.calculerIMC());}
    public static final Controller getInstance(){
        if(null==instance){
            instance=new Controller();
        }
        return instance;
    }


}

package com.example.fitnesstracker.model;

public class User {
    private String username;
    private String password ;
    private int age,gender  ;//gender 1=male ; 0=female
    private float longeur,poids,calories;
    private String activitys; //les activités que le user faire dans ca journer
    public User(String username,String password,int age,int gender,float longeur,float poids,float calories,String activitys){
        this.username=username;
        this.password=password;
        this.age=age;
        this.longeur=longeur;
        this.poids=poids;
        this.gender=gender;
        this.activitys=activitys;
        this.calories=calculerCalories();
    }
    public float calculerCalories(){
        float mb;
        if (gender == 1) { // Pour les hommes
            mb = 88.362f + (13.397f * poids) + (4.799f * longeur) - (5.677f * age);
        } else { // Pour les femmes
            mb = 447.593f + (9.247f * poids) + (3.098f * longeur) - (4.330f * age);
        }

        float pal;
        switch (activitys.toLowerCase()) {
            case "sédentaire":
                pal = 1.2f;
                break;
            case "légèrement actif":
                pal = 1.375f;
                break;
            case "modérément actif":
                pal = 1.55f;
                break;
            case "très actif":
                pal = 1.725f;
                break;
            case "extra actif":
                pal = 1.9f;
                break;
            default:
                pal = 1.0f; // Valeur par défaut si aucune correspondance n'est trouvée
                break;
        }

        return mb * pal;
    }
    public float getCalories(){return calories;}

    }


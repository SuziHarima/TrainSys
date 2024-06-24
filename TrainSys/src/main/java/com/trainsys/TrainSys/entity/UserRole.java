package com.trainsys.TrainSys.entity;


public enum UserRole {

    ADM("admin"),
    USER("user");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String role(){
        return role;
    }
}

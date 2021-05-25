package com.facturar.app.entity;

public class UserSimpleInfoResponse {

    public UserSimpleInfoResponse(Long id, String userName, String name, String lastName, String email) {
        this.id = id;
        this.userName = userName;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }

    private Long id;
    private String userName;
    private String name;
    private String lastName;
    private String email;


    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}

package com.facturar.app.entity;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String auth_token;

    private final Long expires_in;


    public JwtResponse(String jwttoken, Long expiresIn) {

        this.auth_token = jwttoken;
        this.expires_in = expiresIn;
    }

    public String getAuth_token() {

        return this.auth_token;
    }

    public Long getExpires_in() {

        return expires_in;
    }

}

package com.facturar.app.entity;

import java.util.Optional;

public class AuthoWsaaAfipRequest {

    private String PathCertificatePEM;
    private String PathKeyPKCS;
    private Optional<String> signer;
    private Optional<String> password;


    public String getPathCertificatePEM() {
        return PathCertificatePEM;
    }

    public void setPathCertificatePEM(String pathCertificatePEM) {
        PathCertificatePEM = pathCertificatePEM;
    }

    public String getPathKeyPKCS() {
        return PathKeyPKCS;
    }

    public void setPathKeyPKCS(String pathKeyPKCS) {
        PathKeyPKCS = pathKeyPKCS;
    }

   /* public Optional<String> getSigner() {
        return signer;
    }

    public void setSigner(Optional<String> signer) {
        this.signer = signer;
    }

    public Optional<String> getPassword() {
        return password;
    }

    public void setPassword(Optional<String> password) {
        this.password = password;
    }*/

}

package com.facturar.app.controller;

import java.io.File;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.facturar.app.entity.AuthoWsaaAfipRequest;
import com.facturar.app.utils.ws.afip.AfipService;
import com.facturar.app.utils.ws.afip.authorization.Auth;
import com.facturar.app.utils.ws.afip.authorization.Authorizator;
import com.facturar.app.utils.ws.afip.authorization.DSTDN;

@RestController
@CrossOrigin
public class AuthoWsaaAfipController {

    @RequestMapping(value = "/api/authowsaaafip", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthoWsaaAfipRequest authoWsaaAfipRequest){
        File fileCertPem = new File(authoWsaaAfipRequest.getPathCertificatePEM());
        File fileKeyPRC = new File(authoWsaaAfipRequest.getPathKeyPKCS());

        DSTDN dstdn = new DSTDN(AfipService.ELECTRONIC_BILLING, "20317519339" );
        Authorizator autho = new Authorizator(AfipService.ELECTRONIC_BILLING, dstdn,
                fileCertPem, fileKeyPRC, Optional.of("maru"), Optional.of("facturarmaru"));
        Auth auth = autho.authenticate();
        return  ResponseEntity.ok(auth);

    }
}

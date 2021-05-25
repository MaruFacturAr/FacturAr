package com.facturar.app.utils.ws.afip.authorization;

import com.facturar.app.utils.ws.afip.AfipService;

public final class DSTDN {
    private final String environment;
    private final String organization;
    private final String country;
    private final String cuit;

    public DSTDN(AfipService service, String cuit) {
        this.environment  = service.value().contains("production") ? "wsaa" : "wsaahomo";
        this.organization = "afip";
        this.country      = "ar";
        this.cuit         = cuit;
    }

    public String dstdn() {
        return String.format("cn=%s,o=%s,c=%s,serialNumber=CUIT %s", environment, organization, country, cuit);
    }
}

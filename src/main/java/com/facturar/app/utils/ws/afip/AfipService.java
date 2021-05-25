package com.facturar.app.utils.ws.afip;

public enum AfipService {
    ELECTRONIC_BILLING("wsfe"),
    REGISTER_SCOPE_FOUR("ws_sr_padron_a4"),
    REGISTER_SCOPE_FIVE("ws_sr_padron_a5"),
    REGISTER_SCOPE_TEN("ws_sr_padron_a10"),
    REGISTER_SCOPE_THIRTEEN("ws_sr_padron_a13"),
    ELECTRONIC_BILLING_PRODUCTION("wsfe-production"),
    REGISTER_SCOPE_FOUR_PRODUCTION("ws_sr_padron_a4-production"),
    REGISTER_SCOPE_FIVE_PRODUCTION("ws_sr_padron_a5-production"),
    REGISTER_SCOPE_TEN_PRODUCTION("ws_sr_padron_a10-production"),
    REGISTER_SCOPE_THIRTEEN_PRODUCTION("ws_sr_padron_a13-production");

    private final String value;

    AfipService(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}


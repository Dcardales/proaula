package com.turismo.CTG.auth;




public class JwtAuthenticationResponse {

    private String accessToken;
    private String perfil;


    public JwtAuthenticationResponse(String accessToken, String perfil) {
        this.accessToken = accessToken;
        this.perfil = perfil;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
}

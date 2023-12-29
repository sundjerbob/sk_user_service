package raf.sk.sk_user_service.dto.response;

public class LoginResponse {

    private String jwt;


    public LoginResponse setJwt(String jwt) {
        this.jwt = jwt;
        return this;
    }

    public String getJwt() {
        return jwt;
    }

}

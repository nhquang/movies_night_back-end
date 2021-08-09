package nhquang.a2.Models;

public class JwtResponse {
    private String token;

    public JwtResponse(String jwt){
        this.token = jwt;
    }
    public String getJwt() {
        return token;
    }

    public void setJwt(String jwt) {
        this.token = jwt;
    }
}

package nhquang.a2.Models;

public class JwtResponse {
    private String token;

    public JwtResponse(String token){
        this.token = token;
    }
    public String getJwt() {
        return token;
    }

    public void setJwt(String token) {
        this.token = token;
    }
}

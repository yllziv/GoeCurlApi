package about.gson;

import java.util.Date;

public class People {
    private String name;
    private String address;
    private Date dateOfBirth;
    private Integer age;
    private transient String secret;

    public People(String name, String address, Date dateOfBirth) {
        this.name = name;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}

package about.gson;

import com.google.gson.Gson;

import java.util.Calendar;

public class GsonFieldExample {
    public static void main(String[] args) {
        Calendar dob = Calendar.getInstance();
        dob.set(1980, 10, 11);
        People people = new People("John", "350 Banana St.", dob.getTime());
        people.setSecret("This is a secret!");

        Gson gson = new Gson();
        String json = gson.toJson(people);
        System.out.println("json = " + json);
    }
}

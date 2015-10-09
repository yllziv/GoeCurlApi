package about.gson;

import com.google.gson.Gson;
import java.util.Calendar;

/* json 串与对象相互转换*/
public class ObjectToJson {
    public static void main(String[] args) {
        Calendar dob = Calendar.getInstance();
        dob.set(2000, 1, 1, 0, 0, 0);
        Student student = new Student("Duke", "Menlo Park", dob.getTime());

        Gson gson = new Gson();
        String json = gson.toJson(student);
        System.out.println("json = " + json);
        String jsonString = "{\"name\":\"Duke\",\"address\":\"Menlo Park\",\"dateOfBirth\":\"Feb 1, 2000 12:00:00 AM\"}";

        student = gson.fromJson(jsonString, Student.class);

        System.out.println("student.getName()        = " + student.getName());
        System.out.println("student.getAddress()     = " + student.getAddress());
        System.out.println("student.getDateOfBirth() = " + student.getDateOfBirth());
    }
}

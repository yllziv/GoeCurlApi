package about.gson;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*集合与Json相互转换*/
public class CollectionConvertJson {
    public static void main(String[] args) {
        //
        // Converts a collection of string object into JSON string.
        //
        List<String> names = new ArrayList<String>();
        names.add("Alice");
        names.add("Bob");
        names.add("Carol");
        names.add("Mallory");

        Gson gson = new Gson();
        String jsonNames = gson.toJson(names);
        System.out.println("jsonNames = " + jsonNames);

        //
        // Converts a collection Student object into JSON string
        //
        Student a = new Student("Alice", "Apple St", new Date(2000, 10, 1));
        Student b = new Student("Bob", "Banana St", null);
        Student c = new Student("Carol", "Grape St", new Date(2000, 5, 21));
        Student d = new Student("Mallory", "Mango St", null);

        List<Student> students = new ArrayList<Student>();
        students.add(a);
        students.add(b);
        students.add(c);
        students.add(d);

        gson = new Gson();
        String jsonStudents = gson.toJson(students);
        System.out.println("jsonStudents = " + jsonStudents);

        //
        // Converts JSON string into a collection of Student object.
        //
        Type type = new TypeToken<List<Student>>(){}.getType();
        List<Student> studentList = gson.fromJson(jsonStudents, type);

        for (Student student : studentList) {
            System.out.println("student.getName() = " + student.getName());
        }
    }
}
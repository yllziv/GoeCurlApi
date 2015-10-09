
package about.gson;
import com.google.gson.Gson;

/*将一个数据转换成 json 串，并使用 Gson.toJson() 方法将数组序列化为 JSON，
以及Gson.fromJson() 方法将 JSON 串反序列化为 java 数组*/
class JsonConvertArray {
    public static void main(String[] args) {
        int[] numbers = {1, 1, 2, 3, 5, 8, 13};
        String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

        //
        // Create a new instance of Gson
        //
        Gson gson = new Gson();

        //
        // Convert numbers array into JSON string.
        //
        String numbersJson = gson.toJson(numbers);

        //
        // Convert strings array into JSON string
        //
        String daysJson = gson.toJson(days);
        System.out.println("numbersJson = " + numbersJson);
        System.out.println("daysJson = " + daysJson);

        //
        // Convert from JSON string to a primitive array of int.
        //
        int[] fibonacci = gson.fromJson(numbersJson, int[].class);
        for (int i = 0; i < fibonacci.length; i++) {
            System.out.print(fibonacci[i] + " ");
        }
        System.out.println("");

        //
        // Convert from JSON string to a string array.
        //
        String[] weekDays = gson.fromJson(daysJson, String[].class);
        for (int i = 0; i < weekDays.length; i++) {
            System.out.print(weekDays[i] + " ");
        }
        System.out.println("");

        //
        // Converting multidimensional array into JSON
        //
        int[][] data = {{1, 2, 3}, {3, 4, 5}, {4, 5, 6}};
        String json = gson.toJson(data);
        System.out.println("Data = " + json);

        //
        // Convert JSON string into multidimensional array of int.
        //
        int[][] dataMap = gson.fromJson(json, int[][].class);
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                System.out.print(data[i][j] + " ");
            }
            System.out.println("");
        }
    }
}

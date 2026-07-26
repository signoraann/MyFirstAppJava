package com.signoraann.javalearning.lesson23;
import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) {
        String json = "{\n" +
                "  \"name\": \"Ann\",\n" +
                "  \"age\": 19,\n" +
                "  \"hobbies\": [\"dancing\", \"reading\", \"photography\"],\n" +
                "  \"address\": {\n" +
                "    \"country\": \"Belarus\",\n" +
                "    \"city\": \"Brest\",\n" +
                "    \"street\": \"Lenina\",\n" +
                "    \"house\": 20\n" +
                "  }\n" +
                "}";
        Gson gson = new Gson();
        User user = gson.fromJson(json, User.class);
        System.out.println("Object " + user);
        String backToJson = gson.toJson(user);
        System.out.println("Back to json " + backToJson);
    }
}

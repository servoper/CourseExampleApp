package bg.codingschool.myapplication1.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nikolay Vasilev on 7/12/2016.
 */
public class Dog {

    public static final String PARAM_NAME = "name";
    public static final String PARAM_AGE = "age";
    public static final String PARAM_IMAGE_URL = "imageUrl";

    @SerializedName(PARAM_NAME)
    public String name;

    @SerializedName(PARAM_AGE)
    public int age;

    @SerializedName(PARAM_IMAGE_URL)
    public String imageUrl;

    public Dog(String name, int age, String imageUrl) {
        this.name = name;
        this.age = age;
        this.imageUrl = imageUrl;
    }
}

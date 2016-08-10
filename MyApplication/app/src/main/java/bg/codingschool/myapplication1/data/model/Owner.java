package bg.codingschool.myapplication1.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Nikolay Vasilev on 7/13/2016.
 */
public class Owner {

    public int name;

    @SerializedName("dog")
    public Dog dog1;

    public ArrayList<Dog> dogs;
}

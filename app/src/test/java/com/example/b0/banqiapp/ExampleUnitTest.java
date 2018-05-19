package com.example.b0.banqiapp;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    public void addition_test (){assertEquals(4,4);}
    public static List<DataTest> requestHandleWithGson(String jsonData){
        Gson gson=new Gson();
        List<DataTest> dataTests=gson.fromJson(jsonData,new TypeToken<List<DataTest>>(){}.getType());
        return dataTests;

    }
}
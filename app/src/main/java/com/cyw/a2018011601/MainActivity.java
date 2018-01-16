package com.cyw.a2018011601;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click1(View v)
    {
        //通常app僅有權限存取以下資料夾, 外部資料如SD卡, 內部模擬外部資料夾 要謹慎設定
        //app專屬資料夾  D/FILE: /data/user/0/com.cyw.a2018011601/files
        String str = getFilesDir().getAbsolutePath();
        Log.d("FILE", str);
        //app專屬快取資料夾  D/FILE: data/user/0/com.cyw.a2018011601/cache
        String str1 = getCacheDir().getAbsolutePath();
        Log.d("FILE", str1);
    }



    public void click2(View v) throws IOException {

        String str=getFilesDir().getAbsolutePath()+"/test.txt";
        Log.d("File",str);
        FileWriter f1=new FileWriter(str);//開啟檔案
        f1.write("hello巨匠");
        f1.close();//close才會真的將資料寫入檔案

        //老師寫的
        File f = new File(getFilesDir(), "myfile.txt");
        try {
            FileWriter fw = new FileWriter(f);
            fw.write("Hello World");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void click3(View v)
    {
        ArrayList<String> mylist = new ArrayList();
        mylist.add("Bob");
        mylist.add("Mary");
        mylist.add("Peter");
        File f = new File(getFilesDir(), "myfile1.txt");
        try {
            FileWriter fw = new FileWriter(f);
            Gson gson = new Gson();
            String data = gson.toJson(mylist);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void click4(View v)
    {
        ArrayList<Student> mydata = new ArrayList();
        mydata.add(new Student(1, "Bob", 95));
        mydata.add(new Student(2, "Mary", 98));
        mydata.add(new Student(3, "Peter", 80));

        File f = new File(getFilesDir(), "myfile2.txt");
        try {
            FileWriter fw = new FileWriter(f);
            Gson gson = new Gson();
            String data = gson.toJson(mydata);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    class Student
    {
        public int id;
        public String name;
        public int score;
        public Student(int id, String name, int score)
        {
            this.id = id;
            this.name = name;
            this.score = score;
        }
    }

    public void click5(View v) throws IOException, JSONException {
        File f = new File(getFilesDir(), "myfile1.txt");
        FileReader fr = new FileReader(f);
        //FileReader fr=new FileReader(getFilesDir().getAbsolutePath()+"/myfile1");
        BufferedReader br=new BufferedReader(fr);
        String str= br.readLine();
        Log.d("FileReader",str);
        Gson gson=new Gson();
        ArrayList<String> mydata = gson.fromJson(str, new TypeToken<ArrayList<String>>(){}.getType());
        for (String s :  mydata)
        {
            Log.d("FILE", "data:" + s);
        }
        br.close();
        fr.close();
    }
    public void click6(View v) throws IOException, JSONException {
        File f = new File(getFilesDir(), "myfile2.txt");
        FileReader fr = new FileReader(f);
        //FileReader fr=new FileReader(getFilesDir().getAbsolutePath()+"/myfile2.txt");//自己寫的,好像比較爛
        BufferedReader br=new BufferedReader(fr);
        String str= br.readLine();
        Log.d("FileReader",str);
       Gson gson=new Gson();
       ArrayList<Student> mydata = gson.fromJson(str, new TypeToken<ArrayList<Student>>(){}.getType());
       //Student[] mydata=gson.fromJson(str,Student[].class); //這種也可以
       for (Student s :  mydata)
       {
           Log.d("Gson", "id:" + s.id+" name:"+s.name+" score:"+s.score);

        }
        br.close();
        fr.close();
    }
  public void click7(View v)
    {
        //取得內部模擬外部的空間
        //  D/FILE: /storage/emulated/a0/Android/dta/com.cyw.a2018011601/files/data
        File f = getExternalFilesDir("data");
        Log.d("FILE", f.getAbsolutePath());

        //取得外部的空間
        //  D/FILE: /storage/emulated/0
        File f1 = Environment.getExternalStorageDirectory();
        Log.d("FILE", f1.getAbsolutePath());
    }



}

package com.example.yvtc.yvd042003;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class addActivity extends AppCompatActivity {
    ArrayList<Student> mylist;
    ArrayList<Map<String, Student>>showdata;
    File myfile;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myfile = new File(getFilesDir() + File.separator + "mydata.json");
        lv = (ListView) findViewById(R.id.listView);
    }

    public void clickAdd(View v)
    {
        File myfile;
        ArrayList<Student> mylist = null;
        myfile = new File(getFilesDir() + File.separator + "mydata.json");
        Gson gson = new Gson();
        if (myfile.exists())
        {
            try {
                FileReader fr = new FileReader(myfile);
                BufferedReader br = new BufferedReader(fr);
                String data = br.readLine();

                mylist = gson.fromJson(data, new TypeToken<ArrayList<Student>>(){}.getType());

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            mylist = new ArrayList<>();
        }
        mylist.add(new Student(ed1.getText().toString(), ed2.getText().toString(),ed3.getText().toString()));
        try {
            FileWriter fw = new FileWriter(myfile);
            BufferedWriter bw = new BufferedWriter(fw);
            String str = gson.toJson(mylist);
            bw.write(str);
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Add");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getTitle().equals("Add"))
        {
            Intent it = new Intent(MainActivity.this, AddActivity.class);
            startActivity(it);
        }
        return super.onOptionsItemSelected(item);
    }

    private class AddActivity {
    }
}

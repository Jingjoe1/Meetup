package com.example.meetupapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    final DB myDb = new DB(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Data> data = fill_with_data();
        myDb.getWritableDatabase();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        Recycler_View_Adapter adapter = new Recycler_View_Adapter(data, getApplication());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    public void CreateMeeting(View view)
    {
      //  Intent itn = new Intent(this,CreateMeeting.class);
       // startActivity(itn);
    }
    public List<Data> fill_with_data() {

        List<Data> data = new ArrayList<>();

        String arrData[][] = myDb.SelectAllData();
        if(arrData == null)
        {
            Toast.makeText(MainActivity.this, "Not found Data!",
                    Toast.LENGTH_LONG).show();
        }
        else
        {
            int i = 0;
            for(i = 0;i<=arrData.length-1;i++) {
                data.add(new Data(arrData[i][1], arrData[i][6], R.drawable.balloons,arrData[i][7]));
            }
        }

        return data;
    }

}

package com.example.tododemo;

import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private View.OnClickListener btnSet_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            SharedPreferences table = getSharedPreferences("tTODO", 0);
            int sn = table.getInt("COUNT",0);
            sn++;
            table.edit().putInt("COUNT",sn).commit();

            String keyT = "T" + String.valueOf(sn);
            String keyD = "D" + String.valueOf(sn);

            table.edit().putString(keyT,txtSet.getText().toString()).commit();
            table.edit().putString(keyD,txtDate.getText().toString()).commit();

            txtDate.setText(" ");
            txtSet.setText(" ");

        }
    };
    private View.OnClickListener btnGet_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            SharedPreferences table = getSharedPreferences("tTODO", 0);
            int sn = table.getInt("COUNT",0);

            if (sn == 0) {
                return;
            }

            ArrayList<String> list = new ArrayList<>();

            for (int i = 0 ; i <= sn ; i ++){
                String KeyT = "T"+String.valueOf(i);
                String KeyD = "D" + String.valueOf(i);
                if(table.contains(KeyD)){
                    list.add(table.getString(KeyT,"")+"\r\n"+table.getString(KeyD,""));

                }

            }

            String[] todo = list.toArray(new String[list.size()]);
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setItems(todo,null).setTitle("待辦工作").create().show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitialComponent();

    }

    private void InitialComponent() {

        btnSet=findViewById(R.id.btnSet);
        btnSet.setOnClickListener(btnSet_click);

        btnGet=findViewById(R.id.btnGet);
        btnGet.setOnClickListener(btnGet_click);
        txtSet=findViewById(R.id.txtSet);
        txtDate=findViewById(R.id.txtDate);
        lblSet=findViewById(R.id.lblGet);
    }

    Button btnSet,btnGet;
    EditText txtSet,txtDate;
    TextView lblSet;

}

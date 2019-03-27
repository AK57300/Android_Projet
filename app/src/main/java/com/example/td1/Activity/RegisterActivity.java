package com.example.td1.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.td1.R;

import static com.example.td1.Activity.SaisieActvity.ACTION_ANNULER;

public class RegisterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText edit_user_inscription = (EditText) findViewById(R.id.edit_user_inscription);
        final EditText edit_pwd_inscription = (EditText) findViewById(R.id.edit_pwd_inscription);
        final Button btn_register = (Button) findViewById(R.id.btn_register);


    }

    public void onClickReturn(View view){
        this.setResult(ACTION_ANNULER);
        this.finish();
    }

    public void onBackPressed(){
        this.onClickReturn(null);
    }

    protected void onResume(){
        super.onResume();
        Log.i("cycle","onResume");
    }

    protected void onPause(){
        super.onPause();
        Log.i("cycle","onPause");
    }

    
}

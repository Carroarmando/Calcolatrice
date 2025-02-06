package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity
{
    Map<String, Button> buttons = new HashMap<>();
    TextView query;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        RecuperaOggetti();
    }

    protected void RecuperaOggetti()
    {
        buttons.put("0", (Button)findViewById(R.id.b0));
        buttons.put("1", (Button)findViewById(R.id.b1));
        buttons.put("2", (Button)findViewById(R.id.b2));
        buttons.put("3", (Button)findViewById(R.id.b3));
        buttons.put("4", (Button)findViewById(R.id.b4));
        buttons.put("5", (Button)findViewById(R.id.b5));
        buttons.put("6", (Button)findViewById(R.id.b6));
        buttons.put("7", (Button)findViewById(R.id.b7));
        buttons.put("8", (Button)findViewById(R.id.b8));
        buttons.put("9", (Button)findViewById(R.id.b9));
        buttons.put("+", (Button)findViewById(R.id.bplu));
        buttons.put("-", (Button)findViewById(R.id.bmin));
        buttons.put("*", (Button)findViewById(R.id.bmul));
        buttons.put("/", (Button)findViewById(R.id.bdiv));
        buttons.put("=", (Button)findViewById(R.id.bequals));
        buttons.put(".", (Button)findViewById(R.id.bdot));
        buttons.put("ce",(Button)findViewById(R.id.bce));
        buttons.put("del", (Button)findViewById(R.id.bdel));
        buttons.put("^", (Button)findViewById(R.id.bpot));
        buttons.put("sqrt", (Button)findViewById(R.id.bsqrt));
        query = (TextView)findViewById(R.id.query);
    }

    protected void click(View v)
    {
        Button b = (Button)v;
        String s = b.getText().toString();
        if(s!="=" && s!="CE" && s!="C")
            query.append(s);
    }
}
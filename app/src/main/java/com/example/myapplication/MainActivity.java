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
        Button b = (Button) v;
        String s = b.getText().toString();

        if (s.equals("=")) 
            query = String.valueOf(getResult(query)); // Risolve l'espressione
        else if (s.equals("CE")) 
            query = ""; // Cancella tutto
        else if (s.equals("C")) 
        {
            if (!query.isEmpty()) 
                query = query.substring(0, query.length() - 1); // Cancella l'ultimo carattere
        }
        else 
            query += s; // Aggiunge il carattere alla query
    }    
    
    public static int getResult(String expression) {
        int result;
        
        // Se l'espressione è un numero, lo restituisce
        try 
        {
            return Integer.parseInt(expression);
        } 
        catch (NumberFormatException e) {}

        // Risolve le parentesi
        while (expression.contains("(")) 
        {
            int start = expression.lastIndexOf('(');
            int end = expression.indexOf(')', start);

            String s = expression.substring(start + 1, end);
            result = getResult(s);

            expression = expression.substring(0, start) + result + expression.substring(end + 1);
        }

        // Gestisce moltiplicazione e divisione
        while (expression.contains("*") || expression.contains("/")) 
        {
            int mulIndex = expression.indexOf('*');
            int divIndex = expression.indexOf('/');
            int index = (mulIndex != -1 && (divIndex == -1 || mulIndex < divIndex)) ? mulIndex : divIndex;

            String left = "", right = "";
            
            int leftIndex = index - 1;
            while (leftIndex >= 0 && Character.isDigit(expression.charAt(leftIndex))) 
            {
                leftIndex--;
            }
            left = expression.substring(leftIndex + 1, index);

            int rightIndex = index + 1;
            while (rightIndex < expression.length() && Character.isDigit(expression.charAt(rightIndex))) 
            {
                rightIndex++;
            }
            right = expression.substring(index + 1, rightIndex);

            int num1 = Integer.parseInt(left);
            int num2 = Integer.parseInt(right);
            result = (expression.charAt(index) == '*') ? num1 * num2 : num1 / num2;

            expression = expression.substring(0, leftIndex + 1) + result + expression.substring(rightIndex);
        }

        // Gestisce somma e sottrazione
        while (expression.contains("+") || expression.contains("-")) 
        {
            int sumIndex = expression.indexOf('+');
            int subIndex = expression.indexOf('-');
            int index = (sumIndex != -1 && (subIndex == -1 || sumIndex < subIndex)) ? sumIndex : subIndex;

            String left = "", right = "";
            
            int leftIndex = index - 1;
            while (leftIndex >= 0 && Character.isDigit(expression.charAt(leftIndex))) 
            {
                leftIndex--;
            }
            left = expression.substring(leftIndex + 1, index);

            int rightIndex = index + 1;
            while (rightIndex < expression.length() && Character.isDigit(expression.charAt(rightIndex))) 
            {
                rightIndex++;
            }
            right = expression.substring(index + 1, rightIndex);

            int num1 = Integer.parseInt(left);
            int num2 = Integer.parseInt(right);
            result = (expression.charAt(index) == '+') ? num1 + num2 : num1 - num2;

            expression = expression.substring(0, leftIndex + 1) + result + expression.substring(rightIndex);
        }

        try 
        {
            return Integer.parseInt(expression);
        } 
        catch (NumberFormatException e) 
        {
            return 0;
        }
    }
}
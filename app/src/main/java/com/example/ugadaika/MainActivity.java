package com.example.ugadaika;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    TextView tvInfo, tvCounter;
    EditText etInput;
    Button bControl;
    boolean gameFinished;
    int guess;
    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = (TextView)findViewById(R.id.textView);
        etInput = (EditText)findViewById(R.id.input1);
        bControl = (Button)findViewById(R.id.button_main);
        tvCounter = (TextView)findViewById(R.id.textCount);

        generateGuess();
        gameFinished = false;
        counter = 0;
        updateCounter();
    }

    // добавил к приложению из презентации пару проверок и возможность нормально играть сначала + счетчик шагов
    public void onClick(View v){
        if (!gameFinished){
            try {
                int inputValue = Integer.parseInt(etInput.getText().toString());
                if (inputValue > 100 || inputValue < 0) tvInfo.setText(R.string.error); //проверка на правильный интервал
                else if (inputValue > guess) tvInfo.setText(R.string.ahead); //если больше
                else if (inputValue < guess) tvInfo.setText(R.string.behind); //если меньше
                else { //если угадал
                    tvInfo.setText(R.string.hit);
                    bControl.setText(R.string.play_more);
                    gameFinished = true;
                }
                counter++;
                updateCounter();
            } catch (NumberFormatException nfe) {
                tvInfo.setText(R.string.error); //проверка на НЕ число
            }

        } else { //иначе начинаем новую иру
            tvInfo.setText(R.string.try_to_guess);
            etInput.setText("");
            bControl.setText(R.string.input_value);
            gameFinished = false;
            generateGuess();
            counter = 0;
            updateCounter();
        }
    }

    private void generateGuess(){ //генерируем новое число
        guess = (int)(Math.random()*100);
    }

    private void updateCounter(){//обновляем счетчик шагов
        tvCounter.setText(R.string.text_counter);
        tvCounter.append(String.valueOf(counter));
    }

}
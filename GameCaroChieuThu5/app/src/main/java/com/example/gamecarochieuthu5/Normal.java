package com.example.gamecarochieuthu5;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Normal extends AppCompatActivity implements View.OnClickListener{
    private Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10,btn11,btn12,btn13,btn14,btn15,btnReset;
    private TextView tvO, tvX, tvTurn;
    int Score_O = 0;
    int Score_X = 0;
    int PLAYER_O = 0;
    int PLAYER_X = 1;
    int activePlayer = PLAYER_O;
    int[] papan = {2, 2, 2, 2, 2, 2, 2, 2, 2,2,2,2,2,2,2,2};
    boolean isGameActive = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btn10 = findViewById(R.id.btn10);
        btn11 = findViewById(R.id.btn11);
        btn12 = findViewById(R.id.btn12);
        btn13 = findViewById(R.id.btn13);
        btn14 = findViewById(R.id.btn14);
        btn15 = findViewById(R.id.btn15);
        btnReset = findViewById(R.id.btnReset);

        tvO = findViewById(R.id.tvO);
        tvO.setText("O :" + String.valueOf(Score_O));

        tvX = findViewById(R.id.tvX);
        tvX.setText("X :" + String.valueOf(Score_X));
        tvTurn = findViewById(R.id.tvTurn);
        tvTurn.setText("O Turn");

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn10.setOnClickListener(this);
        btn11.setOnClickListener(this);
        btn12.setOnClickListener(this);
        btn13.setOnClickListener(this);
        btn14.setOnClickListener(this);
        btn15.setOnClickListener(this);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }

    private void resetGame() {
        activePlayer = PLAYER_O;
        tvTurn.setText("O Turn");

        papan = new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
        btn0.setText("");
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");
        btn9.setText("");
        btn10.setText("");
        btn11.setText("");
        btn12.setText("");
        btn13.setText("");
        btn14.setText("");
        btn15.setText("");

        isGameActive = true;


    }

    @Override
    public void onClick(View v) {
        if (!isGameActive) {
            return;
        }

        android.widget.Button btnTekan = findViewById(v.getId());
        int tagTekan = Integer.parseInt(v.getTag().toString());

        if (papan[tagTekan] != 2) {
            return;
        }

        papan[tagTekan] = activePlayer;

        if (activePlayer == PLAYER_O) {
            btnTekan.setText("0");
//            btnTekan.setBackground(getDrawable(android.R.color.holo_blue_bright));
            activePlayer = PLAYER_X;
            tvTurn.setText("Người Chơi X");
        } else {
            btnTekan.setText("X");
//            btnTekan.setBackground(getDrawable(android.R.color.holo_orange_light));
            activePlayer = PLAYER_O;
            tvTurn.setText("Người chơi O");

        }
        checkForWin();
    }

    private void checkForWin() {
        int[][] winner = {{0, 1, 2, 3 }, { 4, 5, 6,7}, {8, 9, 10 ,11}, {12,13,14,15}, {0,4,8,12},{1,5,9,13},{2,6,10,14}, {3,7,11,15},{0,5,10,15},{3,6,9,12}};

        for (int i = 0; i < 10; i++) {
            int val0 = winner[i][0];
            int val1 = winner[i][1];
            int val2 = winner[i][2];

            if (papan[val0] == papan[val1] && papan[val1] == papan[val2]) {
                if (papan[val0] != 2) {

                    isGameActive = false;

                    if (papan[val0] == PLAYER_O) {
                        showDialog("Người Chơi 0 Thắng");
                        Score_O++;
                        tvO.setText("O : " + String.valueOf(Score_O));
                    } else {
                        showDialog("Người Chơi X Thắng");
                        Score_X++;
                        tvX.setText("X : " + String.valueOf(Score_X));
                    }
                }
            }
        }
        int draw = 0;
        for (int i = 0; i < 15; i++) {
            if (papan[i] != 2) {
                draw++;
            }
        }
        if (draw == 15) {
            showDialog("hết lượt đánh ");
        }
    }

        private void showDialog(String Winner) {
            new AlertDialog.Builder(this)
                    .setTitle(Winner)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .show();
        }
}



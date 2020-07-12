package com.example.connectthree;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    int active_player = 0;      //if active_player =0 then "o" ; if active_player =1 then "x"
    int[] gamestate ={2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6} };
    boolean gameactive = true;

    public void appear(View view){
        ImageView point = (ImageView) view;
        int tag = Integer.parseInt(point.getTag().toString());

        if(gamestate[tag]==2 && gameactive) {
            gamestate[tag] = active_player;
            if(active_player==0) {
                point.setImageResource(R.drawable.o);
                active_player=1;
            }
            else {
                point.setImageResource(R.drawable.x);
                active_player=0;
            }
        }
        for(int state:gamestate) {
            System.out.println(state);
        }

        for(int[] position : winningPositions){

            if(gamestate[position[0]]==gamestate[position[1]] && gamestate[position[1]]==gamestate[position[2]] && gamestate[position[0]]==gamestate[position[2]] && gamestate[position[0]] != 2){

                gameactive = false;
                String winner = "ZERO";
                if(gamestate[position[0]]==1){
                    winner = "CROSS";
                }

                TextView message = findViewById(R.id.message);
                message.setText(winner + " has won.");
                LinearLayout layout= (LinearLayout) findViewById(R.id.verticalLayout);
                layout.setVisibility(View.VISIBLE);
                break;
            }
            else{
                boolean over = true;
                for(int state: gamestate){
                    if(state == 2)
                        over = false;
                }

                if(over){
                    TextView message = findViewById(R.id.message);
                    message.setText("It's a DRAW");
                    LinearLayout layout= (LinearLayout) findViewById(R.id.verticalLayout);
                    layout.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void playAgain(View view){
        LinearLayout layout = (LinearLayout) findViewById(R.id.verticalLayout);
        layout.setVisibility(View.INVISIBLE);
        active_player = 0;
        for(int i=0;i<gamestate.length;i++)
        {
            gamestate[i]=2;
        }

        GridLayout gridlayout= (GridLayout)findViewById(R.id.grid);
        for(int i=0;i<gridlayout.getChildCount();i++){

            ((ImageView) gridlayout.getChildAt(i)).setImageResource(0);
        }
        gameactive = true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
package com.pranavverma.tic_tac_toe;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;
    //0-O
    //1-X
    //2-Null
    int activeplayer = 0;
    int[] gamestate = {2,2,2,2,2,2,2,2,2};
    int[][] winpos = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    TextView status = findViewById(R.id.status);
    public void playerTap(View view){
        if(!gameActive){
            reset(view);
            return;
        }
        ImageView img = (ImageView)(view);
        int tappedimage = Integer.parseInt(img.getTag().toString());



        if (gamestate[tappedimage]==2) {
            gamestate[tappedimage] = activeplayer;
            img.setTranslationY(-1000f);

            if (activeplayer == 1) {
                img.setImageResource(R.drawable.x);
                activeplayer = 0;
                status.setText("O's Turn : Tap to Play");
            } else {
                img.setImageResource(R.drawable.p1);
                activeplayer = 1;
                status.setText("X's Turn : Tap to Play");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }

        for(int[] condition: winpos){
            if(gamestate[condition[0]]==gamestate[condition[1]]&&gamestate[condition[1]]==gamestate[condition[2]]&&gamestate[condition[0]]!=2) {
                if (gamestate[condition[0]] == 1) {
                    status.setText("X wins: Tap to restart");

                } else {
                    status.setText("O wins: Tap to restart");
                }
                gameActive = false;
            }
        }
        int i= 0;
        for( i=i;(i<9);i++){
            if(gamestate[i]==2){
                break;
            }
        }
        if(i==9&&gameActive){
            status.setText("Game Draw!: Tap to restart");
            gameActive = false;
        }
    }
    public void reset(View view){
        gameActive  = true;
        activeplayer = 0;
        gamestate = new int[] {2,2,2,2,2,2,2,2,2};
        status.setText("O's turn: Tap to Play");
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView9)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView10)).setImageResource(0);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
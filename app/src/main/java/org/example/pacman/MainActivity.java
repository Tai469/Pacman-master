package org.example.pacman;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageButton;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener
{
    //reference to the main view
    GameView gameView;
    //reference to the game class.
    Game game;
    private Timer timer;
    private boolean left, right, up, down;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //saying we want the game to run in one mode only
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        this.gameView =  findViewById(R.id.gameView);
        final TextView viewPoints = findViewById(R.id.points);
        final TextView viewLooser = findViewById(R.id.Looser);
        final TextView viewWinner = findViewById(R.id.Winner);
        final TextView viewGameTimeCounter = findViewById(R.id.gameTimeCounter);

        this.game = new Game(this, viewPoints, viewLooser, viewWinner, viewGameTimeCounter);
        this.game.setGameView(gameView);
        this.gameView.setGame(game);
        this.game.newGame();

        ImageButton buttonRight = findViewById(R.id.moveRight);
        buttonRight.setImageResource(R.drawable.arrowforward);
        buttonRight.setOnClickListener(this);

        ImageButton buttonDown = findViewById(R.id.moveDown);
        buttonDown.setImageResource(R.drawable.arrowdown);
        buttonDown.setOnClickListener(this);

        ImageButton buttonUp = findViewById(R.id.moveUp);
        buttonUp.setImageResource(R.drawable.arrowup);
        buttonUp.setOnClickListener(this);

        ImageButton buttonLeft = findViewById(R.id.moveLeft);
        buttonLeft.setImageResource(R.drawable.arrowback);
        buttonLeft.setOnClickListener(this);

        timer = new Timer();
        timer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                Timermethod();
            }
        },0,40);
    }

    private  void Timermethod()
    {
        this.runOnUiThread(Timer_tick);
    }

    private Runnable Timer_tick = new Runnable()
    {
        @Override
        public void run()
        {
            if (game.isRunning)
            {
                if (left)
                {
                    game.movePacmanLeft(10);
                }
                else if (right)
                {
                    game.movePacmanRight(10);
                }
                else if (up)
                {
                    game.movePacmanUp(10);
                }
                else  if (down)
                {
                    game.movePacmanDown(10);
                }
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings)
        {
            //Toast.makeText(this,"settings clicked",Toast.LENGTH_LONG).show();
            return true;
        }
        else if (id == R.id.action_newGame)
        {
            //Toast.makeText(this,"New Game clicked",Toast.LENGTH_LONG).show();
            game.newGame();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view)
    {
        this.left = false;
        this.right = false;
        this.up = false;
        this.down = false;

        if (view.getId() == R.id.moveLeft)
        {
            this.left = true;
        }
        if (view.getId() == R.id.moveRight)
        {
            this.right = true;
        }
        if (view.getId() == R.id.moveUp)
        {
            this.up = true;
        }
        if (view.getId() == R.id.moveDown)
        {
            this.down = true;
        }
    }
}

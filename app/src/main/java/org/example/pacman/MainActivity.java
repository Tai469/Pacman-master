package org.example.pacman;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener
{
    //reference to the main view
    GameView gameView;
    //reference to the game class.
    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //saying we want the game to run in one mode only
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        gameView =  findViewById(R.id.gameView);
        final TextView textView = findViewById(R.id.points);

        game = new Game(this,textView);
        game.setGameView(gameView);
        gameView.setGame(game);
        game.newGame();

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
    }

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
            Toast.makeText(this,"settings clicked",Toast.LENGTH_LONG).show();
            return true;
        }
        else if (id == R.id.action_newGame)
        {
            game.newGame();
            //Toast.makeText(this,"New Game clicked",Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view)
    {
        if (view.getId() == R.id.moveLeft)
        {
            game.movePacmanLeft(10);
        }
        if (view.getId() == R.id.moveRight)
        {
            game.movePacmanRight(10);
        }
        if (view.getId() == R.id.moveUp)
        {
            game.movePacmanUp(10);
        }
        if (view.getId() == R.id.moveDown)
        {
            game.movePacmanDown(10);
        }
    }
}

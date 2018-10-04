package org.example.pacman;

import android.os.AsyncTask;

import java.util.Timer;
import java.util.TimerTask;

class AsyncGameTimeCounter extends AsyncTask<Game, Void, Void> {

    private Game game;
    private Timer timer;
    private int counter = 0;

    protected void onPreExecute()
    {
        timer = new Timer();
    }

    @Override
    protected Void doInBackground(Game... games) {

        this.game = games[0];
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run()
            {
                onProgressUpdate();
            }
        }, 1000, 1000);
        return null;
    }

    protected void onProgressUpdate()
    {
        this.counter++;
        this.game.setGameTimeCounter(this.counter);
        if(isCancelled()) {
            timer.cancel();
        }
    }

    protected void onPostExecute()
    {
        // TODO: 03/10/2018
    }
}

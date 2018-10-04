package org.example.pacman;

import android.os.AsyncTask;

import java.util.Timer;
import java.util.TimerTask;

class AsyncGameTimeCounter extends AsyncTask<Game, Void, Void> {

    private Game game = null;
    private Timer timer = null;
    private int counter = 0;

    protected void onPreExecute()
    {
        this.timer = new Timer();
    }

    @Override
    protected Void doInBackground(Game... games) {

        this.game = games[0];
        this.timer.scheduleAtFixedRate(new TimerTask() {
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
            this.timer.cancel();
        }
    }

    protected void onPostExecute()
    {
        // TODO: 03/10/2018
    }
}

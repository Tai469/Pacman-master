package org.example.pacman;

import android.content.Context;
import android.os.AsyncTask;
import java.util.Timer;
import java.util.TimerTask;

class AsyncEnemyMove extends AsyncTask<Game, Void, Boolean>
{
    /**
     * Override this method to perform a computation on a background thread. The
     * specified parameters are the parameters passed to {@link #execute}
     * by the caller of this task.
     * <p>
     * This method can call {@link #publishProgress} to publish updates
     * on the UI thread.
     *
     * @return A result, defined by the subclass of this task.
     * @see #onPreExecute()
     * @see #onPostExecute
     * @see #publishProgress
     */
    private Game game = null;
    private Timer timer = null;

    protected void onPreExecute()
    {
        timer = new Timer();
    }

    protected Boolean doInBackground(Game... games)
    {
        this.game = games[0];
        this.timer.schedule(new TimerTask() {
            @Override
            public void run() {
                onProgressUpdate();
            }
        }, 0, 100);
        return true;
    }

    protected void onProgressUpdate()
    {
        this.game.MoveEnemies();
        this.game.invalidateGameView();
        this.game.isPacmanKilled();
        if(isCancelled())
        {
            this.timer.cancel();
        }
    }

    protected void onPostExecute(Boolean isFinish)
    {
        // TODO: 03/10/2018
    }
}
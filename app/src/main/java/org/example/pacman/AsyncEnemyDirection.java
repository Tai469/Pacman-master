package org.example.pacman;

import android.content.Context;
import android.os.AsyncTask;

import java.util.Timer;
import java.util.TimerTask;

class AsyncEnemyDirection extends AsyncTask<Game, Void, Boolean>
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
    private Game game;
    private Timer timer;

    protected void onPreExecute()
    {
        // TODO: 03/10/2018
        timer = new Timer();
    }

    protected Boolean doInBackground(Game... games)
    {
        game = games[0];

       timer.schedule(new TimerTask() {
           @Override
           public void run() {
               onProgressUpdate();
           }
       }, 0, 1000);

        return true;
    }

    protected void onProgressUpdate()
    {
        game.DirectEnemies();
        if(isCancelled())
        {
            timer.cancel();
        }
    }

    protected void onPostExecute(boolean isFinish)
    {
        // TODO: 03/10/2018
    }
}
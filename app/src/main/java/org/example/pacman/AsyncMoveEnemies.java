package org.example.pacman;

import android.os.AsyncTask;
import java.util.Timer;
import java.util.TimerTask;

public class AsyncMoveEnemies extends AsyncTask<Game, Object, Object>
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
    Game game;
    Timer timer = new Timer();

    protected void onPreExecute()
    {
        // TODO: 03/10/2018
    }

    protected Object doInBackground(Game... games)
    {
        game = games[0];

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                publishProgress(-1);
            }
        }, 0, 50);

        timer.schedule(new TimerTask()
        {
            @Override
            public void run() {
                publishProgress(0);
            }
        }, 0, 400);

        timer.schedule(new TimerTask()
        {
            @Override
            public void run() {
                publishProgress(1);
            }
        }, 0, 50);

       return null;
    }

    protected void publishProgress(int process)
    {
        switch (process)
        {
            case -1:
                if(!game.isRunning)
                {
                    timer.cancel();
                }
                break;
            case 0:
                game.DirectEnemies();
                break;
            case 1:
                game.MoveEnemies();
                break;
            default:
        }
    }

    protected void onPostExecute()
    {
        // TODO: 03/10/2018
    }
}
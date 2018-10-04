package org.example.pacman;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * This class should contain all your game logic
 */

public class Game
{
    //context is a reference to the activity
    protected Context context;
    //how many points do we have
    private int points = 0, numCoins = 10, numEnemies = 5;
    //bitmap of the pacman
    private Bitmap pacBitmap, coinBitmap, emenyBlueBitmap, enemyPinkBitmap, enemyRedBitmap, enemyYellowBitmap;
    //textview reference to points
    private TextView viewPoints, viewLooser, viewWinner;
    private int pacx, pacy;
    //the list of goldcoins - initially empty
    private ArrayList<GoldCoin> coins = new ArrayList<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();
    //a reference to the gameview
    private GameView gameView;
    //height and width of screen
    private int h,w;

    public boolean isRunning;
    private AsyncEnemyMove asyncEnemyMove;
    private AsyncEnemyDirection asyncEnemyDirection;

    //constructor
    public Game(Context context, TextView viewPoints, TextView viewLooser, TextView viewWinner)
    {
        this.context = context;
        this.viewPoints = viewPoints;
        this.viewLooser = viewLooser;
        this.viewWinner = viewWinner;

        pacBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.pacman);
        coinBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.euro);
        enemyRedBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.pacman1);
        enemyPinkBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.pacman2);
        emenyBlueBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.pacman3);
        enemyYellowBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.pacman4);
    }

    public void setGameView(GameView view)
    {
        this.gameView = view;
    }

    public ArrayList<GoldCoin> getGoldCoins()
    {
        if(coins.size() > 0)
        {
            return coins;
        }
        for (int idx = 0; idx < numCoins; idx++)
        {
            coins.add(getCoin());
        }
        return coins;
    }

    public ArrayList<Enemy> getEnemies()
    {
        if(enemies.size() > 0)
        {
            return enemies;
        }
        for (int idx = 0; idx < numEnemies; idx++)
        {
            enemies.add(getEnemy());
        }
        return enemies;
    }

    public void newGame()
    {
        this.coins.clear();
        this.enemies.clear();

        this.pacx = 50;
        this.pacy = 400; //just some starting coordinates
        //reset the points
        this.points = 0;

        this.viewLooser.setVisibility(View.INVISIBLE);
        this.viewPoints.setText(context.getResources().getString(R.string.points) + " " + points);
        this.viewWinner.setVisibility(View.INVISIBLE);

        this.isRunning = true;

        asyncEnemyDirection = new AsyncEnemyDirection();
        asyncEnemyDirection.execute(this);

        asyncEnemyMove = new AsyncEnemyMove();
        asyncEnemyMove.execute(this);

        this.gameView.invalidate(); //redraw screen
    }

    public void stopGame(boolean stop)
    {
        if(stop)
        {
            this.isRunning = !stop;
            this.asyncEnemyMove.cancel(stop);
            this.asyncEnemyDirection.cancel(stop);
        }
    }

    public void invalidateGameView()
    {
        this.gameView.invalidate();
    }

    public void setSize(int h, int w)
    {
        this.h = h;
        this.w = w;
    }

    public void movePacmanRight(int pixels)
    {
        //still within our boundaries?
        if (pacx + pixels + pacBitmap.getWidth() < w)
        {
            pacx = pacx + pixels;
            doCollisionCheck();
            gameView.invalidate();
        }
    }

    public void movePacmanLeft(int pixels)
    {
        //still within our boundaries?
        if (pacx - pixels >= 0)
        {
            pacx = pacx - pixels;
            doCollisionCheck();
            gameView.invalidate();
        }
    }

    public void movePacmanUp(int pixels)
    {
        //still within our boundaries?
        if(pacy - pixels >= 0)
        {
            pacy = pacy - pixels;
            doCollisionCheck();
            gameView.invalidate();
        }
    }

    public void movePacmanDown(int pixels)
    {
        //still within our boundaries?
        if (pacy + pixels + pacBitmap.getHeight() < h)
        {
            pacy = pacy + pixels;
            doCollisionCheck();
            gameView.invalidate();
        }
    }

    public void MoveEnemies()
    {
        for (int idx = 0; idx < this.getEnemies().size(); idx++) {
            Enemy enemy = this.getEnemies().get(idx);
            switch (enemy.getDirection())
            {
                case 0: //up
                    if(enemy.getHeight() - 10 - enemy.getBitmap().getHeight() > 10)
                    {
                        enemy.setHeight(enemy.getHeight() - 10);
                    }
                    break;
                case 1: //down
                    if(enemy.getHeight() + 10 + enemy.getBitmap().getHeight() < h)
                    {
                        enemy.setHeight(enemy.getHeight() + 10);
                    }
                    break;
                case 2: //left
                    if(enemy.getWidth() - 10 - enemy.getBitmap().getWidth() > 0)
                    {
                        enemy.setWidth(enemy.getWidth() - 10);
                    }
                    break;
                default: //right
                    if(enemy.getWidth() + 10 + enemy.getBitmap().getWidth() < w)
                    {
                        enemy.setWidth(enemy.getWidth() + 10);
                    }
                    break;
            }
        }
    }

    public void DirectEnemies()
    {
        for (int idx = 0; idx < this.getEnemies().size(); idx++) {
            this.getEnemies().get(idx).setDirection(new Random().nextInt(4));
        }
    }

    public void doCollisionCheck()
    {
        double x1 = this.pacx;
        double y1 = this.pacy;

        for (int idx = 0; idx < coins.size(); idx++)
        {
            if(!coins.get(idx).isTaken())
            {
                double x2 = coins.get(idx).getWidth();
                double y2 = coins.get(idx).getHeight();

                double distance = Math.hypot(x1 - x2, y1 - y2);
                if (distance < 50)
                {
                    coins.get(idx).taken();
                    this.points = this.points + 1;
                    this.viewPoints.setText("Points: " + this.points);
                    if(isEveryCoinSelected())
                    {
                        stopGame(true);
                    }
                    return;
                }
            }
        }
    }

    public int getPacx()
    {
        return pacx;
    }

    public int getPacy()
    {
        return pacy;
    }

    public int getPoints()
    {
        return points;
    }

    public Bitmap getPacBitmap()
    {
        return pacBitmap;
    }

    private GoldCoin getCoin()
    {
        while(true)
        {
            GoldCoin coin = new GoldCoin(this.h, this.w, this.coinBitmap.getWidth(), coinBitmap);
            if(isCoinValid(coin))
            {
                return coin;
            }
        }
    }

    private boolean isCoinValid(GoldCoin coin)
    {
        for (int idx = 0; idx < coins.size(); idx++)
        {
            double x1 = coin.getHeight();
            double y1 = coin.getWidth();

            double x2 = coins.get(idx).getHeight();
            double y2 = coins.get(idx).getWidth();

            double distance = Math.hypot(x1-x2, y1-y2);
            //double tmpDistance = Math.sqrt(Math.pow((x1-x2), 2) + Math.pow((y1-y2), 2));
            //Log.d("Distance", "The distance is : " + distance + " against coin [" + idx + "]");
            if(distance < 100)
            {
                return false;
            }
        }
        return true;
    }

    private boolean isEveryCoinSelected()
    {
        for (int idx = 0; idx < coins.size(); idx++) {
            if(!coins.get(idx).isTaken())
            {
                return false;
            }
        }
        return true;
    }

    private Enemy getEnemy()
    {
        Bitmap bitmap;
        switch (new Random().nextInt(4))
        {
            case 0:
                bitmap = this.emenyBlueBitmap;
                break;
            case 1:
                bitmap = this.enemyPinkBitmap;
                break;
            case 2:
                bitmap = this.enemyRedBitmap;
                break;
            default:
                bitmap = this.enemyYellowBitmap;
                break;
        }
        return new Enemy(h, w, bitmap);
    }

    public void isPacmanKilled()
    {
        double x1 = pacx;
        double y1 = pacy;

        for (int idx = 0; idx < enemies.size(); idx++) {

            Enemy enemy = enemies.get(idx);
            if(enemy.isActive)
            {
                double x2 = enemy.getHeight();
                double y2 = enemy.getWidth();

                double distance = Math.hypot(x1 - x2, y1 - y2);
                if(distance < 50)
                {
                    stopGame(true);
                }
            }
        }
    }
}

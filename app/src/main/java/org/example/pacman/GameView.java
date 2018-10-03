package org.example.pacman;

import android.content.Context;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


import java.util.ArrayList;

public class GameView extends View
{
	Game game;
	ArrayList<GoldCoin> coins = new ArrayList<>();
	ArrayList<Enemy> enemies = new ArrayList<>();
    int h,w; //used for storing our height and width of the view

	/* The next 3 constructors are needed for the Android view system,
	when we have a custom view.
	 */
	public GameView(Context context)
    {
		super(context);
	}

	public GameView(Context context, AttributeSet attrs) {
		super(context,attrs);
	}

	public GameView(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context,attrs,defStyleAttr);
	}

	public void setGame(Game game)
	{
		this.game = game;
	}

	//In the onDraw we put all our code that should be
	//drawn whenever we update the screen.
	@Override
	protected void onDraw(Canvas canvas)
    {
		//Here we get the height and weight
		h = canvas.getHeight();
		w = canvas.getWidth();
		//update the size for the canvas to the game.
		game.setSize(h,w);
		this.coins = game.getGoldCoins();
		this.enemies = game.getEnemies();

		Log.d("GAMEVIEW","h = "+h+", w = "+w);
		//Making a new paint object
		final Paint paint = new Paint();
		canvas.drawColor(Color.WHITE); //clear entire canvas to white color

		//TODO loop through the list of goldcoins and draw them.
		//draw the GoldCoins
		for (int idx = 0; idx < coins.size(); idx++) {
			GoldCoin coin = coins.get(idx);
			if(!coin.isTaken())
			{
				canvas.drawBitmap(coin.getBitmap(), coin.getWidth(), coin.getHeight(), paint);
			}
		}

		for (int idx = 0; idx < this.enemies.size(); idx++) {
			Enemy enemy = enemies.get(idx);
			if(enemy.isActive)
			{
				canvas.drawBitmap(enemy.bitmap, enemy.getWidth(), enemy.getHeight(), paint);
			}
		}

		//draw the pacman
        canvas.drawBitmap(game.getPacBitmap(), game.getPacx(),game.getPacy(), paint);
		super.onDraw(canvas);
	}

}

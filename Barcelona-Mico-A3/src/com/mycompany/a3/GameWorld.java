package com.mycompany.a3;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;
import java.util.Set;

import com.codename1.ui.geom.Dimension;

public class GameWorld extends Observable {
	// Private Fields
	private int lives = 3;
	private int time;
	private Random rand = new Random();
	private int lastFlag = 4;
	private int height;
	private int width;
	private boolean sound;
	private GameObjectCollection gameCollection;
	private boolean pause;
	private boolean position;
	// private MapView mv;
	// private GameObject g;

	BGSound Bg;
	Sound hitSpider;
	Sound cheerFlag;
	Sound AntConsume;

	public GameWorld() {

		gameCollection = new GameObjectCollection();

	}

	public void init() {
		this.pause = false;
		this.sound = false;
		this.time = 0;
		this.position = false;

		// Create four flags
		gameCollection.add(new Flag(200, 200, 100, 1, this));
		gameCollection.add(new Flag(200, 800, 100, 2, this));
		gameCollection.add(new Flag(700, 800, 100, 3, this));
		gameCollection.add(new Flag(900, 400, 100, 4, this));
		gameCollection.add(
				new FoodStation(rand.nextFloat() * this.getWidth(), rand.nextFloat() * this.getHeight(), 100, this));
		gameCollection.add(
				new FoodStation(rand.nextFloat() * this.getWidth(), rand.nextFloat() * this.getHeight(), 110, this));
		new Ant(200, 200, 50, 180, 5, this);
		gameCollection.add(getUser());
		// create two spiders
		gameCollection.add(new Spider(rand.nextFloat() * this.getWidth(), rand.nextFloat() * this.getHeight(),
				rand.nextInt(50) + 50, rand.nextInt(360), rand.nextInt(15) + 5));
		gameCollection.add(new Spider(rand.nextFloat() * this.getWidth(), rand.nextFloat() * this.getHeight(),
				rand.nextInt(50) + 50, rand.nextInt(360), rand.nextInt(15) + 5));

		this.setChanged();
		this.notifyObservers();

	}

	// Accessors and Mutators
	public int getTime() {
		return this.time;
	}

	public int getLastFlag() {
		return this.lastFlag;
	}

	public Ant getUser() {
		return Ant.getAnt();
	}

	public boolean getSound() {
		return this.sound;
	}

	public void setSound(boolean sound) {
		this.sound = sound;
		this.setChanged();
		this.notifyObservers();

		if (sound == true) {
			sound = false;
			System.out.println("Sound on.");
		} else {
			sound = true;
			System.out.println("Sound off.");
		}

	}

	public BGSound getBgSound() {
		return this.Bg;
	}

	public GameObjectCollection getCollection() {
		return this.gameCollection;

	}

	public int getFood() {
		return getUser().getFoodLevel();
	}

	public int getLives() {
		return this.lives;
	}

	public int getHealth() {
		return getUser().getHealthLevel();
	}

	public void setHeight(int mapHeight) {
		this.height = mapHeight;

	}

	public void setWidth(int mapWidth) {
		this.width = mapWidth;

	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public boolean pauseMode() {
		return this.pause;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}

	public void disablePosition(boolean p) {
		this.position = p;
	}

	public boolean getPosition() {
		return position;
	}

	public void accelerate() {
		// Get the maximum speed
		int maxSpeed = getUser().getMaximumSpeed();
		// Add 2 to the speed if it's less than the max speed
		int newSpeed = Math.min(getUser().getSpeed() + 2, maxSpeed);
		if (newSpeed > getUser().getSpeed()) {
			System.out.println("+2 to Speed: " + newSpeed);
		}

		// Update the user's speed
		getUser().setSpeed(newSpeed);

		// Notify observers
		this.setChanged();
		this.notifyObservers();
	}

	public void brake() {
		int min = 0;
		int speed = getUser().getSpeed();
		// checks if decreasing speed by 1 is greater than min
		if (speed > min) {
			// decrease speed by 1
			speed--;
			getUser().setSpeed(speed);
			System.out.println("-1 to Speed: " + getUser().getSpeed());
		} else {
			System.out.println("Speed has already been minimized to 0");
		}

		this.setChanged();
		this.notifyObservers();
	}

	public void turnR() {
		int newHeading = getUser().getHeading() + 15;
		getUser().setHeading(newHeading % 360);
		System.out.println("Ant's Heading has gone down to: " + getUser().getHeading());

		this.setChanged();
		this.notifyObservers();
	}

	public void turnL() {
		int newHeading = getUser().getHeading() - 15;
		getUser().setHeading((newHeading + 360) % 360);
		System.out.println("Ant's Heading has gone down to: " + getUser().getHeading());

		this.setChanged();
		this.notifyObservers();
	}

	public void collideFood(FoodStation SelectedFoodstation) {

		// checks if the Foodstation has a capacity that doesn't equal 0
		if (SelectedFoodstation.getCapacity() != 0) {

			// Increase ant's foodlevel
			int newFoodLevel = SelectedFoodstation.getCapacity() + this.getUser().getFoodLevel();
			this.getUser().setFoodLevel(newFoodLevel);
			SelectedFoodstation.setColor(144, 238, 144);
			SelectedFoodstation.setCapacity(0);

			// Create a new food station and add it to the game collection
			FoodStation newFoodStation = new FoodStation(this.rand.nextFloat() * this.getWidth(),
					this.rand.nextFloat() * this.getHeight(), this.rand.nextInt(50) + 50, this);
			this.gameCollection.add(newFoodStation);

			// Plays sound
			if (this.sound == true) {
				this.AntConsume.play();
			}
		}

		// Notify observers
		this.setChanged();
		this.notifyObservers();
	}

	public boolean collideFlag(Flag picked) {
		boolean flagReached = false;

		int userLastFlag = this.getUser().getLastFlag();
		int pickedSequenceNumber = picked.getSequenceNumber();

		// Check if the User's last flag reached is equal to the next flag (subtract 1)
		if (userLastFlag == pickedSequenceNumber - 1) {
			this.getUser().setLastFlag(pickedSequenceNumber);

			// Play sound
			if (this.sound) {
				this.cheerFlag.play();
			}
		}

		int lastFlag = this.lastFlag;

		// Check if the ant collided with last flag
		if (userLastFlag == lastFlag) {
			// Play sound
			if (this.sound) {
				this.cheerFlag.play();
			}

			// End prompt
			System.out.println("You have reached the last flag, Flag " + userLastFlag);
			System.out.println("Hooray, you have won, total time: " + time);
			com.codename1.ui.Display.getInstance().exitApplication();
		}

		// Notify observers
		this.setChanged();
		this.notifyObservers();

		// Return true once flag is reached
		flagReached = true;
		return flagReached;
	}

	public void collideSpider() {
		int damage = 25;
		getUser().setHealthLevel(getUser().getHealthLevel() - damage);
		System.out.println("The spider has collided with the spider, Current Health: " + getUser().getHealthLevel());
		getUser().setColor(150, 75, Math.min(255, 0 + damage));
		getUser().setSpeed(Math.max(getUser().getSpeed() - damage, getUser().getMaximumSpeed()));
		if (getUser().getHealthLevel() == 0 && lives > 0) {
			lives--;
			gameCollection.clear();
			System.out.println("Ant's speed is " + getUser().getSpeed());
			System.out.println("Current Health is 0, lost 1 life, Remaining Lives: " + lives);
			getUser().reset();
			init();
		}
		if (lives == 0) {
			gameCollection.clear();
			System.out.println("You have " + lives + " lives. Game Over!");
			com.codename1.ui.Display.getInstance().exitApplication();
		}
		this.setChanged();
		this.notifyObservers();
	}

	public void tick(int elapsedTime, Dimension dCmpSize) {

		// Spiders update their heading as indicated above.
		IIterator itr = gameCollection.getIterator();
		while (itr.hasNext()) {
			GameObject s = itr.getNext();
			// checks if object is a spider
			if (s instanceof Spider) {
				// heading range between 5 and -5
				((Spider) s).setHeading(((Spider) s).getHeading() + (rand.nextInt(5 - (-5)) + (-5)));
			}
		}

		// all moveable objects are told to update their positions according to their
		// current heading and speed
		IIterator itr2 = gameCollection.getIterator();
		// Iterates through the gameCollection
		while (itr2.hasNext()) {
			GameObject g = (GameObject) itr2.getNext();
			// Checks if its a moveableObject
			if (g instanceof MoveableGameObject) {
				// Moveables update their heading and speed
				((MoveableGameObject) g).Move(elapsedTime, dCmpSize);
			}

		}
		// Checks if there is any collisions
		IIterator iter1 = gameCollection.getIterator();
		while (iter1.hasNext()) {
			GameObject curObj = (GameObject) iter1.getNext();
			IIterator iter2 = gameCollection.getIterator();
			while (iter2.hasNext()) {
				GameObject otherObj = (GameObject) iter2.getNext();
				if (otherObj != curObj) {
					if (curObj.collidesWith(otherObj)) {
						curObj.handleCollision(otherObj);

					}
					// removes the objects that aren't colliding anymore
					curObj.NotCollision(otherObj);
				}
			}
		}

		// the ants food level is reduced by the amount indicated by its
		// foodConsumptionRate,
		if (getUser().getFoodLevel() > 0) {
			getUser().setFoodLevel(getUser().getFoodLevel() - getUser().getFoodConsumptionRate());
			// Prevent FoodLevel to be negative
		} else {
			getUser().setFoodLevel(0);
			getUser().setSpeed(0);
		}
		if (getUser().getFoodLevel() == 0) {

			// If Users lives is greater than 0 and Users Ant health is zero then..
			if (lives > 0) {
				// increment -1 life
				lives--;
				// clear out the arraylist
				gameCollection.clear();
				// Reset User's health and Food level so it won't end game automatically
				getUser().reset();
				// print remaining lives
				System.out.println("Current Food Level is 0, lost 1 life, Remaining Lives: " + lives);
				// re-initialize
				init();
			}
			// if remaining live is 0 then exit program
			if (lives == 0) {
				// Resets gameCollection
				gameCollection.clear();
				System.out.println("You have " + lives + " lives. Game Over!");
				// Exits program
				com.codename1.ui.Display.getInstance().exitApplication();

			}
		}
		// the elapsed time game clock is incremented by one

		time++;

		// Changes and notifies the observers
		this.setChanged();
		this.notifyObservers();
	}

	/*
	 * Iterates through the gameCollection arraylist and prints out each objects
	 * toString
	 */
	public void PrintMap() {
		IIterator iter = gameCollection.getIterator();
		while (iter.hasNext()) {
			System.out.println((iter.getNext().toString()));
		}
		System.out.println();
	}

	public void Display() {
		System.out.println("Lives Remaining: " + lives);
		System.out.println("Current Time Lapse: " + time);
		System.out.println("Highest Flag Reached: " + (getUser().getLastFlag()));
		System.out.println("Current Ant's Food Level: " + getUser().getFoodLevel());
		System.out.println("Current Ant's Health Level: " + getUser().getHealthLevel());

	}

	public void PositionOn() {
		if (position == true) {
			position = false;
		} else {
			position = true;
		}
	}

	// creates the sounds
	public void createSounds() {
		hitSpider = new Sound("hit.wav");
		cheerFlag = new Sound("FlagCheer.wav");
		AntConsume = new Sound("AntConsume.wav");
		Bg = new BGSound("BackGround.wav");

	}
}

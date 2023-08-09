package com.mycompany.a3;

import java.util.ArrayList;

/*
The purpose of this class is to hold all game objects and provide methods for
iterating and modifying the game objects. This class is used in place of storing
game objects directly in the GameWorld class.
*/

public class GameObjectCollection implements ICollection {
// ArrayList to store all game objects
	private ArrayList<GameObject> ObjectCollection;

	public GameObjectCollection() {
		ObjectCollection = new ArrayList<GameObject>();

	}

//Must Include a way to iterator GameObjects
	@Override
	public IIterator getIterator() {
		return new GameObjectIterator();

	}

	@Override
	public void add(GameObject object) {
		ObjectCollection.add(object);

	}

	public void clear() {
		ObjectCollection.clear();
	}

	private class GameObjectIterator implements IIterator {
		private int currentIndex;

		public GameObjectIterator() {
			currentIndex = -1;
		}

		/*
		 * Checks whether there is a next element in the GameObjectIterator. True if
		 * there is a next element, False if there isn't.
		 */
		@Override
		public boolean hasNext() {
			return ObjectCollection.size() > 0 && currentIndex < ObjectCollection.size() - 1;
		}

		@Override
		public GameObject getNext() {
			// adds one to currentIndex to go to the next index
			currentIndex++;
			return ObjectCollection.get(currentIndex);
		}

		@Override
		public void remove(GameObject object) {
			// Removes the last index
			ObjectCollection.remove(object);

		}

	}
}
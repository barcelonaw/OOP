package com.mycompany.a3;

import java.util.Observer;
import java.util.Observable;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.plaf.Border;

public class MapView extends Container implements Observer {

	private GameWorld gw;
	private Game g;

	public MapView(GameWorld gw, Game g) {
		this.getAllStyles().setBgTransparency(255);
		this.getAllStyles().setBgColor(ColorUtil.WHITE);
		this.getAllStyles().setBorder(Border.createLineBorder(5, ColorUtil.rgb(255, 0, 0)));

		this.gw = gw;
		this.g = g;

	}

	@Override
	public void update(Observable observable, Object data) {
		((GameWorld) observable).PrintMap();
		;
		this.repaint();
	}

	@Override
	public void paint(Graphics g) {

		// call super.paint
		super.paint(g);
		Point pCmpRelPrnt = new Point(getX(), getY());
		// iterates through the gamecollection
		IIterator iter = gw.getCollection().getIterator();
		while (iter.hasNext()) {
			// draws the objects on the mapview custom container
			GameObject next = iter.getNext();
			next.draw(g, pCmpRelPrnt);

		}
		this.repaint();
	}

	@Override
	public void pointerPressed(int x, int y) {
		// makes the point location relative to the component's parent's origin
		x = x - getParent().getAbsoluteX();
		y = y - getParent().getAbsoluteY();

		Point pPtrRelPrnt = new Point(x, y);
		Point pCmpRelPrnt = new Point(getX(), getY());

		IIterator iter = gw.getCollection().getIterator();

		if (gw.getPosition()) {
			// iterates through the gameobject
			while (iter.hasNext()) {
				GameObject obj = iter.getNext();

				// checks if the object is Fixed and that it is selected
				if (obj instanceof Fixed && ((Fixed) obj).isSelected()) {
					// then changes the location to the new location
					obj.setLocation(pPtrRelPrnt.getX() - this.getX(), pPtrRelPrnt.getY() - this.getY());
					((Fixed) obj).setSelected(false);
				}
			}
		} else {
			// iterates through the gameobject
			while (iter.hasNext()) {
				GameObject obj = iter.getNext();
				if (obj instanceof Fixed) {
					// checks if the user has selected it
					if (((Fixed) obj).contains(pPtrRelPrnt, pCmpRelPrnt)) {
						((Fixed) obj).setSelected(true);

					} else {
						// else returns false
						((Fixed) obj).setSelected(false);
					}
				}
			}
		}
		this.repaint();
	}
}
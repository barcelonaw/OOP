package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;

public class Game extends Form implements Runnable {
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	private static String TITLE = "FlagByFlag Game";
	private UITimer timer = new UITimer(this);
	private AccelerateCommand ac;
	private BrakeCommand bc;
	private TurnLeftCommand lc;
	private TurnRightCommand rc;
	private PositionCommand posC;
	private PauseCommand pauC;
	private HelpCommand helpC;
	private SoundCommand soundC;
	private AboutCommand aboutC;
	private ExitCommand exitC;
	private ButtonCommand b;
	private ButtonCommand btAccel;
	private ButtonCommand btBrake;
	private ButtonCommand btRight;
	private ButtonCommand btLeft;
	private ButtonCommand btPause;
	private ButtonCommand btPosition;

	public Game() {

		this.setLayout(new BorderLayout());
		gw = new GameWorld(); // create Observable GameWorld
		mv = new MapView(gw, this); // create an Observer for the map
		sv = new ScoreView(gw); // create an Observer for the game/ant state data
		gw.addObserver(mv); // register the map observer
		gw.addObserver(sv); // register the score observer

		this.add(BorderLayout.CENTER, mv);
		this.add(BorderLayout.NORTH, sv);

		AllCommands();

		this.show();

		gw.setHeight(mv.getHeight());
		gw.setWidth(mv.getWidth());

		// initialize world
		gw.init();
		gw.createSounds();
		revalidate();

		timer.schedule(100, true, this);

	}

	// Sets up all the commands, arrays, loops to create and add buttons.
	private void AllCommands() {

		CommandRight();
		CommandLeft();
		CommandBottom();
		CommandMenu();

	}

	private void CommandRight() {
		Container rightC = new Container(new GridLayout(4, 1));
		rightC.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		bc = new BrakeCommand(gw);
		btBrake = new ButtonCommand(bc);
		rightC.add(btBrake);

		rc = new TurnRightCommand(gw);
		btRight = new ButtonCommand(rc);
		rightC.add(btRight);

		this.add(BorderLayout.EAST, rightC);

	}

	private void CommandLeft() {
		Container leftC = new Container((new GridLayout(4, 1)));
		leftC.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		ac = new AccelerateCommand(gw);
		btAccel = new ButtonCommand(ac);
		leftC.add(btAccel);

		lc = new TurnLeftCommand(gw);
		btLeft = new ButtonCommand(lc);
		leftC.add(btLeft);

		this.add(BorderLayout.WEST, leftC);
	}

	private void CommandBottom() {

		Container bottomC = new Container((new FlowLayout(Component.CENTER)));
		bottomC.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		pauC = new PauseCommand(gw, this, b);
		btPause = new ButtonCommand(pauC);
		bottomC.add(btPause);

		posC = new PositionCommand(gw);
		btPosition = new ButtonCommand(posC);
		bottomC.add(btPosition);

		this.add(BorderLayout.SOUTH, bottomC);
	}

	private void CommandMenu() {
		Toolbar menu = new Toolbar();
		this.setToolbar(menu);
		menu.setTitle(TITLE);
		CheckBox checkSideMenuComponent = new CheckBox();
		checkSideMenuComponent.getAllStyles().setBgTransparency(255);
		checkSideMenuComponent.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		soundC = new SoundCommand(gw);
		checkSideMenuComponent.setCommand(soundC);
		menu.addComponentToSideMenu(checkSideMenuComponent);

		// Adds accelerate command
		ac = new AccelerateCommand(gw);
		menu.addCommandToSideMenu(ac);

		// Adds help command
		helpC = new HelpCommand(gw);
		menu.addCommandToRightBar(helpC);

		// Adds about command
		aboutC = new AboutCommand(gw);
		menu.addCommandToLeftBar(aboutC);

		// Add exit
		exitC = new ExitCommand(gw);
		menu.addCommandToSideMenu(exitC);
	}

	@Override
	public void run() {
		Dimension dCmpSize = new Dimension(gw.getWidth(), gw.getHeight());

		gw.tick(100, dCmpSize);

	}

	public void pauseG() {
		// if the game is paused
		if (!gw.pauseMode()) {
			// disable all keybinds and buttons
			removeKeyListener('b', bc);
			removeKeyListener('r', rc);
			removeKeyListener('a', ac);
			removeKeyListener('l', lc);
			removeKeyListener('x', exitC);
			bc.setEnabled(false);
			rc.setEnabled(false);
			ac.setEnabled(false);
			lc.setEnabled(false);
			exitC.setEnabled(false);
			posC.setEnabled(true);
			btAccel.setEnabled(false);
			btBrake.setEnabled(false);
			btRight.setEnabled(false);
			btLeft.setEnabled(false);
			btPosition.setEnabled(true);
			// pause sound
			gw.getBgSound().pause();
			gw.setPause(!gw.pauseMode());
			timer.cancel();

		} else {
			addKeyListener('b', bc);
			addKeyListener('r', rc);
			addKeyListener('a', ac);
			addKeyListener('l', lc);
			addKeyListener('p', pauC);
			addKeyListener('x', exitC);
			bc.setEnabled(true);
			rc.setEnabled(true);
			ac.setEnabled(true);
			lc.setEnabled(true);
			exitC.setEnabled(true);
			posC.setEnabled(false);
			btAccel.setEnabled(true);
			btBrake.setEnabled(true);
			btRight.setEnabled(true);
			btLeft.setEnabled(true);
			btPosition.setEnabled(false);

			// initialize sound
			gw.getBgSound().run();
			gw.setPause(!gw.pauseMode());
			timer.schedule(100, true, this);
		}

	}

}

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BezierCurve {


	static JFrame frame = new JFrame("Bézier Curve");


	Canvas canvas;

	int code;

	// P0
	int x0 = 100;
	int y0 = 900;



	//P1
	int x1 = 500;
	int y1 = 100;



	//P2
	int x2 = 900; 
	int y2 = 900;


	int size = 2;

	int range = 100;


	move0 move_0 = new move0();
	move1 move_1 = new move1();
	move2 move_2 = new move2();

	public class move0 implements MouseMotionListener {

		public void mouseDragged(MouseEvent e) {

			x0 = e.getX() - 8;
			y0 = e.getY() - 40;
			frame.repaint();
		}

		public void mouseMoved(MouseEvent e) {}

	}

	public class move1 implements MouseMotionListener {

		public void mouseDragged(MouseEvent e) {

			x1 = e.getX() - 8;
			y1 = e.getY() - 40;
			frame.repaint();
		}

		public void mouseMoved(MouseEvent e) {}

	}

	public class move2 implements MouseMotionListener {

		public void mouseDragged(MouseEvent e) {

			x2 = e.getX() - 8;
			y2 = e.getY() - 40;
			frame.repaint();
		}

		public void mouseMoved(MouseEvent e) {}

	}


	public static void removeListeners(move0 obj0, move1 obj1, move2 obj2) {
		
		frame.removeMouseMotionListener(obj0);
		frame.removeMouseMotionListener(obj1);
		frame.removeMouseMotionListener(obj2);
		
	}
	
	
	public class Canvas extends JPanel {

		public void paintComponent(Graphics g) {

			super.paintComponent(g);


			g.setColor(Color.RED);

			float t = 0.00001f;
			float T;

			for (int x = 0; x <= (1/t); x++) {

				T = t * x;

				int xi = x1 + ((int)((x0 - x1) * T));
				int yi = y1 + ((int)((y0 - y1) * T));
				int xf = x2 + ((int)((x1 - x2) * T));
				int yf = y2 + ((int)((y1 - y2) * T));

				int X = xf - (int)((xf - xi) * T);
				int Y = yf - (int)((yf - yi) * T);

				g.drawLine(X, Y, X, Y);

			}

			g.setColor(Color.BLACK);


			g.fillOval(x0, y0, size, size);
			g.fillOval(x1, y1, size, size);
			g.fillOval(x2, y2, size, size);

			g.drawString("P0", x0, y0 - 5);
			g.drawString("P1", x1, y1 - 5);
			g.drawString("P2", x2, y2 - 5);

			g.drawLine(x0, y0, x1, y1);
			g.drawLine(x1, y1, x2, y2);		

		}
	}

	public BezierCurve() {

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 1000);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);

		
		frame.addMouseMotionListener(new MouseMotionListener() {


			public void mouseMoved(MouseEvent e) {

				if ((e.getX() > x0 - range && e.getX() < x0 + range) && (e.getY() > y0 - range && e.getY() < y0 + range)) {

					removeListeners(move_0, move_1, move_2);
					frame.addMouseMotionListener(move_0);
				
				}
				if ((e.getX() > x1 - range && e.getX() < x1 + range) && (e.getY() > y1 - range && e.getY() < y1 + range)) {

					removeListeners(move_0, move_1, move_2);
					frame.addMouseMotionListener(move_1);

				}
				if ((e.getX() > x2 - range && e.getX() < x2 + range) && (e.getY() > y2 - range && e.getY() < y2 + range)) {

					removeListeners(move_0, move_1, move_2);
					frame.addMouseMotionListener(move_2);

				}

			}

			public void mouseDragged(MouseEvent e) {}

		});

		canvas = new Canvas();

		frame.add(canvas);

		frame.setVisible(true);

	}

	
	public static void main(String [] args) {

		new BezierCurve();

	}

}

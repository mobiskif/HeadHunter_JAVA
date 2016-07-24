package comp;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class JPanel_Photo extends JPanel {
	BufferedImage img = null;

	public JPanel_Photo() {
		super();
		setPreferredSize(new Dimension(120, 160));
		loadPhoto("res/img.png");
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (!(img==null)) g.drawImage(img, 0, 0, null);
	}
	
	public void loadPhoto(String filename) {
		try {
			img = ImageIO.read(new File(filename));
			if (!(img==null)) setPreferredSize(new Dimension(img.getWidth(null), img.getHeight(null)));
			else setPreferredSize(new Dimension(120, 160));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}

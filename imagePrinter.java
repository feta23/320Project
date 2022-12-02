import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.JFrame;

public class imagePrinter extends Canvas{
	 
	Image image;
	
	public imagePrinter() {
		image = null;
	}
	
	public void setImage(File f) throws IOException {
		BufferedImage i = new BufferedImage(30,30,BufferedImage.TYPE_INT_ARGB);
		i = ImageIO.read(f);
		image = i;
	}
	
	public void setImage(byte[] b) throws IOException {
		Image i = ImageIO.read(new ByteArrayInputStream(b));
		image = i;
	}
	
	public Image getImage() {
		return image;
	}
	
	public void paint(Graphics g) {
		g.drawImage(getImage(), 0, 0, this);
	}
	
	
}

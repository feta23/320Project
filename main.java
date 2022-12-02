import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.imageio.ImageIO;
import javax.swing.JFrame;



public class main {

    public static void main(String[] args) throws IOException {
        System.out.println("Image Compression...");
        
        //get an image from file
        File file = new File("/Users/George/OneDrive/Desktop/flower.jpg");
        
        //get the bytes
        byte[] orgImage = Files.readAllBytes(file.toPath());
        byte[] comImage = null;
        

        
        System.out.println("Orginal Image Bytes(KB): " + orgImage.length / 1024f);
        
        long start = System.currentTimeMillis();
        
        //compress image
        comImage = ImageUtil.compressImage(orgImage,1);
        
        
        long end = System.currentTimeMillis();
        long elapsed = end - start;
        
        System.out.println("After Image Bytes(KB): " + comImage.length / 1024f);
        System.out.println("\nTime Elapsed: " + elapsed + "ms");
        
        imagePrinter printer0 = new imagePrinter();
        printer0.setImage(file);
        JFrame f0 = new JFrame("Original");
        f0.add(printer0);
        f0.setSize(950,1000);
		f0.setVisible(true);
		
		
		
	
		imagePrinter printer1 = new imagePrinter();
        printer1.setImage(comImage);
        JFrame f1 = new JFrame("Compressed");
        f1.add(printer1);
        f1.setSize(950,1000);
		f1.setVisible(true);
        
		
        
    }
    
}

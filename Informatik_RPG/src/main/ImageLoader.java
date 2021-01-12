package main;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class ImageLoader {
	
	public static BufferedImage loadImg(String path) {
		try {
			return ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static BufferedImage loadImgScaled(String path, double scale) {
		BufferedImage originalImage = loadImg(path);
		
		int newWidth = (int) (originalImage.getWidth()*scale);
		int newHeight = (int) (originalImage.getHeight()*scale);
		
		BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
		
		AffineTransform at = new AffineTransform();
		at.scale(scale, scale);
		AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		
		return scaleOp.filter(originalImage, newImage);
	}
	
	public static BufferedImage loadImgScaled(String path, double scaleWidth, double scaleHeight) {
		BufferedImage originalImage = loadImg(path);
		
		int newWidth = (int) (originalImage.getWidth()*scaleWidth);
		int newHeight = (int) (originalImage.getHeight()*scaleHeight);
		
		BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
		
		AffineTransform at = new AffineTransform();
		at.scale(scaleWidth, scaleHeight);
		AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		
		return scaleOp.filter(originalImage, newImage);
	}
	
}

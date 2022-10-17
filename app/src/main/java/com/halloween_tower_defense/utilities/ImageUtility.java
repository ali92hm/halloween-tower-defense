package com.halloween_tower_defense.utilities;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class ImageUtility {

  private static final String IMAGE_RESOURCE_FOLDER = "images";

  /**
   * Getter to return a buffered image from our images folder.
   *
   * @param imageName
   * @return BufferedImage
   */

  public static BufferedImage getImage(final String imageName) {
    try {
      InputStream inputStream = ImageUtility.class.getClassLoader()
          .getResourceAsStream(IMAGE_RESOURCE_FOLDER + "/" + imageName);

      if (inputStream == null) {
        throw new IllegalArgumentException("Image not found " + imageName);
      }

      return ImageIO.read(inputStream);
    } catch (IOException e) {
      System.err.println(e);
      System.exit(128);
    }
    return null;
  }

  /**
   * Getter to return a buffered image from our images folder.
   *
   * @param imageName
   * @param width
   * @param height
   * @return BufferedImage
   */

  public static BufferedImage getImage(final String imageName,
                                       final int width, final int height) {
    return resizeImage(getImage(imageName), width, height);
  }

  /**
   * Resizes an image from our images folder.
   *
   * @param image
   * @param newWidth
   * @param newHeight
   * @return BufferedImage
   */

  public static BufferedImage resizeImage(final BufferedImage image, final int newWidth,
                                          final int newHeight) {
    final int imageWidth = image.getWidth();
    final int imageHeight = image.getHeight();
    final BufferedImage newImage = new BufferedImage(newWidth,
        newHeight, image.getType());
    final Graphics2D graphicsRenderer = newImage.createGraphics();
    graphicsRenderer.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
        RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    graphicsRenderer.drawImage(image, 0, 0, newWidth, newHeight, 0, 0,
        imageWidth, imageHeight, null);
    graphicsRenderer.dispose();
    return newImage;
  }

  /**
   * Rotates an image from our images folder.
   *
   * @param image
   * @param rotationAngle
   * @return BufferedImage
   */

  public static BufferedImage rotateImage(final BufferedImage image, final int rotationAngle) {
    double rotationRequired = Math.toRadians(rotationAngle);
    return rotateImage(image, rotationRequired);
  }

  /**
   * Rotates an image from our images folder.
   *
   * @param image
   * @param rotationRequired
   * @return BufferedImage
   */

  public static BufferedImage rotateImage(final BufferedImage image,
                                          final double rotationRequired) {
    double locationX = image.getWidth() / 2;
    double locationY = image.getHeight() / 2;
    AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
    AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
    return op.filter(image, null);
  }
}

package com.halloween_tower_defense.utilities;

/**
 * creates and stores motion, direction,
 * and speed of a projectile
 *
 * @author Scorpion
 */

public class Vector {

  /*
   **********
   * Fields *
   **********
   */

  private double componentX;
  private double componentY;
  private double magnitude;
  private double angle;

  /*
   ****************
   * Constructors *
   ****************
   */

  /**
   * constructor for the zero vector
   */

  public Vector() {
    this.componentX = 0;
    this.componentY = 0;
    this.findMagnitude();
    this.findAngle();
  }

  /**
   * constructor for a vector with equal
   * x and y components
   *
   * @param comp
   */

  public Vector(final double comp) {
    this.componentX = comp;
    this.componentY = comp;
    this.findMagnitude();
    this.findAngle();
  }

  /**
   * constructor for a vector with different
   * x and y components
   *
   * @param componentX
   * @param componentY
   */

  public Vector(final double componentX, final double componentY) {
    this.componentX = componentX;
    this.componentY = componentY;
    this.findMagnitude();
    this.findAngle();
  }

  /**
   * constructor for a vector heading from
   * one position to another with a specified
   * magnitude
   *
   * @param startPosition
   * @param destinationPosition
   * @param magnitude
   */

  public Vector(final Position startPosition, final Position destinationPosition,
                final double magnitude) {
    this.componentX = destinationPosition.getXCord() - startPosition.getXCord();
    this.componentY = destinationPosition.getYCord() - startPosition.getYCord();
    this.magnitude = magnitude;
    this.findAngle();
    this.findComps();
  }

  /**
   * gets where an object starting at one position
   * with be after on tick heading along this vector
   *
   * @param position
   * @return
   */

  public Position getNextPosition(final Position position) {
    return this.getNextPosition(position, this.magnitude);
  }

  /**
   * gets the next position of a target moving along this
   * vector with the specified magnitude
   *
   * @param position
   * @param magnitude
   * @return
   */

  public Position getNextPosition(final Position position, final double magnitude) {
    int xMag;
    int yMag;

    xMag = (int) (magnitude * Math.cos(this.angle) + 1 / 2);

    if (this.angle < 0) {
      yMag = (int) (magnitude * Math.sin(this.angle) + 1 / 2) * (-1);
    } else {
      yMag = (int) (magnitude * Math.sin(this.angle) + 1 / 2);
    }

    if (this.angle < 0) {
      yMag *= -1;
    }

    return new Position(position.getXCord() + xMag, position.getYCord() + yMag);
  }

  /*
   ********************
   * Accessor Methods *
   ********************
   */

  /**
   * returns the x component of this vector
   *
   * @return double
   */

  public double getcomponentX() {
    return this.componentX;
  }

  /**
   * returns the y component of this vector
   *
   * @return double
   */

  public double getcomponentY() {
    return this.componentY;
  }

  /**
   * returns the magnitude of this vector
   *
   * @return double
   */

  public double getMagnitude() {
    return this.magnitude;
  }

  /**
   * returns the angle of this vector
   *
   * @return double
   */

  public double getAngle() {
    return this.angle;
  }

  /**
   * sets the magnitude of the vector
   *
   * @param magnitude
   */

  public void setMagnitude(final double magnitude) {
    this.magnitude = magnitude;
  }

  /*
   *******************
   * Utility Methods *
   *******************
   */

  /**
   * finds the x and y components from a vector with
   * a specified angle and magnitude
   */

  public void findComps() {
    if (Math.abs(this.angle) < Math.PI / 2) {
      this.componentX = this.magnitude * Math.cos(Math.abs(this.angle));
      if (this.angle < 0) {
        this.componentY = this.magnitude * Math.sin(Math.abs(this.angle)) * (-1);
      } else {
        this.componentY = this.magnitude * Math.sin(Math.abs(this.angle));
      }
    } else {
      this.componentX = this.magnitude * Math.cos(Math.PI - Math.abs(this.angle)) * (-1);
      if (this.angle < 0) {
        this.componentY = this.magnitude * Math.sin(Math.abs(this.angle)) * (-1);
      } else {
        this.componentY = this.magnitude * Math.sin(Math.abs(this.angle));
      }
    }
  }

  /**
   * finds the magnitude of a vector from a given x and y components
   */

  public void findMagnitude() {
    this.magnitude = Math.sqrt((componentX * componentX) + (componentY * componentY));
  }

  /**
   * gets the angle of a vector from x and y components
   */

  public void findAngle() {
    this.angle = Math.atan2(componentY, componentX);
  }

  /**
   * adds two vectors together to find the
   * resulting vector magnitude and direction
   *
   * @param vector
   * @return Vector
   */

  public Vector findVectorSum(final Vector vector) {
    return new Vector(vector.getcomponentX() + this.componentX, vector.componentY + this.componentY);
  }
}

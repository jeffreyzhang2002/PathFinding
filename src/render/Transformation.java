package render;

import math.geometry.coordinates.Point;
import processing.core.PApplet;

/**
 * A Transformation class describes how the rendering will be transformed. A transformation is described as where
 * the newOrigin is going to be. How the X and Y axis are going to be scaled and how much it rotated.
 */
public class Transformation
{
    private float scaleX = 1, scaleY = 1, rotation = 0;
    private Point<Float> newOrigin;

    /**
     * Creates and instance of a Transformation using Transformation Type Enum. This method uses a predefined
     * transformation type.
     * @param processing
     * @param transformationTypes
     */
    public Transformation(PApplet processing, TransformationTypes transformationTypes)
    { setTransformation(processing, transformationTypes); }

    /**
     * Creates an instance of a Transformation by specifying where the new Origin is going to be.
     * The scale for the x and y remain at one and that rotation is 0.
     * @param newOrigin
     */
    public Transformation(Point<Float> newOrigin)
    { this.newOrigin = newOrigin; }

    /**
     * Creates an instance of a Transformation by specifiying where the new Origin is going to be, The scale of the x and y
     * and how much is is rotated by.
     * @param newOrigin
     * @param scaleX
     * @param scaleY
     * @param rotation
     */
    public Transformation(Point<Float> newOrigin, float scaleX, float scaleY, float rotation)
    {
        this.newOrigin = newOrigin;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.rotation = rotation;
    }

    /**
     * gets where the new origin will be
     * @return the new origin
     */
    public Point<Float> getNewOrigin()
    { return newOrigin; }

    /**
     * gets how the X axis will be scaled
     * @return the x scale
     */
    public float getScaleX()
    { return scaleX; }

    /**
     * gets how the Y axis will be scaled
     * @return the y scale
     */
    public float getScaleY()
    { return scaleY; }

    /**
     * gets how the graph will be rotated
     * @return the rotation
     */
    public float getRotation()
    { return rotation; }

    /**
     * Sets the transformation using a tranformation type
     * @param processing
     * @param transformationType
     */
    public void setTransformation(PApplet processing, TransformationTypes transformationType)
    {
        switch (transformationType)
        {
            case Cartesian:
                newOrigin = new Point<Float>(processing.width/2.0f, processing.height/2.0f);
                scaleX = 1f;
                scaleY = -1f;
                rotation = 0f;
            default:
                newOrigin = new Point<Float>(0f, 0f);
                scaleX = 1f;
                scaleY = 1f;
                rotation = 0f;
        }
    }

    /**
     * performs the transformation given a PApplet rendering engine
     * @param processing
     */
    public void doTransformation(PApplet processing)
    {
        processing.translate(newOrigin.getX(), newOrigin.getY());
        processing.scale(scaleX, scaleY);
        processing.rotate(rotation);
    }

    public String toString()
    { return "New Origin: " + newOrigin.toString() + " Scaling: " + scaleX + " , " + scaleY + " Rotation: " + rotation; }

    public boolean equals(Object other)
    {
        if(other instanceof Transformation)
        {
            return  ((Transformation) other).getNewOrigin().equals(this.getNewOrigin()) &&
                    ((Transformation) other).getScaleX() == this.getScaleX() &&
                    ((Transformation) other).getScaleY() == this.getScaleY() &&
                    ((Transformation) other).getRotation() == this.getRotation();
        }
        return false;
    }
}

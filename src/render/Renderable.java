package render;

import math.geometry.coordinates.Point;
import processing.core.PApplet;

/**
 * A Renderable class describes how a given class will be rendered on a PApplet sketch;
 */
public abstract class Renderable
{
    private Point<Float> origin;
    private float width, height;
    private Transformation transformation;
    private boolean useMethod = true;

    /**
     * Creates an instance of Renderable using the point where it it is rendered.
     * @param origin the origin of the Renderable
     * @param width a reference position for the width of the render
     * @param height a reference position for the height of the render
     */
    public Renderable(Point<Float> origin, float width, float height)
    {
        this.origin = origin;
        this.width = width;
        this.height = height;
    }

    /**
     * Creates an instance of Renderable using the point where it it is rendered.
     * @param origin the origin of the Renderable
     * @param width a reference position for the width of the render
     * @param height a reference position for the height of the render
     * @param transformation the Transformation that will be rendered.
     */
    public Renderable(Point<Float> origin, float width, float height, Transformation transformation)
    {
        this.origin = origin;
        this.width = width;
        this.height = height;
        this.transformation = transformation;
        useMethod = false;
    }

    /**
     * This method is used to render the entire class
     * @param processing rendering Engine
     */
    public final void render(PApplet processing)
    {
        renderSettings(processing);
        processing.pushMatrix();
        if(!useMethod)
            transformation.doTransformation(processing);
        else {
            processing.pushMatrix();
            internalRenderTransformation(processing);
            processing.popMatrix();
        }
        processing.popMatrix();
        renderDraw(processing);
    }

    /**
     * Settings for rendering. For example method like RectMode and EllipseMode should be ran here
     * @param processing the rendering Engine
     */
    public abstract void renderSettings(PApplet processing);

    /**
     * This is an internal method that process where they transformation should be done. It can either be done
     * through overriding the renderTransformation method or by using the setTransformation method.
     * @param processing the rendering Engine
     */
    private void internalRenderTransformation(PApplet processing)
    {
        renderTransformation(processing);
        useMethod = true;
    }

    /**
     * Sets the transformation for rendering. If this method is run, the renderTransformationmethod will no longer be run
     * @param transformation the transformation that will be done
     */
    public final void setTransformation(Transformation transformation)
    {
        this.transformation = transformation;
        useMethod = false;
    }

    /**
     * Sets how the object will be transformed. If true this method will transform using the renderTransformation
     * method. If false it will use the transformation method
     * @param useMethod
     */
    public void setUsingMethod(boolean useMethod)
    { this.useMethod = useMethod; }

    /**
     * This method should be overriden for transformation. If this method is run, the transformation given by the
     * set transformation will be removed from this method;
     * @param processing
     */
    public void renderTransformation(PApplet processing)
    {

    }

    /**
     * gets the Origin for the render
     * @return the Origin
     */
    public Point<Float> getOrigin()
    { return origin; }

    /**
     * gets the Width for the render
     * @return the width of the render
     */
    public float getWidth()
    { return width; }

    /**
     * gets the Height of the render
     * @return the height of the render
     */
    public float getHeight()
    { return height; }

    /**
     * gets the transformation for the render
     * @return the transformation
     */
    public Transformation getTransformation()
    { return transformation; }

    /**
     * What will be drawn for the Renderable.
     * @param processing
     */
    public abstract void renderDraw(PApplet processing);

    public String toString()
    { return "Origin: " + origin.toString(); }
}

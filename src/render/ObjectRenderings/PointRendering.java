package render.ObjectRenderings;

import math.RGB;
import math.geometry.coordinates.Point;
import processing.core.PApplet;
import render.Renderable;

public class PointRendering extends Renderable
{
    private RGB color = new RGB(0);

    public PointRendering(Point<Float> origin, float radius)
    { super(origin, radius, radius); }

    public void renderDraw(PApplet processing)
    {
        processing.fill(color.getR(), color.getG(), color.getB());
        processing.ellipse(super.getOrigin().getX(), super.getOrigin().getY(), super.getWidth(), super.getHeight());
    }

    public void renderSettings(PApplet processing)
    { processing.ellipseMode(PApplet.CENTER); }
}

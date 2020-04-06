package math;

public class OdometryTest
{
    public static void main(String[] args)
    {
        double RD = 18;
        double LD = -18;
        double L = 18;

        double CD = (RD + LD)/2;

        double theta = (RD - LD)/L;

        double Thetai = 0;
        double Xi=0;
        double Yi=0;

        double hyp = CD;

        if(theta != 0)
            hyp = (CD / theta) * (Math.sin(theta) / Math.cos(theta/2));

        double deltaY = Math.sin(Thetai + theta/2)*hyp;
        double deltaX = Math.cos(Thetai + theta/2)*hyp;

        Xi = Xi + deltaX;
        Yi = Yi + deltaY;
        Thetai += theta;

        System.out.println("( " + Xi + " , " + Yi + " )" + theta);
    }
}

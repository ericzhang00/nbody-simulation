public class NBody {
  /* Gives radius of universe */
  public static double readRadius(String filename) {
    In in = new In(filename);
    int N=in.readInt();
    double R=in.readDouble();
    return R;
  }
  /* Creates array of all Bodies */
  public static Body[] readBodies(String filename) {
    In in = new In(filename);
    int N=in.readInt();
    double R=in.readDouble();
    int index=0;
    Body[] BodyArray = new Body[N];
    while (index<N) {
      double x=in.readDouble();
      double y=in.readDouble();
      double dx=in.readDouble();
      double dy=in.readDouble();
      double m=in.readDouble();
      String img=in.readString();
      BodyArray[index]=new Body(x,y,dx,dy,m,img);
      index=index+1;
    }
    return BodyArray;
  }
  public static void main(String[] args) {
    double T = Double.parseDouble(args[0]);
    double dt = Double.parseDouble(args[1]);
    String filename = args[2];
    StdDraw.setScale(-readRadius(filename),readRadius(filename));
    StdDraw.picture(0,0,"images/starfield.jpg");
    Body[] Bodies = readBodies(filename);
    int index=0;
    while (index<Bodies.length) {
      Bodies[index].draw();
      index=index+1;
    }
    StdDraw.enableDoubleBuffering();
    double time=0;
    while (time<=T) {
      double[] xForces = new double[Bodies.length];
      double[] yForces = new double[Bodies.length];
      index=0;
      while (index<Bodies.length) {
        xForces[index]=Bodies[index].calcNetForceExertedByX(Bodies);
        yForces[index]=Bodies[index].calcNetForceExertedByY(Bodies);
        index=index+1;
      }
      index=0;
      while (index<Bodies.length) {
        Bodies[index].update(dt,xForces[index],yForces[index]);
        index=index+1;
      }
      StdDraw.setScale(-readRadius(filename),readRadius(filename));
      StdDraw.picture(0,0,"images/starfield.jpg");
      index=0;
      while (index<Bodies.length) {
        Bodies[index].draw();
        index=index+1;
      }
      StdDraw.show();
      StdDraw.pause(10);
      time=time+dt;
    }
    StdOut.printf("%d\n", Bodies.length);
    StdOut.printf("%.2e\n", readRadius(filename));
    for (int i = 0; i < Bodies.length; i++) {
      StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
      Bodies[i].xxPos, Bodies[i].yyPos, Bodies[i].xxVel,
      Bodies[i].yyVel, Bodies[i].mass, Bodies[i].imgFileName);
    }
  }
}

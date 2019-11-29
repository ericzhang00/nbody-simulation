public class Body {
  public double xxPos;
  public double yyPos;
  public double xxVel;
  public double yyVel;
  public double mass;
  public String imgFileName;
  private static final double g=6.67e-11;
  /* Makes new Body */
  public Body(double xP, double yP, double xV, double yV, double m, String img) {
    xxPos=xP;
    yyPos=yP;
    xxVel=xV;
    yyVel=yV;
    mass=m;
    imgFileName=img;
  }
  /* Copies Body */
  public Body(Body b) {
    xxPos=b.xxPos;
    yyPos=b.yyPos;
    xxVel=b.xxVel;
    yyVel=b.yyVel;
    mass=b.mass;
    imgFileName=b.imgFileName;
  }
  /* Calculates distance between Bodies */
  public double calcDistance(Body b) {
    return Math.sqrt((this.xxPos-b.xxPos)*(this.xxPos-b.xxPos)+(this.yyPos-b.yyPos)*(this.yyPos-b.yyPos));
  }
  /* Calculates force exerted on a Body by another Body */
  public double calcForceExertedBy(Body b) {
    return g*this.mass*b.mass/(this.calcDistance(b)*this.calcDistance(b));
  }
  /* Calculates force exerted on a Body by another Body in the X direction */
  public double calcForceExertedByX(Body b) {
    return this.calcForceExertedBy(b)*(b.xxPos-this.xxPos)/this.calcDistance(b);
  }
  /* Calculates force exerted on a Body by another Body in the Y direction */
  public double calcForceExertedByY(Body b) {
    return this.calcForceExertedBy(b)*(b.yyPos-this.yyPos)/this.calcDistance(b);
  }
  /* Calculates total force exerted on a Body in the X direction */
  public double calcNetForceExertedByX(Body[] allBodys){
    int index=0;
    double total=0;
    while (index<allBodys.length) {
      if (!allBodys[index].equals(this)) {
        total=total+this.calcForceExertedByX(allBodys[index]);
      }
      index=index+1;
    }
    return total;
  }
  /* Calculates total force exerted on a Body in the Y direction */
  public double calcNetForceExertedByY(Body[] allBodys){
    int index=0;
    double total=0;
    while (index<allBodys.length) {
      if (!allBodys[index].equals(this)) {
        total=total+this.calcForceExertedByY(allBodys[index]);
      }
      index=index+1;
    }
    return total;
  }
  /* Updates a Body's stats */
  public void update(double dt, double fX, double fY) {
    double xacceleration=fX/this.mass;
    double yacceleration=fY/this.mass;
    this.xxVel=this.xxVel+xacceleration*dt;
    this.yyVel=this.yyVel+yacceleration*dt;
    this.xxPos=this.xxPos+this.xxVel*dt;
    this.yyPos=this.yyPos+this.yyVel*dt;
  }
  /* Draws a Body */
  public void draw() {
    StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);
  }
}

public class Point2D extends Point {
    public Point2D() throws Exception {
        super(2);
    }

    public Point2D(double[] x) throws Exception {
        super(2, x);
    }

    public static Point2D rot(Point2D p, double phi) throws Exception {
        double Cos = Math.cos(phi);
        double Sin = Math.sin(phi);
        double[] x = new double[2];
        x[0] = p.getX(0) * Cos - p.getX(1) * Sin;
        x[1] = p.getX(0) * Sin + p.getX(1) * Cos;
        return new Point2D(x);
    }
    public Point2D rot(double phi) throws Exception {
        return rot(this, phi);
    }
}

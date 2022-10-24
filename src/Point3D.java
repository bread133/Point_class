public class Point3D extends Point {
    public Point3D() {
        super(3);
    }

    public Point3D(double[] x) throws Exception {
        super(3, x);
    }

    public static Point3D cross_prod(Point3D p1, Point3D p2) throws Exception {
        if(p1.dim == p2.dim && p1.x == p2.x) {
            return new Point3D();
        }
        double[] x = new double[3];
        x[0] = p1.getX(1) * p2.getX(2) - p1.getX(2) * p2.getX(1);
        x[1] = p1.getX(2) * p2.getX(0) - p1.getX(0) * p2.getX(2);
        x[2] = p1.getX(0) * p2.getX(1) - p1.getX(1) * p2.getX(0);

        return new Point3D(x);
    }

    public Point3D cross_prod(Point3D p1) throws Exception {
        return cross_prod(this, p1);
    }

    public static double mix_prod(Point3D p1, Point3D p2, Point3D p3) throws Exception {
        return p1.getX(0) * p2.getX(1) * p3.getX(2) - p1.getX(1) * p2.getX(0) * p3.getX(2) +
                p1.getX(1) * p2.getX(2) * p3.getX(0) - p1.getX(0) * p2.getX(2) * p3.getX(1) +
                p1.getX(2) * p2.getX(0) * p3.getX(1) - p1.getX(2) * p2.getX(1) * p3.getX(0);
    }

    public double mix_prod(Point3D p1, Point3D p2) throws Exception {
        return mix_prod(this, p1, p2);
    }
}

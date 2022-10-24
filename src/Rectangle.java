public class Rectangle extends QGon {
    public Rectangle(Point2D[] p) throws Exception {
        if(p.length != 4) {
            throw new Exception("Введено неправильное количество точек.");
        }
        Point t1 = Point.sub(p[2], p[1]);
        Point t2 = Point.sub(p[3], p[0]);
        Point t3 = Point.sub(p[3], p[2]);
        Point t4 = Point.sub(p[0], p[1]);
        double phi1 = Point.mult(t1, t2) / Math.sqrt(t1.notabs() * t2.notabs());
        double phi2 = Point.mult(t3, t4) / Math.sqrt(t3.notabs() * t4.notabs());
        if(phi1 != 1 || phi2 != 1) {
            throw new Exception("Введенные координаты не образуют прямоугольника.");
        }
        this.n = 4;
        this.p = p;
    }

    @Override
    public double square() throws Exception {
        return super.square();
    }
}

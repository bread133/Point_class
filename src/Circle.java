
class Circle implements IShape, IMoveable {
    protected Point2D p;
    protected double r;

    public Circle(Point2D p, double r) {
        this.p = p;
        this.r = r;
    }

    public Point2D getP() {
        return this.p;
    }

    public void setP(Point2D p) {
        this.p = p;
    }

    public double getR() {
        return this.r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public double square() {
        return this.r * this.r * Math.PI;
    }

    public double length() {
        return 2 * this.r * Math.PI;
    }

    public boolean cross(IShape i) throws Exception {
        if(!(i instanceof Circle)) {
            throw new Exception("Фигуры не относятся к одному классу.");
        }

        double d = Point.sub(i.getSegment(0).getStart(),
                this.getSegment(0).getStart()).abs();
        if(d == 0 && this.r == i.getSegment(0).getFinish().getX(0)) {
            return true;
        }
        if(d > this.r + i.getSegment(0).getFinish().getX(0)) {
            return false;
        }
        if(d < Math.abs(this.r - i.getSegment(0).getFinish().getX(0))) {
            return false;
        }
        return true;
    }

    public Circle shift(Point2D a) throws Exception {
        Point2D f = new Point2D(new double[] {this.getP().getX(0) + a.getX(0), this.getP().getX(1) + a.getX(1)});
        return new Circle(f, this.r);
    }

    public Circle rot(double phi) throws Exception {
        Point2D f = Point2D.rot(this.p, phi);
        return new Circle(f, this.r);
    }

    public IMoveable symAxis(int i) throws Exception {
        if(i >= 2 || i < 0) {
            throw new Exception("Индекс не соответствует размерности.");
        }
        Point2D res = this.getP();
        res.symAxis(i);
        return new Circle(res, this.r);
    }

    public Segment getSegment(int i) throws Exception {
        return new Segment(this.getP(), new Point2D(new double[] {r, 0}));
    }

    public int getShapeN() {
        return 0;
    }

    public String toString() {
        return "Окружность: \nЦентр: " + this.p + "\nРадиус: " + this.r;
    }
}
class Segment extends OpenFigure{
    protected Point2D start;
    protected Point2D finish;

    public Segment(Point2D s, Point2D f) {
        this.start = s;
        this.finish = f;
    }

    public Point2D getStart() {
        return this.start;
    }

    public void setStart(Point2D a) {
        this.start = a;
    }

    public Point2D getFinish() {
        return this.finish;
    }

    public void setFinish(Point2D a) {
        this.finish = a;
    }

    public double length() throws Exception {
        Point res = Point2D.sub(finish, start);
        return res.abs();
    }

    public Segment shift(Point2D a) throws Exception {
        return new Segment(new Point2D(new double[] { this.getStart().getX(0)
                + a.getX(0),
                this.getStart().getX(1) + a.getX(1)} ),
                new Point2D(new double[] {this.getFinish().getX(0)
                        + a.getX(0),
                this.getFinish().getX(1) + a.getX(1)} ));
    }

    public Segment rot(double phi) throws Exception {
        Segment res = this;
        res.setStart(Point2D.rot(res.getStart(), phi));
        res.setFinish(Point2D.rot(res.getFinish(), phi));
        return res;
    }

    public Segment symAxis(int i) throws Exception {
        if (i >= 2 || i < 0) {
            throw new Exception("Указанный индекс выходит " +
                    "за пределы размерности точки.");
        }
        Segment res = this;
        res.getStart().symAxis(i);
        res.getFinish().symAxis(i);
        return res;
    }

    public Segment getSegment(int i) {
        return this;
    }

    private double area(Point2D a, Point2D b, Point2D c) throws Exception {
        double t1 = (b.getX(0) - a.getX(0)) * (c.getX(1) - a.getX(1));
        double t2 = (b.getX(1) - a.getX(1)) * (c.getX(0) - a.getX(0));
        return t1 - t2;
    } //без нее никак

    private boolean inters(double a, double b, double c, double d) {
        if(a > b) {
            double t = a;
            a = b;
            b = t;
        }
        if(c > d) {
            double t = c;
            c = d;
            d = t;
        }
        return Math.max(a, c) <= Math.min(b, d);
    }

    private boolean intersect(Point2D a, Point2D b, Point2D c, Point2D d) throws Exception {
        return inters(a.getX(0), b.getX(0), c.getX(0), d.getX(0)) &&
                inters(a.getX(1), b.getX(1), c.getX(1), d.getX(1)) &&
                (area(a, b, c) * area(a, b, d) <= 0) &&
                (area(c, d, a) * area(c, d, b) <= 0);
    }

    public int getShapeN() {
        return 2;
    }

    public boolean cross(IShape i) throws Exception {
        if(!(i instanceof Segment)) {
            throw new Exception("Фигуры не относятся к одному классу.");
        }
        return intersect(this.getStart(), this.getFinish(),
                i.getSegment(0).getStart(), i.getSegment(0).getFinish());
    }

    public String toString() {
        return "Отрезок:\nНачало: " + this.getStart() + ",\nКонец: " +
                this.getFinish();
    }
}

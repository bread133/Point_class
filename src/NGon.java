class NGon implements IMoveable, IShape, IPolyPoint {
    protected int n;
    protected Point2D[] p;

    public NGon(Point2D[] p) throws Exception {
        if(p.length < 3) {
            throw new Exception("В n-угольнике должно быть " +
                    "больше двух вершин");
        }
        this.n = p.length;
        this.p = p;
    }

    NGon() {}

    public int getN() {
        return this.n;
    }

    public int getAN() {
        return this.getN();
    }

    public Point2D[] getP() {
        return this.p;
    }

    public Point2D getP(int i) throws Exception {
        if(i >= this.n || i < 0) {
            throw new Exception("Указанный индекс находится вне диапазона. " +
                    "Выберете индекс из следующего интервала:");
        }
        return this.p[i];
    }

    public void setP(Point2D[] p) throws Exception {
        if(p.length != this.n) {
            throw new Exception("Неправильная длина массива.");
        }
        this.p = p;
    }

    public void setP(Point2D p, int i) throws Exception {
        if(i >= this.n || i < 0) {
            throw new Exception("Указанный индекс находится вне диапазона. " +
                    "Выберете индекс из следующего интервала:");
        }
        this.p[i] = p;
    }

    public double square() throws Exception {
        double res = 0;
        for(int i = 0; i < this.getN(); i++){
            Point2D a = this.getP(i);
            Point2D b = this.getP((i + 1) % this.getN());
            res += a.getX(0) * b.getX(1) - b.getX(0) * a.getX(1);
        }
        return 0.5 * Math.abs(res);
    }

    public double length() throws Exception {
        double res = 0;
        for (int i = 1; i < n; i++) {
            Point t = Point.sub(this.p[i], this.p[i - 1]);
            res += t.abs();
        }
        Point t = Point.sub(this.p[this.getN() - 1], this.p[0]);
        res += t.abs();
        return res;
    }

    public NGon shift(Point2D a) throws Exception {
        NGon res = this;
        for (int i = 0; i < this.n; i++) {
            res.setP(new Point2D(new double[] {res.getP(i).getX(0) + a.getX(0),
                    res.getP(i).getX(1) + a.getX(1)}), i);
        }
        return res;
    }

    public NGon rot(double phi) throws Exception {
        NGon res = this;
        for (int i = 0; i < res.n; i++) {
            res.p[i].rot(phi);
            res.setP(res.getP(i).rot(phi), i);
        }
        return res;
    }

    public NGon symAxis(int i) throws Exception {
        if(i >= 2 || i < 0) {
            throw new Exception("Индекса выходит за" +
                    " диапазон размерности.");
        }
        NGon res = this;
        for (int j = 0; j < n; j++) {
            res.getP(j).symAxis(i);
        }
        return res;
    }

    public int getShapeN() {
        return this.getN();
    }

    public Segment getSegment(int i) throws Exception {
        return new Segment(this.getP(i), this.getP((i + 1) % this.getN()));
    }

    public boolean cross(IShape i) throws Exception {
        if(!(i instanceof NGon)) {
            throw new Exception("Фигуры не относятся к одному классу.");
        }
        for (int j = 0; j < i.getShapeN(); j++) {
            for (int k = 0; k < this.getShapeN(); k++) {
                if(this.getSegment(k).cross(i.getSegment(j))) {
                    return true;
                }
            }
        }
        return false;
    }

    public String toString() {
        StringBuilder res = new StringBuilder("Многоугольник:\nКоличество углов: "
                + this.getN() + "\nКоординаты точек:\n");
        for (int i = 0; i < this.getN(); i++) {
            res.append(this.p[i].toString()).append('\n');
        }
        return res.toString();
    }
}

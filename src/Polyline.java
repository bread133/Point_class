class Polyline extends OpenFigure implements IPolyPoint {
    protected int n;
    protected Point2D[] p;

    public Polyline(Point2D[] p) throws Exception {
        if(p.length < 3) {
            throw new Exception("Создайте отрезок.");
        }
        this.n = p.length;
        this.p = p;
    }

    public int getN() {
        return this.n;
    }

    public Point2D[] getP() {
        return this.p;
    }

    public Point2D getP(int i) throws Exception {
        if(i >= this.n || i < 0) {
            throw new Exception("Вводимый индекс выходит за " +
                    "границу количества точек. Выберете индекс из следующего " +
                    "интервала:");
        }
        return this.p[i];
    }

    public void setP(Point2D[] p) throws Exception {
        if(p.length != this.getN()) {
            throw new Exception("Длина вводимого массива отличается от размерности.");
        }
        this.p = p;
    }

    public void setP(Point2D p, int i) throws Exception {
        if (i >= this.getN() || i < 0) {
            throw new Exception("Индекс выходит за пределы количества точек. " +
                    "Выберете индекс из данного интервала:");
        }
        this.p[i] = p;
    }

    public double length() throws Exception {
        double res = 0;
        for (int i = 1; i < n; i++) {

            Point t = Point.sub(this.p[i], this.p[i - 1]);
            res += t.abs();
        }
        return res;
    }

    public Polyline shift(Point2D a) throws Exception {
        Polyline res = this;
        for (int i = 0; i < this.getN(); i++) {
            res.p[i].add(a);
            res.setP(new Point2D(new double[] {res.getP(i).getX(0) + a.getX(0),
                    res.getP(i).getX(1) + a.getX(1)}), i);
        }
        return res;
    }

    public Polyline rot(double phi) throws Exception {
        Polyline res = this;
        for (int i = 0; i < res.getN(); i++) {
            res.setP(res.getP(i).rot(phi), i);
        }
        return res;
    }

    public Polyline symAxis(int i) throws Exception {
        if(i >= 2 || i < 0) {
            throw new Exception("Индекс выходит за" +
                    " диапазон размерности.");
        }
        Polyline res = this;
        for (int j = 0; j < n; j++) {
            res.getP(j).symAxis(i);
        }
        return res;
    }

    public Segment getSegment(int i) throws Exception {
        return new Segment(this.getP(i), this.getP((i + 1) % this.getN()));
    }

    public int getShapeN() {
        return this.getN();
    }

    public boolean cross(IShape i) throws Exception {
        if (!(i instanceof Polyline)) {
            throw new Exception("Фигуры не относятся к одному классу.");
        }
        for (int j = 0; j < i.getShapeN() - 1; j++) {
            for (int k = 0; k < this.getShapeN() - 1; k++) {
                if (this.getSegment(k).cross(i.getSegment(j))) {
                    return true;
                }
            }
        }
        return false;
    }

    public String toString() {
        StringBuilder res = new StringBuilder("Ломаная линия: Количество углов: "
                + this.getN() + "\nКоординаты точек:\n");
        for (int i = 0; i < this.getN(); i++) {
            res.append(this.p[i].toString()).append(",\n");
        }
        return res.toString();
    }
}

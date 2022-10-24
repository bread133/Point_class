public class Point {
    protected int dim;
    protected double[] x;

    public Point(int dim) {
        this.dim = dim;
        this.x = new double[dim];
        for (int i = 0; i < dim; i++) {
            x[i] = 0;
        }
    }

    public Point(int dim, double[] x) throws Exception {
        if(dim != x.length) {
            throw new Exception("Длина вводимого массива не совпадает с " +
                    "указанной в аргументе размерностью.");
        }
        this.dim = dim;
        this.x = x;
    }

    public int getDim() {
        return this.dim;
    }

    public double[] getX() {
        return this.x;
    }

    public double getX(int i) throws Exception{
        if(i >= this.dim || i < 0) {
            throw new Exception("Индекс больше, чем размерность элемента.");
        }
        return this.x[i];
    }

    public void setX(double[] x) throws Exception{
        if(x.length != this.dim) {
            throw new Exception("Длина массива не соответствует размерности точки.");
        }
        this.x = x;
    }

    public void setX(double x, int i) throws Exception{
        if(i >= this.dim || i < 0) {
            throw new Exception("Индекс находится за пределом диапазона.");
        }
        this.x[i] = x;
    }

    public double notabs() {
        if(this.dim == 1)
            return x[0];
        double res = 0;
        for (int i = 0; i < this.dim; i++) {
            res += (x[i] * x[i]);
        }
        return res;
    }

    public double abs() {
        return Math.sqrt(this.notabs());
    }

    public static Point add(Point a, Point b) throws Exception{
        if(a.dim != b.dim) {
            throw new Exception("Размерность двух точек разная.");
        }
        double[] x = new double[a.dim];
        for (int i = 0; i < a.dim; i++) {
            x[i] = a.x[i] + b.x[i];
        }
        return new Point(a.dim, x);
    }

    public Point add(Point b) throws Exception{
        return Point.add(this, b);
    }

    public static Point sub(Point a, Point b) throws Exception {
        if(a.dim != b.dim) {
            throw new Exception("Размерность двух точек разная.");
        }
        double[] x = new double[a.dim];
        for (int i = 0; i < a.dim; i++) {
            x[i] = b.x[i] - a.x[i];
        }
        return new Point(a.dim, x);
    }

    public Point sub(Point b) throws Exception {
        return Point.sub(this, b);
    }

    public static Point mult(Point a, double r) throws Exception {
        Point res = new Point(a.dim, a.x);
        for (int i = 0; i < a.dim; i++) {
            res.x[i] *= r;
        }
        return res;
    }

    public Point mult(double r) throws Exception {
        return Point.mult(this, r);
    }

    public static double mult(Point a, Point b) throws Exception {
        if(a.dim != b.dim) {
            throw new Exception("Размерность двух точек разная.");
        }
        double res = 0;
        for (int i = 0; i < a.dim; i++) {
            res += a.x[i] * b.x[i];
        }
        return res;
    }

    public double mult(Point b) throws Exception {
        return Point.mult(this, b);
    }

    public static Point symAxis(Point a, int i) throws Exception {
        if(i >= a.dim || i < 0) {
            throw new Exception("Выход индекса за предел диапазона.");
        }
        for (int j = 0; j < a.getDim(); j++) {
            if(j == i)
                continue;
            a.setX(a.getX(j) * (-1), j);
        }
        return a;
    }

    public Point symAxis(int i) throws Exception {
        return Point.symAxis(this, i);
    }

    public String toString() {
        StringBuilder res = new StringBuilder("Точка: Размерность: "
                + this.dim + ", (");
        for (int i = 0; i < this.dim - 1; i++) {
            res.append(this.x[i]).append("; ");
        }
        res.append(this.x[this.dim - 1]).append(")");
        return res.toString();
    }
}

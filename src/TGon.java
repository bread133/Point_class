public class TGon extends NGon {
    public TGon(Point2D[] p) throws Exception {
        if(p.length != 3) {
            throw new Exception("Неверное количество точек.");
        }

        Point l1 = Point.sub(p[1], p[0]);
        Point l2 = Point.sub(p[2], p[1]);
        Point l3 = Point.sub(p[0], p[1]);
        if(l1.abs() + l2.abs() <= l3.abs() ||
                l2.abs() + l3.abs() <= l1.abs() ||
                l3.abs() + l1.abs() <= l2.abs()) {
            throw new Exception("Такого треугольника не " +
                    "существует.");
        }
        this.n = 3;
        this.p = p;
    }

    public double square() throws Exception { //смысл переопределять здесь площадь?
        return super.square();
    }
}

public class QGon extends NGon{
    public QGon(Point2D[] p) throws Exception {
        if(p.length != 4) {
            throw new Exception("Неверное количество точек.");
        }
        this.n = 4;
        this.p = p;
    }

    public double square() throws Exception {
        return super.square();
    }
    public QGon() {}
}

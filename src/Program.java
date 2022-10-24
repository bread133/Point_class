import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Program {
    private static IShape Move(String movement, Scanner in, IShape shape) throws Exception {
        switch (movement) {
            case "Shift" -> {
                System.out.print("Введите координаты сдвига: ");
                double x = in.nextDouble();
                double y = in.nextDouble();
                return (IShape) shape.shift(new Point2D(new double[]{x, y}));
            }
            case "Rotate" -> {
                System.out.print("Введите угол поворота: ");
                double phi = in.nextDouble();
                return (IShape) shape.rot(phi);
            }
            case "Symmetry" -> {
                System.out.print("Введите ось симметрии:");
                int sym = in.nextInt();
                return (IShape) shape.symAxis(sym);
            }
            default -> throw new Exception("Неправильное движение.");
        }
    }
    private static String getShape(IShape i) {
        if(i instanceof Segment)
            return "Segment";
        if(i instanceof Polyline)
            return "Polyline";
        if(i instanceof TGon)
            return "TGon";
        if(i instanceof Rectangle)
            return "Rectangle";
        if(i instanceof Trapeze)
            return "Trapeze";
        if(i instanceof QGon)
            return "QGon";
        if(i instanceof NGon)
            return "NGon";
        if(i instanceof Circle)
            return "Circle";
        return "Error";
    }

    private static IShape createShape(Point2D[] points, String str) throws Exception {
        return switch (str) {
            case "Segment" -> new Segment(new Point2D(new double[]
                    {points[0].getX(0), points[0].getX(1)}),
                    new Point2D(new double[]{
                            points[1].getX(0), points[1].getX(1)}));
            case "Polyline" -> new Polyline(points);
            case "NGon" -> new NGon(points);
            case "TGon" -> new TGon(points);
            case "QGon" -> new QGon(points);
            case "Rectangle" -> new Rectangle(points);
            case "Trapeze" -> new Trapeze(points);
            default -> throw new Exception("Неправильный тип фигуры.");
        };
    }
    private static IShape newShape(String str, Scanner in) throws Exception {
        int n;
        switch (str) {
            case "Segment":
                n = 2;
                break;
            case "Polyline", "NGon":
                System.out.print("Введите количество вершин: ");
                n = in.nextInt();
                break;
            case "Circle":
                return new Circle(new Point2D(new double[]{
                        in.nextDouble(), in.nextDouble()}), in.nextDouble());
            case "TGon":
                n = 3;
                break;
            case "QGon", "Rectangle", "Trapeze":
                n = 4;
                break;
            default:
                throw new Exception("Неверный тип фигуры.");
        }

        Point2D[] points = new Point2D[n];
        for (int i = 0; i < n; i++) {
            System.out.println("Введите координаты точек: ");
            points[i] = new Point2D(new double[]
                    {in.nextDouble(), in.nextDouble()});
        }
        return createShape(points, str);
    }

    public static void main(String[] args) throws Exception {
        ArrayList<IShape> shapes = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        System.out.print("Введите количество фигур: ");
        int n = in.nextInt();
        if(n < 1) {
            throw new Exception("Количество фигур должно быть положительным.");
        }

        double square = 0;
        double lenght = 0;
        double s = 0;
        for (int i = 0; i < n; i++) {
            System.out.println("""
                    Добавьте фигуру на выбор:\s
                    Segment - Отрезок,\s
                    Polyline - Ломаная,\s
                    TGon - Треугольник,\s
                    Rectangle - Прямоугольник,\s
                    Trapeze - Трапеция,\s
                    QGon - Четырехугольник,\s
                    NGon - Многоугольник,\s
                    Circle - Окружность.""");
            String chsn;
            do {
                chsn = in.nextLine();
            } while(Objects.equals(chsn, ""));
            shapes.add(newShape(chsn, in));
            System.out.println(shapes.get(i));
            square += shapes.get(shapes.size() - 1).square();
            lenght += shapes.get(shapes.size() - 1).length();
            s += shapes.get(shapes.size() - 1).square();
        }
        s /= shapes.size();
        System.out.println("Площадь: " + square);
        System.out.println("Длина: " + lenght);
        System.out.println("Средняя площадь: " + s);


        for (IShape iShape : shapes) {
            String typeShape = getShape(iShape);
            IShape shape = newShape(typeShape, in);
            System.out.println(shape);
            System.out.println("Пересекаются ли фигуры? " + iShape.cross(shape));
            String movement;
            do {
                movement = in.nextLine();
            } while(Objects.equals(movement, ""));

            IShape shape1 = Move(movement, in, shape);
            System.out.println(shape1);
            System.out.println("Пересекаются ли фигуры теперь? " + iShape.cross(shape1));
        }
    }
}

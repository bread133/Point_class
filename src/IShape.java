interface IShape extends IMoveable{
    double square() throws Exception;
    double length() throws Exception;
    boolean cross(IShape i) throws Exception;
    Segment getSegment(int i) throws Exception;
    int getShapeN();
}


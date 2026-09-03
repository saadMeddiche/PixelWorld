package org.saadmeddiche.pixelworld;

public class Square {

    public String name = "unknown";
    public int previousX;
    public int previousY;
    public int currentX;
    public int currentY;
    public int color;
    public int length;
    public PixelWorld world;

    private Square() {

    }

    public Square(String name, int length, int color, PixelWorld world) {
        this(name, world.width / 2, world.height / 2, length, color, world);
    }

    public Square(String name, int currentX, int currentY, int length, int color, PixelWorld world) {

        assert 0 <= currentX && currentX < world.width : "x not in world range";
        assert 0 <= currentY && currentY < world.height : "y not in world range";

        this.name = name;

        this.currentX = currentX;
        this.currentY = currentY;

        this.previousX = -1;
        this.previousY = -1;

        this.color = color;
        this.world = world;
        this.length = length;

        world.squares.add(this);

    }

    public void up() {
        moveBy(0, -1);
    }

    public void down() {
        moveBy(0, 1);
    }

    public void right() {
        moveBy(1, 0);
    }

    public void left() {
        moveBy(-1, 0);
    }

    public void moveBy(int dx, int dy) {

        if(currentX + dx < 0) return; // reached left border
        if(currentX + dx + length > world.width) return; // reached right border

        if(currentY + dy < 0) return; // reached up border
        if(currentY + dy + length > world.height) return; // reached down border

        this.previousX = this.currentX;
        this.previousY = this.currentY;
        this.currentX += dx;
        this.currentY += dy;

    }

    public boolean moved() {
        return previousX != currentX || previousY != currentY;
    }

    public boolean newBorn() {
        return previousX == -1 && previousY == -1;
    }

}
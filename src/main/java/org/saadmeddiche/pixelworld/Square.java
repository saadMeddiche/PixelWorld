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
        this.previousY = this.currentY;
        this.previousX = this.currentX;
        this.currentY -= 1;
    }

    public void down() {
        this.previousY = this.currentY;
        this.previousX = this.currentX;
        this.currentY +=1;
    }

    public void right() {
        this.previousY = this.currentY;
        this.previousX = this.currentX;
        this.currentX +=1;
    }

    public void left() {
        this.previousY = this.currentY;
        this.previousX = this.currentX;
        this.currentX -=1;
    }

    public boolean moved() {
        return previousX != currentX || previousY != currentY;
    }

    public boolean newBorn() {
        return previousX == -1 && previousY == -1;
    }

}
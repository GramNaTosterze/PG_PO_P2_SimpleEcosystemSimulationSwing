package World;

public interface CanvasInterface {
    public enum WorldType {
        HEX,
        SQR,
    }
    public void gameBoard(GUI window, World world);
}

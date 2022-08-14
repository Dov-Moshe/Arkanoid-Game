package other;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

public class ListOfColors {

    public static List<Color> basicColorsFirst() {
        List<Color> colors = new ArrayList<>();
        colors.add(Color.RED);
        colors.add(Color.ORANGE);
        colors.add(Color.YELLOW);
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);
        colors.add(Color.PINK);
        colors.add(Color.CYAN);
        return colors;
    }

    public static List<Color> basicColorsSecond() {
        List<Color> colors = new ArrayList<>();
        colors.add(Color.GRAY);
        colors.add(Color.RED);
        colors.add(Color.YELLOW);
        colors.add(Color.BLUE);
        colors.add(Color.WHITE);
        return colors;
    }

    public static List<Color> basicColorsThird() {
        List<Color> colors = new ArrayList<>();
        colors.add(Color.GRAY);
        colors.add(Color.RED);
        colors.add(Color.YELLOW);
        colors.add(Color.GREEN);
        colors.add(Color.WHITE);
        colors.add(Color.PINK);
        colors.add(Color.CYAN);
        return colors;
    }
}

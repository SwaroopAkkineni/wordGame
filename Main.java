import wordFinder.SimpleWordFinder;
import wordFinder.ThreadedWordfinder;

public class Main {
    public static void main(String[] args) {
        // var wordFinder = new SimpleWordFinder();
        // wordFinder.startProgram();

        var wordFinder = new ThreadedWordfinder();
        wordFinder.startProgram();
    }
}

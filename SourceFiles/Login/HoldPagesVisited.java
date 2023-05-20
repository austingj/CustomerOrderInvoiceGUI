package Login;

public class HoldPagesVisited
{
    private static int numberOfPagesVisited = 0;

    public static void incrementPagesVisited()
    {
        numberOfPagesVisited++;
    }

    public static int getNumberOfPagesVisited()
    {
        return numberOfPagesVisited;
    }

    public static void resetNumberOfPagesVisited()
    {
        numberOfPagesVisited = 0;
    }
}

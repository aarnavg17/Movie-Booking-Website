public class CurrentDay extends Day {
    private static CurrentDay instance;

    public static CurrentDay getInstance() {
        return instance;
    }

    private CurrentDay(String newday) throws ExInsufficientArguments {
         super(newday);
    }

    public static CurrentDay createInstance(String newday) throws ExInstanceOfSingleton, ExInsufficientArguments {
        if (instance == null)
            instance = new CurrentDay(newday);
        else
            throw new ExInstanceOfSingleton();
        return instance;
    }

    public void updateCurrentDay(String newDay) throws ExInsufficientArguments {
        super.setDay(newDay);
    }
}

public class Day implements Cloneable {
    private int day;
    private int month;
    private int year;
    private static final String allMonths="JanFebMarAprMayJunJulAugSepOctNovDec";

    public Day(String newday) throws ExInsufficientArguments {
        formatString(newday);
    }

    public String toString() {
        return this.day + "-" + allMonths.substring((month - 1) * 3, month * 3) + "-" + year;
    }

    public void setDay(String day) throws ExInsufficientArguments {
        formatString(day);
    }

    public void formatString(String newday) throws ExInsufficientArguments {
        String[] dateParts = newday.split("-");
        if (dateParts.length != 3)
            throw new ExInsufficientArguments();
        this.day = Integer.parseInt(dateParts[0]);
        this.month = allMonths.indexOf(dateParts[1])/3+1;
        this.year = Integer.parseInt(dateParts[2]);
    }

    @Override
    public Day clone() {
        Day copy = null;
        try {
            copy = (Day) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return copy;
    }
}

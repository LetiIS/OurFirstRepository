package pack;

/**
 *
 * @author abelash
 */
public class EducationYear {

    private int firstYear;
//    private int secondYear;

    public EducationYear() {
    }

    public EducationYear(int firstYear) {
        this.firstYear = firstYear;
//        this.secondYear = secondYear;
    }

    public int getFirstYear() {
        return firstYear;
    }

    public void setFirstYear(int firstYear) {
        this.firstYear = firstYear;
    }

    public int getSecondYear() {
        return firstYear + 1;
    }

    @Override
    public String toString() {
        int secondYear = firstYear + 1;
        return firstYear + "/" + secondYear;
    }
    
    public static EducationYear getEducationYear(String str)
    {
        String[] f = str.split("\\D");
        return new EducationYear(Integer.parseInt(f[0]));
    }
    
    public static EducationYear[] getEducationYears(int firstYear, int yearCount)
    {
        EducationYear[] ey = new EducationYear[yearCount];
        for (int i = 0; i < ey.length; i++) {
            ey[i] = new EducationYear(firstYear + i);
        }
        return ey;
    }
    
    public static String[] getStringArray(EducationYear[] ey)
    {
        String[] s = new String[ey.length];
        for (int i = 0; i < s.length; i++) {
            s[i] = ey[i].toString();
        }
        return s;
    }

}

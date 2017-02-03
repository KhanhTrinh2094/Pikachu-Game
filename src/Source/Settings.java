
package Source;

public class Settings {
    
    public static int Dong = 12;
    public static int Cot = 14;
    public static int Cao = 65;
    public static int Dai = 53;
    public static int ThoiGian = 100;
    public static int pnWidth = 1200;
    public static int pnHeight = 700;
    public static int randomCount = 10;
    public static int helpCount = 10;
    public static int HighScore = 0;
    public static boolean Music = true;
    public static int Total(){
        return Dong * Cot;
    }
    public static int Sub(){
        return (Cot * 2) + (Dong * 2) - 4;
    }
}

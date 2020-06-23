package anntool.enums;

public enum Nationality
{
    TURKEY("Türkiye"),
    OTHER("Diğer");

    private String str;

    private Nationality(String str)
    {
        this.str = str;
    }

    public String toString()
    {
        return str;
    }
}

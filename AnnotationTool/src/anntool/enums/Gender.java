package anntool.enums;

public enum Gender
{
    MALE("Erkek"),
    FEMALE("Kadın");

    private String str;

    private Gender(String str)
    {
        this.str = str;
    }

    public String toString()
    {
        return str;
    }
}

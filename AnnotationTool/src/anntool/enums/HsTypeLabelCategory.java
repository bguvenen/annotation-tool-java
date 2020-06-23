package anntool.enums;

public enum HsTypeLabelCategory
{
    RACIAL("Irkçılık"),
    XENOPHOBIC("Yabancı Düşmanlığı"),
    ANTI_IMMIGRANT("Göçmen Karşıtlığı"),
    SEXIST("Cinsiyetçilik"),
    POLITICAL("Politik Nefret"),
    RELIGIOUS("Dini Nefret"),
    OTHER("Diğer");

    private String str;

    private HsTypeLabelCategory(String str)
    {
        this.str = str;
    }

    public String toString()
    {
        return str;
    }
}

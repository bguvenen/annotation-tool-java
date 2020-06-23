package anntool.enums;

public enum ContentLabelCategory
{
    MORAL("Ahlaki hakaret"),
    INTELLECTUAL("Entellektüel beceriye hakaret"),
    APPEARANCE("Dış görünüşe hakaret"),
    VIOLENCE("Şiddet Yanlılığı"),
    PROFANITY("Küfür"),
    CURSE("Lanet / Beddua"),
    SYMBOLIZATION("Simgeleştirme"),
    OTHER("Diğer");

    private String str;

    private ContentLabelCategory(String str)
    {
        this.str = str;
    }

    public String toString()
    {
        return str;
    }
}

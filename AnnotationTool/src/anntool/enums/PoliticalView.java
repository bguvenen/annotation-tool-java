package anntool.enums;

public enum PoliticalView
{
    LIBERAL("Liberal"),
    NATIONALIST("Milliyetçi"),
    DEMOCRAT("Demokrat"),
    CONSERVATIVE("Muhafazakar"),
    SOCIALIST("Sosyalist"),
    RIGHTIST("Sağ Görüşlü"),
    LEFTIST("Sol Görüşlü");

    private String str;

    private PoliticalView(String str)
    {
        this.str = str;
    }

    public String toString()
    {
        return str;
    }
}

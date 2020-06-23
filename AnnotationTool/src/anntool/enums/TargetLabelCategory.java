package anntool.enums;

public enum TargetLabelCategory
{	SPECIFIC_PERSON("Belirli bir kişi"),
    WOMEN("Kadınlar"),
    MEN("Erkekler"),
    SYRIAN("Suriyeliler"),
    EUROPEAN("Avrupalılar"),
    ARABIAN("Araplar"),
    KURDISH("Kürtler"),
    AK_PARTI_SUPPORTER("Ak Parti'liler"),
    CHP_SUPPORTER("CHP'liler"),
    HDP_SUPPORTER("HDP'liler"),
    RELIGIOUS("Dindarlar"),
    SECULAR("Laikler"),
    NATIONALIST("Milliyetçiler"),
    OTHER("Diğer");

    private String str;

    private TargetLabelCategory(String str)
    {
        this.str = str;
    }

    public String toString()
    {
        return str;
    }
}

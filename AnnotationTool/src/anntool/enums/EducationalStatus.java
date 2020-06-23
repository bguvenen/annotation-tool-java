package anntool.enums;

public enum EducationalStatus
{
    ELEMENTARY_SCHOOL("İlkokul"),
    MIDDLE_SCHOOL("Ortaokul"),
    HIGH_SCHOOL("Lise"),
    ASSOCIATE_DEGREE("Önlisans"),
    UNIVERSITY("Üniversite"),
    MASTER("Yüksel Lisans"),
    DOCTORATE("Doktora");

    private String str;

    private EducationalStatus(String str)
    {
        this.str = str;
    }

    public String toString()
    {
        return str;
    }
}

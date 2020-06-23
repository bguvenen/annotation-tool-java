package anntool.window.composites.admin.labelviewer;

public enum LabelColumnData
{
    F("", 0, 0),
    PARTICIPANT_ID("Katılımcı No", 1, 150),
    HS_TYPE_CATEGORY("HS Türü Etiketi", 2, 250),
    TARGET_CATEGORY("Hedef Etiketi", 3, 250),
    CONTENT_CATEGORY("İçerik Etiketi", 4, 250),
    QUOTE("Alıntı", 5, 250);

    private String colName;
    private int colNo;
    private int colWidth;

    private LabelColumnData(String colName, int colNo, int colWidth)
    {
        this.colName = colName;
        this.colNo = colNo;
        this.colWidth = colWidth;
    }

    public String getColName()
    {
        return colName;
    }

    public int getColNo()
    {
        return colNo;
    }

    public int getColWidth()
    {
        return colWidth;
    }
}

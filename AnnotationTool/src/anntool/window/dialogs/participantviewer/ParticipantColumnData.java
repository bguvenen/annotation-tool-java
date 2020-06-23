package anntool.window.dialogs.participantviewer;

public enum ParticipantColumnData
{
    F("", 0, 0),
    PARTICIPANT_ID("Katılımcı No", 1, 130),
    GENDER("Cinsiyet", 2, 130),
    AGE("Yaş", 3, 70),
    NATIONALITY("Uyruk", 4, 150),
    EDUCATION("Eğitim Durumu", 5, 150),
    POLITICS("Politik Görüş", 6, 350);

    private String colName;
    private int colNo;
    private int colWidth;

    private ParticipantColumnData(String colName, int colNo, int colWidth)
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

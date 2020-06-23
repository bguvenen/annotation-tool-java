package anntool.window.composites.admin.tweetviewer;

public enum TweetColumnData
{
    F("", 0, 0),
    TWEET_ID("Tweet No", 1, 60),
    TWEET_TEXT("Tweet", 2, 600),
    ANS_COUNT("Etiketleyen Sayısı", 3, 50),
    HS_TYPE_CATEGORY("HS Türü Etiketi", 4, 150),
    TARGET_CATEGORY("Hedef Etiketi", 5, 150),
    CONTENT_CATEGORY("İçerik Etiketi", 6, 150);

    private String colName;
    private int colNo;
    private int colWidth;

    private TweetColumnData(String colName, int colNo, int colWidth)
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

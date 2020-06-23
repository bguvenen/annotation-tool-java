package anntool.window.composites.admin.tweetviewer;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Display;

import anntool.AnnToolColor;
import anntool.datamodels.Tweet;

public class TweetViewerLabelProvider extends ColumnLabelProvider
{
    private TweetColumnData columnData;

    public TweetViewerLabelProvider(TweetColumnData columnData)
    {
        super();
        this.columnData = columnData;
    }

    @Override
    public String getText(Object element)
    {
        Tweet tweet = (Tweet) element;
        String text = "";

        switch (columnData)
        {
            case ANS_COUNT:
                text = tweet.getAnsCount() + "";
                break;
            case TWEET_ID:
                text = tweet.getTweetId() + "";
                break;
            case TWEET_TEXT:
                text = tweet.getTweetText();
                break;
            case CONTENT_CATEGORY:
                text = tweet.getContentsString();
                break;
            case TARGET_CATEGORY:
                text = tweet.getTargetsString();
                break;
            case HS_TYPE_CATEGORY:
                text = tweet.getHsTypesString();
                break;
        }

        return text;
    }

    @Override
    public Color getBackground(Object element)
    {
        Tweet tweet = (Tweet) element;

        if (tweet.getAnsCount() == 0)
            return AnnToolColor.GREY;
        if (tweet.areLabelListsEmpty())
            return AnnToolColor.SOFT_GREEN2;

        return AnnToolColor.SOFT_RED;
    }
    
}

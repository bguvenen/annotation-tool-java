package anntool.window.composites.admin.labelviewer;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.swt.graphics.Color;

import anntool.AnnToolColor;
import anntool.datamodels.TweetLabel;

public class LabelViewerLabelProvider extends ColumnLabelProvider
{
    private LabelColumnData columnData;

    public LabelViewerLabelProvider(LabelColumnData columnData)
    {
        super();
        this.columnData = columnData;
    }

    @Override
    public String getText(Object element)
    {
        TweetLabel tweetLabel = (TweetLabel) element;
        String text = "";

        switch (columnData)
        {
            case PARTICIPANT_ID:
                text = tweetLabel.getParticipantId() + "";
                break;
            case CONTENT_CATEGORY:
                text = tweetLabel.getContentsString();
                break;
            case TARGET_CATEGORY:
                text = tweetLabel.getTargetsString();
                break;
            case QUOTE:
                text = tweetLabel.getQuote();
                break;
            case HS_TYPE_CATEGORY:
                text = tweetLabel.getHsTypesString();
                break;
        }

        return text;
    }

    @Override
    public Color getBackground(Object element)
    {
        TweetLabel tweetLabel = (TweetLabel) element;

        if (!tweetLabel.isHateSpeech())
            return AnnToolColor.SOFT_GREEN2;

        return AnnToolColor.SOFT_RED;
    }
}

package anntool.datamodels;

import java.util.List;

public class Record
{
    private Participant participant;
    private List<TweetLabel> labelList;

    public Record(Participant participant, List<TweetLabel> labelList)
    {
        this.participant = participant;
        this.labelList = labelList;
    }

    public Participant getParticipant()
    {
        return participant;
    }

    public List<TweetLabel> getLabelList()
    {
        return labelList;
    }
}

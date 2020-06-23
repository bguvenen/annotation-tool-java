package anntool.window.dialogs.participantviewer;

import org.eclipse.jface.viewers.ColumnLabelProvider;

import anntool.datamodels.Participant;

public class ParticipantViewerLabelProvider extends ColumnLabelProvider
{
    private ParticipantColumnData columnData;

    public ParticipantViewerLabelProvider(ParticipantColumnData columnData)
    {
        super();
        this.columnData = columnData;
    }

    @Override
    public String getText(Object element)
    {
        Participant participant = (Participant) element;
        String text = "";

        switch (columnData)
        {
            case AGE:
                text = participant.getAge() + "";
                break;
            case NATIONALITY:
                text = participant.getNationality().toString();
                break;
            case PARTICIPANT_ID:
                text = participant.getParticipantId() + "";
                break;
            case EDUCATION:
                text = participant.getEducationalStatus();
                break;
            case GENDER:
                text = participant.getGender();
                break;
            case POLITICS:
                text = participant.getPoliticalViews();
                break;
        }

        return text;
    }
}

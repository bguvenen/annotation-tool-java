package anntool.window.dialogs.participantviewer;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

import anntool.database.Database;

public class ParticipantViewerComposite extends Composite
{
    private TableViewer participantViewer;

    public ParticipantViewerComposite(Composite parent)
    {
        super(parent, SWT.NONE);

        GridLayout gridLayout = new GridLayout();
        gridLayout.horizontalSpacing = 0;
        gridLayout.verticalSpacing = 0;
        gridLayout.marginWidth = 0;
        gridLayout.marginHeight = 0;

        setLayout(gridLayout);

        participantViewer = new TableViewer(this, SWT.NONE);
        Table table = participantViewer.getTable();
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        participantViewer.setContentProvider(ArrayContentProvider.getInstance());

        generateColumns();
        resetInput();
    }

    public void resetInput()
    {
        participantViewer.setInput(Database.getParticipantList());
    }

    private void generateColumns()
    {
        for (ParticipantColumnData colData : ParticipantColumnData.values())
        {
            String colName = colData.getColName();
            int colNo = colData.getColNo();
            int colWidth = colData.getColWidth();

            TableViewerColumn column = new TableViewerColumn(participantViewer, SWT.CENTER, colNo);
            column.getColumn().setWidth(colWidth);
            column.getColumn().setText(colName);
            column.getColumn().setMoveable(false);

            ParticipantViewerLabelProvider labelProvider = new ParticipantViewerLabelProvider(colData);
            column.setLabelProvider(labelProvider);

        }
    }
}
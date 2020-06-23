package anntool.window.composites.admin.labelviewer;

import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

import anntool.datamodels.TweetLabel;

public class LabelViewerComposite extends Composite
{
    private TableViewer labelViewer;

    public LabelViewerComposite(Composite parent)
    {
        super(parent, SWT.NONE);

        setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        GridLayout gridLayout = new GridLayout();
        gridLayout.horizontalSpacing = 0;
        gridLayout.verticalSpacing = 0;
        gridLayout.marginWidth = 0;
        gridLayout.marginHeight = 0;

        setLayout(gridLayout);

        labelViewer = new TableViewer(this, SWT.FULL_SELECTION);
        Table table = labelViewer.getTable();
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        labelViewer.setContentProvider(ArrayContentProvider.getInstance());
        generateColumns();
    }

    public void setInput(List<TweetLabel> labelList)
    {
        labelViewer.setInput(labelList);
    }

    private void generateColumns()
    {
        for (LabelColumnData colData : LabelColumnData.values())
        {
            String colName = colData.getColName();
            int colNo = colData.getColNo();
            int colWidth = colData.getColWidth();

            TableViewerColumn column = new TableViewerColumn(labelViewer, SWT.CENTER, colNo);
            column.getColumn().setWidth(colWidth);
            column.getColumn().setText(colName);
            column.getColumn().setMoveable(false);

            LabelViewerLabelProvider labelProvider = new LabelViewerLabelProvider(colData);
            column.setLabelProvider(labelProvider);

        }
    }
}
package anntool.window.composites.admin.tweetviewer;

import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

import anntool.AnnToolColor;
import anntool.database.Database;
import anntool.datamodels.Tweet;
import anntool.datamodels.TweetLabel;
import anntool.window.composites.admin.labelviewer.LabelViewerComposite;

public class TweetViewerComposite extends Composite
{
    private TableViewer tweetViewer;
    private TweetViewerToolbar toolbar;
    private Tweet selectedTweet;
    private LabelViewerComposite labelViewer;

    public TweetViewerComposite(Composite parent)
    {
        super(parent, SWT.NONE);

        GridData tweetViewerCompGD = new GridData(SWT.FILL, SWT.FILL, true, true);
        tweetViewerCompGD.widthHint = 1000;
        tweetViewerCompGD.heightHint = 400;
        setLayoutData(tweetViewerCompGD);
        // setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        GridLayout gridLayout = new GridLayout();
        gridLayout.horizontalSpacing = 0;
        gridLayout.verticalSpacing = 0;
        gridLayout.marginWidth = 0;
        gridLayout.marginHeight = 0;

        setLayout(gridLayout);
        setBackground(AnnToolColor.SOFT_RED);
        toolbar = new TweetViewerToolbar(this);

        tweetViewer = new TableViewer(this, SWT.FULL_SELECTION);
        Table table = tweetViewer.getTable();
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        // tweetViewer.addDoubleClickListener(new IDoubleClickListener()
        // {
        //
        // @Override
        // public void doubleClick(DoubleClickEvent event)
        // {
        // IStructuredSelection selection = tweetViewer.getStructuredSelection();
        //
        // if (!selection.isEmpty())
        // {
        // Tweet tweet = (Tweet) selection.getFirstElement();
        //
        // }
        // }
        // });

        toolbar.setViewer(tweetViewer);

        tweetViewer.setContentProvider(ArrayContentProvider.getInstance());
        tweetViewer.addSelectionChangedListener(new ISelectionChangedListener()
        {
            @Override
            public void selectionChanged(SelectionChangedEvent event)
            {
                IStructuredSelection selection = tweetViewer.getStructuredSelection();

                if (!selection.isEmpty())
                {
                    selectedTweet = (Tweet) selection.getFirstElement();
                    List<TweetLabel> labelList = Database.getTweetLabelList(selectedTweet.getTweetId());

                    labelViewer.setInput(labelList);
                    parent.layout();
                }
            }
        });
        generateColumns();
        resetInput();
    }

    public void resetInput()
    {
        tweetViewer.setInput(Database.getTweetList());
    }

    public void setLabelViewer(LabelViewerComposite labelViewer)
    {
        this.labelViewer = labelViewer;
    }

    private void generateColumns()
    {
        for (TweetColumnData colData : TweetColumnData.values())
        {
            String colName = colData.getColName();
            int colNo = colData.getColNo();
            int colWidth = colData.getColWidth();

            TableViewerColumn column = new TableViewerColumn(tweetViewer, SWT.CENTER, colNo);
            column.getColumn().setWidth(colWidth);
            column.getColumn().setText(colName);
            column.getColumn().setMoveable(false);

            TweetViewerLabelProvider labelProvider = new TweetViewerLabelProvider(colData);
            column.setLabelProvider(labelProvider);

        }
    }
}

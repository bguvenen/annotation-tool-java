package anntool.window.composites.admin.tweetviewer;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

public class TweetViewerToolbar extends Composite
{
    private TableViewer viewer;

    public TweetViewerToolbar(Composite parent)
    {
        super(parent, SWT.NONE);

        GridLayout gridLayout = new GridLayout();
        gridLayout.marginWidth = 0;
        gridLayout.marginHeight = 0;

        setLayout(gridLayout);
    }

    public void setViewer(TableViewer viewer)
    {
        this.viewer = viewer;
    }
}

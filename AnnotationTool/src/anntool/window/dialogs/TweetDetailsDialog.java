package anntool.window.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import anntool.datamodels.Tweet;

public class TweetDetailsDialog extends Dialog
{
    private Tweet tweet;

    public TweetDetailsDialog(Shell parentShell, Tweet tweet)
    {
        super(parentShell);
        this.tweet = tweet;
    }

    @Override
    protected Control createDialogArea(Composite parent)
    {
        Composite container = (Composite) super.createDialogArea(parent);

        GridLayout gridLayout = new GridLayout(2, true);
        gridLayout.horizontalSpacing = 20;
        gridLayout.verticalSpacing = 15;

        container.setLayout(gridLayout);

        // TODO

        return container;
    }

    @Override
    protected void configureShell(Shell newShell)
    {
        super.configureShell(newShell);
        newShell.setText("Tweet Ayrıntıları");
    }

    @Override
    protected Point getInitialSize()
    {
        getShell().pack();

        return getShell().getSize();
    }

    @Override
    protected void createButtonsForButtonBar(Composite parent)
    {
        createButton(parent, IDialogConstants.OK_ID, "Kapat", true);
    }

    @Override
    protected void buttonPressed(int buttonId)
    {
        close();
    }

    @Override
    protected boolean isResizable()
    {
        return false;
    }

}

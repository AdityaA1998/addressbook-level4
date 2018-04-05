package systemtests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static seedu.progresschecker.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.progresschecker.ui.testutil.GuiTestAssert.assertListMatching;

import org.junit.Test;

import guitests.GuiRobot;
import guitests.guihandles.HelpWindowHandle;
import seedu.progresschecker.logic.commands.DeleteCommand;
import seedu.progresschecker.logic.commands.HelpCommand;
import seedu.progresschecker.logic.commands.SelectCommand;
import seedu.progresschecker.ui.BrowserPanel;
import seedu.progresschecker.ui.StatusBarFooter;

/**
 * A system test class for the help window, which contains interaction with other UI components.
 */
public class HelpCommandSystemTest extends ProgressCheckerSystemTest {
    private static final String ERROR_MESSAGE = "ATTENTION!!!! : On some computers, this test may fail when run on "
            + "non-headless mode as FxRobot#clickOn(Node, MouseButton...) clicks on the wrong location. We suspect "
            + "that this is a bug with TestFX library that we are using. If this test fails, you have to run your "
            + "tests on headless mode. See UsingGradle.adoc on how to do so.";

    private final GuiRobot guiRobot = new GuiRobot();

    @Test
    public void openHelpWindow() {
        //use accelerator
        getCommandBox().click();
        getMainMenu().openHelpWindowUsingAccelerator();
        assertHelpWindowOpen();

        getResultDisplay().click();
        getMainMenu().openHelpWindowUsingAccelerator();
        assertHelpWindowOpen();

        getPersonListPanel().click();
        getMainMenu().openHelpWindowUsingAccelerator();
        assertHelpWindowOpen();

        getBrowserPanel().click();
        getMainMenu().openHelpWindowUsingAccelerator();

        //use command box
        executeCommand(HelpCommand.COMMAND_WORD);
        assertHelpWindowOpen();

        // open help window and give it focus
        executeCommand(HelpCommand.COMMAND_WORD);
        getMainWindowHandle().focus();

        // assert that while the help window is open the UI updates correctly for a command execution
        executeCommand(SelectCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals("", getCommandBox().getInput());
        assertCommandBoxShowsDefaultStyle();
        assertNotEquals(HelpCommand.SHOWING_HELP_MESSAGE, getResultDisplay().getText());
        assertNotEquals(BrowserPanel.DEFAULT_PAGE, getBrowserPanel().getLoadedUrl());
        assertListMatching(getPersonListPanel(), getModel().getFilteredPersonList());

        // assert that the status bar too is updated correctly while the help window is open
        // note: the select command tested above does not update the status bar
        executeCommand(DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertNotEquals(StatusBarFooter.SYNC_STATUS_INITIAL, getStatusBarFooter().getSyncStatus());
    }

    /**
     * Asserts that the help window is open, and closes it after checking.
     */
    private void assertHelpWindowOpen() {
        assertTrue(ERROR_MESSAGE, HelpWindowHandle.isWindowPresent());
        guiRobot.pauseForHuman();

        new HelpWindowHandle(guiRobot.getStage(HelpWindowHandle.HELP_WINDOW_TITLE)).close();
        getMainWindowHandle().focus();
    }
}

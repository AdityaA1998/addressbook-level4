package seedu.progresschecker.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

import seedu.progresschecker.commons.core.index.Index;
import seedu.progresschecker.logic.commands.exceptions.CommandException;

/**
 * Uploads a photo to the profile.
 */
public class UploadCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "upload";
    public static final String COMMAND_ALIAS = "up";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Uploads a photo to the profile. "
            + "Parameter: INDEX(must be a positive integer) PATH...\n"
            + "Example: " + COMMAND_WORD + " 1 C:\\Users\\ProgressChecker";

    public static final String MESSAGE_SUCCESS = "New photo uploaded!";
    public static final String MESSAGE_IMAGE_NOT_FOUND = "The image cannot be found!";

    private final Path toUpload;
    private final Index targetIndex;

    /**
     * Creates an UploadCommand to upload the profile photo with specified {@code Path}
     */
    public UploadCommand(Index index, Path path) {
        requireNonNull(path);
        this.toUpload = path;
        this.targetIndex = index;
    }

    @Override
    public CommandResult executeUndoableCommand() throws CommandException {
        requireNonNull(model);
        try {
            model.uploadPhoto(toUpload);
            return new CommandResult(MESSAGE_SUCCESS);
        } catch (FileNotFoundException e) {
            throw new CommandException(MESSAGE_IMAGE_NOT_FOUND);
        } catch (IOException e) {
            throw new CommandException("Caught IOException!");
        }
    }

}

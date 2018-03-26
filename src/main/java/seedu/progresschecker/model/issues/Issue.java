package seedu.progresschecker.model.issues;

import static seedu.progresschecker.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents an Issue.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Issue {

    private final Title title;
    private final Assignees assignees;
    private final Milestone milestone;
    private final Body body;

    /**
     * Every field must be present and not null.
     */
    public Issue(Title title, Assignees assignees, Milestone milestone, Body body) {
        requireAllNonNull(title, assignees, milestone, body);
        this.title = title;
        this.assignees = assignees;
        this.milestone = milestone;
        this.body = body;
    }

    public Title getTitle() {
        return title;
    }

    public Assignees getAssignees() {
        return assignees;
    }

    public Milestone getMilestone() {
        return milestone;
    }

    public Body getBody() { return body; }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof seedu.progresschecker.model.issues.Issue)) {
            return false;
        }

        seedu.progresschecker.model.issues.Issue otherIssue = (seedu.progresschecker.model.issues.Issue) other;
        return otherIssue.getTitle().equals(this.getTitle())
                && otherIssue.getAssignees().equals(this.getAssignees())
                && otherIssue.getMilestone().equals(this.getMilestone())
                && otherIssue.getBody().equals(this.getBody());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, assignees, milestone, body);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle())
                .append(" Assignees: ")
                .append(getAssignees())
                .append(" Milestone: ")
                .append(getMilestone())
                .append(" Body: ")
                .append(getBody());
        return builder.toString();
    }

}


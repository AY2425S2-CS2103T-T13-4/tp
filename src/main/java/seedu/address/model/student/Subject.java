package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's subject in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidSubject(String)}
 */
public class Subject {
    public static final String MESSAGE_CONSTRAINTS =
            "Subjects should only contain alphanumeric characters and spaces, and it should not be blank";

    /**
     * The first character of the subject must not be a whitespace.
     * Allow special characters like @, -, _, &, *.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} \\-@_&*]*";

    public final String subject;

    /**
     * Constructs a {@code Name}.
     *
     * @param subject A valid subject.
     */
    public Subject(String subject) {
        requireNonNull(subject);
        checkArgument(isValidSubject(subject), MESSAGE_CONSTRAINTS);
        this.subject = subject;
    }

    /**
     * Returns true if a given string is a valid subject.
     */
    public static boolean isValidSubject(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return subject;
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Subject)) {
            return false;
        }

        Subject otherSubject = (Subject) other;
        return subject.equals(otherSubject.subject);
    }
}

package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SUBJECT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.SUBJECT_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SUBJECT_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalStudents.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddStudentCommand;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;
import seedu.address.testutil.StudentBuilder;

public class AddStudentCommandParserTest {
    private AddStudentCommandParser parser = new AddStudentCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Student expectedStudent = new StudentBuilder(BOB).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + SUBJECT_DESC_BOB + ADDRESS_DESC_BOB, new AddStudentCommand(expectedStudent));


        // multiple tags - all accepted
        //        Student expectedStudentMultipleTags =
        //        new StudentBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
        //                .build();
        //        assertParseSuccess(parser,
        //                NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
        //                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
        //                new AddStudentCommand(expectedStudentMultipleTags));
    }

    //    @Test
    //    public void parse_repeatedNonTagValue_failure() {
    //        String validExpectedStudentString = NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
    //                + SUBJECT_DESC_BOB;
    //
    //        // multiple names
    //        assertParseFailure(parser, NAME_DESC_AMY + validExpectedStudentString,
    //                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));
    //
    //        // multiple phones
    //        assertParseFailure(parser, PHONE_DESC_AMY + validExpectedStudentString,
    //                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));
    //
    //        // multiple emails
    //        assertParseFailure(parser, EMAIL_DESC_AMY + validExpectedStudentString,
    //                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));
    //
    //        // multiple addresses
    //        assertParseFailure(parser, ADDRESS_DESC_AMY + validExpectedStudentString,
    //                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));
    //
    //        // multiple fields repeated
    //        assertParseFailure(parser,
    //                validExpectedStudentString + PHONE_DESC_AMY + EMAIL_DESC_AMY
    //                + NAME_DESC_AMY + ADDRESS_DESC_AMY
    //                        + validExpectedStudentString,
    //                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME,
    //                PREFIX_ADDRESS, PREFIX_EMAIL, PREFIX_PHONE));
    //
    //        // invalid assignmentName followed by valid assignmentName
    //
    //        // invalid name
    //        assertParseFailure(parser, INVALID_NAME_DESC + validExpectedStudentString,
    //                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));
    //
    //        // invalid email
    //        assertParseFailure(parser, INVALID_EMAIL_DESC + validExpectedStudentString,
    //                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));
    //
    //        // invalid phone
    //        assertParseFailure(parser, INVALID_PHONE_DESC + validExpectedStudentString,
    //                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));
    //
    //        // invalid address
    //        assertParseFailure(parser, INVALID_ADDRESS_DESC + validExpectedStudentString,
    //                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));
    //
    //        // valid assignmentName followed by invalid assignmentName
    //
    //        // invalid name
    //        assertParseFailure(parser, validExpectedStudentString + INVALID_NAME_DESC,
    //                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));
    //
    //        // invalid email
    //        assertParseFailure(parser, validExpectedStudentString + INVALID_EMAIL_DESC,
    //                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));
    //
    //        // invalid phone
    //        assertParseFailure(parser, validExpectedStudentString + INVALID_PHONE_DESC,
    //                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));
    //
    //        // invalid address
    //        assertParseFailure(parser, validExpectedStudentString + INVALID_ADDRESS_DESC,
    //                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));
    //    }

    //    @Test
    //    public void parse_optionalFieldsMissing_success() {
    //        // zero tags
    //        Student expectedStudent = new StudentBuilder(AMY).build();
    //        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + SUBJECT_DESC_AMY,
    //                new AddStudentCommand(expectedStudent));
    //    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudentCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + SUBJECT_DESC_BOB
                        + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + SUBJECT_DESC_BOB
                        + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + SUBJECT_DESC_BOB
                        + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_SUBJECT_BOB
                        + VALID_ADDRESS_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_SUBJECT_BOB
                        + VALID_ADDRESS_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + SUBJECT_DESC_BOB
                        + ADDRESS_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + SUBJECT_DESC_BOB
                        + ADDRESS_DESC_BOB,
                Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + SUBJECT_DESC_BOB
                        + ADDRESS_DESC_BOB,
                Email.MESSAGE_CONSTRAINTS);

        //        // invalid address
        //        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
        //                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Address.MESSAGE_CONSTRAINTS);
        //
        //        // invalid tag
        //        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
        //                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid assignmentName reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_SUBJECT_DESC
                        + ADDRESS_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + SUBJECT_DESC_BOB + ADDRESS_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudentCommand.MESSAGE_USAGE));
    }
}

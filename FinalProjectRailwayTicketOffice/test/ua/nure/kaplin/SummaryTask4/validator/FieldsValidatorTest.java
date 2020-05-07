package ua.nure.kaplin.SummaryTask4.validator;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FieldsValidatorTest {
	private FieldsValidator validator;

	public FieldsValidatorTest() {
		validator = new FieldsValidator();
	}

	@Test
	public void validateDateTest() {
		assertTrue(validator.validateDate("2020-02-02 15:15:00"));
	}

	@Test
	public void validateFieldEmailTest() {
		assertTrue(validator.validateFieldEmail("cl@ukr.net"));
	}
}

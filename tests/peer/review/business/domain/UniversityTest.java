package peer.review.business.domain;

import org.junit.Assert;
import org.junit.Test;

public class UniversityTest {
	@Test
	public void ShouldBeEqualsToSameInstance() {
		University university = new University("UFRGS");

		Boolean result = university.equals(university);

		Assert.assertTrue(result);
	}

	@Test
	public void ShouldBeEqualsToOtherInstanceWithSameName() {
		University universityA = new University("UFRGS");
		University universityB = new University("UFRGS");

		Boolean result = universityA.equals(universityB);

		Assert.assertTrue(result);
	}

	@Test
	public void ShouldNotBeEqualsToOtherInstanceWithDifferentName() {
		University universityA = new University("UFRGS");
		University universityB = new University("PUCRS");

		Boolean result = universityA.equals(universityB);

		Assert.assertFalse(result);
	}

	@Test
	public void ShouldNotBeEqualsToOtherObjectWithSameAttributeValue() {
		Topic topic = new Topic("PUCRS");
		University university = new University("PUCRS");

		Boolean result = university.equals(topic);

		Assert.assertFalse(result);
	}
}

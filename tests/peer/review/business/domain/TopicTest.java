package peer.review.business.domain;

import org.junit.Assert;
import org.junit.Test;

public class TopicTest {
	@Test
	public void ShouldBeEqualsToSameInstance() {
		Topic topic = new Topic("Redes Neurais");

		Boolean result = topic.equals(topic);

		Assert.assertTrue(result);
	}

	@Test
	public void ShouldBeEqualsToOtherInstanceWithSameName() {
		Topic topicA = new Topic("Redes Neurais");
		Topic topicB = new Topic("Redes Neurais");

		Boolean result = topicA.equals(topicB);

		Assert.assertTrue(result);
	}

	@Test
	public void ShouldNotBeEqualsToOtherInstanceWithDifferentName() {
		Topic topicA = new Topic("Redes Neurais");
		Topic topicB = new Topic("Engenharia de Software");

		Boolean result = topicA.equals(topicB);

		Assert.assertFalse(result);
	}

	@Test
	public void ShouldNotBeEqualsToOtherObjectWithSameAttributeValue() {
		Topic topic = new Topic("PUCRS");
		University university = new University("PUCRS");

		Boolean result = topic.equals(university);

		Assert.assertFalse(result);
	}
}

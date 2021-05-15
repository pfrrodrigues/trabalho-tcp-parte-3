package peer.review.business.domain;

import static org.mockito.Mockito.mock;

import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class ResearcherTest {
	@Test
	public void ShouldCheckThatResearchTopicNotExistsInEmptyInterests() {
		University mockedAffiliation = mock(University.class);

		Topic topic = mock(Topic.class);

		List<Topic> mockedInterests = Collections.emptyList();

		Researcher researcher = new Researcher(1, "Test Researcher", mockedAffiliation, mockedInterests);

		Assert.assertFalse(researcher.isTopicOfInterest(topic));
	}

	@Test
	public void ShouldCheckThatResearchTopicNotExistsInInterests() {
		University mockedAffiliation = mock(University.class);

		Topic topicA = mock(Topic.class);
		Topic topicB = mock(Topic.class);

		List<Topic> mockedInterests = List.of(topicA);

		Researcher researcher = new Researcher(1, "Test Researcher", mockedAffiliation, mockedInterests);

		Assert.assertFalse(researcher.isTopicOfInterest(topicB));
	}

	@Test
	public void ShouldCheckThatResearchTopicExistsInInterests() {
		University mockedAffiliation = mock(University.class);

		Topic topic = mock(Topic.class);

		List<Topic> mockedInterests = List.of(topic);

		Researcher researcher = new Researcher(1, "Test Researcher", mockedAffiliation, mockedInterests);

		Assert.assertTrue(researcher.isTopicOfInterest(topic));
	}
}
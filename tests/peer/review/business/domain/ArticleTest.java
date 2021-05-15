package peer.review.business.domain;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import peer.review.business.exception.IllegalValueException;

public class ArticleTest {
	@Test
	public void ShouldThrowExceptionWhenUpdatingScoreForANonExistentReview() {
		Topic mockedTopic = mock(Topic.class);
		Researcher mockedAuthor = mock(Researcher.class);
		Researcher mockedResearcher = mock(Researcher.class);
		Conference mockedConference = mock(Conference.class);

		Article article = new Article(1, "Test Article", mockedAuthor, mockedTopic, mockedConference);

		Assert.assertThrows(NullPointerException.class, () -> {
			article.updateReview(mockedResearcher, 3);
		});
	}

	@Test
	public void ShouldThrowExceptionWhenGivingInvalidScoreForAReview() {
		Topic mockedTopic = mock(Topic.class);
		Researcher mockedAuthor = mock(Researcher.class);
		Researcher mockedResearcher = mock(Researcher.class);
		Conference mockedConference = mock(Conference.class);

		Article article = new Article(1, "Test Article", mockedAuthor, mockedTopic, mockedConference);

		article.addReviewer(mockedResearcher);

		Assert.assertThrows(IllegalValueException.class, () -> {
			article.updateReview(mockedResearcher, 5);
		});
	}

	@Test
	public void ShouldUpdateReviewCorrectly() {
		Topic mockedTopic = mock(Topic.class);
		Researcher mockedAuthor = mock(Researcher.class);
		Researcher mockedResearcher = mock(Researcher.class);
		Conference mockedConference = mock(Conference.class);

		Article article = new Article(1, "Test Article", mockedAuthor, mockedTopic, mockedConference);

		Integer score = 0;

		article.addReviewer(mockedResearcher);
		article.updateReview(mockedResearcher, score);

		Integer actual = article.getReviewScore(mockedResearcher);
		Integer expected = score;

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void ShouldReturnNullOnRetrievingScoreForInvalidReview() {
		Topic mockedTopic = mock(Topic.class);
		Researcher mockedAuthor = mock(Researcher.class);
		Researcher mockedResearcher = mock(Researcher.class);
		Conference mockedConference = mock(Conference.class);

		Article article = new Article(1, "Test Article", mockedAuthor, mockedTopic, mockedConference);

		Integer actual = article.getReviewScore(mockedResearcher);
		Integer expected = null;

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void ShouldThrowExceptionWhenTestingIfAcceptedWithEmptyReviews() {
		Topic mockedTopic = mock(Topic.class);
		Researcher mockedAuthor = mock(Researcher.class);
		Conference mockedConference = mock(Conference.class);

		Article article = new Article(1, "Test Article", mockedAuthor, mockedTopic, mockedConference);

		NullPointerException exception = Assert.assertThrows(NullPointerException.class, () -> {
			article.accepted();
		});

		Assert.assertEquals("exception.reviews.empty", exception.getMessage());
	}

	@Test
	public void ShouldThrowExceptionWhenTestingIfAcceptedWithNullScoreInReviews() {
		Topic mockedTopic = mock(Topic.class);
		Researcher mockedAuthor = mock(Researcher.class);
		Researcher mockedResearcher = mock(Researcher.class);
		Conference mockedConference = mock(Conference.class);

		Article article = new Article(1, "Test Article", mockedAuthor, mockedTopic, mockedConference);

		article.addReviewer(mockedResearcher);

		NullPointerException exception = Assert.assertThrows(NullPointerException.class, () -> {
			article.accepted();
		});

		Assert.assertEquals("exception.score.notFound", exception.getMessage());
	}

	@Test
	public void ShouldGetValidReviewersFromCandidates() {
		Topic mockedTopic = mock(Topic.class);
		Researcher mockedAuthor = mock(Researcher.class);
		Conference mockedConference = mock(Conference.class);
		Researcher mockedResearcherA = mock(Researcher.class);
		Researcher mockedResearcherB = mock(Researcher.class);
		Researcher mockedResearcherC = mock(Researcher.class);

		Article article = spy(new Article(1, "Test Article", mockedAuthor, mockedTopic, mockedConference));

		when(article.isValidReviewer(mockedResearcherA)).thenReturn(false);
		when(article.isValidReviewer(mockedResearcherB)).thenReturn(true);
		when(article.isValidReviewer(mockedResearcherC)).thenReturn(false);

		List<Researcher> candidates = List.of(mockedResearcherA, mockedResearcherB, mockedResearcherC);

		List<Researcher> expected = List.of(mockedResearcherB);
		List<Researcher> actual = article.getValidReviewers(candidates);

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void ShouldEquallyCompareSameArticle() {
		Topic mockedTopic = mock(Topic.class);
		Researcher mockedAuthor = mock(Researcher.class);
		Conference mockedConference = mock(Conference.class);

		Article articleA = new Article(1, "Test Article", mockedAuthor, mockedTopic, mockedConference);
		Article articleB = new Article(1, "Test Article", mockedAuthor, mockedTopic, mockedConference);

		boolean isEqual = articleA.compareTo(articleB) == 0;

		Assert.assertTrue(isEqual);
	}
}
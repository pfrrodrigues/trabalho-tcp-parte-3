package peer.review.business.domain;

import static org.mockito.Mockito.mock;

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
}
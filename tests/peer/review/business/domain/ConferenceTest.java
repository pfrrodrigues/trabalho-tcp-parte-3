package peer.review.business.domain;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class ConferenceTest {
	@Test
	public void ShouldGetAcceptedArticles() {
		Article mockedArticleA = mock(Article.class);
		Article mockedArticleB = mock(Article.class);
		Article mockedArticleC = mock(Article.class);
		Article mockedArticleD = mock(Article.class);

		List<Researcher> committeeMembers = Collections.emptyList();

		Conference conference = new Conference("SBRC", committeeMembers);

		when(mockedArticleA.accepted()).thenReturn(true);
		when(mockedArticleB.accepted()).thenReturn(false);
		when(mockedArticleC.accepted()).thenReturn(true);
		when(mockedArticleD.accepted()).thenReturn(false);

		conference.addArticle(mockedArticleA);
		conference.addArticle(mockedArticleB);
		conference.addArticle(mockedArticleC);
		conference.addArticle(mockedArticleD);

		List<Article> expected = List.of(mockedArticleA, mockedArticleC);
		List<Article> actual = conference.getAcceptedArticles();

		Assert.assertEquals(expected, actual);

		verify(mockedArticleA, times(1)).accepted();
		verify(mockedArticleB, times(1)).accepted();
		verify(mockedArticleC, times(1)).accepted();
		verify(mockedArticleD, times(1)).accepted();
	}

	@Test
	public void ShouldGetRejectedArticles() {
		Article mockedArticleA = mock(Article.class);
		Article mockedArticleB = mock(Article.class);
		Article mockedArticleC = mock(Article.class);
		Article mockedArticleD = mock(Article.class);

		List<Researcher> committeeMembers = Collections.emptyList();

		Conference conference = new Conference("SBRC", committeeMembers);

		when(mockedArticleA.accepted()).thenReturn(true);
		when(mockedArticleB.accepted()).thenReturn(true);
		when(mockedArticleC.accepted()).thenReturn(true);
		when(mockedArticleD.accepted()).thenReturn(false);

		conference.addArticle(mockedArticleA);
		conference.addArticle(mockedArticleB);
		conference.addArticle(mockedArticleC);
		conference.addArticle(mockedArticleD);

		List<Article> expected = List.of(mockedArticleD);
		List<Article> actual = conference.getRejectedArticles();

		Assert.assertEquals(expected, actual);

		verify(mockedArticleA, times(1)).accepted();
		verify(mockedArticleB, times(1)).accepted();
		verify(mockedArticleC, times(1)).accepted();
		verify(mockedArticleD, times(1)).accepted();
	}

	@Test
	public void ShouldEmptyGetAcceptedArticles() {
		List<Researcher> committeeMembers = Collections.emptyList();

		Conference conference = new Conference("SBRC", committeeMembers);

		List<Article> expected = Collections.emptyList();
		List<Article> actual = conference.getAcceptedArticles();

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void ShouldEmptyGetRejectedArticles() {
		List<Researcher> committeeMembers = Collections.emptyList();

		Conference conference = new Conference("SBRC", committeeMembers);

		List<Article> expected = Collections.emptyList();
		List<Article> actual = conference.getRejectedArticles();

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void ShouldNotAddSameArticleTwice() {
		Article mockedArticleA = mock(Article.class);
		Article mockedArticleB = mock(Article.class);

		List<Researcher> committeeMembers = Collections.emptyList();

		Conference conference = new Conference("SBRC", committeeMembers);

		conference.addArticle(mockedArticleA);
		conference.addArticle(mockedArticleA);
		conference.addArticle(mockedArticleB);

		int expected = 2;
		int actual = conference.getArticles().size();

		Assert.assertEquals(expected, actual);
	}
}
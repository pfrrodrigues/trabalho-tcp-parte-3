package peer.review.business.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Conference {
	private String acronym;
	private Map<Researcher, Integer> committeeReviewAllocation;
	private List<Article> articles;
	
	public Conference(String acronym, List<Researcher> committeeMembers, List<Article> articles) {
		this.acronym = acronym;
		this.committeeReviewAllocation = new HashMap<Researcher, Integer>();
		for (Researcher researcher : committeeMembers) {
			this.committeeReviewAllocation.put(researcher, 0);
		}
		this.articles = articles;
	}

	public List<Article> getArticles() {
		return articles;
	}
	
	public void allocate() {
		Collections.sort(articles);
		for (Article article : articles) {
			List<Researcher> validReviewers = article.validateReviewers(
						new ArrayList<Researcher>(committeeReviewAllocation.keySet())
					);
			Researcher reviewer = getFirstReviewer(validReviewers);
			article.addReviewer(reviewer);
			committeeReviewAllocation.put(reviewer, committeeReviewAllocation.get(reviewer) + 1);
		}
	}
	
	private Researcher getFirstReviewer(List<Researcher> validReviewers) {
		List<Integer> allocationValues = new ArrayList<Integer>(committeeReviewAllocation.values());
		int minValue = Collections.min(allocationValues);
		Researcher firstReviewer = null;
		for (Map.Entry<Researcher, Integer> allocatedReview : committeeReviewAllocation.entrySet()) {
			if (allocatedReview.getValue() == minValue) {
				Researcher candidateReviewer = allocatedReview.getKey();
				if (firstReviewer == null) {
					firstReviewer = candidateReviewer;
				} else if (candidateReviewer.getId() < firstReviewer.getId()) {
					firstReviewer = candidateReviewer;
				}
			}
		}
		return firstReviewer;
	}
	
	public List<Article> getAcceptedArticles() throws NullPointerException {
		List<Article> acceptedArticles = new ArrayList<Article>();
		for (Article article : this.articles) {
			if (article.accepted()) {
				acceptedArticles.add(article);
			}
		}
		return acceptedArticles;
	}
	
	public List<Article> getRejectedArticles() throws NullPointerException {
		List<Article> rejectedArticles = new ArrayList<Article>();
		for (Article article : this.articles) {
			if (!article.accepted()) {
				rejectedArticles.add(article);
			}
		}
		return rejectedArticles;
	}
}

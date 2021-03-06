package peer.review.business.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Conference {
	private String acronym;
	private Map<Researcher, Integer> committeeReviewAllocation;
	private List<Article> articles;
	
	public Conference(String acronym, List<Researcher> committeeMembers) {
		this.acronym = acronym;
		this.committeeReviewAllocation = new HashMap<>();
		this.articles = new ArrayList<>();
		for (Researcher researcher : committeeMembers) {
			this.committeeReviewAllocation.put(researcher, 0);
		}
	}

	public List<Article> getArticles() {
		return articles;
	}
	
	public String getAcronym() {
		return this.acronym;
	}
	
	public void addArticle(Article article) {
		if (!articles.contains(article)) {
			articles.add(article);
		}
	}
	
	public void allocateReview(Article article, Researcher reviewer) throws NullPointerException {
		if (!articles.contains(article)) {
			throw new NullPointerException("exception.article.notFound");			
		} else if (!committeeReviewAllocation.containsKey(reviewer)) {
			throw new NullPointerException("exception.reviewer.notFound");			
		}
		
		article.addReviewer(reviewer);
		committeeReviewAllocation.put(reviewer, committeeReviewAllocation.get(reviewer) + 1);
	}
	
	public void allocate() {
		articles = this.sortArticlesById(this.articles);
		for (Article article : articles) {
			List<Researcher> validReviewers = article.getValidReviewers(
						new ArrayList<Researcher>(committeeReviewAllocation.keySet())
					);
			Researcher reviewer = getFirstReviewer(validReviewers);
			this.allocateReview(article, reviewer);
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

	public boolean isAllocated() {
		List<Integer> valuesAllocated = new ArrayList<>(this.committeeReviewAllocation.values());
		for (Integer value : valuesAllocated) {
			if (value != 0)
				return true;
		}
		return false;
	}
	
	public List<Article> getAcceptedArticles() throws NullPointerException {
		List<Article> acceptedArticles = new ArrayList<Article>();
		for (Article article : this.articles) {
			if (article.accepted()) {
				acceptedArticles.add(article);
			}
		}
		boolean reversedOrder = true;
		return sortArticlesByAverage(acceptedArticles, reversedOrder);
	}
	
	public List<Article> getRejectedArticles() throws NullPointerException {
		List<Article> rejectedArticles = new ArrayList<Article>();
		for (Article article : this.articles) {
			if (!article.accepted()) {
				rejectedArticles.add(article);
			}
		}
		return sortArticlesByAverage(rejectedArticles);
	}

	private List<Article> sortArticlesById(List<Article> articles) {
		Comparator<Article> compareById = (Article a1, Article a2) -> a1.getId().compareTo(a2.getId());
		Collections.sort(articles, compareById);
		return articles;
	}

	private List<Article> sortArticlesByAverage(List<Article> articles, boolean reversedOrder) {
		Comparator<Article> compareByAverage = (Article a1, Article a2) -> a1.averageScore().compareTo(a2.averageScore());
		if (reversedOrder) {
			Collections.sort(articles, compareByAverage.reversed());
		} else {
			Collections.sort(articles, compareByAverage);
		}
		return articles;
	}

	private List<Article> sortArticlesByAverage(List<Article> articles) {
		return sortArticlesByAverage(articles, false);
	}
}

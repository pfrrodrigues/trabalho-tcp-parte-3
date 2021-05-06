package peer.review.business.domain;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import peer.review.business.exception.IllegalValueException;

public class Article {
	private int id;
	private String title;
	private Researcher author;
	private Map<Researcher, Integer> reviews;
	private Conference conference;
	
	public Article(int id, String title, Researcher author, Conference conference) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.conference = conference;
		reviews = new  HashMap<Researcher, Integer>();
	}

	public String getTitle() {
		return title;
	}

	public Map<Researcher, Integer> getReviews() {
		return reviews;
	}
	
	public void addReviewer(Researcher reviewer) {
		this.reviews.put(reviewer, null);
	}
	
	public void updateReview(Researcher reviewer, int score) throws NullPointerException, 
																	IllegalValueException {
		if (!this.reviews.containsKey(reviewer)) {
			throw new NullPointerException("exception.reviewer.notFound");
		} else if (!isValidScore(score)) {
			throw new IllegalValueException("exception.score.invalid");
		}
	}
	
	public List<Researcher> validateReviewers(List<Researcher> reviewerCandidates) {
		List<Researcher> validReviewers = new ArrayList<Researcher>();
		for (Researcher candidate : reviewerCandidates) {
			if (isValidReviewer(candidate)) {
				validReviewers.add(candidate);
			}
		}
		return validReviewers;
	}
	
	public boolean isValidReviewer(Researcher reviewer) {
		if (this.author.equals(reviewer)) {
			return false;
		} else if (this.author.isSameAffiliation(reviewer)) {
			return false;
		} else if (this.reviews.containsKey(reviewer)) {
			return false;
		}
		return true;
	}
	
	private boolean isValidScore(int score) {
		return (score >= -3 && score <= 3); 
	}
}

package peer.review.business.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import peer.review.business.exception.IllegalValueException;

public class Article implements Comparable<Article> {
	private Integer id;
	private String title;
	private Researcher author;
	private Topic relatedTopic;
	private Map<Researcher, Integer> reviews;
	private Conference conference;

	public Article(int id, String title, Researcher author, Topic relatedTopic, Conference conference) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.relatedTopic = relatedTopic;
		this.conference = conference;
		reviews = new HashMap<Researcher, Integer>();
	}

	public Integer getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Map<Researcher, Integer> getReviews() {
		return reviews;
	}

	public Integer getReviewScore(Researcher reviewer) {
		return this.reviews.get(reviewer);
	}

	public Conference getConference() {
		return this.conference;
	}

	public void addReviewer(Researcher reviewer) {
		this.addReviewer(reviewer, null);
	}

	private void addReviewer(Researcher reviewer, Integer score) {
		this.reviews.put(reviewer, score);
	}

	public void updateReview(Researcher reviewer, int score) throws NullPointerException, IllegalValueException {
		if (!this.getReviews().containsKey(reviewer)) {
			throw new NullPointerException("exception.reviewer.notFound");
		} else if (!this.isValidScore(score)) {
			throw new IllegalValueException("exception.score.invalid");
		}

		this.addReviewer(reviewer, score);
	}

	public List<Researcher> getValidReviewers(List<Researcher> reviewerCandidates) {
		return reviewerCandidates.stream().filter(candidate -> {
			return this.isValidReviewer(candidate);
		}).collect(Collectors.toList());
	}

	public boolean isValidReviewer(Researcher reviewer) {
		return !this.author.equals(reviewer) && !this.author.isSameAffiliation(reviewer)
				&& !this.reviews.containsKey(reviewer) && reviewer.isTopicOfInterest(this.relatedTopic);
	}

	private boolean isValidScore(int score) {
		return (score >= -3 && score <= 3);
	}

	public boolean accepted() throws NullPointerException {
		if (this.getReviews().isEmpty()) {
			throw new NullPointerException("exception.reviews.empty");
		}

		Collection<Integer> scores = this.getReviews().values();

		int sum = 0;
		float average;

		for (Integer score : scores) {
			if (score == null) {
				throw new NullPointerException("exception.score.notFound");
			}

			sum += score;
		}

		average = sum / scores.size();

		return average >= 0;
	}

	@Override
	public int compareTo(Article article) {
		return this.getId().compareTo(article.getId());
	}
}

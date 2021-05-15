package peer.review.business;

import java.util.Map;

import peer.review.business.domain.Article;
import peer.review.business.domain.Conference;

public interface PeerReviewService {

	public Map<String, Conference> getAllConferences();
	
	public Conference getConference(String acronym);
	
	public Map<Integer, Article> getAllArticles();
}

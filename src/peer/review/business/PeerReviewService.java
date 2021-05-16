package peer.review.business;

import java.util.List;
import java.util.Map;

import peer.review.business.domain.Article;
import peer.review.business.domain.Conference;
import peer.review.business.domain.Researcher;

public interface PeerReviewService {

	public Map<String, Conference> getAllConferences();
	
	public Map<Integer, Article> getAllArticles();

	public List<Conference> getUnallocatedConferences();
	
	public Researcher getResearcherById(int key);
}

package peer.review.business.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import peer.review.business.PeerReviewService;
import peer.review.business.domain.Article;
import peer.review.business.domain.Conference;
import peer.review.business.domain.Researcher;
import peer.review.data.Database;

public class PeerReviewServiceImpl implements PeerReviewService {

	private final Database database;
	
	public PeerReviewServiceImpl(Database database) {
		this.database = database;
	}
	
	public Map<String, Conference> getAllConferences() {
		return this.database.getAllConferences();
	}
	
	public Map<Integer, Article> getAllArticles() {
		return this.database.getAllArticles();
	}
	public List<Conference> getUnallocatedConferences() {
		Map<String, Conference> allConferences = this.database.getAllConferences();
		
		return allConferences.values().stream().filter(conference -> {
		 	return !conference.isAllocated();
		}).collect(Collectors.toList());
	}
	
	public Researcher getResearcherById(int key) {
    	return this.database.getResearcher(key);
    }
	
}

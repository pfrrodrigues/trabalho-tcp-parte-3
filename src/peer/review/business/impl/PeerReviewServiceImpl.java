package peer.review.business.impl;

import java.util.List;

import peer.review.business.PeerReviewService;
import peer.review.business.domain.Conference;
import peer.review.data.Database;

public class PeerReviewServiceImpl implements PeerReviewService {

	private final Database database;
	
	public PeerReviewServiceImpl(Database database) {
		this.database = database;
	}
	
	public List<Conference> getAllConferences() {
		return this.database.getAllConferences();
	}
	
}

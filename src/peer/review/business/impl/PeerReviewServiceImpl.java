package peer.review.business.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import peer.review.business.PeerReviewService;
import peer.review.business.domain.Conference;
import peer.review.data.Database;

public class PeerReviewServiceImpl implements PeerReviewService {

	private final Database database;
	
	public PeerReviewServiceImpl(Database database) {
		this.database = database;
	}
	
	public Map<String, Conference> getAllConferences() {
		return this.database.getAllConferences();
	}
	
	public Conference getConference(String acronym) {
		return this.database.getConference(acronym);
	}
	
	public List<Conference> getUnallocatedConferences() {
		Map<String, Conference> allConferences = this.database.getAllConferences();
		
		return allConferences.values().stream().filter(conference -> {
		 	return !conference.isAllocated();
		}).collect(Collectors.toList());
	}
	
}

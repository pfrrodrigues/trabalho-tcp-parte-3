package peer.review.business;

import java.util.List;
import peer.review.business.domain.Conference;

public interface PeerReviewService {

	public List<Conference> getAllConferences();
}

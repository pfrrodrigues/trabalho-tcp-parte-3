package peer.review;

import peer.review.business.PeerReviewService;
import peer.review.business.impl.PeerReviewServiceImpl;
import peer.review.data.Database;
import peer.review.ui.PeerReviewUI;
import peer.review.ui.command.ReportCommand;

public abstract class PeerReview {

	public static void main(String[] args) throws Exception {
		
		PeerReview peerReviewUI = new PeerReviewUI();
		peerReviewUI.showInterface();
	}
	
	public abstract void showInterface() throws Exception;
}

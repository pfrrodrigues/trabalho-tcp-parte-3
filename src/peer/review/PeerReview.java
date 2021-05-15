package peer.review;

import peer.review.ui.PeerReviewUI;

public abstract class PeerReview {

	public static void main(String[] args) throws Exception {
		
		PeerReview peerReviewUI = new PeerReviewUI();
		peerReviewUI.showInterface();
	}
	
	public abstract void showInterface() throws Exception;
}

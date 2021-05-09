package peer.review.ui.command;

import peer.review.data.Database;
import peer.review.ui.TextManager;
import peer.review.ui.UIUtils;

public abstract class Command {
	
	protected Command(Database database) {
		// TODO
	}
	
	public abstract void execute() throws Exception;
	
	protected TextManager getTextManager() {
		return UIUtils.INSTANCE.getTextManager();
	}

}

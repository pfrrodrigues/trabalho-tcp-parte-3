package peer.review.ui.command;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Matchers;

import peer.review.business.PeerReviewService;
import peer.review.business.domain.Conference;
import peer.review.ui.UIUtils;

class AllocationCommandTest {

	@Test
	void ShouldThrowsExceptionWhenGivenEmptyUnallocatedConferencesList() throws Exception {
		List<Conference> unallocatedConferences = Collections.emptyList();
		PeerReviewService mockedService = mock(PeerReviewService.class);

		AllocationCommand command = spy(new AllocationCommand(mockedService));

		when(mockedService.getUnallocatedConferences()).thenReturn(unallocatedConferences);

		String message = UIUtils.INSTANCE.getTextManager().getText("exception.unallocated.empty");

		Assert.assertThrows(message, IllegalArgumentException.class, () -> {
			command.execute();
		});

		verify(command, times(0)).printConferences(Matchers.<List<Conference>>any());
		verify(mockedService, times(1)).getUnallocatedConferences();
	}

}

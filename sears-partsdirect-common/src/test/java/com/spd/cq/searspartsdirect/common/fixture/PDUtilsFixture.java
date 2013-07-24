package com.spd.cq.searspartsdirect.common.fixture;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.adobe.cq.social.commons.Comment;
import com.adobe.cq.social.commons.CommentSystem;
import com.day.cq.tagging.Tag;
import com.day.cq.wcm.api.Page;
import com.spd.cq.searspartsdirect.common.helpers.Constants;

public class PDUtilsFixture {

	public PDUtilsFixture() {
		
	}

	public Page getPageWithSubcategory() {
		Page page = getPageWithoutTags();
		Tag subCat = mock(Tag.class);
		when(subCat.getTagID()).thenReturn(Constants.SUBCATEGORY_TAG);
		Tag[] tags = new Tag[]{subCat};
		when(page.getTags()).thenReturn(tags);
		return page;
	}

	public Page getPageWithoutTags() {
		Page page = mock(Page.class);
		return page;
	}

	public Page getPageWithEmptyTags() {
		//Tag[] tagsArr = page.getTags();
		Tag[] tagsArr = new Tag[0];
		Page page = getPageWithoutTags();
		when(page.getTags()).thenReturn(tagsArr);
		return page;
	}

	public Page getPageWithOtherTag() {
		Page page = getPageWithoutTags();
		Tag notSubCat = mock(Tag.class);
		when(notSubCat.getTagID()).thenReturn("inrlyehde");
		Tag[] tags = new Tag[]{notSubCat};
		when(page.getTags()).thenReturn(tags);
		return page;
	}

	public CommentSystem getCommentsSystem() {
		CommentSystem commentSystem = mock(CommentSystem.class);
		final List<Comment> commentsList = new ArrayList<Comment>(3);
		when(commentSystem.getComments()).thenAnswer(new Answer<Iterator<Comment>>() {
			public Iterator<Comment> answer(InvocationOnMock invocation)
					throws Throwable {
				return commentsList.iterator();
			}
		});
		Comment okayComment = mock(Comment.class);
		commentsList.add(okayComment);
		Comment deniedComment = mock(Comment.class);
		commentsList.add(deniedComment);
		when(deniedComment.isDenied()).thenReturn(true);
		Comment spamComment = mock(Comment.class);
		commentsList.add(spamComment);
		when(spamComment.isSpam()).thenReturn(true);
		
		return commentSystem;
	}

}

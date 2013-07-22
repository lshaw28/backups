package com.spd.cq.searspartsdirect.common.fixture;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Matchers.any;


public class CleanTextTagFixture {

	PageContext pageContext;
	StringWriter snoop;
	
	public CleanTextTagFixture(PageContext pageContext) throws IOException {
		this.pageContext = pageContext;
		snoop = new StringWriter();
		JspWriter snoopWrapper = mock(JspWriter.class);
		when(pageContext.getOut()).thenReturn(snoopWrapper);
		doAnswer(new Answer<Void>() {
			public Void answer(InvocationOnMock invocation) throws Throwable {
				String arg = (String)invocation.getArguments()[0];
				snoop.write(arg);
				return null;
			}
		}).when(snoopWrapper).write(any(String.class));
	}
	
	public String getCleanText() {
		return "<p foo=\"foo\">Foo</p><p bar=\"bar\">Bar</p><p foo=\"foo\" bar=\"bar\">Baz</p>";
	}
	
	public String getDirtyText() {
		return "<p foo=\"foo\" style=\"display:none\">Foo</p><p style=\"display:none\" bar=\"bar\">Bar</p><p foo=\"foo\" style=\"display:none\" bar=\"bar\">Baz</p>";
	}

	public String getOutput() {
		snoop.flush();
		return snoop.toString();
	}

	public void breakWriter() throws IOException {
		JspWriter brokenWriter = mock(JspWriter.class);
		when(pageContext.getOut()).thenReturn(brokenWriter);
		doThrow(new IOException()).when(brokenWriter).write(any(String.class));
	}

}

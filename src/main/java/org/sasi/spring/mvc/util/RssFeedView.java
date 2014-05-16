/*package org.sasi.spring.mvc.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sasi.spring.mvc.model.State;
import org.springframework.web.servlet.view.feed.AbstractRssFeedView;

import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Content;
import com.sun.syndication.feed.rss.Item;

public class RssFeedView extends AbstractRssFeedView {

	@Override
	protected void buildFeedMetadata(Map<String, Object> model, Channel feed,
			HttpServletRequest request) {
		
		feed.setTitle("Sample Title");
		feed.setDescription("Sample Description");
		feed.setLink("http://google.com");
		
		super.buildFeedMetadata(model, feed, request);
	}
	
	
	@Override
	protected List<Item> buildFeedItems(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		State fruit = (State) model.get("state");
		String msg = fruit.getStateName() + fruit.getQuality();
		
		List<Item> items = new ArrayList<Item>(1);
		Item item = new Item();
		item.setAuthor("mkyong");
		item.setLink("http://www.mkyong.com");
		
		Content content = new Content();
		content.setValue(msg);
		
		item.setContent(content);
		
		items.add(item);
		
		return items;
	}

	
}*/
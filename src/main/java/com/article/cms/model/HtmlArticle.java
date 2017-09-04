package com.article.cms.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class HtmlArticle {

	private String header;
	private String body;
	private String footer;
	private String resources;
	public String getHeader() {
		return header;
	}
	@XmlElement
	public void setHeader(String header) {
		this.header = header;
	}
	public String getBody() {
		return body;
	}
	@XmlElement
	public void setBody(String body) {
		this.body = body;
	}
	public String getFooter() {
		return footer;
	}
	@XmlElement
	public void setFooter(String footer) {
		this.footer = footer;
	}
	public String getResources() {
		return resources;
	}
	
	@XmlElement
	public void setResources(String resources) {
		this.resources = resources;
	}
	
	
}

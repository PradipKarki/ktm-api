package com.ktm.utils.mail.model;

import java.util.List;
import java.util.Map;

import org.simplejavamail.email.Recipient;

public final class EmailPO {
	
	private long id;
	private String fromName;
	private String fromAddress;
	private String toName;
	private String toAddress;
	private String bounceToAddress;
	private List<Recipient> recipients;
	private String text;
	
	// sample htmlText -> "<img src='cid:wink1'><b>We should meet up!</b><img src='cid:wink2'>"
	private String htmlText;
	private String subject;
	private String base64String;
	private Map<String, String> headers;
	
	public EmailPO() {
		super();
	}

	public EmailPO(String toName, String toAddress, String text, String htmlText, String subject) {
		this.toName = toName;
		this.toAddress = toAddress;
		this.text = text;
		this.htmlText = htmlText;
		this.subject = subject;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public String getFromAddress() {
		return fromAddress;
	}
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
	public String getToName() {
		return toName;
	}
	public void setToName(String toName) {
		this.toName = toName;
	}
	public String getToAddress() {
		return toAddress;
	}
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}
	public String getBounceToAddress() {
		return bounceToAddress;
	}

	public void setBounceToAddress(String bounceToAddress) {
		this.bounceToAddress = bounceToAddress;
	}
	public List<Recipient> getRecipients() {
		return recipients;
	}
	public void setRecipients(List<Recipient> recipients) {
		this.recipients = recipients;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String gethtmlText() {
		return htmlText;
	}
	public void sethtmlText(String htmlText) {
		this.htmlText = htmlText;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBase64String() {
		return base64String;
	}
	public void setBase64String(String base64String) {
		this.base64String = base64String;
	}
	public Map<String, String> getHeaders() {
		return headers;
	}
	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

}

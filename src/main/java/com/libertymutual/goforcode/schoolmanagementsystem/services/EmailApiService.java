package com.libertymutual.goforcode.schoolmanagementsystem.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Service
public class EmailApiService {

	@Value("${MAIL_GUN_API_KEY}")
	private String key;

	public JsonNode sendSimpleMessage(String email) throws UnirestException {
		String html = "<h1>Welcome to School Management System!</h1>\r\n"
		+ "A new account: " + email + " has been created.<br>\r\n" 
		+ "<b>The account's password is defaulted to: password</b><br>\r\n"
		+ "Please forward this email to " + email + " and advise them to change their password immediately. This can be found on the preference tab.<br>\r\n"
		+ "Please navigate to Home > Preferences > Update Password to update your password immediately. <br>\r\n";

		HttpResponse<JsonNode> request = Unirest
				.post("https://api.mailgun.net/v3/" + "sandboxf39f405f504940fb9ce6cb2115b5d2e9.mailgun.org"
						+ "/messages")
				.basicAuth("api", key).queryString("from", "School Management System <schooldiy@gmail.com>")
				.queryString("to", "schooltiy@gmail.com").queryString("subject", "Welcome!").queryString("html", html)
				.asJson();

		return request.getBody();
	}
}

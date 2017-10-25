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
		+ "A new account has been created for you to manage your school data via the School Management System.<br>\r\n"
		+ "Please navigate to the School management system and login with this email as your username and your password.<br>\r\n"
		+ "<b>Your password is: password</b><br>\r\n"
		+ "Please navigate to Home > Preferences > Update Password to update your password immediately. <br>\r\n";


		HttpResponse<JsonNode> request = Unirest
				.post("https://api.mailgun.net/v3/" + "sandboxf39f405f504940fb9ce6cb2115b5d2e9.mailgun.org"
						+ "/messages")
				.basicAuth("api", key).queryString("from", "Excited User <USER@YOURDOMAIN.COM>")
				.queryString("to", email).queryString("subject", "Welcome!").queryString("html", html)
				.asJson();

		return request.getBody();
	}
}

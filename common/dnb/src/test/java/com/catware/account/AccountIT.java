package com.catware.account;

import static org.junit.Assert.*;

import java.net.URL;
import java.util.List;

import org.json.JSONObject;
import org.junit.Test;

import com.catware.account.impl.AccountServiceImpl;
import com.catware.consent.impl.ConsentServiceImpl;
import com.catware.model.AccountDetails;
import com.catware.model.AccountList;
import com.catware.model.Balance;
import com.catware.util.http.MyResponse;
import com.catware.util.json.JsonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AccountIT {
	
	private static final String psuid = "31125464346";
	
    //@Test
    public void accList() throws Exception {
    	MyResponse txt = new ConsentServiceImpl().create(psuid);
    	System.out.println(txt);
    	JSONObject jo = new JSONObject(txt);
    	String consentid = jo.getString("consentId");
    	System.out.println("ConsentId " + consentid);
    	JSONObject links = jo.getJSONObject("_links");
    	System.out.println("ConsentId " + consentid);
    	JSONObject redirect = links.getJSONObject("scaRedirect");
    	System.out.println("Redirect " + redirect);
    	String href = redirect.getString("href");
    	int index = href.indexOf('/', 10);
    	String base = href.substring(0, index);
    	System.out.println("Baseurl " + base);
    	String newUrl = base + "/Prod/bankid/auth";
    	String body = "{ \"auth_status\" : \"Ok\", \"url\" : \"" + href + "\" }";
    	JSONObject json = new JSONObject();
    	json.put("auth_status", "Ok");
    	json.put("url", href);
    	body = json.toString();
    	System.out.println(body);
    	URL baseUrl = new URL(base);
		URL url = new URL(baseUrl, "Prod/bankid/auth");
		RequestBody requestBody = null;
		if (body != null) {
			MediaType JSON = MediaType.parse("application/json; charset=utf-8");
			System.out.println("Body " + body);
			requestBody = RequestBody.create(JSON, body);
		}
		Request request = new Request.Builder().url(url)
				.post(requestBody)
				.build();
		
		OkHttpClient httpClient = new OkHttpClient.Builder()
				.build();
		Response response = httpClient.newCall(request).execute();
		System.out.println("---------- Response ----------");
		String body2 = response.body().string();
		System.out.println(response.code());
		System.out.println(response.headers().toString());
		System.out.println(body2);
		
		
    	MyResponse txt2 = new AccountServiceImpl().getAccount(consentid, true);
    	System.out.println(txt2);
    }
    @Test
    public void accList1() throws Exception {
    	String consentid = "a7623d67-66af-42e6-bca5-463459fe9649";
    	MyResponse txt2 = new AccountServiceImpl().getAccount(psuid, true);
    	System.out.println(txt2);
    	//List<ResultMeta> resultMeta = new ObjectMapper().convertValue(objectList, new TypeReference<List<ResultMeta>>() { });
    	//com.catware.model.Account[] accts = JsonUtil.convert(txt2, new TypeReference<com.catware.model.Account[]>() { });
    	AccountList accts = JsonUtil.convert(txt2.getBody(), AccountList.class);
    	System.out.println(accts);
    	assertEquals(accts.getAccounts().size(), 2);
    	String accid = accts.getAccounts().get(0).getBban();
    	
    	MyResponse txt3 = new AccountServiceImpl().getAccountDetails(consentid, accid, true);
    	System.out.println(txt3);
    	AccountDetails acct = JsonUtil.convert(txt3.getBody(), AccountDetails.class);    	
    	System.out.println(acct.getBban());
    	
    	MyResponse txt4 = new AccountServiceImpl().getBalance(consentid, accid);
    	System.out.println(txt4);
    	Balance balance = JsonUtil.convert(txt4.getBody(), Balance.class);    	
    	System.out.println("B" + balance);
    	System.out.println(balance.getLastComittedTransaction());
    	
    	MyResponse txt5 = new AccountServiceImpl().getAccountTransactions(consentid, accid);
    	System.out.println(txt5);
    	
    }


}

package com.catware.account.impl;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.catware.account.AccountService;
import com.catware.constants.Constants;
import com.catware.constants.DNBConstants;
import com.catware.database.hibernate.Consent;
import com.catware.model.AccountDetails;
import com.catware.model.AccountList;
import com.catware.model.AccountReference;
import com.catware.model.AccountReport;
import com.catware.model.Amount;
import com.catware.model.Balance;
import com.catware.model.GetAccountBalancesResponse;
import com.catware.model.GetAccountTransactionsResponse;
import com.catware.model.TppMessageGeneric;
import com.catware.model.TransactionDetails;
import com.catware.util.http.MyResponse;
import com.catware.util.http.dnb.DNBRequest;
import com.catware.util.http.dnb.ErrorUtil;
import com.catware.util.json.JsonUtil;

public class AccountServiceImpl extends AccountService {

	@Override
	public MyResponse getAccount(String psuid, boolean withBalance) throws UnrecoverableKeyException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		Consent aConsent = Consent.find(psuid);
		if (aConsent != null) {
			String consentid = aConsent.getConsentid();
			Map<String, String> header = new LinkedHashMap<>();
			header.put(DNBConstants.CONSENTID, consentid);
			header.put(DNBConstants.TPPREDIRECTURI, DNBConstants.DNBREDIRECT);
			MyResponse response = new DNBRequest(DNBConstants.PSD2ENDPOINT, "v1/accounts", null, Constants.GET, header, null).request();
			if (response.getCode() != 200) {
				return ErrorUtil.getError(response);
			}
			String json = response.getBody();
			System.out.println(json);
			AccountList accounts = JsonUtil.convert(json, AccountList.class);
			if (withBalance) {
				for (AccountDetails account : accounts.getAccounts()) {
					String bban = account.getBban();			
					MyResponse balanceResponse = getBalanceInner(consentid, bban);
					GetAccountBalancesResponse accountBalanceResponse;
					if(balanceResponse.getCode() == 200) {
						accountBalanceResponse = JsonUtil.convert(balanceResponse.getBody(), GetAccountBalancesResponse.class);
					} else {
						return ErrorUtil.getError(balanceResponse);
					}
					account.setBalances(accountBalanceResponse.getBalances());
				}
			}
			List<com.catware.service.model.AccountDetails> newAccounts = new ArrayList<>();
			for (AccountDetails acc : accounts.getAccounts()) {
				List<com.catware.service.model.Balance> newBalance = convertBalance(acc.getBalances());
				String authorisedBalance = getBalance(acc.getBalances(), "authorised");
				String openingBookedBalance = getBalance(acc.getBalances(), "openingBooked");
				com.catware.service.model.AccountDetails newAcc = new com.catware.service.model.AccountDetails(authorisedBalance, newBalance, acc.getBban(), acc.getCurrency(), acc.getName(), openingBookedBalance);
				newAccounts.add(newAcc);
			}
			String newJson = JsonUtil.convert(new com.catware.service.model.AccountList(newAccounts));
			response.setBody(newJson);
			return response;
		} else {
			return ErrorUtil.getError(500, Constants.PSUWITHOUTCONSENT);
		}
	}

	private String getBalance(List<Balance> balances, String balanceType) {
		for (Balance balance : balances) {
			if (balance.getBalanceType().equals(balanceType)) {
				return balance.getBalanceAmount().getAmount();
			}
		}
		return null;
	}

	@Override
	public MyResponse getAccountDetails(String psuid, String accid, boolean withBalance) throws UnrecoverableKeyException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		Consent aConsent = Consent.find(psuid);
		if (aConsent != null) {
			String consentid = aConsent.getConsentid();
			String requestId = UUID.randomUUID().toString();
			Map<String, String> header = new LinkedHashMap<>();
			header.put(DNBConstants.CONTENTTYPE, "application/json; utf-8");
			header.put(DNBConstants.ACCEPT, "application/json");
			header.put(DNBConstants.XREQUESTID, requestId);
			header.put(DNBConstants.CONSENTID, consentid);
			header.put(DNBConstants.TPPREDIRECTURI, DNBConstants.DNBREDIRECT);
			MyResponse transactionResponse = new DNBRequest(DNBConstants.PSD2ENDPOINT, "v1/accounts/" + accid, null, Constants.GET, header, null).request();
			if (transactionResponse.getCode() == 200) {
				AccountDetails details = JsonUtil.convert(transactionResponse.getBody(), AccountDetails.class);
				String newJson = JsonUtil.convert(new com.catware.service.model.AccountDetails(null, convertBalance(details.getBalances()), details.getBban(), details.getCurrency(), details.getName(), null));
				return new MyResponse(200, newJson);
			} else {
				return transactionResponse;
			}
		} else {
			return ErrorUtil.getError(500, Constants.PSUWITHOUTCONSENT);
		}
	}

	@Override
	public MyResponse getBalance(String psuid, String accid) throws UnrecoverableKeyException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		Consent aConsent = Consent.find(psuid);
		if (aConsent != null) {
			String consentid = aConsent.getConsentid();
			MyResponse response = getBalanceInner(consentid, accid);
			GetAccountBalancesResponse accountBalanceResponse;
			if(response.getCode() == 200) {
				accountBalanceResponse = JsonUtil.convert(response.getBody(), GetAccountBalancesResponse.class);
			} else {
				return ErrorUtil.getError(response);
			}
			AccountReference account = accountBalanceResponse.getAccount();
			List<Balance> balances = accountBalanceResponse.getBalances();
			com.catware.service.model.GetAccountBalancesResponse newBalance = new com.catware.service.model.GetAccountBalancesResponse(convertAccount(account), convertBalance(balances));
			response.setBody(JsonUtil.convert(newBalance));
			return response;
		} else {
			return ErrorUtil.getError(500, Constants.PSUWITHOUTCONSENT);
		}
	}

	private com.catware.service.model.AccountReference convertAccount(AccountReference account) {
		return new com.catware.service.model.AccountReference(account.getBban(), account.getCurrency());
	}

	private MyResponse getBalanceInner(String consentid, String accid) throws IOException, KeyStoreException,
			UnrecoverableKeyException, KeyManagementException, NoSuchAlgorithmException, CertificateException {
		Map<String, String> header = new LinkedHashMap<>();
		header.put(DNBConstants.CONSENTID, consentid);		
		MyResponse response = new DNBRequest(DNBConstants.PSD2ENDPOINT, "v1/accounts/" + accid + "/balances", null, Constants.GET, header, null).request();
		return response;
	}

	private List<com.catware.service.model.Balance> convertBalance(List<Balance> balances) {
		List<com.catware.service.model.Balance> newBalances = new ArrayList<>();
		for (Balance balance : balances) {
			com.catware.service.model.Amount newAmount = new com.catware.service.model.Amount(balance.getBalanceAmount().getAmount(), balance.getBalanceAmount().getCurrency());
			newBalances.add(new com.catware.service.model.Balance(newAmount, balance.getBalanceType(), balance.getReferenceDate()));
		}
		return newBalances;
	}

	@Override
	public MyResponse getAccountTransactions(String psuid, String accid) throws UnrecoverableKeyException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		Consent aConsent = Consent.find(psuid);
		if (aConsent != null) {
			String consentid = aConsent.getConsentid();
			Map<String, String> header = new LinkedHashMap<>();
			header.put(DNBConstants.CONSENTID, consentid);
			header.put(DNBConstants.TPPREDIRECTURI, DNBConstants.DNBREDIRECT);
			Map<String, String> queries = new HashMap<>();
			queries.put("bookingStatus", "both");
			MyResponse transactionResponse = new DNBRequest(DNBConstants.PSD2ENDPOINT, "v1/accounts/" + accid + "/transactions", queries, Constants.GET, header, null).request();
			if(transactionResponse.getCode() == 200) {
				GetAccountTransactionsResponse trans = JsonUtil.convert(transactionResponse.getBody(), GetAccountTransactionsResponse.class);			
				AccountReport report = trans.getTransactions();
				List<TransactionDetails> bookedList = report.getBooked();
				List<TransactionDetails> pendingList = report.getPending();
				com.catware.service.model.AccountReport newTrans = new com.catware.service.model.AccountReport(convertTransaction(bookedList), convertTransaction(pendingList));
				com.catware.service.model.GetAccountTransactionsResponse response = new com.catware.service.model.GetAccountTransactionsResponse(convertAccount(trans.getAccount()), newTrans);
				transactionResponse.setBody(JsonUtil.convert(response));
				return transactionResponse;
			} else {
				return ErrorUtil.getError(transactionResponse);
			}
		} else {
			return ErrorUtil.getError(500, Constants.PSUWITHOUTCONSENT);
		}
	}

	private List<com.catware.service.model.TransactionDetails> convertTransaction(
			List<TransactionDetails> list) {
		List<com.catware.service.model.TransactionDetails> retlist = new ArrayList<>();
		for (TransactionDetails transaction : list) {
			Amount amount = transaction.getTransactionAmount();
			com.catware.service.model.Amount newAmount = new com.catware.service.model.Amount(amount.getAmount(), amount.getCurrency());
			// enough?
			// dnb also returns
			// dnbTransactionDateTime
			// dnbTransactionType
			com.catware.service.model.TransactionDetails newTransactionDetail = new com.catware.service.model.TransactionDetails(transaction.getTransactionId(), transaction.getBookingDate(), transaction.getValueDate(), newAmount);
			retlist.add(newTransactionDetail);
		}
		return retlist;
	}
	
}

package io.namoosori.travelclub.spring.aggregate.club;

import io.namoosori.travelclub.spring.aggregate.Entity;
import io.namoosori.travelclub.spring.aggregate.club.vo.Address;
import io.namoosori.travelclub.spring.shared.NameValue;
import io.namoosori.travelclub.spring.shared.NameValueList;
import io.namoosori.travelclub.spring.util.exception.InvalidEmailException;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class CommunityMember extends Entity {
	//
	private String email;		// key
	private String name;
	private String phoneNumber;
	private String nickName;
	private String birthDay;

	private List<Address> addresses;

	public CommunityMember() {
		//
		super();
		this.addresses = new ArrayList<>();
	}

	public CommunityMember(String id) {
		//
		super(id);
	}

	public CommunityMember(String email, String name, String phoneNumber) {
		//
		this();
		this.email = email;
		this.name = name;
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		//
		StringBuilder builder = new StringBuilder();

		builder.append("Name:").append(name);
		builder.append(", email:").append(email);
		builder.append(", nickname:").append(nickName);
		builder.append(", phone number:").append(phoneNumber);
		builder.append(", birthDay:").append(birthDay);

		if (addresses != null) {
			int i = 1;
			for (Address address : addresses) {
				builder.append(", Address[").append(i).append("]").append(address.toString());
			}
		}

		return builder.toString();
	}

	public void checkValidation() {
		//
		checkEmailValidation(email);
	}

    private void checkEmailValidation(String email) {
		//
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);
		boolean valid = m.matches();

		if (!valid) {
			throw new InvalidEmailException("Email is invalid.");
		}
	}

	public void modifyValues(NameValueList nameValues) {
		//
		for (NameValue nameValue : nameValues.getNameValues()) {
			String value = nameValue.getValue();
			switch (nameValue.getName()) {
				case "name":
					this.name = value;
					break;
				case "phoneNumber":
					this.phoneNumber = value;
					break;
				case "nickName":
					this.nickName = value;
					break;
				case "birthDay":
					this.birthDay = value;
					break;
			}
		}
	}

	public static CommunityMember sample() {
		//
		CommunityMember member = new CommunityMember("mymy@nextree.co.kr", "Minsoo Lee", "010-3321-1001");
		member.setBirthDay("2001.09.23");
		member.getAddresses().add(Address.sampleHomeAddress());
		return member;
	}

	public static void main(String[] args) {
		//
		System.out.println(sample().toString());
	}
}

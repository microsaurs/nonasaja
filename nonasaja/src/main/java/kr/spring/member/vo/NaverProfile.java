package kr.spring.member.vo;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class NaverProfile {

	private String resultcode;
	private String message;
	private Response response;

	public String getResultcode() {
		return resultcode;
	}

	public void setResultcode(String resultcode) {
		this.resultcode = resultcode;
	}

	public NaverProfile withResultcode(String resultcode) {
		this.resultcode = resultcode;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public NaverProfile withMessage(String message) {
		this.message = message;
		return this;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public NaverProfile withResponse(Response response) {
		this.response = response;
		return this;
	}
	
	@Generated("jsonschema2pojo")
	public class Response {

		private String id;
		private String nickname;
		private String profile_image;
		private String email;
		private String mobile;
		private String mobile_e164;
		private String name;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public Response withId(String id) {
			this.id = id;
			return this;
		}

		public String getNickname() {
			return nickname;
		}

		public void setNickname(String nickname) {
			this.nickname = nickname;
		}

		public Response withNickname(String nickname) {
			this.nickname = nickname;
			return this;
		}

		public String getProfile_image() {
			return profile_image;
		}

		public void setProfile_image(String profile_image) {
			this.profile_image = profile_image;
		}

		public Response withProfile_image(String profile_image) {
			this.profile_image = profile_image;
			return this;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public Response withEmail(String email) {
			this.email = email;
			return this;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public Response withMobile(String mobile) {
			this.mobile = mobile;
			return this;
		}

		public String getMobile_e164() {
			return mobile_e164;
		}

		public void setMobile_e164(String mobile_e164) {
			this.mobile_e164 = mobile_e164;
		}

		public Response withMobile_e164(String mobile_e164) {
			this.mobile_e164 = mobile_e164;
			return this;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Response withName(String name) {
			this.name = name;
			return this;
		}

	}

}
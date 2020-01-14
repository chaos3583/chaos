package com.chaos.model;

import java.util.Date;


/**
 * @author Administrator
 *@date 2017年9月28日
 */
public class Member implements Cloneable{
	
        private Long id;
        private Integer employeeId;
        private Integer gender;
        private String nickname;
        private String uname;
		
		private Integer age;
		
        private String passwd;
        private String email;
        private String phone;
        private Date regTime;

        private Date lastLogin;

        private String lastIp;

        
		public Member() {
			super();
		}

		public Member(String nickname, Integer age) {
			super();
			this.nickname = nickname;
			this.age = age;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Integer getEmployeeId() {
			return employeeId;
		}

		public void setEmployeeId(Integer employeeId) {
			this.employeeId = employeeId;
		}

		public Integer getGender() {
			return gender;
		}

		public void setGender(Integer gender) {
			this.gender = gender;
		}

		public Integer getAge() {
			return age;
		}

		public void setAge(Integer age) {
			this.age = age;
		}

		public String getNickname() {
			return nickname;
		}

		public void setNickname(String nickname) {
			this.nickname = nickname;
		}

		public String getUname() {
			return uname;
		}

		public void setUname(String uname) {
			this.uname = uname;
		}

		public String getPasswd() {
			return passwd;
		}

		public void setPasswd(String passwd) {
			this.passwd = passwd;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getLastIp() {
			return lastIp;
		}

		public void setLastIp(String lastIp) {
			this.lastIp = lastIp;
		}

		public Date getRegTime() {
			return regTime;
		}

		public void setRegTime(Date regTime) {
			this.regTime = regTime;
		}

		public Date getLastLogin() {
			return lastLogin;
		}

		public void setLastLogin(Date lastLogin) {
			this.lastLogin = lastLogin;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((email == null) ? 0 : email.hashCode());
			result = prime * result + ((employeeId == null) ? 0 : employeeId.hashCode());
			result = prime * result + ((gender == null) ? 0 : gender.hashCode());
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((lastIp == null) ? 0 : lastIp.hashCode());
			result = prime * result + ((lastLogin == null) ? 0 : lastLogin.hashCode());
			result = prime * result + ((nickname == null) ? 0 : nickname.hashCode());
			result = prime * result + ((passwd == null) ? 0 : passwd.hashCode());
			result = prime * result + ((phone == null) ? 0 : phone.hashCode());
			result = prime * result + ((regTime == null) ? 0 : regTime.hashCode());
			result = prime * result + ((uname == null) ? 0 : uname.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Member other = (Member) obj;
			if (email == null) {
				if (other.email != null)
					return false;
			} else if (!email.equals(other.email))
				return false;
			if (employeeId == null) {
				if (other.employeeId != null)
					return false;
			} else if (!employeeId.equals(other.employeeId))
				return false;
			if (gender == null) {
				if (other.gender != null)
					return false;
			} else if (!gender.equals(other.gender))
				return false;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (lastIp == null) {
				if (other.lastIp != null)
					return false;
			} else if (!lastIp.equals(other.lastIp))
				return false;
			if (lastLogin == null) {
				if (other.lastLogin != null)
					return false;
			} else if (!lastLogin.equals(other.lastLogin))
				return false;
			if (nickname == null) {
				if (other.nickname != null)
					return false;
			} else if (!nickname.equals(other.nickname))
				return false;
			if (passwd == null) {
				if (other.passwd != null)
					return false;
			} else if (!passwd.equals(other.passwd))
				return false;
			if (phone == null) {
				if (other.phone != null)
					return false;
			} else if (!phone.equals(other.phone))
				return false;
			if (regTime == null) {
				if (other.regTime != null)
					return false;
			} else if (!regTime.equals(other.regTime))
				return false;
			if (uname == null) {
				if (other.uname != null)
					return false;
			} else if (!uname.equals(other.uname))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Member [id=" + id + ", emplyeeId=" + employeeId + ", gender=" + gender + ", nickname=" + nickname
					+ ", uname=" + uname + ", passwd=" + passwd + ", email=" + email + ", phone=" + phone + ", regTime="
					+ regTime + ", lastLogin=" + lastLogin + ", lastIp=" + lastIp + "]";
		}

		@Override
		public Member clone() throws CloneNotSupportedException {
			
			return (Member)super.clone();
		}
		
		
}

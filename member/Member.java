package member;

public class Member {
	private String id;
	private String passwd;
	private String name;
	private String tel;
	private boolean admin;
	
	public Member(){}
	
	public Member(String id, String passwd, String name, String tel, boolean admin) {
		super();
		this.id = id;
		this.passwd = passwd;
		this.name = name;
		this.tel = tel;
		this.admin = admin;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj != null && obj instanceof Member) {
			Member other  = (Member)obj;
			if (id.equals(other.getId()) && passwd.equals(other.getPasswd())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", passwd=" + passwd + ", name=" + name + ", tel=" + tel + ", admin=" + admin + "]";
	}	
}

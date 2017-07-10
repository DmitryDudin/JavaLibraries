import java.util.Date;

public class StudentProfile {

    private Long id;

    private String name;

    private String grade;

    private Date birthday;

    private String birthdayString;

    private String email;

    private String phone;

    private String address;

    public StudentProfile() {
    }

    public StudentProfile(Long id, String name, String grade, Date birthday, String email, String phone, String address) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        StudentProfile other = (StudentProfile) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("StudentProfile {")
                .append("id=").append(id).append(", ")
                .append("name='").append(name).append("', ")
                .append("grade='").append(grade).append("', ")
                .append("email='").append(email).append("', ")
                .append("phone='").append(phone).append("', ")
                .append("address='").append(address).append("'}");
        return sb.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdayString() {
        return birthdayString;
    }

    public void setBirthdayString(String birthdayString) {
        this.birthdayString = birthdayString;
    }
}
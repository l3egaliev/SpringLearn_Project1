package kg.rakhim.classes.model;


import jakarta.validation.constraints.*;

public class Person {
    private Integer person_id;
    @NotEmpty(message = "Пишите свое Ф.И.О")
    @Size(min = 3, max = 200, message = "Ф.И.О должна быть не менее 3 символов и не больше 200")
  //  @Pattern(regexp = "[А-Я]\\w+ [А-Я]\\w+ [А-Я]\\w+", message = "Пишите Ф.И.О в таком формате: " +
     //       "Фамилия Имя Отчество")
    private String fullName;

    @Min(value = 0, message = "Вы слишком малы чтобы взять книгу)")
    private int yearOfBirth;


    public Person() {
    }

    public Person(int id, String fullName, int age) {
        this.person_id = id;
        this.fullName = fullName;
        this.yearOfBirth = age;
    }



    public String getFullName() {
        return fullName;
    }

    public void setFullName(String name) {
        this.fullName = name;
    }


    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }
}

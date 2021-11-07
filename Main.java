package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String persons_file = "Files/persons.csv";
        String persons_extend_file = "Files/persons_extend.csv";
        String line = "";
        String splitBy = ",";
        List<Person> person_list = new ArrayList<>();
        try {
            //parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader(persons_file));
            BufferedReader br_extend = new BufferedReader(new FileReader(persons_extend_file));
            DateFormat formatter = new SimpleDateFormat("dd/MM/yy");
            int num = 0;
            int id = 0;
            while ((line = br.readLine()) != null) {
                num++;
                String line_extend = br_extend.readLine();
                if (num > 1) {
                    String[] persons = line.split(splitBy);
                    String[] extend = line_extend.split(splitBy);
                    //use comma as separator
                    Date date_birthdate = formatter.parse(persons[3]);
                    person_list.add(new Person(id, persons[1], date_birthdate, persons[2], persons[4], extend[1]));
                    id++;
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        //Sorting
        Collections.sort(person_list, Comparator.comparing(Person::getBirthdate));

        //Show list
        person_list.forEach(person -> {
            System.out.println(person);
        });

    }

    static class Person {
        private int id;
        private String name;
        private String address;
        private Date birthdate;
        private String role;
        private String hobbies;

        public Person(int id, String name, Date birthdate, String address, String role, String hobbies) {
            this.id = id;
            this.name = name;
            this.address = address;
            this.birthdate = birthdate;
            this.role = role;
            this.hobbies = hobbies;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Date getBirthdate() {
            return birthdate;
        }

        public void setBirthdate(Date birthdate) {
            this.birthdate = birthdate;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getHobbies() {
            return hobbies;
        }

        public void setHobbies(String hobbies) {
            this.hobbies = hobbies;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", birthdate='" + birthdate + '\'' +
                    ", address='" + address + '\'' +
                    ", role='" + role + '\'' +
                    ", hobbies='" + hobbies + '\'' +
                    '}';
        }
    }
}

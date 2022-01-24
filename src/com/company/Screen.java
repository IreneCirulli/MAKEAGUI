package com.company;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Screen extends JFrame{
    private JPanel panelTop;
    private JPanel panelLeft;
    private JPanel panelRight;
    private JList listPeople;
    private JButton buttonNew;
    private JButton buttonSave;
    private JTextField textName;
    private JTextField textEmail;
    private JTextField textPhoneNumber;
    private JTextField textAddress;
    private JLabel labelAge;
    private JTextField textDateOfBirth;
    private JPanel panelMain;
    private ArrayList<Person> people;
    private DefaultListModel listPeopleModel;

    Screen(){
    super("My fancy contact project");
    this.setContentPane(this.panelMain);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.pack();
    people = new ArrayList<Person>();
    listPeopleModel = new DefaultListModel();
    listPeople.setModel(listPeopleModel);
    buttonSave.setEnabled(false);

        buttonNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int personNumber = listPeople.getSelectedIndex();
                if(personNumber >= 0){
                    Person p = new Person(textName.getText(), textEmail.getText(),
                            textPhoneNumber.getText(), textDateOfBirth.getText());
                    Person p = people.get(personNumber);
                    p.setName( textName.getText());
                    p.setEmail(textName.getText());
                    p.setPhonenumber(textPhoneNumber.getText());
                    p.setDateOfBirth(textDateOfBirth.getText());
                    refreshPeopleList();
                }
            }
        });

        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        listPeople.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
               int personNumber = listPeople.getSelectedIndex();
               if(personNumber >= 0){
                   Person p = people.get(personNumber);
                   textName.setText(p.getName());
                   textName.setText(p.getEmail());
                   textName.setText(p.getPhonenumber());
                   textName.setText(p.getDateOfBirth().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                   labelAge.setText(Integer.toString(p.getAge()) + " years");
                   buttonSave.setEnabled(true);
               }
               else{
                   buttonSave.setEnabled(false);
               }
            }
        });

    }

    public void refreshPeopleList(){
        listPeopleModel.removeAllElements();
        System.out.println("Removing all people from list");
        for(Person p : people){
            System.out.println("Adding person to list: " + p.getName());
            listPeopleModel.addElement(p.getName());
        }
    }

    public void addPerson(Person p){
        people.add(p);
        refreshPeopleList();
    }

    public static void main(String[] args) {
        Screen screen = new Screen();
        screen.setVisible(true);

        Person sheldon = new Person("Sheldon Lee Cooper", "sheldon@gmail.com", "555 0001", "26/02/1980");
        Person howard = new Person("Howard Joel Wolowitz", "howard@gmail.com", "555 0002", "01/03/1981");
        Person bernadette = new Person("Bernadette Rostenkowski-Wolowitz", "bernadette@gmail.com", "555 0002",
                "01/01/1984");
        Person raj = new Person("Rajesh Ramayan Koothrappali", "raj@gmail.com", "555 0003", "06/10/1981");
        Person penny = new Person("Penny Hofstadter", "penny@gmail.com", "555 0004", "02/12/1985");
        Person leonard = new Person("Leonard Hofstadter", "leonard@gmail.com", "555 0004", "17/05/1980");
        Person amy = new Person("Amy Farrah Fowler", "amy@gmail.com", "555 0005", "17/12/1979");
        screen.addPerson(sheldon);
        screen.addPerson(howard);
        screen.addPerson(bernadette);
        screen.addPerson(raj);
        screen.addPerson(penny);
        screen.addPerson(leonard);
        screen.addPerson(amy);
    }
}

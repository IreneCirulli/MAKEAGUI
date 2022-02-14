package com.company;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

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
    private JButton ClearAll;
    private JPanel panelPic;
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

                Person p = new Person(textName.getText(), textEmail.getText(),
                            textPhoneNumber.getText(), textAddress.getText(), textDateOfBirth.getText());
                people.add(p);
                refreshPeopleList();
            }
        });

        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int personNumber = listPeople.getSelectedIndex();
               Person p = people.get(personNumber);
               p.setName(textName.getText());
               p.setEmail(textEmail.getText());
               p.setPhonenumber(textPhoneNumber.getText());
               p.setAddress(textAddress.getText());
               p.setDateOfBirth(textDateOfBirth.getText());
               refreshPeopleList();

            }
        });

        listPeople.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
               int personNumber = listPeople.getSelectedIndex();
               if(personNumber >= 0){
                   Person p = people.get(personNumber);
                   textName.setText(p.getName());
                   textEmail.setText(p.getEmail());
                   textPhoneNumber.setText(p.getPhonenumber());
                   textAddress.setText(p.getAddress());
                   textDateOfBirth.setText(p.getDateOfBirth().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                   labelAge.setText(Integer.toString(p.getAge()) + " years");
                   buttonSave.setEnabled(true);
               }
               else{
                   buttonSave.setEnabled(false);
               }
            }
        });

        ClearAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int personNumber = listPeople.getSelectedIndex();
                    Person p = people.get(personNumber);
                    textName.setText(" ");
                    textEmail.setText(" ");
                    textPhoneNumber.setText(" ");
                    textAddress.setText(" ");
                    textDateOfBirth.setText(" ");
                    labelAge.setText(" ");
                    buttonSave.setEnabled(false);

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

    public static void main(String[] args) throws IOException{
        Screen screen = new Screen();
        screen.setSize(700, 450);
        screen.setVisible(true);

        screen.panelPic.setLayout(new FlowLayout());
        BufferedImage penpic = ImageIO.read(new File("penpic.png"));
        Image dmyPicture = penpic.getScaledInstance(216, 62, Image.SCALE_SMOOTH);
        JLabel picLabel = new JLabel(new ImageIcon(dmyPicture));
        screen.panelPic.add(picLabel);

        FileWriter fw  = new FileWriter("Contact.txt", true);

        fw.close();

        FileReader fr = new FileReader("Contact.txt");
        BufferedReader br = new BufferedReader(fr);

        String line = br.readLine();

        while ((line =br.readLine())!= null) {
            System.out.println(line);
            String[] person = line.split(",");
            Person p  = new Person(person[0], person[1], person[2], person[3],person[4]);
            screen.addPerson(p);

        }

        br.close();
        fr.close();
    }
}

package partyView;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import partyController.Controller;

public class CenterPanelGuestInput extends JPanel
{
    private int width;
    private int height;

    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JTextField txtAge;
    private JTextField txtStreet;
    private JTextField txtCity;
    private JTextField txtZipCode;
    private JLabel txtNumGuest;

    private JComboBox cmbCountries;

    private JPanel pnlLeft;  //for labels at left
    private JPanel pnlRight; //for textboxes at right

    private Controller controller;

    public CenterPanelGuestInput(Controller controller, int width, int height, int margin)
    {
        this.controller = controller;
        this.width = width;
        this.height = height;

        setBorder(BorderFactory.createTitledBorder("Guest information"));

        Border border = this.getBorder();
        Border emptyBorder = BorderFactory.createEmptyBorder(margin, margin, margin, margin);
        setBorder(new CompoundBorder(border, emptyBorder));

        setLayout(new BorderLayout());

        setPreferredSize(new Dimension(width,height));
        CreateComponents();
    }


    private void CreateComponents()
    {
        createComponentsOnLeftPanel();
        createComponentsOnRightPanel();
        createAndInitiateComboBox();
    }

    private void createComponentsOnLeftPanel()
    {
        JLabel lblNumGuest= new JLabel("Number of guests");
        JLabel lblFirstName  = new JLabel("First name");
        JLabel lblLastName = new JLabel("Last name");
        JLabel lblAge = new JLabel("Age");
        JLabel lblStreet = new JLabel("Street");
        JLabel lblCountry  = new JLabel("Country");
        JLabel lblCity  = new JLabel("City");
        JLabel lblZipCode  = new JLabel("Zip code");


        GridLayout layoutLeft = new GridLayout(10, 1, 2, 2);
        Dimension dim =new Dimension(4*width / 10, height);

        pnlLeft = new JPanel(layoutLeft);
        pnlLeft.setPreferredSize(dim);
        pnlLeft.add(lblNumGuest);
        pnlLeft.add(lblFirstName);
        pnlLeft.add(lblLastName);
        pnlLeft.add(lblAge);

        pnlLeft.add(lblStreet);
        pnlLeft.add(lblCity);
        pnlLeft.add(lblZipCode);
        pnlLeft.add(lblCountry);


        add(pnlLeft, BorderLayout.WEST);
    }

    private void createComponentsOnRightPanel()
    {
        GridLayout layoutRight = new GridLayout(10, 1, 2, 2);
        pnlRight = new JPanel(layoutRight);
        Dimension dim =new Dimension(6*width / 10, height);

        pnlRight.setPreferredSize(dim);


       // Dimension dim = new Dimension(90, 50);
        txtNumGuest = new JLabel("0");
        txtNumGuest.setPreferredSize(dim);

        txtFirstName = new JTextField("First name");
        txtFirstName.setPreferredSize(dim);

        txtLastName = new JTextField("Family name");
        txtLastName.setPreferredSize(dim);

        txtAge = new JTextField("Age");
        txtAge.setPreferredSize(dim);

        txtStreet = new JTextField("Street");
        txtStreet.setPreferredSize(dim);

        txtCity = new JTextField("City");
        txtCity.setPreferredSize(dim);

        txtZipCode = new JTextField("zip code");
        txtZipCode.setPreferredSize(dim);

        pnlRight.add(txtNumGuest);
        pnlRight.add(txtFirstName);
        pnlRight.add(txtLastName);
        pnlRight.add(txtAge);

        pnlRight.add(txtStreet);
        pnlRight.add(txtCity);
        pnlRight.add(txtZipCode);

        add(pnlRight, BorderLayout.CENTER);
    }

    private void createAndInitiateComboBox()
    {
        cmbCountries = new JComboBox(controller.getCountriesItems());
        cmbCountries.setSelectedIndex(0);
        pnlRight.add(cmbCountries);
        cmbCountries.addItemListener(new ItemListener()
        {
            @Override
            public void itemStateChanged(ItemEvent e)
            {
                Object obj = cmbCountries.getSelectedItem();
                int index = cmbCountries.getSelectedIndex();
            }
        });
    }

    public String getFirstNameText()
    {
        return txtFirstName.getText();
    }
    public void setFirstNameText(String text)
    {
        txtFirstName.setText(text);
    }

    public String getLastNameText()
    {
        return txtLastName.getText();
    }
    public void setLastNameText(String text)
    {
        txtLastName.setText(text);
    }

    public String getAgeText()
    {
        return txtAge.getText();
    }
    public void setAgeText(String text)
    {
        txtAge.setText(text);
    }

    public String getStreetText()
    {
        return txtStreet.getText();
    }
    public void setStreetText(String text)
    {
        txtStreet.setText(text);
    }

    public String getCityText()
    {
        return txtCity.getText();
    }
    public void setCityText(String text)
    {
        txtCity.setText(text);
    }

    public String getZipCodeText()
    {
        return txtZipCode.getText();
    }
    public void setZipCodeText(String text)
    {
        txtZipCode.setText(text);
    }

    public void setTxtNumGuest(String text)
    {
        txtNumGuest.setText(text);
    }

    public Object getCountriesItem()
    {
        return cmbCountries.getSelectedItem();
    }
    public void setCountriesItem(Object item) {cmbCountries.setSelectedItem(item);}

}





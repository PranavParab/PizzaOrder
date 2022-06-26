import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;
import java.text.*;
import javax.swing.*;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * This is the Pizza Ordering class
 */
public class PizzaOrder extends Applet implements ActionListener, KeyListener {

	// Constants for storing Prices
	private final double smallPizzaPrice = 150.00,
				   		 regularPizzaPrice = 350.00,
				   		 largePizzaPrice = 700.00,
				   		 
				   		 cheeseToppingPrice = 50.00,
				   		 sausageToppingPrice = 50.00,
				   		 mushroomToppingPrice = 50.00,
				   		 oreganoToppingPrice = 20.00,
				   		 pepperToppingPrice = 10.00,
				   		 chickenToppingPrice = 80.00;

	//Graphical components
	Panel topPanel,
		  centerPanel,
		  centerQuantityPanel,
		  centerSizePanel,
		  centerToppingPanel,
		  centerPricePanel,
		  bottomPanel;

	Label titleLabel,
		  quantityLabel,
		  amountLabel,
		  sizeLabel,
		  toppingLabel,
		  priceLabel,
		  priceDisplayLabel;

	Button orderButton;

	TextField pizzaText,
			  priceText;

	ButtonGroup sizeBGroup;

	JRadioButton smallJRadio,
				 regularJRadio,
				 largeJRadio;

	JCheckBox cheeseJCheck,
			  sausageJCheck,
			  mushroomJCheck,
			  oreganoJCheck,
			  pepperJCheck,
			  chickenJCheck;

	/**
	 * Initialize variable and components
	 */
	public void init() {

		// Setting applet 
		setBackground(new Color(205,205,205));
		setLayout(new BorderLayout(0,0));

		// Initializing Panels
		topPanel = new Panel();
		topPanel.setBackground(new Color(249,50,19));
		topPanel.setLayout(new FlowLayout());

		centerPanel = new Panel();

		centerQuantityPanel = new Panel();
		centerQuantityPanel.setLayout(new FlowLayout());

		centerSizePanel = new Panel();
		centerSizePanel.setLayout(new FlowLayout());

		centerToppingPanel = new Panel();
		centerToppingPanel.setLayout(new GridLayout(2,3,0,0));

		centerPricePanel = new Panel();
		centerPricePanel.setLayout(new FlowLayout());

		bottomPanel = new Panel();
		bottomPanel.setLayout(new FlowLayout());

		// Initializing Labels
		titleLabel = new Label("Who said Pizza?!");
		titleLabel.setBackground(new Color(249,50,19));
		titleLabel.setForeground(new Color(255,255,255));
		titleLabel.setFont(new Font("helvetica",Font.BOLD,50));

		quantityLabel = new Label("Enter the number of pizza(s) you wish to order : ");
		quantityLabel.setForeground(new Color(0,0,205));
		quantityLabel.setFont(new Font("Arial",Font.BOLD,14));

		amountLabel = new Label("Quantity: ");
		amountLabel.setForeground(new Color(0,0,0));
		amountLabel.setFont(new Font("Arial",Font.BOLD,14));

		sizeLabel = new Label("Select the size of the pizza(s) above to be made in :");
		sizeLabel.setForeground(new Color(0,0,205));
		sizeLabel.setFont(new Font("Arial",Font.BOLD,14));

		toppingLabel = new Label("Select one or more choices of the toppings below : ");
		toppingLabel.setForeground(new Color(0,0,205));
		toppingLabel.setFont(new Font("Arial",Font.BOLD,14));

		priceLabel = new Label("Total price of your order : ");
		priceLabel.setForeground(new Color(200,0,0));
		priceLabel.setFont(new Font("Arial",Font.BOLD,14));

		priceDisplayLabel = new Label("Rs. 0.00       ");
		priceDisplayLabel.setForeground(new Color(235,0,0));
		priceDisplayLabel.setFont(new Font("Arial",Font.BOLD,34));

		// Initializing Textfields
		pizzaText = new TextField("0",1);
		pizzaText.setFont(new Font("Arial",Font.BOLD,14));

		// Initializing JRadio buttons
		smallJRadio = new JRadioButton("Small", true);
		regularJRadio = new JRadioButton("Regular", false);
		largeJRadio = new JRadioButton("Large", false);

		// Grouping radio buttons
		sizeBGroup = new ButtonGroup();
		sizeBGroup.add(smallJRadio);
		sizeBGroup.add(regularJRadio);
		sizeBGroup.add(largeJRadio);

		// Initializing JCheckboxes
		cheeseJCheck = new JCheckBox("Extra Cheese",false);
		sausageJCheck = new JCheckBox("Sausage Slices",false);
		mushroomJCheck = new JCheckBox("Button Mushroom",false);
		oreganoJCheck = new JCheckBox("Oregano",false);
		pepperJCheck = new JCheckBox("Green Pepper",false);
		chickenJCheck = new JCheckBox("Chicken Nugget",false);

		// Initializing Buttons
		orderButton = new Button("Order Now");
		orderButton.setFont(new Font("Arial",Font.BOLD,16));

		// Registering listeners to components
		smallJRadio.addActionListener(this);
		regularJRadio.addActionListener(this);
		largeJRadio.addActionListener(this);

		cheeseJCheck.addActionListener(this);
		sausageJCheck.addActionListener(this);
		mushroomJCheck.addActionListener(this);
		oreganoJCheck.addActionListener(this);
		pepperJCheck.addActionListener(this);
		chickenJCheck.addActionListener(this);

		pizzaText.addKeyListener(this);

		orderButton.addActionListener(this);
	}

	/**
	 * Start of applet
	 */
	public void start() {

		// Top Panel
		topPanel.add(titleLabel);

		// Nested Center Panels
		centerQuantityPanel.add(amountLabel);
		centerQuantityPanel.add(pizzaText);

		centerSizePanel.add(smallJRadio);
		centerSizePanel.add(regularJRadio);
		centerSizePanel.add(largeJRadio);

		centerToppingPanel.add(cheeseJCheck);
		centerToppingPanel.add(sausageJCheck);
		centerToppingPanel.add(mushroomJCheck);
		centerToppingPanel.add(oreganoJCheck);
		centerToppingPanel.add(pepperJCheck);
		centerToppingPanel.add(chickenJCheck);

		centerPricePanel.add(priceLabel);
		centerPricePanel.add(priceDisplayLabel);

		// Center Panel
		centerPanel.add(quantityLabel);
		centerPanel.add(centerQuantityPanel);
		centerPanel.add(sizeLabel);
		centerPanel.add(centerSizePanel);
		centerPanel.add(toppingLabel);
		centerPanel.add(centerToppingPanel);
		centerPanel.add(centerPricePanel);

		// Bottom Panel
		bottomPanel.add(orderButton);

		// Main Applet Window
		add(topPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);

		pizzaText.selectAll();
	}

	// Unused interface methods
	public void keyTyped(KeyEvent e) { }
	public void keyPressed(KeyEvent e) { }

	/**
	 * Perform validation check on user text input
	 */
	public void keyReleased(KeyEvent e) { 

		// Trap all non-valid numbers
		try {
			Integer.parseInt(pizzaText.getText());
		}
		catch (NumberFormatException fe) {
			pizzaText.setText("0");
		}

		refreshPrice();
	}	

	/**
	 * Traps user input
	 */
	public void actionPerformed(ActionEvent e) {

		// If order now button is pressed
		if (e.getSource() == orderButton) {
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql//localhost:3306/pizza","root"," ");
			System.out.println("Connected!");
			String query = "insert into table1 values(?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, pizzaText.getText());
			statement.setString(2, smallJRadio.getText());
			statement.setString(3, regularJRadio.getText());
			statement.setString(4, largeJRadio.getText());
			statement.setString(5, cheeseJCheck.getText());
			statement.setString(6, sausageJCheck.getText());
			statement.setString(7, mushroomJCheck.getText());
			statement.setString(8, oreganoJCheck.getText());
			statement.setString(9, pepperJCheck.getText());
			statement.setString(10, chickenJCheck.getText());
			statement.setString(11, priceDisplayLabel.getText());
			
			
			
			
			//return conn;
		}
		catch(Exception a)
		{
			JOptionPane.showMessageDialog(null,a);
			//return null;
		}
		
		
			JOptionPane.showMessageDialog(this,
				"Thank you for your " + 
				priceDisplayLabel.getText() + "  payment." +
				"\n\nYour pizza will be delivered to you in 3 months !" + 
				"\nWhy dont you order another pizza while waiting ?",
				"Orders Confirmed",
				JOptionPane.INFORMATION_MESSAGE);
		}

		refreshPrice();
	}

	/**
	 * Method to calculate and refresh the price display
	 */
	private void refreshPrice() {

		// Local variables used to accumulate total price
		double price = 0;
		int pizzaAmount = Integer.parseInt(pizzaText.getText());

		// Initializing Number Format		
		NumberFormat numberForm = NumberFormat.getNumberInstance();
		DecimalFormat moneyForm = (DecimalFormat)numberForm;
		moneyForm.applyPattern("0.00");

		// Pizza size prices
		if (smallJRadio.isSelected()) {
			price+= smallPizzaPrice * pizzaAmount;
		}

		if (regularJRadio.isSelected()) {
			price+= regularPizzaPrice * pizzaAmount;
		}

		if (largeJRadio.isSelected()) {
			price+= largePizzaPrice * pizzaAmount;
		}

		// Pizza topping prices
		if (cheeseJCheck.isSelected()) {
			price+= cheeseToppingPrice * pizzaAmount;
		}

		if (sausageJCheck.isSelected()) {
			price+= sausageToppingPrice * pizzaAmount;
		}

		if (mushroomJCheck.isSelected()) {
			price+= mushroomToppingPrice * pizzaAmount;
		}

		if (oreganoJCheck.isSelected()) {
			price+= oreganoToppingPrice * pizzaAmount;
		}

		if (pepperJCheck.isSelected()) {
			price+= pepperToppingPrice * pizzaAmount;
		}

		if (chickenJCheck.isSelected()) {
			price+= chickenToppingPrice * pizzaAmount;
		}

		priceDisplayLabel.setText("Rs."+moneyForm.format(price));
	}
}


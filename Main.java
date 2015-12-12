import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Main {
    private static ArrayList<Transporter> transporters;
    
    
    
    /**
     * Creates the UI, all components are defined in the natural reading order
     * (left to right, top to bottom) unless otherwise specified.
     */
    private static void create() {
	// list of locations to choose from is generated based on the ArrayList
	String locations[] = new String[transporters.size()];
	for (int i=0; i<transporters.size(); i++)
	    locations[i] = transporters.get(i).toStringShort();
	
	JFrame frame;
	frame = new JFrame();
	frame.setBounds(100, 100, 500, 300);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	GridBagLayout gridBagLayout = new GridBagLayout();
	gridBagLayout.columnWidths = new int[]{0, 0, 0};
	gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
	gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
	gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0};
	frame.getContentPane().setLayout(gridBagLayout);

	JLabel title = new JLabel("Transport Portal Route Planner");
	GridBagConstraints gbc_title = new GridBagConstraints();
	gbc_title.insets = new Insets(5, 5, 30, 5);
	gbc_title.gridwidth = 3;
	gbc_title.gridx = 0;
	gbc_title.gridy = 0;
	frame.getContentPane().add(title, gbc_title);

	JLabel opt1_lbl = new JLabel("Start Point:");
	GridBagConstraints gbc_opt1_lbl = new GridBagConstraints();
	gbc_opt1_lbl.insets = new Insets(10, 10, 10, 10);
	gbc_opt1_lbl.gridx = 0;
	gbc_opt1_lbl.gridy = 1;
	frame.getContentPane().add(opt1_lbl, gbc_opt1_lbl);

	JLabel opt2_lbl = new JLabel("Destination:");
	GridBagConstraints gbc_opt2_lbl = new GridBagConstraints();
	gbc_opt2_lbl.insets = new Insets(10, 10, 10, 10);
	gbc_opt2_lbl.gridx = 2;
	gbc_opt2_lbl.gridy = 1;
	frame.getContentPane().add(opt2_lbl, gbc_opt2_lbl);

	final JComboBox<String> start = new JComboBox<String>(locations);
	GridBagConstraints gbc_start = new GridBagConstraints();
	gbc_start.insets = new Insets(10, 10, 10, 10);
	gbc_start.fill = GridBagConstraints.HORIZONTAL;
	gbc_start.gridx = 0;
	gbc_start.gridy = 2;
	frame.getContentPane().add(start, gbc_start);

	JLabel lbl = new JLabel("to");
	GridBagConstraints gbc_lbl = new GridBagConstraints();
	gbc_lbl.insets = new Insets(10, 10, 10, 10);
	gbc_lbl.gridx = 1;
	gbc_lbl.gridy = 2;
	frame.getContentPane().add(lbl, gbc_lbl);

	final JComboBox<String> destination = new JComboBox<String>(locations);
	GridBagConstraints gbc_destination = new GridBagConstraints();
	gbc_destination.insets = new Insets(10, 10, 10, 10);
	gbc_destination.fill = GridBagConstraints.HORIZONTAL;
	gbc_destination.gridx = 2;
	gbc_destination.gridy = 2;
	frame.getContentPane().add(destination, gbc_destination);

	JLabel type_lbl = new JLabel("Route Type:");
	GridBagConstraints gbc_type_lbl = new GridBagConstraints();
	gbc_type_lbl.insets = new Insets(10, 10, 10, 10);
	gbc_type_lbl.gridx = 0;
	gbc_type_lbl.gridy = 3;
	frame.getContentPane().add(type_lbl, gbc_type_lbl);

	String options[] = {"cheapest", "quickest"}; 
	JComboBox<String> type = new JComboBox<String>(options);
	GridBagConstraints gbc_type = new GridBagConstraints();
	gbc_type.insets = new Insets(10, 10, 10, 10);
	gbc_type.fill = GridBagConstraints.HORIZONTAL;
	gbc_type.gridx = 1;
	gbc_type.gridy = 3;
	frame.getContentPane().add(type, gbc_type);

	// label for the results, text will be set when the user presses the submit button
	final JLabel results_lbl = new JLabel("");  //TODO: label no good, use smth else
	GridBagConstraints gbc_results_lbl = new GridBagConstraints();
	gbc_results_lbl.insets = new Insets(10, 10, 10, 10);
	gbc_results_lbl.gridwidth = 3;
	gbc_results_lbl.gridx = 0;
	gbc_results_lbl.gridy = 4;
	frame.getContentPane().add(results_lbl, gbc_results_lbl);
	
	// needs to come after all the parts is uses are declared
	JButton submit = new JButton("GO"); 
	submit.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		String path = "";
		int a = start.getSelectedIndex();
		int b = destination.getSelectedIndex();
		path += transporters.get(a).goesTo().get(1).get(Transporter.TAG_PLACE) + "\n";
		path += transporters.get(b).goesTo().get(0).get(Transporter.TAG_PLACE) + "\n";
		results_lbl.setText(path);
		// TODO: replace contents with pathGenerator method
	    }
	});
	GridBagConstraints gbc_submit = new GridBagConstraints();
	gbc_submit.insets = new Insets(10, 10, 10, 10);
	gbc_submit.gridx = 2;
	gbc_submit.gridy = 3;
	frame.getContentPane().add(submit, gbc_submit);

	// show the whole thing
	frame.setVisible(true);
    }

    

    /**
     * Starts the program. The list of transporters is loaded and then the Main 
     * thread passes control immediately to the Swing event thread.
     * 
     * @param args not used
     */
    public static void main(String[] args) {
	TransporterParser t = new TransporterParser();
	transporters = t.parseFromFile("src/transporters.json");
	//TODO: call method to sort transporters by alphabetical order
	//TODO: rename TransporterParser to TransporterUtils
	
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		create();
	    }
	});
    }

}

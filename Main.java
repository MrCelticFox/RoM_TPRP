import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;




public class Main
{

    /**
     * Cell size in pixels.
     */
    public static final int SIZE = 32; 

    /**
     * Number of unique icons.
     */
    public static final int TYPES = 6;

    /**
     * Game mode: 0 for Mine, 1 for Farm
     */
    public static final int MODE = 0;

    /**
     * Font size for displaying score.
     */
    public static final int SCORE_FONT_SIZE = 20; 


    //-------------------------------------------------------------------------------------------


    /**
     * Helper method for instantiating the components.  This
     * method should be executed in the context of the Swing
     * event thread only.
     */
    private static void create()
    {
	JFrame frame;
	frame = new JFrame();
	frame.setBounds(100, 100, 450, 300);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	GridBagLayout gridBagLayout = new GridBagLayout();
	gridBagLayout.columnWidths = new int[]{0, 0, 0};
	gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
	gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
	gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0};
	frame.getContentPane().setLayout(gridBagLayout);

	JLabel title = new JLabel("Transport Portal Route Planner");
	//lblTransport.setText(text);
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

	String locations[] = {"one", "two", "3"}; //define elsewhere
	final JComboBox start = new JComboBox(locations);
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

	final JComboBox destination = new JComboBox(locations);
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
	JComboBox type = new JComboBox(options);
	GridBagConstraints gbc_type = new GridBagConstraints();
	gbc_type.insets = new Insets(10, 10, 10, 10);
	gbc_type.fill = GridBagConstraints.HORIZONTAL;
	gbc_type.gridx = 1;
	gbc_type.gridy = 3;
	frame.getContentPane().add(type, gbc_type);

	final JLabel results_lbl = new JLabel(""); //text set later
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
		path = path + start.getItemAt(start.getSelectedIndex()) + "\n";
		path = path + destination.getItemAt(destination.getSelectedIndex()) + "\n";
		results_lbl.setText(path);
	    }
	});
	GridBagConstraints gbc_submit = new GridBagConstraints();
	gbc_submit.insets = new Insets(10, 10, 10, 10);
	gbc_submit.gridx = 2;
	gbc_submit.gridy = 3;
	frame.getContentPane().add(submit, gbc_submit);

	// rock and roll...
	frame.setVisible(true);
    }


    //-------------------------------------------------------------------------------------------


    /**
     * Entry point.  Main thread passed control immediately
     * to the Swing event thread.
     * @param args not used
     */
    public static void main(String[] args)
    {
	Runnable r = new Runnable()
	{
	    public void run()
	    {
		create();
	    }
	};
	SwingUtilities.invokeLater(r);
    }

}



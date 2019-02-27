/*
 * The MIT License
 *
 * Copyright 2018.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package finalmodel;

// needed imports
// swing for ui components
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

// various imports for style
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Toolkit;

// lists with type specifier
import java.util.List;
import java.util.ArrayList;

// track button actions
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModelUI extends JFrame {

    // the JLabel labels for the user inputs
    protected JLabel[] variableLabels;
    
    // the JTextField entry boxes for continuous data
    // In Order of Index in Array:
    // 0. Age
    // 1. AIS Severity
    // 2. Glasgow Coma Score
    // 3. Injury Severity Score
    // 4. Systolic Blood Pressure
    // 5. Pulse Rate
    // 6. Body Temperature
    // 7. Blood Oxygen Saturation
    private static final int AGE_INDEX = 0;
    private static final int AIS_SEV_INDEX = 1;
    private static final int GCS_INDEX = 2;
    private static final int ISS_INDEX = 3;
    private static final int SBP_INDEX = 4;
    private static final int PULSE_INDEX = 5;
    private static final int BODY_TEMP_INDEX = 6;
    private static final int OXY_SAT_INDEX = 7;
    protected JTextField[] contVarInputs;
    
    // the JComboBox scroll-through options for categorical variables
    // Lists are used here instead of arrays to prevent raw-variable typing
    // In Order of Index in List:
    // 0. Gender
    // 1. Supplemental Oxygen
    // 2. Drug Use
    // 3. Race
    // 4. Injury Type
    private static final int GENDER_INDEX = 0;
    private static final int SUPP_OXY_INDEX = 1;
    private static final int DRUG_USE_INDEX = 2;
    private static final int RACE_INDEX = 3;
    private static final int INJ_TYPE_INDEX = 4;
    protected List<JComboBox<String>> disVarInputs = new ArrayList<>();
        
    // calculateProb calls functions to run user inputs through model
    // and output result to screen
    protected JButton calculateProb = new JButton("Enter");
    
    // clearEntries resets all choices to index 0 and sets all text fields
    // to "" (empty string). it furthermore sets all saved values back to their
    // default values i.e. 0 for continuous variables and the prespecified
    // defaults for categorical variables e.g. female for gender
    protected JButton clearEntries = new JButton("Reset");
    
    // the JTextField which displays the model output
    protected JTextField modelOutput = new JTextField("");
    
    // the Header displayed at the top of the caluclator
    // (right above user inputs)
    protected JLabel modelTitle = new JLabel();
        
    protected static TBIModel tbiModel;
    
    private static final long serialVersionUID = 133212L;

    // *************************************************************************
    // Constructor
    // *************************************************************************

    public ModelUI (String windowTitle) {
        
        // *********************************************************************
        // Call to Super
        // *********************************************************************
        
        super(windowTitle);
        
        // *********************************************************************
        // Initialize Variables
        // *********************************************************************
        
        tbiModel = new TBIModel();
                
        variableLabels = new JLabel[tbiModel.getNumVariables()];
        
        contVarInputs = new JTextField[tbiModel.getNumContVariables()];
        
        // *********************************************************************
        // Various UI Dimension Definitions
        // *********************************************************************

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        
        Dimension windowSize = new Dimension(dim.width * 2 / 5,
                dim.height * 9 / 10);
        
        Dimension sideBarSize =
            new Dimension(windowSize.width * 45 / 100,
                    windowSize.height * 2 / 3);
        
        Dimension labelSize = 
            new Dimension(sideBarSize.width * 9 / 10,
                    sideBarSize.height / tbiModel.getNumVariables());         
        
        Dimension inputSize = 
                 new Dimension(sideBarSize.width * 9 / 10,
                         sideBarSize.height /
                                 tbiModel.getNumVariables() * 9 / 10);
        
        Dimension insAreaSize = 
                new Dimension(2 * sideBarSize.width,
                        windowSize.height / 10);

        Dimension buttonBarSize = 
                new Dimension(2 * sideBarSize.width,
                        windowSize.height / 10);

        Dimension insTxtAreaSize = 
                new Dimension(insAreaSize.width * 90 / 100, 
                              insAreaSize.height * 90 / 100);

        Dimension bttnSize = 
                new Dimension(buttonBarSize.width * 23 / 100,
                        buttonBarSize.height * 80 / 100);
        
        Dimension outputTextSize =
                new Dimension(bttnSize.width * 2, bttnSize.height);
        
        // *********************************************************************
        // Various UI Color Definitions
        // *********************************************************************

        Color lightGray = new Color(247, 248, 249);
        
        Color darkGray = new Color(204, 206, 209);
        
        Color backgroundBlue = new Color(154, 176, 211);
        
        // *********************************************************************
        // Various UI Font Definitions
        // *********************************************************************

        Font labelFont = new Font("Serif", Font.BOLD, 22);
    
        Font bttnFont = new Font("Serif", Font.BOLD, 15);
    
        Font titleFont = new Font("Serif", Font.BOLD, 28);
        
        // *********************************************************************
        // Set up JFrame window properties
        // *********************************************************************

        this.setPreferredSize(new Dimension(dim.width/2, dim.height * 9 / 10));
                
        this.pack();
        
        this.setLocation(dim.width/2-this.getSize().width/2,
                dim.height/2-this.getSize().height/2);
        
        this.getContentPane().setBackground(backgroundBlue);

        // *********************************************************************
        // Add listeners to buttons and window
        // *********************************************************************
                    
        this.addWindowListener(new WindowDestroyer());
        
        calculateProb.addActionListener(new ActionHandler());
        
        clearEntries.addActionListener(new ActionHandler());
        
        // *********************************************************************
        // Add layout manager to main JFrame
        // *********************************************************************
        
        // GridBag layout is used for the main JFrame along with all JPanels
        this.setLayout(new BorderLayout());
        
        JPanel mainPanel = new JPanel(new GridBagLayout());
        
        // set background color
        mainPanel.setBackground(backgroundBlue);

        GridBagConstraints mainFrame = new GridBagConstraints();

        // *********************************************************************
        // Set up Panel layouts && add them to mainFrame
        // *********************************************************************
        
        // *********************************************************************
        // Left panel has variable input labels
        // *********************************************************************
        
        // The left side panel which has all JLabels for variable names placed 
        // onto it
        JPanel varNames = new JPanel();
        
        // set left panel properties
        varNames.setLayout(new GridBagLayout());
        varNames.setMinimumSize(sideBarSize);
        varNames.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0,
                Color.BLACK));
        varNames.setPreferredSize(sideBarSize);
        varNames.setBackground(lightGray);
        
        // gridbag constraints for variable labels
        GridBagConstraints varLabelCons = new GridBagConstraints();
        
        // give some spacing to variable labels
        varLabelCons.weighty = 0.25;
        
        // set properties for each variable label
        // add variable input labels to left panel
        for(int i = 0; i < tbiModel.getNumVariables(); i++){
            variableLabels[i] = new JLabel ();
            variableLabels[i].setPreferredSize(labelSize);
            variableLabels[i].setMinimumSize(labelSize);
            variableLabels[i].setFont(labelFont);
            variableLabels[i].setHorizontalAlignment(JLabel.RIGHT);
            // if the text is too big, this will refit to the jlabel
            variableLabels[i] = setTextFit(variableLabels[i],
                    tbiModel.getIthVariableName(i), labelSize);
            varLabelCons.gridx = 0;
            varLabelCons.gridy = i;
            varLabelCons.fill = GridBagConstraints.BOTH;
            varNames.add(variableLabels[i], varLabelCons);
        }

        // set location for left panel in main constraints
        mainFrame.gridx = 0;
        mainFrame.gridy = 1;
        mainFrame.fill = GridBagConstraints.BOTH;
        // add panel to the main panel
        mainPanel.add(varNames, mainFrame);
        
        // *********************************************************************
        // Right panel with user inputs
        // *********************************************************************

        // The right side panel which manages the JTextFields and JComboBoxes
        // for user inputs
        JPanel userInputs = new JPanel();
   
        // right panel properties
        userInputs.setLayout(new GridBagLayout());
        userInputs.setMinimumSize(sideBarSize);
        userInputs.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1,
                Color.BLACK));
        userInputs.setPreferredSize(sideBarSize);
        userInputs.setBackground(lightGray);
        
        // gridbag constraints for continuous variables portion of panel
        GridBagConstraints contVarInputCons = new GridBagConstraints();
        // gridbbag constraints for discete variable portion of panel
        GridBagConstraints disVarInputCons = new GridBagConstraints();
        
        // give vertical spacing to both continous and discrete variable inputs
        contVarInputCons.weighty = 0.25;
        contVarInputCons.fill = GridBagConstraints.BOTH;
        
        disVarInputCons.weighty = 0.25;
        disVarInputCons.fill = GridBagConstraints.BOTH;
        
        // attach discrete variable inputs to the panel
        for(int i = 0; i < tbiModel.getNumDisVariables(); i++){
            disVarInputs.add(i, new JComboBox<>(tbiModel.getIthCatVarOptions(i)));
            disVarInputs.get(i).setPreferredSize(inputSize);
            disVarInputs.get(i).setFont(labelFont);
            disVarInputs.get(i).setMinimumSize(inputSize);
            disVarInputCons.gridx = 0;
            disVarInputCons.gridy = i;
            userInputs.add(disVarInputs.get(i), disVarInputCons);
        }
        
        // attach continous variable inputs to the panel
        for(int i = 0; i < tbiModel.getNumContVariables(); i++){
            contVarInputs[i] = new JTextField("");
            contVarInputs[i].setFont(labelFont);
            contVarInputs[i].setHorizontalAlignment(JTextField.CENTER);
            contVarInputs[i].setPreferredSize(inputSize);
            contVarInputs[i].setMinimumSize(inputSize);
            contVarInputCons.gridx = 0;
            contVarInputCons.gridy = tbiModel.getNumDisVariables() + i;
            userInputs.add(contVarInputs[i], contVarInputCons);
        }
        
        // set location for right panel on the main panel
        mainFrame.gridx = 1;
        mainFrame.gridy = 1;
        // attach right panel to the main panel
        mainPanel.add(userInputs, mainFrame);
       
        // *********************************************************************
        // top title panel 
        // *********************************************************************

        // The Top most panel which house a JLabel for the model description
        JPanel titleArea = new JPanel();

        // set top panel properties
        titleArea.setLayout(new GridBagLayout());
        titleArea.setMinimumSize(insAreaSize);
        titleArea.setBorder(BorderFactory.createLineBorder(Color.black));
        titleArea.setPreferredSize(insAreaSize);
        titleArea.setBackground(darkGray);
        
        // gridbag constraints for top panel
        GridBagConstraints titleAreaCons = new GridBagConstraints();
        
        // jlabel properties for top panel
        modelTitle = new JLabel("Probability of TBI Mortality Calculator");
        modelTitle.setPreferredSize(insTxtAreaSize);
        modelTitle.setMinimumSize(insTxtAreaSize);
        modelTitle.setHorizontalAlignment(JLabel.CENTER);
        modelTitle.setFont(titleFont);
        
        // top panel jlabel location
        titleAreaCons.gridx = 0;
        titleAreaCons.gridy = 0;
        
        // attach jlabel to top panel
        titleArea.add(modelTitle, titleAreaCons);

        // set location for top panel
        mainFrame.gridx = 0;
        mainFrame.gridy = 0;
        // make top panel the length of the right and left panel together
        mainFrame.gridwidth = 2;
        
        // attach top panel to main panel
        mainPanel.add(titleArea, mainFrame);
        
        // *********************************************************************
        // bottom panel with buttons and model output  
        // *********************************************************************
        
        // The bottom most panel which manages the model output JTextField and
        // the user action JButtons
        JPanel actionButtons = new JPanel();

        // bottom panel properties
        actionButtons.setLayout(new GridBagLayout());
        actionButtons.setMinimumSize(buttonBarSize);
        actionButtons.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
                Color.BLACK));
        actionButtons.setPreferredSize(buttonBarSize);
        actionButtons.setBackground(darkGray);
        
        // gridbag constraints for each bottom panel component
        GridBagConstraints actBttnCons = new GridBagConstraints();
        
        // enter button properties
        calculateProb.setPreferredSize(bttnSize);
        calculateProb.setFont(bttnFont);
        calculateProb.setMinimumSize(bttnSize);
        actBttnCons.gridx = 0;
        actBttnCons.gridy = 0;
        // give horizontal spacing
        actBttnCons.weightx = 0.25;
        // attach to bottom panel
        actionButtons.add(calculateProb, actBttnCons);
        
        // reset button properties
        clearEntries.setPreferredSize(bttnSize);
        clearEntries.setFont(bttnFont);
        clearEntries.setMinimumSize(bttnSize);
        actBttnCons.gridx = 3;
        actBttnCons.gridy = 0;
        // give horizontal spacing
        actBttnCons.weightx = 0.25;
        // attach to bottom panel
        actionButtons.add(clearEntries, actBttnCons);
        
        // output text properties
        modelOutput.setPreferredSize(outputTextSize);
        modelOutput.setMinimumSize(outputTextSize);
        modelOutput.setFont(labelFont);
        modelOutput.setHorizontalAlignment(JTextField.CENTER);
        // do not allow user changes to the output
        modelOutput.setEditable(false);
        // set location on bottom panel
        actBttnCons.gridx = 1;
        actBttnCons.gridy = 0;
        //actBttnCons.fill = GridBagConstraints.BOTH;
        actBttnCons.gridwidth = 2;
        // attach to the bottom panel
        actionButtons.add(modelOutput, actBttnCons);
        
        // set bottom panel location on main panel
        mainFrame.gridx = 0;
        mainFrame.gridy = 2;
        mainFrame.gridwidth = 2;
        
        // attach bottom panel to main panel
        mainPanel.add(actionButtons, mainFrame);

        // create scrollbar for window to account for small windows
        JScrollPane scrollPane = new JScrollPane();
        
        // scrollbar properties
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.
                HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.
                VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setEnabled(true);
        scrollPane.setWheelScrollingEnabled(true);
        scrollPane.setViewportView(mainPanel);
        
        // add scroll bar to main panel
        this.add(scrollPane, BorderLayout.CENTER);
        this.pack();
        
        // set window to visible
        this.setVisible(true);
        
    }
    
    private void getValues() throws NumberFormatException, IllegalArgumentException {
                
        tbiModel.setAge(Double.parseDouble(contVarInputs[AGE_INDEX].getText()));
        tbiModel.setAISSev(Integer.parseInt(contVarInputs[AIS_SEV_INDEX].getText()));
        tbiModel.setGCS(Integer.parseInt(contVarInputs[GCS_INDEX].getText()));
        tbiModel.setISS(Integer.parseInt(contVarInputs[ISS_INDEX].getText()));
        tbiModel.setSBP(Double.parseDouble(contVarInputs[SBP_INDEX].getText()));
        tbiModel.setPulseRate(Double.parseDouble(contVarInputs[PULSE_INDEX].getText()));
        tbiModel.setBodyTemp(Double.parseDouble(contVarInputs[BODY_TEMP_INDEX].getText()));
        tbiModel.setOxySat(Double.parseDouble(contVarInputs[OXY_SAT_INDEX].getText()));
        tbiModel.setGender((String) disVarInputs.get(GENDER_INDEX).getSelectedItem());
        tbiModel.setSupplementalOxy((String) disVarInputs.get(SUPP_OXY_INDEX).getSelectedItem());
        tbiModel.setDrugUse((String) disVarInputs.get(DRUG_USE_INDEX).getSelectedItem());
        tbiModel.setRace((String) disVarInputs.get(RACE_INDEX).getSelectedItem());
        tbiModel.setInjuryType((String) disVarInputs.get(INJ_TYPE_INDEX).getSelectedItem());
            
    }    
    
    private class ActionHandler implements ActionListener {
    
        @Override
        public void actionPerformed(ActionEvent e) {
            
            if (e.getSource() == calculateProb){
    
                try {
                    
                    getValues();
                    double prediction = tbiModel.getProbabilityOfMortality();
                    modelOutput.setText(Double.toString(prediction * 100) + " %");
                    setTitle(Double.toString(prediction * 100));
                    System.out.println(tbiModel.toString());
                
                } catch (NumberFormatException incorrectInput) {
                    
                    JOptionPane.showMessageDialog(null, "Ensure that all fields"
                            + " are filled and are filled properly. Aside"
                            + " from dropdown menus, there should be no empty"
                            + " or string inputs!" + "\nERROR REASON: " +
                            incorrectInput.getMessage(), "Input Error",
                            JOptionPane.ERROR_MESSAGE);
   
                } catch (IllegalArgumentException invalidInput) {
                    
                    JOptionPane.showMessageDialog(null, "Ensure that all fields"
                            + " have a valid entry!" + "\nERROR REASON: " +
                            invalidInput.getMessage(), "Input Error",
                            JOptionPane.ERROR_MESSAGE);

                }
                    
            } else if (e.getSource() == clearEntries) {
                
                for(int i  = 0; i < tbiModel.getNumDisVariables(); i++){
                    disVarInputs.get(0).setSelectedIndex(0);
                }
                
                for(int i = 0; i < tbiModel.getNumContVariables(); i++){
                    contVarInputs[i].setText("");
                }
                
                setTitle(".....");
                modelOutput.setText("");
                
            }
        }
    }
    
    private JLabel setTextFit(JLabel label, String text, Dimension labelDimensions) {
        
        Font originalFont = (Font)label.getClientProperty("originalfont"); // Get the original Font from client properties
        if (originalFont == null) { // First time we call it: add it
            originalFont = label.getFont();
            label.putClientProperty("originalfont", originalFont);
        }

        int stringWidth = label.getFontMetrics(originalFont).stringWidth(text);
        int componentWidth = labelDimensions.width;

        if (stringWidth > componentWidth) { // Resize only if needed
            // Find out how much the font can shrink in width.
            double widthRatio = (double)componentWidth / (double)stringWidth;

            int newFontSize = (int)Math.floor(originalFont.getSize() * widthRatio); // Keep the minimum size

            // Set the label's font size to the newly determined size.
            label.setFont(new Font(originalFont.getName(), originalFont.getStyle(), newFontSize));
        } else
            label.setFont(originalFont); // Text fits, do not change font size

        label.setText(text);
        return (label);
    }
    
    private class WindowDestroyer implements WindowListener {
    
        @Override
        public void windowClosing (WindowEvent we) {
            System.out.println("Ending Session ---");
            dispose();
        }
        
        @Override
        public void windowDeactivated (WindowEvent we) {}

        @Override
        public void windowOpened(WindowEvent we) {}

        @Override
        public void windowClosed(WindowEvent we) {
            System.out.println("Window successfully closed.");
            System.exit(0);
        }

        @Override
        public void windowIconified(WindowEvent we) {}

        @Override
        public void windowDeiconified(WindowEvent we) {}

        @Override
        public void windowActivated(WindowEvent we) {}
        
    }
    
}

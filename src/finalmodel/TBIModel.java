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

import java.util.Arrays;

/**
 *
 * @author okama
 */
public class TBIModel {
    
    // total number of variables
    private static final int NUM_VARIABLES = 13;
    // number of continuous variables
    private static final int NUM_CONT_VARIABLES = 8;
    // number of discrete variables
    private static final int NUM_DIS_VARIABLES = 5;
    
    // model specifications i.e. coefficient values
    private static final double INTERCEPT = 12.7459826; //intercept value
    private static final double AGE_COEF = -0.0414436; //age
    private static final double AIS_SEVERITY_COEF = 0.4008520; //ais head severity
    private static final double GENDER_COEF = 0.1857815; //gender
    private static final double DRUG_USE_COEF = 0.5589011; //drug use indicator
    private static final double ISS_COEF = 0.1006756; //injury severity score
    private static final double SBP_COEF = -0.0135510; //systolic blood pressure
    private static final double PULSE_COEF = -0.0018600; //pulse rate
    private static final double OXY_SAT_COEF = -0.0272553; //oxygen saturation
    private static final double BODY_TEMP_COEF = -0.2532920; //body temp (celsius)
    private static final double SUPP_OXY_COEF = 0.4832936; //supplemental oxy ind.
    private static final double GCS_COEF = -0.3682123; //glasgow coma score
    private static final double WHITE_COEF = 0.3658753; //white race
    private static final double AMER_IND_COEF = 0.4280905; //american indian
    private static final double ASIAN_COEF = -0.3194037; //asian
    private static final double PAC_ISLNDR_COEF = 0.2007908; //hawaiian
    private static final double OTHER_RACE_COEF = 0.1966492; //other race
    private static final double OTHER_INJ_COEF = 1.0684514; //other injury type
    private static final double PEN_INJ_COEF = 2.3975298; //penetrating
    
    // model variable values -- default values
    // value of age entered into model
    private double ageVal = 0;
    // value of gcs entered into model
    private int gcsVal = 0;
    // value of iss entered into model
    private int issVal = 0;
    // value of ais severity entered into model
    private int aisSevVal = 0;
    // value of race entered into model
    private String raceVal = "African American";
    // race indicator variables for categorical variables
    // african american is default
    private int whRaceInd = 0; //white
    private int aiRaceInd = 0; //american indian
    private int othRaceInd = 0; //other race
    private int haRaceInd = 0; //hawaiian-pacific islander
    private int asRaceInd = 0; //asian
    // value of gender entered into model
    // female is default
    private String genderVal = "Female";
    // gender indicator variable for categorical variable
    private int maGenInd = 0; //male = 1, female = 0
    // value of drug use entered into model
    private String drugVal = "No"; 
    // drug use indicator for categorical variable
    private int drugInd = 0; //yes = 1, no = 0
    // value for supplemental oxygen entered into model
    private String suppOxyVal = "No";
    // supplemental oxygen indicator
    private int suppOxyValInd = 0; //yes = 1, no = 0
    // injury tyoe entered into model
    private String injTypeVal = "Blunt";
    // injury type indicators for categorical variable
    // blunt is the dafault
    private int peInjInd = 0; //penetrating
    private int othInjInd = 0; //other injury type
    // value for systolic blood pressure entered into model
    private double sbpVal = 0;
    // value for heart pulse rate entered into model
    private double pulVal = 0;
    //value for body temperature entered into model
    private double tempVal = 0;
    // value for blood oxygen satruation entered into model
    private double oxySatVal = 0;
    
    // the options that each categorical variable can take on
    private static final String[][] CAT_OPTION_LISTS = {
        {"Female", "Male"}, //gender
        {"No", "Yes"}, //supplemental oxygen
        {"No", "Yes"}, //drug use
        {"African American", "White", "American Indian", "Asian",
            "Pacific Islander", "Other"}, //race
        {"Blunt", "Penetrating", "Other"} //injury type
    };
    
    private static final int RACE_INDEX = 3;
    private static final int GENDER_INDEX = 0;
    private static final int SUPP_OXY_INDEX = 1;
    private static final int DRUG_USE_INDEX = 2;
    private static final int INJ_TYPE_INDEX = 4;
    
    // linear function value entered into final model link function
    private double link = 0;
    
    // predicted probability of mortality
    // holds the final calculation
    private double probOfMort = 0;
    
    // variable names used to construct labels for their entry locations
    // on the calculator UI
    private static final String[] VARIABLE_NAMES = {
        "Gender:",
        "Supplemental Oxygen:",
        "Drug Use:",
        "Race:",
        "Type of Injury:",
        "Age:",
        "AIS Severity:",
        "Glasgow Coma Score:",
        "Injury Severity Score:",
        "Systolic Blood Pressure:",
        "Pulse Rate:",
        "Body Temperature:",
        "Blood Oxygen Saturation:"
    };
    
    /**
     * The constructor for the TBI Model class
     */
    public TBIModel () {
    
    }
    
    /**
     * Accessor method for number of variables
     * @return NUM_VARIABLES the total number of variables in the model
     */
    public int getNumVariables () {
        return (NUM_VARIABLES);
    }
    
    /**
     * Accessor method for number of continuous variables
     * @return NUM_CONT_VARIABLES the number of continuous variables in the
     * model 
     */
    public int getNumContVariables () {
        return (NUM_CONT_VARIABLES);
    }
    
    /**
     * Accessor method for number of discrete variables
     * 
     * @return NUM_DIS_VARIABLES the number of discrete/categorical variables 
     * in the model
     */
    public int getNumDisVariables () {
        return (NUM_DIS_VARIABLES);
    }
    
    /**
     * Computes the probability of mortality for a TBI patient
     * 
     * @return probOfMort the probability of mortality for TBI patient
     */
    public double getProbabilityOfMortality () {
        
        link = INTERCEPT + AGE_COEF * ageVal + AIS_SEVERITY_COEF * aisSevVal +
               GCS_COEF * gcsVal + ISS_COEF * issVal + SBP_COEF * sbpVal +
               PULSE_COEF * pulVal + BODY_TEMP_COEF * tempVal + 
               OXY_SAT_COEF * oxySatVal + GENDER_COEF * maGenInd + 
               DRUG_USE_COEF * drugInd + asRaceInd * ASIAN_COEF +
               AMER_IND_COEF * aiRaceInd + whRaceInd * WHITE_COEF +
               othRaceInd * OTHER_RACE_COEF + haRaceInd + PAC_ISLNDR_COEF +
               suppOxyValInd * SUPP_OXY_COEF + othInjInd * OTHER_INJ_COEF +
               PEN_INJ_COEF * peInjInd;
        
        probOfMort = (1) / (1 + Math.exp(-link)); 
        
        return probOfMort;
    }
    
    /**
     * Accessor method for glasgow coma score
     * 
     * @return gcsVal the glasgow coma score of the patient entered into model;
     * 0 by default
     */
    public int getGCS () {
        return (gcsVal);
    }
    
    /**
     * Set method for glasgow coma score
     * 
     * @param newGCSVal the new glasgow coma score for the new patient entered 
     * into the model
     * @throws IllegalArgumentException the glasgow coma score must be between
     * 3 and 15
     */
    public void setGCS (int newGCSVal) throws IllegalArgumentException {
        if (newGCSVal > 15 || newGCSVal < 3) {
            throw new IllegalArgumentException ("Invalid GCS Value"
                    + " (GCS should be between 3 and 15)");
        } else {
            gcsVal = newGCSVal;
        }
    }
    
    /**
     * Accessor method for the injury severity score
     * 
     * @return issVal the injury severity score for the patient entered into the
     * model
     */
    public int getISS () {
        return (issVal);
    }
    
    /**
     * Set method for the injury severity score
     * 
     * @param newISSVal the new injury severity score for the patient entered
     * into the model
     * @throws IllegalArgumentException injury severity scores must be between 3
     * and 75
     */
    public void setISS (int newISSVal) throws IllegalArgumentException {
        if (newISSVal > 75 || newISSVal < 3) {
            throw new IllegalArgumentException ("Invalid ISS Value "
                    + " (ISS should be between 3 and 75)");
        } else {
            issVal = newISSVal;
        } 
    }
    
    /**
     * Accessor method for the abbreviated injury scale severity rating
     * 
     * @return aisSevVal the abbreviated injury scale severity rating for the 
     * patient
     */
    public int getAISSev () {
        return (aisSevVal);
    }
    
    /**
     * Set method for the abbreviated injury scale severity rating
     * 
     * @param newAISSevVal the new abbreviated injury scale for the patient 
     * entered into the model
     * @throws IllegalArgumentException the abbreviated injury severity rating
     * takes a value between 1 and 6
     */
    public void setAISSev (int newAISSevVal) throws IllegalArgumentException {
        if (newAISSevVal > 6 || newAISSevVal < 1) {
            throw new IllegalArgumentException ("Invalid AIS Severity Value"
                    + " (AIS Severity should be between 1 and 6)");
        } else {
            aisSevVal = newAISSevVal;
        }
    }
    
    public double getAge () {
        return (ageVal);
    }
    
    public void setAge (double newAgeVal) throws IllegalArgumentException {
        if (newAgeVal > 14 || newAgeVal < 0) {
            throw new IllegalArgumentException ("Invalid Age. Intended is for"
                    + " pediatric patients. (Age should be between 0 and 14)");
        } else {
            ageVal = newAgeVal;
        }
    }
    
    public double getSBP () {
        return (sbpVal);
    }
    
    public void setSBP (double newSBPVal) throws IllegalArgumentException {
        if (newSBPVal < 0) {
            throw new IllegalArgumentException ("Negative SBP");
        } else {
            sbpVal = newSBPVal;
        }
    }
    
    public double getOxySat () {
        return (oxySatVal);
    }
    
    public void setOxySat (double newOxySatVal)
            throws IllegalArgumentException {
        if (newOxySatVal > 100 || newOxySatVal < 0) {
            throw new IllegalArgumentException ("Invalid Oxygen Saturation Value"
                    + " (Oxygen Saturation should be between 0 and 100)");
        } else {
            oxySatVal = newOxySatVal;
        }
    }
    
    public double getBodyTemp () {
        return (tempVal);
    }
    
    public void setBodyTemp(double newBodyTemp)
            throws IllegalArgumentException {
        if (newBodyTemp < 0) {
            throw new IllegalArgumentException ("Negative Body Temperature.");
        } else {
            tempVal = newBodyTemp;
        }
    }
    
    public double getPulseRate () {
        return (pulVal);
    }
    
    public void setPulseRate (double newPulseRate)
            throws IllegalArgumentException {
        if (newPulseRate < 0) {
            throw new IllegalArgumentException ("Negative Pulse Rate");
        } else {
            pulVal = newPulseRate;
        }
    }
    
    public String getRace () {
        return (raceVal);
    }
    
    public void setRace (String newRace) throws IllegalArgumentException {
        if (!Arrays.asList(CAT_OPTION_LISTS[RACE_INDEX]).contains(newRace)) {
            throw new IllegalArgumentException ("Invalid Race Option (Race"
                    + " should be \"African American\", \"Asian\","
                    + " \"White\", \"American Indian\","
                    + " \"Pacific Islander\", or \"Other\")");
        } else {
            raceVal = newRace;
            this.setRaceIndicators();
        }
    }
    
    public String getGender () {
        return (genderVal);
    }
    
    public void setGender (String newGender) throws IllegalArgumentException {
        if (!Arrays.asList(CAT_OPTION_LISTS[GENDER_INDEX]).contains(newGender)) {
            throw new IllegalArgumentException ("Invalid Gender Option (Gender"
                    + " must be either \"Male\" or \"Female\")");
        } else {
            genderVal = newGender;
            this.setGenderIndicator();
        }
    }
    
    public String getDrugUse () {
        return (drugVal);
    }
    
    public void setDrugUse (String newDrugUse) throws IllegalArgumentException {
        if (!Arrays.asList(CAT_OPTION_LISTS[DRUG_USE_INDEX]).contains(newDrugUse)) {
            throw new IllegalArgumentException ("Invalid Drug Use Option"
                    + " (Drug use must be either \"Yes\" or \"No\")");
        } else {
            drugVal = newDrugUse;
            this.setDrugUseIndicator();
        }
    }
    
    public String getSupplementalOxy () {
        return (suppOxyVal);
    }
    
    public void setSupplementalOxy (String newSupplementalOxy)
            throws IllegalArgumentException {
        if (!Arrays.asList(CAT_OPTION_LISTS[SUPP_OXY_INDEX]).contains(newSupplementalOxy)) {
            throw new IllegalArgumentException ("Invalid Supplemental Oxygen"
                    + " Value (Supplemental Oxygen must be either \"Yes\""
                    + " or \"No\")");
        } else {
            suppOxyVal = newSupplementalOxy;
            this.setSupplementalOxygenIndicator();
        }
    }
    
    public String getInjuryType () {
        return (injTypeVal);
    }
    
    public void setInjuryType (String newInjType)
            throws IllegalArgumentException {
        if (!Arrays.asList(CAT_OPTION_LISTS[INJ_TYPE_INDEX]).contains(newInjType)) {
            throw new IllegalArgumentException ("Invalid Injury Type (Injury"
                    + " type must be either \"Blunt\", \"Penetrating\", or"
                    + " \"Other\")");
        } else {
            injTypeVal = newInjType;
            this.setInjTypeIndicators();
        }
    }
    
    /**
     * Accessor method for the names of each variable in the model.
     * 
     * @param i the index of the desired variable name
     * @return VARIABLE_NAMES[i] the string of the desired variable name
     * @throws IndexOutOfBoundsException the user entered index must be a valid
     * integer between 0 and the total number of variables in the model
     */
    public String getIthVariableName (int i) throws IndexOutOfBoundsException {
        if (i >= VARIABLE_NAMES.length || i < 0) {
            throw new IndexOutOfBoundsException ("Invalid Variable Index");
        } else {
            return (VARIABLE_NAMES[i]);
        } 
    } 

    /**
     * Accessor method for the categorical variable option lists
     * 
     * @param i the index of the desired categorical variable options 
     * @return CAT_OPTIONS_LISTS[i] an array of strings that are valid inputs to
     * their respective categorical variables e.g. {'yes', 'no'} for drug use
     * @throws IndexOutOfBoundsException user entered index must be a within
     * the allottable range of indeces
     */
    public String[] getIthCatVarOptions (int i)
            throws IndexOutOfBoundsException {
        if (i >= CAT_OPTION_LISTS.length || i < 0) {
            throw new IndexOutOfBoundsException ("Invalid Option Index");
        } else {
            return (CAT_OPTION_LISTS[i]);
        }
    }
    
    /**
     * Set method for the race indicator. Takes the user entered string e.g. 
     * 'Asian' and converts into a valid model input
     */
    private void setRaceIndicators () {
        
        switch (raceVal) {
            
            case "African American":
                asRaceInd = 0;
                aiRaceInd = 0;
                whRaceInd = 0;
                othRaceInd = 0;
                haRaceInd = 0;
                break;

            case "White":
                asRaceInd = 0;
                aiRaceInd = 0;
                whRaceInd = 1;
                othRaceInd = 0;
                haRaceInd = 0;
                break;

            case "Asian":
                asRaceInd = 1;
                aiRaceInd = 0;
                whRaceInd = 0;
                othRaceInd = 0;
                haRaceInd = 0;
                break;

            case "American Indian":
                asRaceInd = 0;
                aiRaceInd = 1;
                whRaceInd = 0;
                othRaceInd = 0;
                haRaceInd = 0;
                break; 

            case "Pacific Islander":
                asRaceInd = 0;
                aiRaceInd = 0;
                whRaceInd = 0;
                othRaceInd = 0;
                haRaceInd = 1;
                break;  

            case "Other":
                asRaceInd = 0;
                aiRaceInd = 0;
                whRaceInd = 0;
                othRaceInd = 1;
                haRaceInd = 0;
                break;
                
            default:
                break;
        }
    }
    
    /**
     * Set method for the injury type indicators. Converts the user entered
     * string 'blunt', 'penetrating' or 'other' and changes into a valid model
     * input e.g. 0, 0 for blunt injuries
     */
    private void setInjTypeIndicators () {
        switch (injTypeVal) {

            case "Blunt":
                othInjInd = 0;
                peInjInd = 0;
                break;

            case "Penetrating":
                othInjInd = 0;
                peInjInd = 1; 
                break;

            case "Other":
                othInjInd = 1;
                peInjInd = 0;
                break;

            default:
                break;

        }
    }
    
    /**
     * Set method for the drug use indicator. Takes the user entered string
     * 'yes' or 'no' and converts it into a valid model input 0 or 1
     */
    private void setDrugUseIndicator () {
        switch (drugVal) {

            case "No":
                drugInd = 0;
                break;

            case "Yes":
                drugInd = 1;
                break;

        }
    }
    
    /**
     * Set method for the gender indicator. Takes the user entered string
     * response 'male' or 'female' and converts to valid model input 0 or 1
     */
    private void setGenderIndicator () {
        switch (genderVal) {

            case "Male":
                maGenInd = 1;
                break;

            case "Female":
                maGenInd = 0;
                break;

        }
    }
      
    /**
     * Set method for the supplemental oxygen indicator. Takes the user entered
     * string response 'yes' or 'no' and converts to model input value 1 or 0
     */
    private void setSupplementalOxygenIndicator () {
        switch (suppOxyVal) {

            case "No":
                suppOxyValInd = 0;
                break;

            case "Yes":
                suppOxyValInd = 1;
                break;
        }

    }
    
    /**
     * Converts model inputs and outputs into a clear list to be printed
     * 
     * @return outString the stringified version of the model
     */
    @Override
    public String toString () {
        
        String outString = "Age: " + Double.toString(ageVal) + "\n" +
                "AIS Severity: " + Double.toString(aisSevVal) + "\n" +
                "GCS: " + Double.toString(gcsVal) + "\n" +
                "ISS: " + Double.toString(issVal) + "\n" +
                "SBP: " + Double.toString(sbpVal) + "\n" +
                "Body Temp: " + Double.toString(tempVal) + "\n" +
                "Oxygen Sat: " + Double.toString(oxySatVal) + "\n" +
                "Gender: " + genderVal + "\n" +
                "Drug Use: " + drugVal + "\n" +
                "Race: " + raceVal + "\n" +
                "Supplemental Oxy: " + suppOxyVal + "\n" +
                "Injury Type: " + injTypeVal + "\n" +
                "Link: " + Double.toString(link) + "\n" +
                "Probability of Mortality: " + Double.toString(probOfMort) + "\n" +
                "========================================================";
        
        return (outString);
    }
    
}

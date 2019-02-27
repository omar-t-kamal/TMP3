# TMP3 - TBI Mortality Prediction Model for Pediatric Patients
A clinical application for predicting mortality with pediatric traumatic brain injury patients.

To get started, download a recent java version from https://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html if your computer does not already have it installed.

With an appropriate java version, download "TMP3.jar". If you are trying to run the program on Windows or Mac all you need to do is click the downloaded file as if it were any other program.

In unix environments, run the following command in the directory of the downloaded file:
```bash
java -jar TMP3.jar
```
Once running all you need to do is provide *valid inputs* to each of the variables and hit "Enter". The resulting probability of mortality will be given in the box to the right of the afformentioned button. "Reset" can be used to quickly clear everything.

By *valid inputs" we mean that each value entered for a certain variable can actually be a value for said variable e.g. giving an age of -1 will present the user with an error message. Furthermore, ensure that all fields are filled as not doing so will also result in an error message.

**Example**

Let's say we have a patient with the following characteristics: male, aged 14, no need of supplemental oxygen, under the influence of some sort of drug/medication, white, with a penetrating injury to the head. The vitals read include systolic blood pressure - 120, pulse rate - 70, body temp - 36.5 C, and blood oxygen saturation 100%. Furthermore, other trauma indicators have scores: AIS head severity - 3, Glasgow Coma Score - 7, and Injury Severity Score - 52.

Then a use of TMP3 would look like:

![alt text](https://i.imgur.com/QV33bwJ.png)

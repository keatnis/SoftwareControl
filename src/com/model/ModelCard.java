package com.model;

/**
 *
 * @author keatnis
 */
public class ModelCard {
    String Values,Description;

    public ModelCard(String Values, String Description) {
        this.Values = Values;
        this.Description = Description;
    }

    public String getValues() {
        return Values;
    }

    public void setValues(String Values) {
        this.Values = Values;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }
}

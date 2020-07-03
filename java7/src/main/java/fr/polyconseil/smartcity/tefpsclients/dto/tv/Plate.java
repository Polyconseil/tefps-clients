package fr.polyconseil.smartcity.tefpsclients.dto.tv;

import javax.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Plate {

    /**
     * Immatriculation du véhicule contrôlé
     */
    private String plate;

    /**
     * Pays d'origine de la plaque au format ISO 3166-1 alpha-2
     */
    @Nullable
    private String plateCountry;

    private String pricingCategory;

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getPlateCountry() {
        return plateCountry;
    }

    public void setPlateCountry(String plateCountry) {
        this.plateCountry = plateCountry;
    }

    public String getPricingCategory() {
        return pricingCategory;
    }

    public void setPricingCategory(String pricingCategory) {
        this.pricingCategory = pricingCategory;
    }
}
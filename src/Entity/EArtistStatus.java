/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 * EArtistSTatus - Artist Status Types
 *
 * @author Administrator
 */
public enum EArtistStatus {

    Active, Inactive;

    public static EArtistStatus getStatus(String str) {
        Integer i = Integer.parseInt(str);
        EArtistStatus toReturn = EArtistStatus.Active;
        if (i == 2) {
            toReturn = EArtistStatus.Inactive;
        }

        return toReturn;

    }

    /**
     * This class returns a toString of a given EAuth object
     *
     * @return
     */
    @Override
    public String toString() {
        String toReturn = "";
        switch (this) {
            case Active:
                toReturn = "Active";
                break;
            case Inactive:
                toReturn = "Inactive";
                break;

            default:
                toReturn = "Unknown Type";
                break;
        }
        return toReturn;
    }
}

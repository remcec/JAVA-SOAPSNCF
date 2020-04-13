package calcul;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService()
public class CalculDistance {

    @WebMethod
    public String calcDistance (double latitudeA, double longitudeA, double latitudeB, double longitudeB) {

        // On passe les valeurs de latitude et longitude en radian pour la formule plus tard.
        double laA = Math.toRadians(latitudeA);
        double loA = Math.toRadians(longitudeA);
        double laB = Math.toRadians(latitudeB);
        double loB = Math.toRadians(longitudeB);


        // https://geodesie.ign.fr/contenu/fichiers/Distance_longitude_latitude.pdf
        //Calcul de dlambda
        double dLongitude = (loB - loA);

        //Calcul de S A_B
        double part1SAB = (Math.sin(laA)*Math.sin(laB));
        double part2SAB = (Math.cos(laA)*Math.cos(laB)*Math.cos(dLongitude));
        double additionPart = part1SAB+part2SAB;

        double SAB = Math.acos(additionPart);
        System.out.println(SAB);



        // On multiplie par le rayon puis on divise par 1000 pour obtenir une distance en km.
        double result = (SAB * 6378137)/1000;
        System.out.println(result);

        // On retourne la valeur en km en format String avec deux chiffres décimaux.
        return String.format("%.2f", result);
    }
}

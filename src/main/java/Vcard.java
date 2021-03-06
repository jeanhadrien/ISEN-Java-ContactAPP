import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Vcard {

    /**
     * Export contact to .vcf format.
     * @param stg the stage from where the contact is exported.
     * @param selected the selected contact to export.
     * @throws IOException
     *
     * TODO : Addres related stuff.
     *        (currently saved as a single string in Contact so we can't easily get city, postal, etc.)
     */
    public static void exportContact(Stage stg, Contact selected) throws IOException {

        FileChooser fc = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Vcard (*.vcf)", "*.vcf");
        fc.getExtensionFilters().add(extFilter);
        final File file = fc.showSaveDialog(stg);
        if(file != null){
            FileOutputStream fop = new FileOutputStream(file);
            String str = "BEGIN:VCARD\n" +
                    "VERSION:4.0\n" +
                    "N:" + selected.getName().getLast() + ";" + selected.getName().getFirst() + ";;;\n" +
                    "FN:" + selected.getName().toString() + "\n" +
                    //"ADR;;LABEL='"+selected.getAddress().toString()+"':;;;;;;"+"\n"+
                    "BDAY:" + selected.getBirth().toString() + "\n" +
                    "TEL;TYPE=work,voice;VALUE=tel:" + selected.getPhone().toString() + "\n" +
                    "EMAIL:" + selected.getEmail().toString() + "\n" +
                    "END:VCARD";
            fop.write(str.getBytes());
            fop.flush();
            fop.close();
        }

    }

}

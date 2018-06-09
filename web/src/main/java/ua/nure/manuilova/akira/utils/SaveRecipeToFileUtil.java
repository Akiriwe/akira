package ua.nure.manuilova.akira.utils;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Variant;
import ua.nure.manuilova.models.RecipeDto;

import java.io.File;
import java.io.IOException;

public class SaveRecipeToFileUtil {
    public static void saveToFile(RecipeDto recipeDto) {
        String fileName = "E:\\temp\\" + recipeDto.get_id() + ".doc";
        createEvenIfExists(fileName);

        ActiveXComponent wordComponent = new ActiveXComponent("Word.Application");
        wordComponent.setProperty("Visible", true);
        ActiveXComponent oDocuments = wordComponent.getPropertyAsComponent("Documents");
        ActiveXComponent oDocument = oDocuments.invokeGetComponent("Open", new Variant(fileName));
        ActiveXComponent oSelection = wordComponent.getPropertyAsComponent("Selection");

        oSelection.setProperty("Text", recipeDto.toString());
        ActiveXComponent oFont = oSelection.getPropertyAsComponent("Font");
        oFont.setProperty("Bold", "1");
        oFont.setProperty("Italic", "1");
        oFont.setProperty("Underline", "0");



        ActiveXComponent oWordBasic = wordComponent.getPropertyAsComponent("WordBasic");
        oWordBasic.invoke("FileSaveAs", fileName);
        oDocument.invoke("Close", false);
        wordComponent.invoke("Quit", 0);
    }

    private static void createEvenIfExists(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
